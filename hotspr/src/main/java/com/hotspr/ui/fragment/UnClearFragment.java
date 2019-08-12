package com.hotspr.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.modulebase.HttpConfig;
import com.hotspr.R;
import com.hotspr.business.api.WardRoundPressenterAPI;
import com.hotspr.business.presenter.UnClearPressenter;
import com.hotspr.ui.activity.WardRoundActivity;
import com.hotspr.ui.adapter.RoundAdapter;
import com.hotspr.ui.bean.Round;
import com.hotspr.ui.view.SearchView;
import com.modulebase.toolkit.NetworkUtils;
import com.modulebase.ui.dialog.LoadDialog;
import com.modulebase.ui.fragment.BaseFragment;
import com.modulebase.view.recyclerview.adapter.LRecyclerViewAdapter;
import com.modulebase.view.recyclerview.recinterface.OnLoadMoreListener;
import com.modulebase.view.recyclerview.recinterface.OnRefreshListener;
import com.modulebase.view.recyclerview.view.LRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import static android.app.Activity.RESULT_OK;

public class UnClearFragment extends BaseFragment implements WardRoundPressenterAPI.View, RoundAdapter.CheckLisnter, SearchView.SearchLisnter {

    private String TAG = "UnClearFragment";
    public static int REQUEST_CAMERA = 7;
    private static int REQUEST_CODE = 1003;

    private View mView;
    private Context mContext;
    private SearchView mSearchView;
    private LRecyclerView mLRecyclerView;
    private RoundAdapter mAdapter;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private UnClearPressenter mPressenter;

    private int page = 1;
    private int TOLTE_PAGE_NUMBER;
    private LoadDialog mLoadDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getContext();
        if (mView == null) {
            mView = LayoutInflater.from(mContext).inflate(R.layout.fragment_unclearround_layout, null);
            findView(mView);
            initLRecyclerView();
            initData();
            initDilaog();
        }
        ViewGroup parent = (ViewGroup) mView.getParent();
        if (parent != null) {
            parent.removeView(mView);
        }
        return mView;
    }

    private void findView(View view) {
        mSearchView = view.findViewById(R.id.searchView);
        mLRecyclerView = view.findViewById(R.id.lrecyclerviewunclear);
        mSearchView.setSearchLisnter(this);
    }

    private void initDilaog() {
        mLoadDialog = new LoadDialog(mContext);
        mLoadDialog.setCanceledOnTouchOutside(true);
        mLoadDialog.setCancelable(true);
    }

    private void initLRecyclerView() {
        mLRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 3)); // 不设会不显示
        mAdapter = new RoundAdapter(mContext);
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
                page = 1;
                initLRecyclerView();
                initData();
                //mAdapter.upData(new ArrayList<Round>());
//                page = 1;
//                load(mSearchView.getFloor(), mSearchView.getRoomType(), mSearchView.getRoomNumber(), WardRoundPressenterAPI.Pressente.LOAD_MODLE_REFRASH, page);
                //mPressenter.loadData(WardRoundPressenterAPI.Pressente.LOAD_MODLE_REFRASH , page ,null); // 加载网络数据
            }
        });
        // 加载更多
        mLRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (page < TOLTE_PAGE_NUMBER) {
                    load(mSearchView.getFloor(), mSearchView.getRoomType(), mSearchView.getRoomNumber(), WardRoundPressenterAPI.Pressente.LOAD_MODLE_MORE, ++page);
                    //mPressenter.loadData(WardRoundPressenterAPI.Pressente.LOAD_MODLE_MORE , ++page , null); // 加载网络数据
                } else {
                    mLRecyclerView.setNoMore(true); //没有更多了
                }
            }
        });
    }

    private void initData() {
        mPressenter = new UnClearPressenter(mContext, this);
        load(mSearchView.getFloor(), mSearchView.getRoomType(), mSearchView.getRoomNumber(), WardRoundPressenterAPI.Pressente.LOAD_MODLE_REFRASH, page);
    }

    /**
     * 数据加载成功
     *
     * @param rounds
     */
    @Override
    public void upDatd(int mode, ArrayList<Round> rounds, int pageNumber) {
        if (rounds != null) {
            mLRecyclerView.refreshComplete(rounds.size());  // 不调用这句方法就表示没有刷新成功
            if (mode == WardRoundPressenterAPI.Pressente.LOAD_MODLE_REFRASH) {
                mAdapter.upData(rounds);
            } else {
                mAdapter.addData(rounds);
            }
        } else {
            if (mode == WardRoundPressenterAPI.Pressente.LOAD_MODLE_REFRASH ||
                    mode == WardRoundPressenterAPI.Pressente.LOAD_MODLE_SEARCH) {
                mAdapter.upData(new ArrayList<Round>());
            }
            mLRecyclerView.refreshComplete(0);  // 不调用这句方法就表示没有刷新成功
        }
        if (pageNumber >= 0) {
            TOLTE_PAGE_NUMBER = pageNumber;
        }
        if (mLoadDialog != null && mLoadDialog.isShowing()) {
            mLoadDialog.dismiss();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            if(data!=null){
                Bundle bundle = data.getExtras() ;

                if(bundle!=null){
                    Round round = bundle.getParcelable(WardRoundActivity.resrt_round_key);

                    page = 1;
                    initLRecyclerView();
                    initData();
//                    int index = bundle.getInt(WardRoundActivity.resrt_index_key);
//                    if(round!=null){
//
//                        mAdapter.getData().remove(index);
//                        mAdapter.getData().add(index , round);
//                        mAdapter.notifyItemChanged(index);
//                    }
                }
            }
        }
    }


    @Override
    public void check(int i, Round round) {
        Bundle bundle = new Bundle();
        Intent intent = new Intent(mContext, WardRoundActivity.class);
        bundle.putParcelable(WardRoundActivity.round_key, round);
        bundle.putInt(WardRoundActivity.code_key, REQUEST_CODE);
        bundle.putInt(WardRoundActivity.index_key, i);
        intent.putExtras(bundle);
        startActivityForResult(intent, REQUEST_CODE);
    }


    @Override
    public void search(String floor, String roomType, String roomNumber) {
        mAdapter.upData(new ArrayList<Round>());
        mLoadDialog.show();
        page = 1;
        load(floor, roomType, roomNumber, WardRoundPressenterAPI.Pressente.LOAD_MODLE_SEARCH, page);
    }


    /**
     * 加载数据
     *
     * @param floor
     * @param roomType
     * @param roomNumber
     * @param lodelModel
     * @param page
     */
    private void load(String floor, String roomType, String roomNumber, int lodelModel, int page) {
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
            mPressenter.loadData(lodelModel, page, null);
        }
    }

}
