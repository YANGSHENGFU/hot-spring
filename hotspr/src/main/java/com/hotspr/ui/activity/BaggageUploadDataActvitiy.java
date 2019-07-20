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
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.modulebase.HttpConfig;
import com.hotspr.R;
import com.hotspr.business.presenter.LoginPresenter;
import com.hotspr.toolkit.Base64Code;
import com.hotspr.toolkit.FileHandle;
import com.modulebase.toolkit.SharepreFHelp;
import com.hotspr.ui.bean.Deposit;
import com.hotspr.ui.bean.Round;
import com.hotspr.ui.bean.User;
import com.modulebase.log.LogF;
import com.modulebase.okhttp.JsonResponseHandler;
import com.modulebase.okhttp.MyOkHttp;
import com.modulebase.toolkit.Compress;
import com.modulebase.toolkit.FileUtils;
import com.modulebase.toolkit.sort.SortTools;
import com.modulebase.ui.activity.BaseActivity;
import com.modulebase.ui.dialog.LoadDialog;
import com.sunmi.utils.AidlUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * 登记上传数据
 */
public class BaggageUploadDataActvitiy extends BaseActivity implements View.OnClickListener{

    private String TAG = "BaggageUploadDataActvitiy";
    public static String DATA_KEY = "DATA_KEY";

    private ImageView photoImg ;
    private TextView uploadphotoTv ;
    private EditText reservaNumberEt  ;
    private EditText reservaNameEt  ;
    private EditText reservaPhoneEt  ;
    private EditText roundNumberEt  ;
    private EditText remarksEt  ;
    private TextView baggageTv ;

    private static int PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    public static int REQUEST_CAMERA = 9;
    private File mFile;
    private Bitmap bitmap;
    private LoadDialog mDialog ;
    private User mUser ;
    private int state ;
    private String xlID ;
    private Deposit mDeposit ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvitiy_baggage_upload_data_layout);
        findViewByID() ;
        intiDialog() ;
        intiData() ;
        chekPermission() ;
    }

    private void findViewByID() {
        findViewById(R.id.back_tv).setOnClickListener(this);
        photoImg = findViewById(R.id.photo_img);
        uploadphotoTv = findViewById(R.id.uploadphoto_tv);
        reservaNumberEt = findViewById(R.id.reserva_number_et);
        reservaNameEt = findViewById(R.id.reserva_name_et);
        reservaPhoneEt = findViewById(R.id.reserva_phone_et);
        roundNumberEt = findViewById(R.id.round_number_et);
        remarksEt = findViewById(R.id.remarks_et);
        baggageTv = findViewById(R.id.baggage_tv);
        uploadphotoTv.setOnClickListener(this);
        baggageTv.setOnClickListener(this);
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
            reservaNumberEt.setText(mDeposit.getGROUPNO());
            reservaNameEt.setText(mDeposit.getNAME());
            reservaPhoneEt.setText(mDeposit.getTELE());
            roundNumberEt.setText(mDeposit.getROOM());
            reservaNumberEt.clearFocus();
            reservaNameEt.clearFocus();
            reservaPhoneEt.clearFocus();
        }
    }


    @Override
    public void onClick(View v) {
        int id = v.getId() ;
        if(id == R.id.baggage_tv){
            if(state == 1){
                printing(); // 打印
            } else {
                baggage() ; // 登记
            }
        } else if(id == R.id.uploadphoto_tv){
            goPhotograph() ;
        } else if(id==R.id.back_tv){
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

    /**
     * 登记
     */
    private void baggage(){
        if(TextUtils.isEmpty(reservaNameEt.getText().toString())){
            Toast.makeText(this , "请输入名字" , Toast.LENGTH_SHORT).show();
            return;
        } else if(TextUtils.isEmpty(reservaPhoneEt.getText().toString())){
            Toast.makeText(this , "请输入手机号码" , Toast.LENGTH_SHORT).show();
            return;
        }
        String url = HttpConfig.HOST_NAME + HttpConfig.INTERFACE_BAGGAGE;
        String userid = SharepreFHelp.getInstance(this).getUserID();
        String userkey = SharepreFHelp.getInstance(this).getUserKey();

        Map<String, String> paer = new HashMap<>();

        paer.put(HttpConfig.Field.mid, userid);
        paer.put(HttpConfig.Field.key, userkey);
        // 预定号
        if(!TextUtils.isEmpty(reservaNumberEt.getText().toString())){
            paer.put(HttpConfig.Field.order_no, reservaNumberEt.getText().toString());
        }
        paer.put(HttpConfig.Field.name, reservaNameEt.getText().toString());
        paer.put(HttpConfig.Field.tel, reservaPhoneEt.getText().toString());
        // 房间号
        if(!TextUtils.isEmpty(roundNumberEt.getText().toString())){
            paer.put(HttpConfig.Field.room, roundNumberEt.getText().toString());
        }
        // 备注
        if(!TextUtils.isEmpty(remarksEt.getText().toString())){
            paer.put(HttpConfig.Field.memo1, remarksEt.getText().toString());
        }

        paer.put(HttpConfig.Field.onduty1n, mUser.getU_NAME());

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
                                xlID = object.getString("xl_id") ;
                                String base = Base64Code.bitmapToBase64(bitmap) ;
                                if(TextUtils.isEmpty(xlID) ||  TextUtils.isEmpty(base)){
                                    baggageOk();
                                    return;
                                }
                                uploadPhoto(xlID , base) ;
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
        Toast.makeText(BaggageUploadDataActvitiy.this , "登记失败，请重试" , Toast.LENGTH_SHORT).show();
    }

    /**
     * 登记成功
     */
    private void baggageOk(){
        state = 1 ;
        uploadphotoTv.setVisibility(View.GONE);
        baggageTv.setText("打  印");
        mDialog.dismiss();

        reservaNumberEt.setFocusable(false);
        reservaNumberEt.setFocusableInTouchMode(false);

        reservaNameEt.setFocusable(false);
        reservaNameEt.setFocusableInTouchMode(false);

        reservaPhoneEt.setFocusable(false);
        reservaPhoneEt.setFocusableInTouchMode(false);

        roundNumberEt.setFocusable(false);
        roundNumberEt.setFocusableInTouchMode(false);

        remarksEt.setFocusable(false);
        remarksEt.setFocusableInTouchMode(false);


        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if(imm!=null){
            imm.hideSoftInputFromWindow(remarksEt.getWindowToken() , 0);
        }
        Toast.makeText(BaggageUploadDataActvitiy.this , "登记成功，请打印" , Toast.LENGTH_SHORT).show();
    }

    /**
     * 打印
     */
    private void printing(){
        AidlUtil.getInstance().printQr(xlID , 8 ,3); // 打印二维码
        AidlUtil.getInstance().printText("预约号："+reservaNumberEt.getText().toString() , 30 , false , false );
        AidlUtil.getInstance().printText("寄存人："+reservaNameEt.getText().toString(), 30 , false , false );
        AidlUtil.getInstance().printText("手机号："+reservaPhoneEt.getText().toString() , 30 , false , false );
        AidlUtil.getInstance().printText("房间号："+roundNumberEt.getText().toString() , 30 , false , false );
        AidlUtil.getInstance().printText("备  注："+remarksEt.getText().toString() , 30 , false , false );
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
