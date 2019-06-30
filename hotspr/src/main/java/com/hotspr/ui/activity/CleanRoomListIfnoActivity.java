package com.hotspr.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.hotspr.R;
import com.hotspr.ui.adapter.WardRoundPagerAdapter;
import com.hotspr.ui.fragment.AllCleanRoomFragment;
import com.hotspr.ui.fragment.CheckedFragment;
import com.hotspr.ui.fragment.UncleanedFragment;
import com.hotspr.ui.view.WardRoundTableView;
import com.hotspr.ui.view.WardRoundViewpager;
import com.modulebase.ui.activity.BaseActivity;

public class CleanRoomListIfnoActivity extends BaseActivity implements View.OnClickListener , WardRoundTableView.ItemChoiceListeners{

    private WardRoundTableView mTableView ;
    private TextView mBackTv ;
    private WardRoundViewpager mViewPager;
    private WardRoundPagerAdapter mAdapter;

    private AllCleanRoomFragment allRoundFragment =  new AllCleanRoomFragment() ;
    private CheckedFragment clearFragment = new CheckedFragment() ;
    private UncleanedFragment unClearFragment = new UncleanedFragment();
    private Fragment[] fragments = new Fragment[]{allRoundFragment , clearFragment , unClearFragment };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clean_room_litinfo_layout);
        findView() ;
        initData() ;
    }

    private void findView(){
        mBackTv = findViewById(R.id.back_tv);
        mBackTv.setOnClickListener(this);
        mTableView = findViewById(R.id.table_view);
        mViewPager = findViewById(R.id.viewpager);
        mTableView.setItemChoiceListeners(this);
        findViewById(R.id.back_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTableView.setTextConctent(new String[]{"全部房" , "已检查" , "未清洁"});
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

    @Override
    public void onClick(View v) {
        int id = v.getId() ;
        if(id == R.id.back_tv){
            finish();
        }
    }
}
