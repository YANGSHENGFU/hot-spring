package com.hotspr.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import com.hotspr.HttpConfig;
import com.hotspr.R;
import com.hotspr.business.api.ArrangCleanAPI;
import com.hotspr.business.api.WardRoundPressenterAPI;

import com.hotspr.business.presenter.ReadyInspectRoomPressenter;
import com.hotspr.ui.activity.UnqualifiedActivity;
import com.hotspr.ui.adapter.ReadyInspectRoomAdapter;
import com.hotspr.ui.bean.Round;
import com.hotspr.ui.fragment.base.ArrangCleanBaseFragment;
import com.modulebase.toolkit.NetworkUtils;
import com.modulebase.view.recyclerview.adapter.LRecyclerViewAdapter;
import com.modulebase.view.recyclerview.recinterface.OnLoadMoreListener;
import com.modulebase.view.recyclerview.recinterface.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReadyInspectRoomFragment extends ArrangCleanBaseFragment implements ArrangCleanAPI.View , ReadyInspectRoomAdapter.CheckLisnter {

    private String TAG = "ReadyInspectRoomFragment";
    private int page = 1 ;
    private int TOLTE_PAGE_NUMBER ;
    private ReadyInspectRoomAdapter mAdapter ;
    private LRecyclerViewAdapter mLRecyclerViewAdapter ;
    private ReadyInspectRoomPressenter mPressenter ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initLRecyclerView() {
        mLRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 3)); // 不设会不显示
        mAdapter = new ReadyInspectRoomAdapter(mContext);
        mAdapter.setCheckLisnter(this);
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mAdapter);
        mLRecyclerView.setAdapter(mLRecyclerViewAdapter);
        mLRecyclerView.setHeaderViewColor(R.color.colorAccent, R.color.color_5e5656, android.R.color.white); //设置头部加载颜色
        mLRecyclerView.setFooterViewColor(R.color.colorAccent, R.color.color_5e5656, android.R.color.white); //设置底部加载颜色
        mLRecyclerView.setFooterViewHint("努力加载中", "已全部加载完", "网络不给力啊，点击再试一次吧");
        mLRecyclerView.setHasFixedSize(true);                    //这是item的固定大小
        // 刷新
        mLRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!NetworkUtils.isNetworkConnected(mContext)) {
                    mLRecyclerView.refreshComplete(0);  //不调用这句就表示没有刷新成功(不会回上去)
                    mAdapter.notifyDataSetChanged();
                    Toast.makeText(mContext, "当前网络不可用,请检查网络设置", Toast.LENGTH_SHORT).show();
                    return;
                }
                //mAdapter.upData(new ArrayList<Round>()); // 清空数据
                page = 1;
                load(mSearchView.getFloor() , mSearchView.getRoomType() , mSearchView.getRoomNumber() , WardRoundPressenterAPI.Pressente.LOAD_MODLE_REFRASH , page );
            }
        });
        // 加载更多
        mLRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (page < TOLTE_PAGE_NUMBER) {
                    load(mSearchView.getFloor() , mSearchView.getRoomType() , mSearchView.getRoomNumber() , WardRoundPressenterAPI.Pressente.LOAD_MODLE_MORE , ++page );
                } else {
                    mLRecyclerView.setNoMore(true); //没有更多了
                }
            }
        });
    }

    /**
     * 初次加载数据
     */
    @Override
    protected void initData(){
        mPressenter = new ReadyInspectRoomPressenter(mContext, this);
        load(mSearchView.getFloor() , mSearchView.getRoomType() , mSearchView.getRoomNumber() , WardRoundPressenterAPI.Pressente.LOAD_MODLE_REFRASH , page );
    }


    @Override
    public void upDatd(int mode, ArrayList<Round> rounds, int pageNumber) {
        if (rounds != null) {
            mLRecyclerView.refreshComplete(rounds.size());  // 不调用这句方法就表示没有刷新成功
            if (mode == WardRoundPressenterAPI.Pressente.LOAD_MODLE_REFRASH ||
                    mode == WardRoundPressenterAPI.Pressente.LOAD_MODLE_SEARCH) {
                mAdapter.upData(rounds);
            } else {
                mAdapter.addData(rounds);
            }
        }else{
            if (mode == WardRoundPressenterAPI.Pressente.LOAD_MODLE_REFRASH ||
                    mode == WardRoundPressenterAPI.Pressente.LOAD_MODLE_SEARCH) {
                mAdapter.upData(new ArrayList<Round>());
            }
            mLRecyclerView.refreshComplete(0);  // 不调用这句方法就表示没有刷新成功
        }
        if (pageNumber >= 0) {
            TOLTE_PAGE_NUMBER = pageNumber;
        }
        if(mLoadDialog!=null && mLoadDialog.isShowing()){
            mLoadDialog.dismiss();
        }
    }

    /**
     * 搜素数据
     * @param floor
     * @param roomType
     * @param roomNumber
     */
    @Override
    public void search(String floor , String roomType , String roomNumber) {
        mAdapter.upData(new ArrayList<Round>()); // 清空数据
        mLoadDialog.show();
        page = 1  ;
        load(floor , roomType , roomNumber ,WardRoundPressenterAPI.Pressente.LOAD_MODLE_SEARCH , page );
    }

    /**
     * 加载数据
     * @param floor
     * @param roomType
     * @param roomNumber
     * @param lodelModel
     * @param page
     */
    private void load(String floor , String roomType , String roomNumber , int lodelModel , int page){
        if (!TextUtils.isEmpty(floor) || !TextUtils.isEmpty(roomType) || !TextUtils.isEmpty(roomNumber)) {
            Map<String, String> searchParamsMap = new HashMap<>();
            if (!TextUtils.isEmpty(floor)) {
                searchParamsMap.put(HttpConfig.Field.floor, floor);
            }
            if (!TextUtils.isEmpty(roomType)) {
                searchParamsMap.put(HttpConfig.Field.type_class, roomType);
            }
            if (!TextUtils.isEmpty(roomNumber)) {
                searchParamsMap.put(HttpConfig.Field.room, roomNumber);
            }
            mPressenter.loadData(lodelModel, page, searchParamsMap);
        } else {
            mPressenter.loadData(lodelModel, page , null);
        }
    }


    @Override
    public void qualified(String roundID) {
        mPressenter.qualified();
    }


    @Override
    public void unqualiFied(String roundID, String memo) {
        Intent intent = new Intent(mContext , UnqualifiedActivity.class);
        startActivity(intent);
    }
}
