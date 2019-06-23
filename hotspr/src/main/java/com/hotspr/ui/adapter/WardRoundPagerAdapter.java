package com.hotspr.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class WardRoundPagerAdapter extends FragmentPagerAdapter {

    private Fragment[] mDatas ;

    public WardRoundPagerAdapter(FragmentManager fm , Fragment[] datas) {
        super(fm);
        mDatas = datas ;
    }

    @Override
    public Fragment getItem(int i) {
        return mDatas[i];
    }

    @Override
    public int getCount() {
        return mDatas.length;
    }

}
