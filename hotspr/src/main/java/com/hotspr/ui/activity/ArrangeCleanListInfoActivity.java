package com.hotspr.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;
import com.hotspr.R;
import com.hotspr.ui.adapter.WardRoundPagerAdapter;
import com.hotspr.ui.fragment.AllArrangCleanFragment;
import com.hotspr.ui.fragment.ReadyCleanRoomFragment;
import com.hotspr.ui.fragment.ReadyInspectRoomFragment;
import com.hotspr.ui.view.WardRoundTableView;
import com.hotspr.ui.view.WardRoundViewpager;
import com.modulebase.ui.activity.BaseActivity;

public class ArrangeCleanListInfoActivity extends BaseActivity implements View.OnClickListener ,WardRoundTableView.ItemChoiceListeners{

    private WardRoundTableView mTableView ;
    private TextView mBackTv ;
    private WardRoundViewpager mViewPager;
    private WardRoundPagerAdapter mAdapter;

    private AllArrangCleanFragment allRoundFragment =  new AllArrangCleanFragment() ;
    private ReadyInspectRoomFragment clearFragment = new ReadyInspectRoomFragment() ;
    private ReadyCleanRoomFragment unClearFragment = new ReadyCleanRoomFragment();
    private Fragment[] fragments = new Fragment[]{allRoundFragment , clearFragment , unClearFragment };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrange_clean_listinfo_layout);
        findView();
        initData();
        mTableView.setTextConctent(new String[]{"全部房"  , "待检查房"  , "待清洁房"});
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
