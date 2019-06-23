package com.hotspr.ui.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;


public class WardRoundViewpager extends ViewPager {

    private boolean mScrollble = false;

    public WardRoundViewpager(@NonNull Context context) {
        super(context);
    }

    public WardRoundViewpager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(!mScrollble){
            return false ;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(!mScrollble){
            return false ;
        }
        return super.onTouchEvent(ev) ;
    }

}
