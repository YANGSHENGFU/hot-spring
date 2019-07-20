package com.restaurant.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.modulebase.HttpConfig;
import com.modulebase.ui.activity.BaseActivity;
import com.restaurant.R;
import com.restaurant.business.common.PublicInfoHandle;

import java.util.HashMap;
import java.util.Map;

public class DeskNumberActivity extends BaseActivity {

    private PublicInfoHandle mPublicInfoHandle ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desk_number_layout);
        findViewById();
        initData() ;
    }

    private void findViewById(){
        // 关闭界面
        findViewById(R.id.back_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,String>map = new HashMap<>();
                map.put(HttpConfig.Field.ctbm , "1");
                mPublicInfoHandle.getRegion( DeskNumberActivity.this , map);
                mPublicInfoHandle.getFlavor(DeskNumberActivity.this , null);
                mPublicInfoHandle.getProcessingMethod(DeskNumberActivity.this , null);
            }
        });

    }

    private void initData(){
        mPublicInfoHandle = new PublicInfoHandle(this);
    }


}
