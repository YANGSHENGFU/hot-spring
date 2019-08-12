package com.hotspr.ui.fragment.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hotspr.R;
import com.hotspr.ui.view.SearchView;
import com.modulebase.ui.dialog.LoadDialog;
import com.modulebase.ui.fragment.BaseFragment;
import com.modulebase.view.recyclerview.view.LRecyclerView;


public class ArrangCleanBaseFragment extends BaseFragment implements  SearchView.SearchLisnter {

    protected View mView ;
    protected Context mContext ;
    protected SearchView mSearchView ;
    protected LRecyclerView mLRecyclerView ;
    protected LoadDialog mLoadDialog ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mContext = getContext();
            mView = LayoutInflater.from(mContext).inflate(R.layout.fragment_all_round_layout, null);
            findView(mView) ;
            initDilaog();
            initLRecyclerView();
            initData();
        }
        ViewGroup parent = (ViewGroup) mView.getParent();
        if (parent != null) {
            parent.removeView(mView);
        }
        return mView;
    }

    protected void findView(View mView) {
        mSearchView = mView.findViewById(R.id.searchView);
        mSearchView.setSearchLisnter(this);
        mLRecyclerView = mView.findViewById(R.id.lrecyclerview);
    }

    protected void initDilaog(){
        mLoadDialog = new LoadDialog(mContext);
        mLoadDialog.setCanceledOnTouchOutside(true);
        mLoadDialog.setCancelable(true);
    }

    protected void initLRecyclerView() {

    }

    protected void initData(){

    }


    @Override
    public void search(String a, String b, String c) {

    }
}
