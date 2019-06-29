package com.hotspr.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.hotspr.HttpConfig;
import com.hotspr.R;
import com.hotspr.business.presenter.LoginPresenter;
import com.hotspr.toolkit.CacheHandle;
import com.hotspr.toolkit.FileHandle;
import com.hotspr.toolkit.SharepreFHelp;
import com.hotspr.ui.adapter.RoomTypeAndCleanAdapter;
import com.hotspr.ui.bean.Round;
import com.hotspr.ui.bean.User;
import com.modulebase.log.LogF;
import com.modulebase.okhttp.JsonResponseHandler;
import com.modulebase.okhttp.MyOkHttp;
import com.modulebase.toolkit.DisplayUtil;
import com.modulebase.toolkit.sort.SortTools;
import com.modulebase.ui.activity.BaseActivity;
import com.modulebase.ui.dialog.LoadDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ArrangeCleaningActivity extends BaseActivity implements View.OnClickListener {

    private String TAG = "ArrangeCleaningActivity";
    public static String Room_Key = "Room_Key";
    public static String index_key = "index_key";

    private TextView roundNumberTv;
    private LinearLayout roomTypeLayout;
    private EditText roomTypeEt;
    private ImageView roomTypeIv;
    private LinearLayout cleanerLayout;
    private EditText cleanerEt;
    private ImageView cleanerIm;

    private TextView againCleanTv;
    private PopupWindow roomTypePopupWindow;
    private PopupWindow cleanPopupWindow;
    private RoomTypeAndCleanAdapter roomTypeAdapter;
    private RoomTypeAndCleanAdapter cleanAdapter;

    private String mRoomType;
    private String mCleaner;
    private String roomNumber;
    private int indeKey;
    private Round round;
    private Bundle mBundle;
    private User user;
    private LoadDialog mLoadDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrange_cleaning_layout);
        mBundle = getIntent().getExtras();
        if (mBundle != null) {
            round = mBundle.getParcelable(Room_Key);
            roomNumber = round.getROOM();
            indeKey = mBundle.getInt(index_key);
        }
        initView();
        initData();
        initDilaog();
        initRoomtypePopupWindow();
        initrCleanPopupWindow();
    }

    private void initView() {
        findViewById(R.id.back_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        roundNumberTv = findViewById(R.id.round_number_tv);
        roomTypeLayout = findViewById(R.id.room_type_layout);
        roomTypeEt = findViewById(R.id.room_type_et);
        roomTypeIv = findViewById(R.id.room_type_iv);
        cleanerLayout = findViewById(R.id.cleaner_layout);
        cleanerEt = findViewById(R.id.cleaner_et);
        cleanerIm = findViewById(R.id.cleaner_im);

        againCleanTv = findViewById(R.id.again_clean_tv);

        roomTypeIv.setOnClickListener(this);
        cleanerIm.setOnClickListener(this);

        againCleanTv.setOnClickListener(this);
        roundNumberTv.setText("房间号:" + roomNumber);
    }

    protected void initDilaog() {
        mLoadDialog = new LoadDialog(this);
        mLoadDialog.setCanceledOnTouchOutside(true);
        mLoadDialog.setCancelable(true);
    }

    private void initData() {
        user = FileHandle.getUser();
        if (user == null) {
            user = LoginPresenter.mUser;
        }
    }

    //房型
    private void initRoomtypePopupWindow() {
        View view = LayoutInflater.from(this).inflate(R.layout.popuwindow_roomtype_and_clean_layout, null);
        roomTypePopupWindow = new PopupWindow(view, DisplayUtil.dip2px(this, 110), ViewGroup.LayoutParams.WRAP_CONTENT);
        roomTypePopupWindow.setOutsideTouchable(true);

        ListView list = view.findViewById(R.id.listView);
        roomTypeAdapter = new RoomTypeAndCleanAdapter(this);
        roomTypeAdapter.setDatas(CacheHandle.roomTypeCach);
        list.setAdapter(roomTypeAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mRoomType = roomTypeAdapter.getContent(position);
                roomTypeEt.setText(mRoomType);
                roomTypePopupWindow.dismiss();
            }
        });
    }

    //房型
    private void initrCleanPopupWindow() {
        View view = LayoutInflater.from(this).inflate(R.layout.popuwindow_roomtype_and_clean_layout, null);
        cleanPopupWindow = new PopupWindow(view, DisplayUtil.dip2px(this, 110), ViewGroup.LayoutParams.WRAP_CONTENT);
        cleanPopupWindow.setOutsideTouchable(true);

        ListView list = view.findViewById(R.id.listView);
        cleanAdapter = new RoomTypeAndCleanAdapter(this);
        cleanAdapter.setDatas(CacheHandle.cleanerCach);
        list.setAdapter(cleanAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCleaner = cleanAdapter.getContent(position);
                cleanerEt.setText(mCleaner);
                cleanPopupWindow.dismiss();
            }
        });
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.room_type_iv) {
            cleanPopupWindow.dismiss();
            if (roomTypePopupWindow.isShowing()) {
                roomTypePopupWindow.dismiss();
            } else {
                roomTypePopupWindow.setClippingEnabled(false);
                roomTypePopupWindow.showAsDropDown(roomTypeLayout, 0, 2);
            }
        } else if (id == R.id.cleaner_im) {
            roomTypePopupWindow.dismiss();
            if (cleanPopupWindow.isShowing()) {
                cleanPopupWindow.dismiss();
            } else {
                cleanPopupWindow.setClippingEnabled(false);
                cleanPopupWindow.showAsDropDown(cleanerLayout, 0, 2);
            }
        } else if (id == R.id.again_clean_tv) {
            arrangeCleaning();
        }
    }

    /**
     * 安排清洁
     */
    private void arrangeCleaning() {
        if (TextUtils.isEmpty(mCleaner)) {
            Toast.makeText(this, "请选择清洁人员", Toast.LENGTH_SHORT).show();
            return;
        }
        mLoadDialog.show();
        String url = HttpConfig.HOST_NAME + HttpConfig.INTERFACE_RoomClAdd;
        String userid = SharepreFHelp.getInstance(this).getUserID();
        String userkey = SharepreFHelp.getInstance(this).getUserKey();

        Map<String, String> paer = new HashMap<>();
        paer.put(HttpConfig.Field.mid, userid);
        paer.put(HttpConfig.Field.key, userkey);
        paer.put(HttpConfig.Field.room, roomNumber);
        paer.put(HttpConfig.Field.onduty1n, user.getU_NAME());
        paer.put(HttpConfig.Field.onduty3n, mCleaner);
        if (!TextUtils.isEmpty(mRoomType)) {
            paer.put(HttpConfig.Field.class_new, mRoomType);
        }
        paer.put(HttpConfig.Field.timestamp, String.valueOf(System.currentTimeMillis() / 1000));
        Set<String> keySet = paer.keySet();  //获取set集合
        List<String> sortKey = SortTools.listSort(keySet);
        TreeMap<String, String> parameter = SortTools.getSortMap(sortKey, paer);

        MyOkHttp.get().get(this, url, parameter, new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogF.i(TAG, "success statusCode = " + statusCode + " response = " + response);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        mLoadDialog.dismiss();
                    }
                });
                try {
                    JSONObject resObj = new JSONObject(response);
                    if (resObj.getString("errCode").equals("200")) {
                        JSONObject res = resObj.getJSONObject("Data");
                        Round round = new Round();
                        round.setCLASS(res.getString("CLASS")); //房型
                        round.setRoom_id(res.getString("room_id"));
                        round.setSTATE2(res.getString("STATE2"));//T：停用    D：脏房  L：锁房  R：净房  M：维修S：清扫
                        round.Setroom_wh_id(res.getString("room_wh_id"));
                        round.setROOM(res.getString("ROOM")); //房间号
                        round.Setcl_onduty1n(res.getString("cl_onduty1n")); //安排人
                        round.Setcl_onduty2n(res.getString("cl_onduty2n")); //服务员
                        round.Setcl_date1(res.getString("cl_date1")); //安排日期
                        round.Setcl_time1(res.getString("cl_time1")); //安排时间
                        round.Setcl_onduty3n(res.getString("cl_onduty3n")); //清洁员
                        round.setCl_state(res.getString("cl_state")); //状态0未完成  1已完成 2已检查
                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putParcelable(Room_Key, round);
                        bundle.putInt(index_key, indeKey);
                        intent.putExtras(bundle);
                        setResult(Activity.RESULT_OK, intent);
                        ArrangeCleaningActivity.this.finish();
                    } else {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(ArrangeCleaningActivity.this, "上传失败，请重试", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(ArrangeCleaningActivity.this, "上传失败，请重试", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                LogF.i(TAG, "onFailure statusCode = " + statusCode + " error_msg = " + error_msg);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ArrangeCleaningActivity.this, "上传失败，请重试", Toast.LENGTH_SHORT).show();
                        mLoadDialog.dismiss();
                    }
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (mLoadDialog != null && mLoadDialog.isShowing()) {
            mLoadDialog.dismiss();
        }
        super.onDestroy();
    }
}
