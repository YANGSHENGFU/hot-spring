package com.hotspr.business.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;
import com.hotspr.HttpConfig;
import com.hotspr.business.api.WardRoundPressenterAPI;
import com.hotspr.toolkit.SharepreFHelp;
import com.hotspr.ui.bean.Round;
import com.modulebase.log.LogF;
import com.modulebase.okhttp.JsonResponseHandler;
import com.modulebase.okhttp.MyOkHttp;
import com.modulebase.toolkit.sort.SortTools;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


public class ClearRoundPressenter implements WardRoundPressenterAPI.Pressente {

    private String TAG = "ClearRoundPressenter";

    private Context mContext;
    private WardRoundPressenterAPI.View mView;
    private PressenterHandler handler;

    public String rows;
    public boolean isRsh;
    public boolean isLodMore;
    private static int STOP_LOAD = 70001;

    public ClearRoundPressenter(Context context, WardRoundPressenterAPI.View view) {
        mContext = context;
        mView = view;
        rows = String.valueOf(15);
        handler = new PressenterHandler(this);
    }

    @Override
    public void loadData(final int lodelModel , int page , Map<String ,String> paramse) {
        String url = HttpConfig.HOST_NAME + HttpConfig.INTERFACE;
        //handler.sendEmptyMessageDelayed(STOP_LOAD, 15 * 1000); // 每次请求数据最多给15s的时间，15s过当作放弃
        String userid = SharepreFHelp.getInstance(mContext).getUserID();
        String userkey = SharepreFHelp.getInstance(mContext).getUserKey();

        Map<String, String> paer = new HashMap<>();
        paer.put(HttpConfig.Field.mid, userid);
        paer.put(HttpConfig.Field.key, userkey);
        paer.put(HttpConfig.Field.page, String.valueOf(page));
        paer.put(HttpConfig.Field.rows, rows);
        paer.put(HttpConfig.Field.tage,"Y");
        paer.put(HttpConfig.Field.timestamp, String.valueOf(System.currentTimeMillis() / 1000));

        Set<String> keySet = paer.keySet();  //获取set集合
        List<String> sortKey = SortTools.listSort(keySet);
        TreeMap<String, String> parameter = SortTools.getSortMap(sortKey, paer);

        MyOkHttp.get().get(mContext, url, parameter, new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                Log.i("success", response);
                ArrayList<Round> datas = new ArrayList<>();
                String page = "0";
                try {
                    JSONObject resObj = new JSONObject(response);
                    if (resObj.getString("errCode").equals("200")) {
                        Log.i("DataList", resObj.getString("DataList"));
                        JSONArray resDataList = resObj.getJSONArray("DataList");
                        page = resObj.getString("total");
                        for (int i = 0; i < resDataList.length(); i++) {
                            JSONObject res = (JSONObject) resDataList.get(i);
                            Round round = new Round();
                            round.setCLASS(res.getString("CLASS"));
                            round.setRoom_id(res.getString("room_id"));
                            round.setROOM(res.getString("ROOM"));
                            round.setLook_id(res.getString("look_id"));
                            round.setLook_tage(res.getString("look_tage"));
                            round.setLook_picture_path(res.getString("look_picture_path"));
                            round.setLook_server_memo(res.getString("look_server_memo"));
                            round.setLook_server_name(res.getString("look_server_name"));
                            round.setLook_time_out(res.getString("look_time_out"));
                            datas.add(round);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    page = "0";
                } finally {
                    mView.upDatd(lodelModel, datas , Integer.valueOf(page));
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                LogF.i(TAG, "onFailure statusCode = " + statusCode + " error_msg = " + error_msg);
                mView.upDatd(lodelModel, new ArrayList<Round>(), -1);
            }
        });
    }

    /**
     * 上传照片
     */
    public void uploadPhoto( String look_id ,  String imgBase64){
        String url = HttpConfig.HOST_NAME + HttpConfig.INTERFACE_uploadPhoto;
        String userid = SharepreFHelp.getInstance(mContext).getUserID();
        String userkey = SharepreFHelp.getInstance(mContext).getUserKey();
        Map<String, String> paer = new HashMap<>();
        paer.put(HttpConfig.Field.mid, userid);
        paer.put(HttpConfig.Field.key, userkey);
        paer.put(HttpConfig.Field.look_id, look_id);
        paer.put(HttpConfig.Field.img, imgBase64);
        paer.put(HttpConfig.Field.timestamp, String.valueOf(System.currentTimeMillis() / 1000));
        Set<String> keySet = paer.keySet();  //获取set集合
        List<String> sortKey = SortTools.listSort(keySet);
        TreeMap<String, String> parameter = SortTools.getSortMap(sortKey, paer);
        MyOkHttp.get().postImage( mContext , url , parameter , new JsonResponseHandler() {
            @Override
            public void onFailure(int statusCode, String error_msg) {
                Log.d(TAG , "uploadPhoto onFailure ") ;
                Toast.makeText(mContext,error_msg,Toast.LENGTH_SHORT).show();
                mView.uploadPhoto(false);
            }

            @Override
            public void onSuccess(int statusCode, String response) {
                Log.d(TAG , "uploadPhoto onSuccess ") ;
                mView.uploadPhoto(true);
            }
        });
    }

    /**
     * 退房完成
     * @param look_id 房间id
     * @param memo 备注
     */
    public void check_out(String look_id,String memo){
        String url = HttpConfig.HOST_NAME + HttpConfig.INTERFACE_checkOut;
        String userid = SharepreFHelp.getInstance(mContext).getUserID();
        String userkey = SharepreFHelp.getInstance(mContext).getUserKey();
        String server_memo = "";
        Map<String, String> paer = new HashMap<>();
        if (memo != null && memo != "" && !memo.isEmpty()) {
            server_memo = memo;
            paer.put(HttpConfig.Field.server_memo, server_memo);
        }
        paer.put(HttpConfig.Field.mid, userid);
        paer.put(HttpConfig.Field.key, userkey);
        paer.put(HttpConfig.Field.look_id, look_id);
        paer.put(HttpConfig.Field.hserver_name, userid);
        paer.put(HttpConfig.Field.tage, "Y");
        paer.put(HttpConfig.Field.timestamp, String.valueOf(System.currentTimeMillis() / 1000));
        Set<String> keySet = paer.keySet();  //获取set集合
        List<String> sortKey = SortTools.listSort(keySet);
        TreeMap<String, String> parameter = SortTools.getSortMap(sortKey, paer);
        MyOkHttp.get().get( mContext , url , parameter , new JsonResponseHandler() {
            @Override
            public void onFailure(int statusCode , String error_msg) {
                Log.d(TAG , "checkOut onFailure ") ;
                Toast.makeText(mContext,error_msg,Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onSuccess(int statusCode, String response) {
                Log.d(TAG , "checkOut onSuccess ") ;
                try {
                    JSONObject res = new JSONObject(response);
                    Toast.makeText(mContext,res.getString("errTxt"),Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    static class PressenterHandler extends Handler {
        private WeakReference<ClearRoundPressenter> activityWeakReference;

        public PressenterHandler(ClearRoundPressenter activity) {
            activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ClearRoundPressenter pressenter = activityWeakReference.get();
            if (pressenter != null) {
                if (msg.what == STOP_LOAD) {
                    pressenter.isRsh = false;
                    pressenter.isLodMore = false;
                    pressenter.mView.upDatd(1, new ArrayList<Round>(), -1);
                    removeMessages(STOP_LOAD); // 移除计时
                }
            }
        }
    }
}
