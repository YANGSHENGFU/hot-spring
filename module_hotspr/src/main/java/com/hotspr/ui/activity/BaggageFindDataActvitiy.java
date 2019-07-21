package com.hotspr.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hotspr.R;
import com.hotspr.business.presenter.LoginPresenter;
import com.hotspr.toolkit.FileHandle;
import com.hotspr.ui.bean.Round;
import com.hotspr.ui.bean.User;
import com.hotspr.ui.bean.Xl;
import com.modulebase.HttpConfig;
import com.modulebase.log.LogF;
import com.modulebase.okhttp.JsonResponseHandler;
import com.modulebase.okhttp.MyOkHttp;
import com.modulebase.toolkit.Compress;
import com.modulebase.toolkit.FileUtils;
import com.modulebase.toolkit.SharepreFHelp;
import com.modulebase.toolkit.sort.SortTools;
import com.modulebase.ui.activity.BaseActivity;
import com.modulebase.ui.dialog.LoadDialog;
import com.sunmi.utils.AidlUtil;

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

/**
 * 登记上传数据
 */
public class BaggageFindDataActvitiy extends BaseActivity implements View.OnClickListener{

    private String TAG = "BaggageFindDataActvitiy";
    public static String DATA_KEY = "DATA_KEY";

    private ImageView photoImg ;
    private TextView reserva_order_no_et ;
    private TextView reserva_name_et ;
    private TextView reserva_tel_et ;
    private TextView round_room_et ;
    private TextView mem1_et ;
    private TextView baggage_tv ;
    private TextView baggage_print_tv ;


    private static int PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    public static int REQUEST_CAMERA = 9;
    private File mFile;
    private Bitmap bitmap;
    private LoadDialog mDialog ;
    private User mUser ;
    private String tag ;
    private String xlID ;
    private Xl mDeposit ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitiy_baggage_find_data_layout);
        findViewByID() ;
        intiDialog() ;
       intiData() ;
//        chekPermission() ;
    }

    private void findViewByID() {
        findViewById(R.id.back_tv).setOnClickListener(this);
        photoImg = findViewById(R.id.photo_img);
        reserva_order_no_et = findViewById(R.id.reserva_order_no_et);
        reserva_name_et = findViewById(R.id.reserva_name_et);
        reserva_tel_et = findViewById(R.id.reserva_tel_et);
        round_room_et = findViewById(R.id.round_room_et);
        mem1_et = findViewById(R.id.mem1_et);
        baggage_tv = findViewById(R.id.baggage_tv);
        baggage_print_tv = findViewById(R.id.baggage_print_tv);
        baggage_tv.setOnClickListener(this);
        baggage_print_tv.setOnClickListener(this);
    }

    /**
     * 检查权限
     */
    private void chekPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
        }
    }

    /**
     * 加载框
     */
    private void intiDialog(){
        mDialog = new LoadDialog(this);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setCancelable(false);
    }

    private void intiData(){
        mUser = FileHandle.getUser() ;
        if(mUser == null){
            mUser = LoginPresenter.mUser ;
        }
        AidlUtil.getInstance().initPrinter();
        mDeposit = getIntent().getExtras().getParcelable(DATA_KEY);
        if(mDeposit!=null){

            reserva_order_no_et.setText(mDeposit.getOrder_NO());
            reserva_name_et.setText(mDeposit.getName());
            reserva_tel_et.setText(mDeposit.getTel());
            round_room_et.setText(mDeposit.getRoom());
            mem1_et.setText(mDeposit.getMemo1());
            if (!TextUtils.isEmpty(mDeposit.getPicture_path())) {
                getPhoto(mDeposit.getPicture_path());
            }
            tag=mDeposit.getTag();
            xlID=mDeposit.getXl_id();
            if(tag.equals("0")){
                baggage_tv.setVisibility(View.INVISIBLE);

            }
            if(tag.equals("1")){
                baggage_tv.setText("取   件");
            }
//            reservaNumberEt.setText(mDeposit.getGROUPNO());
//            reservaNameEt.setText(mDeposit.getNAME());
//            reservaPhoneEt.setText(mDeposit.getTELE());
//            roundNumberEt.setText(mDeposit.getROOM());
//            reservaNumberEt.clearFocus();
//            reservaNameEt.clearFocus();
//            reservaPhoneEt.clearFocus();
        }
    }


    @Override
    public void onClick(View v) {
        int id = v.getId() ;
        if(id == R.id.baggage_tv){
       baggage() ; // 登记

        }
        if(id == R.id.baggage_print_tv){
            printing() ; // 登记

        }
        if(id== R.id.back_tv){
            finish();
        }
    }

    /**
     * 拍照
     */
    private void goPhotograph(){
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
                            BaggageFindDataActvitiy.this.runOnUiThread(new Runnable() {
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
     * 登记
     */
    private void baggage(){

        String url = HttpConfig.HOST_NAME + "Luggage/DataUp?";
        String userid = SharepreFHelp.getInstance(this).getUserID();
        String userkey = SharepreFHelp.getInstance(this).getUserKey();

        Map<String, String> paer = new HashMap<>();

        paer.put(HttpConfig.Field.mid, userid);
        paer.put(HttpConfig.Field.key, userkey);
        paer.put("xl_id", mDeposit.getXl_id());
        if(tag.equals("2")){
            paer.put("onduty2n", mUser.getU_NAME());
        }
        if(tag.equals("1")){
            paer.put("onduty3n", mUser.getU_NAME());
        }


        paer.put(HttpConfig.Field.timestamp, String.valueOf(System.currentTimeMillis() / 1000));
        Set<String> keySet = paer.keySet();  //获取set集合
        List<String> sortKey = SortTools.listSort(keySet);
        TreeMap<String, String> parameter = SortTools.getSortMap(sortKey, paer);

        mDialog.show(); // 加载框
        MyOkHttp.get().get( this , url , parameter , new JsonResponseHandler() {
            @Override
            public void onFailure(int statusCode, String error_msg) {
                LogF.i(TAG , "onFailure statusCode = "+ statusCode + " error_msg = "+ error_msg) ;
                baggageFild();
            }
            @Override
            public void onSuccess(int statusCode, String response) {
                LogF.i(TAG , "onSuccess statusCode = "+ statusCode + " response = "+ response) ;
                if(statusCode == 200){
                    try {
                        JSONObject res = new JSONObject(response);
                        if(res.getString("errCode").equals("200")){
                            Round round = null ;
                            JSONObject object = res.getJSONObject("Data");
                            if(object!=null){
                          //不要上传了
                                baggageOk();
                            } else {
                                baggageFild();
                            }
                        } else {
                            baggageFild();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        baggageFild();
                    }
                }else{
                    baggageFild();
                }
            }
        });
    }

    /**
     * 上传照片
     */
    public void uploadPhoto( String xl_id ,  String imgBase64){
        String url = HttpConfig.HOST_NAME + HttpConfig.INTERFACE_BAGGAGE_UPLOADPHOTO;
        String userid = SharepreFHelp.getInstance(this).getUserID();
        String userkey = SharepreFHelp.getInstance(this).getUserKey();
        Map<String, String> paer = new HashMap<>();
        paer.put(HttpConfig.Field.mid, userid);
        paer.put(HttpConfig.Field.key, userkey);
        paer.put(HttpConfig.Field.xl_id, xl_id);
        paer.put(HttpConfig.Field.img, imgBase64);
        paer.put(HttpConfig.Field.timestamp, String.valueOf(System.currentTimeMillis() / 1000));
        Set<String> keySet = paer.keySet();  //获取set集合
        List<String> sortKey = SortTools.listSort(keySet);
        TreeMap<String, String> parameter = SortTools.getSortMap(sortKey, paer);
        MyOkHttp.get().postImage( this , url , parameter , new JsonResponseHandler() {
            @Override
            public void onFailure(int statusCode, String error_msg) {
                LogF.i(TAG , "onFailure statusCode = "+ statusCode + " error_msg = "+ error_msg) ;
                baggageFild();
            }
            @Override
            public void onSuccess(int statusCode, String response) {
                LogF.i(TAG , "onSuccess statusCode = "+ statusCode + " response = "+ response) ;
                if(statusCode == 200){
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        if(jsonObject!=null && "200".equals(jsonObject.getString("errCode"))){
                            baggageOk();
                        } else {
                            baggageFild();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        baggageFild();
                    }
                } else {
                    baggageFild();
                }
            }
        });
    }

    /**
     * 登记失败
     */
    private void baggageFild(){
        mDialog.dismiss();
        Toast.makeText(BaggageFindDataActvitiy.this , "登记失败，请重试" , Toast.LENGTH_SHORT).show();
    }

    /**
     * 登记成功
     */
    private void baggageOk(){
        mDialog.dismiss();
        finish();
    }

    /**
     * 打印
     */
    private void printing(){
        AidlUtil.getInstance().printQr(xlID , 8 ,3); // 打印二维码
        AidlUtil.getInstance().printText("预约号："+reserva_order_no_et.getText().toString() , 30 , false , false );
        AidlUtil.getInstance().printText("寄存人："+reserva_name_et.getText().toString(), 30 , false , false );
        AidlUtil.getInstance().printText("手机号："+reserva_tel_et.getText().toString() , 30 , false , false );
        AidlUtil.getInstance().printText("寄存日期："+mDeposit.getDate2() , 30 , false , false );
        AidlUtil.getInstance().printText("寄存时间："+mDeposit.getTime2() , 30 , false , false );
        AidlUtil.getInstance().printText("房间号："+round_room_et.getText().toString() , 30 , false , false );
        AidlUtil.getInstance().printText("备  注："+mem1_et.getText().toString() , 30 , false , false );

        //AidlUtil.getInstance().print2Line();

        AidlUtil.getInstance().printText("登记人："+mDeposit.getOnduty1n() , 30 , false , false );
        AidlUtil.getInstance().printText("登记日期："+mDeposit.getDate1() , 30 , false , false );
        AidlUtil.getInstance().printText("登记时间："+mDeposit.getTime1() , 30 , false , false );

        //AidlUtil.getInstance().print2Line();

        AidlUtil.getInstance().printText("领取人："+mDeposit.getOnduty3n() , 30 , false , false );
        AidlUtil.getInstance().printText("领取日期："+mDeposit.getDate3() , 30 , false , false );
        AidlUtil.getInstance().printText("领取时间："+mDeposit.getTime3() , 30 , false , false );

        if (mDeposit.getTag().equals("2") ){
            AidlUtil.getInstance().printText("状态：登记" , 30 , false , false );
        } else if (mDeposit.getTag().equals("1") ){
            AidlUtil.getInstance().printText("状态：寄存" , 30 , false , false );
        } else if (mDeposit.getTag().equals("0") ){
            AidlUtil.getInstance().printText("状态：领取" , 30 , false , false );
        }

        AidlUtil.getInstance().print5Line();
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
               //uploadPhoto(mRound.getLook_id() ,  FileUtils.imgToBase64(mFile.getAbsolutePath())) ; // 上传图片
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


}
