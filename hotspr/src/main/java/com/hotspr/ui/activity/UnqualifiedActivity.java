package com.hotspr.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hotspr.HttpConfig;
import com.hotspr.R;
import com.hotspr.business.presenter.LoginPresenter;
import com.hotspr.toolkit.FileHandle;
import com.hotspr.toolkit.SharepreFHelp;
import com.hotspr.ui.bean.Round;
import com.hotspr.ui.bean.User;
import com.modulebase.log.Log;
import com.modulebase.log.LogF;
import com.modulebase.okhttp.JsonResponseHandler;
import com.modulebase.okhttp.MyOkHttp;
import com.modulebase.toolkit.Compress;
import com.modulebase.toolkit.FileUtils;
import com.modulebase.toolkit.sort.SortTools;
import com.modulebase.ui.activity.BaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class UnqualifiedActivity extends BaseActivity implements View.OnClickListener{

    public static final String resrt_round_key = "Round";
    public static final String resrt_index_key = "resrt_index_key";
    private String TAG = "UnqualifiedActivity";

    private static int PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    public static int REQUEST_CAMERA = 7;
    public static String round_key = "round";
    public static String code_key = "code";
    public static String index_key = "index";
    private ImageView photoImg;
    private TextView uploadTv;
    private EditText remarksEt;
    private TextView confirmTv;
    private TextView confirmTv_no;
    private File mFile;
    private Bitmap bitmap;
    private Round mRound ;
    private User mUser ;
    private String mUrl ;
    private int requestCode ;
    private int index ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_unqualified_round_layout);
        mRound = getIntent().getExtras().getParcelable(round_key);
        requestCode = getIntent().getExtras().getInt(code_key);
        index = getIntent().getExtras().getInt(index_key);
        mUser = FileHandle.getUser();
        if (mUser == null) {
            mUser = LoginPresenter.mUser;
        }
        // LogF.i("xiahong111",mRound.getCl_state().toString());
        findViewById();
        // chekPermission();

    }

    private void findViewById() {
        findViewById(R.id.back_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        photoImg = findViewById(R.id.photo_img);
        uploadTv = findViewById(R.id.uploadphoto_tv);
        remarksEt = findViewById(R.id.remarks_et);
        confirmTv = findViewById(R.id.confirm_tv);
        confirmTv_no = findViewById(R.id.confirm_tv_no);
        uploadTv.setOnClickListener(this);
        confirmTv.setOnClickListener(this);
        confirmTv_no.setOnClickListener(this);



        if (mRound != null ) {
            Log.i("xiahong11",mRound.getCl_state().toString());
            if(mRound.getSTATE5()!=null){
                mRound.setCl_state(mRound.getSTATE5().toString());
            }
            if (mRound.getCl_state().equals("2")||mRound.getCl_state().equals("0")) {
              if(mRound.getCl_state().equals("0"))  {
                  confirmTv.setText("清洁中");
              }else{
                  confirmTv.setText("已清洁");
              }

                confirmTv.setClickable(false);
                uploadTv.setVisibility(View.GONE);
                confirmTv_no.setVisibility(View.GONE);
                if (TextUtils.isEmpty(mRound.getcl_memo1())) {
                    remarksEt.setHint("");
                } else {
                    remarksEt.setText(mRound.getcl_memo1());
                }
                remarksEt.setFocusable(false);
                remarksEt.setClickable(false);
                if (!TextUtils.isEmpty(mRound.getCl_picture_path())) {
                    getPhoto(mRound.getCl_picture_path());
                }
            }

        }
    }

    private void getPhoto(final String photoUrl){
        new Thread() {
            @Override
            public void run() {
                InputStream inputStream = null ;
                try {
                    //把传过来的路径转成URL
                    URL url = new URL(HttpConfig.HOST_NAME + photoUrl);
                    //获取连接
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    //使用GET方法访问网络
                    connection.setRequestMethod("GET");
                    //超时时间为10秒
                    connection.setConnectTimeout(10000);
                    //获取返回码
                    int code = connection.getResponseCode();
                    if (code == 200) {
                        inputStream = connection.getInputStream();
//                        //使用工厂把网络的输入流生产Bitmap
                        final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        if(bitmap!=null){
                            UnqualifiedActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    photoImg.setImageBitmap(bitmap);
                                }
                            });
                        }
                        inputStream.close();
                    }else {

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if(inputStream!=null){
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();
    }



    /**
     * 检查权限
     */
    private void chekPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        if (requestCode == PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE) {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) { // 相机
//                PermissionPromptDialog mPromptDialog = new PermissionPromptDialog(this);
//                mPromptDialog.setContentTvString("此应用需要使用相机，否则无法正常使用。请移步到设置-应用-权限中打开");
//                mPromptDialog.setCancelable(false);
//                mPromptDialog.setCanceledOnTouchOutside(false);
//                mPromptDialog.getIKnoeTv().setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        startActivity(new Intent(Settings.ACTION_SETTINGS)); //直接进入手机中设置界面
//                        finish();
//                    }
//                });
//                mPromptDialog.show();
//            } else if (grantResults[1] != PackageManager.PERMISSION_GRANTED) {
//
//            }
//        }
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        if (id == R.id.uploadphoto_tv) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "请开启相机权限", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                mFile = new File(FileUtils.PHOTODIR + System.currentTimeMillis() + ".jpg");
                if (!mFile.exists()) {
                    mFile.getParentFile().mkdirs();
                }
                //判断版本
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {   //如果在Android7.0以上,使用FileProvider获取Uri
                    Uri contentUri = FileProvider.getUriForFile(this, "com.hotspr.fileprovider", mFile);
                    intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
                } else {    //否则使用Uri.fromFile(file)方法获取Uri
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mFile));
                }
                startActivityForResult(intent, REQUEST_CAMERA);
            }
        } else if(id == R.id.confirm_tv){
            checkOut(mRound.getRoom_wh_id() ,"2", remarksEt.getText().toString());
        }
        else if(id == R.id.confirm_tv_no){
            checkOut(mRound.getRoom_wh_id() ,"0", remarksEt.getText().toString());
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK) {//
            //先压缩图片，自动判断是否要压缩
            Compress.compress(mFile.getAbsolutePath(), 500, 0, mFile.getAbsolutePath());
            // 显示上传加载框
            //mPressenter.uploadPhoto(mLookID , FileUtils.imgToBase64(mFile.getAbsolutePath()));
            bitmap = getLoacalBitmap(mFile.getAbsolutePath());//显示本地照片
            if (bitmap != null) {
                photoImg.setImageBitmap(bitmap);
                uploadPhoto(mRound.getRoom_wh_id() ,  FileUtils.imgToBase64(mFile.getAbsolutePath())) ; // 上传图片
            }
        }
    }

    /**
     * 加载本地图片
     *
     * @param url
     * @return
     */
    public static Bitmap getLoacalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);  ///把流转化为Bitmap图片
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 上传照片
     */
    public void uploadPhoto( String room_wh_id , String imgBase64) {
        String url = HttpConfig.HOST_NAME + HttpConfig.INTERFACE_CL_uploadPhoto;
        String userid = SharepreFHelp.getInstance(this).getUserID();
        String userkey = SharepreFHelp.getInstance(this).getUserKey();
        Map<String, String> paer = new HashMap<>();
        paer.put(HttpConfig.Field.mid, userid);
        paer.put(HttpConfig.Field.key, userkey);
        paer.put("room_wh_id", room_wh_id);
        paer.put(HttpConfig.Field.img, imgBase64);
        paer.put(HttpConfig.Field.timestamp, String.valueOf(System.currentTimeMillis() / 1000));


        Set<String> keySet = paer.keySet();  //获取set集合
        List<String> sortKey = SortTools.listSort(keySet);
        TreeMap<String, String> parameter = SortTools.getSortMap(sortKey, paer);
        MyOkHttp.get().postImage(this, url, parameter, new JsonResponseHandler() {
            @Override
            public void onFailure(int statusCode, String error_msg) {
                LogF.i(TAG, "onFailure statusCode = " + statusCode + " error_msg = " + error_msg);

            }

            @Override
            public void onSuccess(int statusCode, String response) {
                LogF.i(TAG, "onSuccess statusCode = " + statusCode + " response = " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject != null && "200".equals(jsonObject.getString("errCode"))) {
                        mUrl = jsonObject.getString("url");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    /**
     * 不合格完成
     * @param room_wh_id 房间id
     * @param memo 备注
     */
    public void checkOut(String room_wh_id,String StateStr ,String memo) {

        String url = HttpConfig.HOST_NAME + HttpConfig.INTERFACE_roomClUp;
        String userid = SharepreFHelp.getInstance(this).getUserID();
        String userkey = SharepreFHelp.getInstance(this).getUserKey();
        String server_memo = "";
        Map<String, String> paer = new HashMap<>();
        if (memo != null && memo != "" && !memo.isEmpty()) {
            paer.put("memo1", memo);
        }
        paer.put(HttpConfig.Field.mid, userid);
        paer.put(HttpConfig.Field.key, userkey);
        paer.put("room_wh_id", room_wh_id);
        paer.put("check_er", mUser.getU_NAME());
        paer.put("check_time", "Y");
        paer.put("state",  StateStr );
        paer.put(HttpConfig.Field.timestamp, String.valueOf(System.currentTimeMillis() / 1000));
        Set<String> keySet = paer.keySet();  //获取set集合
        List<String> sortKey = SortTools.listSort(keySet);
        TreeMap<String, String> parameter = SortTools.getSortMap(sortKey, paer);
        MyOkHttp.get().get(this, url, parameter, new JsonResponseHandler() {
            @Override
            public void onFailure(int statusCode, String error_msg) {
                LogF.i(TAG, "onFailure statusCode = " + statusCode + " error_msg = " + error_msg);
                Toast.makeText(UnqualifiedActivity.this, "上传失败", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onSuccess(int statusCode, String response) {
                LogF.i(TAG, "onSuccess statusCode = " + statusCode + " response = " + response);
                if (statusCode == 200) {
                    Toast.makeText(UnqualifiedActivity.this, "检查完成", Toast.LENGTH_SHORT).show();
                    try {
                        JSONObject res = new JSONObject(response);
                        if (res.getString("errCode").equals("200")) {
                            Round round = null;
                            JSONObject object = res.getJSONObject("look");
                            if (object != null) {
                                round = new Round();
                                round.setCLASS(object.getString("CLASS")); //房型
                                round.setRoom_id(object.getString("room_id"));
                                round.setSTATE2(object.getString("STATE2"));//T：停用    D：脏房  L：锁房  R：净房  M：维修S：清扫
                                round.setRoom_wh_id(object.getString("room_wh_id"));
                                round.setROOM(object.getString("ROOM")); //房间号
                                round.setCl_onduty1n(object.getString("cl_onduty1n")); //安排人
                                round.setCl_onduty2n(object.getString("cl_onduty2n")); //服务员
                                round.setCl_date1(object.getString("cl_date1")); //安排日期
                                round.setCl_time1(object.getString("cl_time1")); //安排时间
                                round.setCl_onduty3n(object.getString("cl_onduty3n")); //清洁员
                                round.setCl_state(object.getString("cl_state")); //状态0未完成  1已完成 2已检查
                                round.setCl_class_new(object.getString("cl_class_new")); //状态0未完成  1已完成 2已检查
                                round.setCl_time3(object.getString("cl_time3")); //安排时间
                                round.setCl_check_er(object.getString("cl_check_er")); //安排时间
                                round.setLook_picture_path(object.getString("look_picture_path"));//图片





                            }
                            if (round != null) {
                                if (!TextUtils.isEmpty(mUrl)) {
                                    round.setLook_picture_path(mUrl);
                                }
                                Intent intent = new Intent();
                                Bundle bundle = new Bundle();
                                bundle.putParcelable(resrt_round_key, round);
                                bundle.putInt(resrt_index_key, index);
                                intent.putExtras(bundle);
                                setResult(Activity.RESULT_OK, intent);
                            }
                            UnqualifiedActivity.this.finish();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        UnqualifiedActivity.this.finish();
                    }
                } else {
                    Toast.makeText(UnqualifiedActivity.this, "查房失败，请重试", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


}
