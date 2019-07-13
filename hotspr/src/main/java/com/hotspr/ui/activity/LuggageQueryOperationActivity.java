package com.hotspr.ui.activity;

import android.os.Bundle;

import com.hotspr.R;
import com.hotspr.ui.bean.LuggageQuiryData;
import com.modulebase.ui.activity.BaseActivity;

public class LuggageQueryOperationActivity extends BaseActivity {

    public static String DATA_KEY = "DATA_KEY" ;
    private LuggageQuiryData mData ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_luggage_query_operation_layout);
        findViewById();
        initData();
    }

    private void findViewById(){

    }

    private void initData(){

    }

}
