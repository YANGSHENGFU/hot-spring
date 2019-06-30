package com.hotspr.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hotspr.R;
import com.modulebase.ui.activity.BaseActivity;

public class UnqualifiedActivity extends BaseActivity implements View.OnClickListener{

    private TextView backTv ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unquali_fied_layout);
        findView() ;
    }

    private void findView(){
        backTv = findViewById(R.id.back_tv);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId() ;
        if(id == R.id.back_tv){
            finish();
        }
    }

}
