package com.hotspr.ui.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hotspr.R;
import com.hotspr.business.api.LogingAPI;
import com.hotspr.business.presenter.LoginPresenter;
import com.hotspr.toolkit.FileHandle;
import com.hotspr.ui.dialog.PermissionPromptDialog;
import com.modulebase.HttpConfig;
import com.modulebase.toolkit.FileUtils;
import com.modulebase.toolkit.SharepreFHelp;
import com.modulebase.ui.activity.BaseActivity;
import com.sunmi.utils.AidlUtil;
import com.sunmi.utils.BytesUtil;


public class LoginActivity extends BaseActivity implements View.OnClickListener, LogingAPI.View {

    private EditText et_username;
    private EditText et_password;
    private TextView tv_set;
    private Button butLogin;
    private ProgressDialog progressDialog;
    private Button daying_but;
    private CheckBox checkBox;

    private static int PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        butLogin = findViewById(R.id.btn_login);
        tv_set = findViewById(R.id.tv_set);
        checkBox = findViewById(R.id.check_box);
        butLogin.setOnClickListener(this);
        tv_set.setOnClickListener(this);
        daying_but = findViewById(R.id.daying_but);
        daying_but.setOnClickListener(this);
        HttpConfig.HOST_NAME = SharepreFHelp.getInstance(LoginActivity.this).getHOSTURL();
        HttpConfig.PIC_HOST_NAME = SharepreFHelp.getInstance(LoginActivity.this).getPICHOSTURL();
        HttpConfig.CURRENT_HOST = HttpConfig.HOST_NAME;
        presenter = new LoginPresenter(this, this);
        initProgressDialog();
        chekPermission();
        String s = new String(BytesUtil.getMeituanBill());
        initUserData();

    }

    private void initUserData(){
        checkBox.setChecked(SharepreFHelp.getInstance(this).getKeepPasswordState());
        et_username.setText(SharepreFHelp.getInstance(this).getUserName());
        if(checkBox.isChecked()){
            et_password.setText(SharepreFHelp.getInstance(this).getPassword());
        }
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharepreFHelp.getInstance(LoginActivity.this).setKeepPasswordState(isChecked);
            }
        });
    }

    /**
     * 初始化请求时loading
     * Dialog
     */
    private void initProgressDialog() {
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setIndeterminate(false);//循环滚动
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("loading...");
        progressDialog.setCancelable(false);//false不能取消显示，true可以取消显示
    }

    /**
     * 检查权限
     */
    private void chekPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
        } else {
            FileUtils.caretCachAndXlogExists();
            FileHandle.creatUserExists();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 得到权限
                FileUtils.caretCachAndXlogExists();
            } else if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                PermissionPromptDialog mPromptDialog = new PermissionPromptDialog(this);
                mPromptDialog.setContentTvString("此应用需要使用存储权限，否则无法正常使用。请移步到设置-应用-权限中打开");
                mPromptDialog.setCancelable(false);
                mPromptDialog.setCanceledOnTouchOutside(false);
                mPromptDialog.getIKnoeTv().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(Settings.ACTION_SETTINGS)); //直接进入手机中设置界面
                        finish();
                    }
                });
                mPromptDialog.show();
            } else if (grantResults[1] == PackageManager.PERMISSION_GRANTED) { // 相机
                PermissionPromptDialog mPromptDialog = new PermissionPromptDialog(this);
                mPromptDialog.setContentTvString("此应用需要使用相机，否则无法正常使用。请移步到设置-应用-权限中打开");
                mPromptDialog.setCancelable(false);
                mPromptDialog.setCanceledOnTouchOutside(false);
                mPromptDialog.getIKnoeTv().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(Settings.ACTION_SETTINGS)); //直接进入手机中设置界面
                        finish();
                    }
                });
                mPromptDialog.show();
            } else if (grantResults[1] != PackageManager.PERMISSION_GRANTED) {

            }
        }
    }

    public void Load_Set() {

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(LoginActivity.this);
        //    builder.setIcon(R.layout.activity_login_set);
        mBuilder.setTitle("参数设置");
        //    通过LayoutInflater来加载一个xml的布局文件作为一个View对象
        View view = LayoutInflater.from(LoginActivity.this).inflate(R.layout.dialog_seturl_layout, null);
        //    设置我们自己定义的布局文件作为弹出框的Content
        mBuilder.setView(view);

        final EditText iniurl = view.findViewById(R.id.txt_login_set_iniurl);
        final EditText imgurl = view.findViewById(R.id.txt_login_set_imgurl);
        iniurl.setText(HttpConfig.HOST_NAME);
        imgurl.setText(HttpConfig.PIC_HOST_NAME);
        mBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                HttpConfig.HOST_NAME = iniurl.getText().toString().trim();
                HttpConfig.CURRENT_HOST = HttpConfig.HOST_NAME;
                HttpConfig.PIC_HOST_NAME = imgurl.getText().toString().trim();
                SharepreFHelp.getInstance(LoginActivity.this).setHOSTURL(HttpConfig.HOST_NAME);
                SharepreFHelp.getInstance(LoginActivity.this).setPICHOSTURL(HttpConfig.PIC_HOST_NAME);
            }
        });
        mBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        mBuilder.show();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.btn_login) {
            loging();
        } else if (id == R.id.tv_set) {
            Load_Set();
        } else if (id == R.id.daying_but) {
            AidlUtil.getInstance().printQr("御温泉", 8, 3);
        }
    }

    /**
     * 登录逻辑
     */
    private void loging() {
        String username = et_username.getText().toString();
        String password = et_password.getText().toString();
        if (username.trim().equals("")) {
            Toast.makeText(this, "请输入账号", Toast.LENGTH_SHORT).show();
            return;
        } else if (password.trim().equals("")) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        SharepreFHelp.getInstance(this).setUserName(username); // 保存用户名
        presenter.loging(username, password);
    }

    @Override
    public void showProgressDialog() {
        if (progressDialog != null && !progressDialog.isShowing()) {
            progressDialog.show();
        }

    }

    @Override
    public void dismissDialog() {
        if (!isFinishing() && progressDialog != null && progressDialog.isShowing() && !isFinishing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void lodingResult(boolean isOk, String error_msg) {
        dismissDialog();
        if (isOk) {
            if(checkBox.isChecked()){
                SharepreFHelp.getInstance(this).setPassword(et_password.getText().toString()); // 保存用户名
            } else {
                SharepreFHelp.getInstance(this).setPassword(""); // 保存用户名
            }
            Intent intent = new Intent(this, UserRightsActivity.class);
            startActivity(intent);
        } else {
            if (!TextUtils.isEmpty(error_msg)) {
                Toast.makeText(this, error_msg, Toast.LENGTH_SHORT).show();
            }
        }
    }

}
