package com.hotspr.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.hotspr.R;
import com.hotspr.toolkit.ActivityUtils;
import com.hotspr.ui.fragment.BaggageEnquiryFragment;
import com.modulebase.ui.activity.BaseActivity;

public class BaggageEnquiryActivity extends BaseActivity implements View.OnClickListener{

    private BaggageEnquiryFragment mFragment ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baggage_enquiry_layout);
        findViewByID();
        addFramentToActivity();
    }

    private void findViewByID(){
        findViewById(R.id.back_tv).setOnClickListener(this);
    }

    private void addFramentToActivity(){
        if(mFragment == null){
            mFragment = new BaggageEnquiryFragment();
        }
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager() , mFragment , R.id.fragment_layout);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.back_tv){
            finish();
        }
    }

}
