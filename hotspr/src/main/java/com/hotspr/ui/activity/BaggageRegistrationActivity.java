package com.hotspr.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.hotspr.R;
import com.hotspr.toolkit.ActivityUtils;
import com.hotspr.ui.fragment.BaggageRegistrationFragemnt;
import com.modulebase.ui.activity.BaseActivity;

/**
 * 行李登记
 */
public class BaggageRegistrationActivity extends BaseActivity implements View.OnClickListener{


    private BaggageRegistrationFragemnt mFragment ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baggage_registration_layout);
        findViewByID() ;
        addFramentToActivity() ;
    }

    private void findViewByID(){
        findViewById(R.id.back_tv).setOnClickListener(this);
    }

    private void addFramentToActivity(){
        if(mFragment == null){
            mFragment = new BaggageRegistrationFragemnt();
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
