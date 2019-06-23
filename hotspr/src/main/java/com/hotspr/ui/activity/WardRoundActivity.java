package com.hotspr.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.View;

import com.hotspr.R;
import com.hotspr.ui.adapter.WardRoundPagerAdapter;
import com.hotspr.ui.dialog.PermissionPromptDialog;
import com.hotspr.ui.fragment.AllRoundFragment;
//import com.hotspr.ui.fragment.ClearFragment;
//import com.hotspr.ui.fragment.UnClearFragment;
import com.hotspr.ui.fragment.ClearFragment;
import com.hotspr.ui.fragment.UnClearFragment;
import com.hotspr.ui.view.WardRoundTableView;
import com.hotspr.ui.view.WardRoundViewpager;
import com.modulebase.ui.activity.BaseActivity;


public class WardRoundActivity extends BaseActivity implements WardRoundTableView.ItemChoiceListeners{

    private WardRoundTableView mTableView ;
    private WardRoundViewpager mViewPager ;

    private WardRoundPagerAdapter mAdapter ;

    private int PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 0 ;


    private AllRoundFragment allRoundFragment =  new AllRoundFragment() ;
    private ClearFragment clearFragment = new ClearFragment() ;
    private UnClearFragment unClearFragment = new UnClearFragment();
    private Fragment[] fragments = new Fragment[]{allRoundFragment , clearFragment , unClearFragment };//  , unClearFragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_ward_round_layout);
        findView() ;
        initData() ;
        chekPermission();
    }

    private void findView(){
        mTableView = findViewById(R.id.table_view);
        mViewPager = findViewById(R.id.viewpager);
        mTableView.setItemChoiceListeners(this);
        findViewById(R.id.back_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initData(){
        mAdapter = new WardRoundPagerAdapter( getSupportFragmentManager() , fragments );
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
    }

    @Override
    public void itemChoice(int index) {
        mViewPager.setCurrentItem(index);
    }

    private void chekPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.CAMERA}, PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
        } else {

        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE) {
            if(grantResults[0] != PackageManager.PERMISSION_GRANTED){ // 相机
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
            } else {

            }
        } else {

        }
    }

}
