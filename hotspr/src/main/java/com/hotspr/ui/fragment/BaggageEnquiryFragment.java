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
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import com.hotspr.HttpConfig;
import com.hotspr.R;
import com.hotspr.business.api.BaggageEnquiryAPI;
import com.hotspr.business.api.BaggageRegistrationAPI;
import com.hotspr.business.presenter.BaggageEnquiryPresenter;
import com.hotspr.ui.activity.BaggageUploadDataActvitiy;
import com.hotspr.ui.activity.LuggageQueryOperationActivity;
import com.hotspr.ui.adapter.LuggageQueryInfoAdapter;
import com.hotspr.ui.bean.LuggageQuiryData;
import com.modulebase.ui.fragment.BaseFragment;
import com.modulebase.view.recyclerview.adapter.LRecyclerViewAdapter;
import com.modulebase.view.recyclerview.view.LRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BaggageEnquiryFragment extends BaseFragment implements View.OnClickListener , BaggageEnquiryAPI.View<LuggageQuiryData>,LuggageQueryInfoAdapter.OnItemClickListener {

    private Context mContext ;
    private View mView ;
    private EditText keywordEt ;
    private BaggageEnquiryPresenter mPressentre ;

    private LRecyclerView mLRecyclerView ;
    private LRecyclerViewAdapter mLRecyclerViewAdapter ;
    private LuggageQueryInfoAdapter mAdapter ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mView == null ){
            mContext = getContext();
            mView = LayoutInflater.from(mContext).inflate(R.layout.fragment_baggage_enquiry_layout ,null);
            findViewByID(mView);
            initLRecyclerView();
            initData();
        }
        ViewGroup parent = (ViewGroup) mView.getParent();
        if(parent!=null){
            parent.removeView(mView);
        }
        return mView;
    }

    private void findViewByID(View view){
        view.findViewById(R.id.sweep_code_layout).setOnClickListener(this);
        view.findViewById(R.id.query_tv).setOnClickListener(this);
        mLRecyclerView = view.findViewById(R.id.recycler_view);
        keywordEt = view.findViewById(R.id.keyword_et);
    }

    private void initLRecyclerView(){
        mLRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2)); // 不设会不显示
        mAdapter = new LuggageQueryInfoAdapter(mContext);
        mAdapter.setOnItemClickListener(this);
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mAdapter);
        mLRecyclerView.setAdapter(mLRecyclerViewAdapter);
        mLRecyclerView.setPullRefreshEnabled(false);
        mLRecyclerView.setLoadMoreEnabled(false);
        mLRecyclerView.setHasFixedSize(true);                    //这是item的固定大小
//        // 刷新
//        mLRecyclerView.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                if (!NetworkUtils.isNetworkConnected(mContext)) {
//                    mLRecyclerView.refreshComplete(0);  //不调用这句就表示没有刷新成功(不会回上去)
//                    mAdapter.notifyDataSetChanged();
//                    Toast.makeText(mContext, "当前网络不可用,请检查网络设置", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                //mAdapter.upData(new ArrayList<Round>()); // 清空数据
//                page = 1;
//                load(mSearchView.getFloor() , mSearchView.getRoomType() , mSearchView.getRoomNumber() , WardRoundPressenterAPI.Pressente.LOAD_MODLE_REFRASH , page );
//            }
//        });
//        // 加载更多
//        mLRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
//            @Override
//            public void onLoadMore() {
//                if (page < TOLTE_PAGE_NUMBER) {
//                    load(mSearchView.getFloor() , mSearchView.getRoomType() , mSearchView.getRoomNumber() , WardRoundPressenterAPI.Pressente.LOAD_MODLE_MORE , ++page );
//                } else {
//                    mLRecyclerView.setNoMore(true); //没有更多了
//                }
//            }
//        });
    }

    private void initData(){
        mPressentre = new BaggageEnquiryPresenter(mContext , this);
    }


    /**
     * 加载数据
     * @param kword
     * @param xl_id
     * @param lodelModel
     * @param page
     */
    private void load(String kword , String xl_id , int lodelModel , int page){
        if (!TextUtils.isEmpty(kword) || !TextUtils.isEmpty(xl_id)) {
            Map<String, String> searchParamsMap = new HashMap<>();
            if (!TextUtils.isEmpty(kword)) {
                searchParamsMap.put(HttpConfig.Field.kword, kword);
            }
            if (!TextUtils.isEmpty(xl_id)) {
                searchParamsMap.put(HttpConfig.Field.xl_id, xl_id);
            }
            mPressentre.loadData(lodelModel, page, searchParamsMap);
        } else {
            mPressentre.loadData(lodelModel, page , null);
        }
    }

    @Override
    public void upDatd(int mode, ArrayList<LuggageQuiryData> rounds, int pageNumber) {
        if(mAdapter!=null){
            mAdapter.addDatas(rounds);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.baggage_layout){ // 登记按钮
            goBgUploadData();
        } else if(id == R.id.query_tv){
            if(TextUtils.isEmpty(keywordEt.getText().toString())){
                Toast.makeText(mContext , "请输入关键字" , Toast.LENGTH_SHORT).show();
                return;
            }
            // 隐藏键盘
            InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            if(imm!=null){
                imm.hideSoftInputFromWindow(keywordEt.getWindowToken() , 0);
            }
            load(keywordEt.getText().toString() , "" , BaggageRegistrationAPI.LOAD_MODLE_REFRASH , 1);
        }
    }

    /**
     * 登记-上传数据
     */
    private void goBgUploadData(){
        Intent intent = new Intent(mContext , BaggageUploadDataActvitiy.class);
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        startActivity(intent);
    }


    @Override
    public void onItemClick(LuggageQuiryData info) {
        Intent intent = new Intent(mContext , LuggageQueryOperationActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(LuggageQueryOperationActivity.DATA_KEY , info);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
