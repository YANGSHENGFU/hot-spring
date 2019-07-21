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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hotspr.R;
import com.hotspr.business.api.BaggageRegistrationAPI;
import com.hotspr.business.presenter.BaggageRegistrationPressentre;
import com.hotspr.ui.activity.BaggageUploadDataActvitiy;
import com.hotspr.ui.adapter.BaggageQueryAdapter;
import com.hotspr.ui.bean.Deposit;
import com.modulebase.HttpConfig;
import com.modulebase.ui.fragment.BaseFragment;
import com.modulebase.view.recyclerview.adapter.LRecyclerViewAdapter;
import com.modulebase.view.recyclerview.view.LRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BaggageRegistrationFragemnt extends BaseFragment implements View.OnClickListener , BaggageRegistrationAPI.View <Deposit>, BaggageQueryAdapter.OnItemClickListener {

    private Context mContext ;
    private View mView ;
    private LinearLayout baggageLayout ; // 登记
    private EditText nameEt ;
    private EditText phoneEt ;
    private TextView queryTv ; // 查询

    private BaggageRegistrationPressentre mPressentre ;

    private LRecyclerView mLRecyclerView ;
    private LRecyclerViewAdapter mLRecyclerViewAdapter ;
    private BaggageQueryAdapter mAdapter ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mView == null ){
            mContext = getContext();
            mView = LayoutInflater.from(mContext).inflate(R.layout.fragment_baggage_registration_layout ,null);
            findViewByID(mView);
            initLRecyclerView() ;
            initData();
        }
        ViewGroup parent = (ViewGroup) mView.getParent();
        if(parent!=null){
            parent.removeView(mView);
        }
        return mView;
    }

    private void findViewByID(View view){
        view.findViewById(R.id.baggage_layout).setOnClickListener(this);
        view.findViewById(R.id.query_tv).setOnClickListener(this);
        mLRecyclerView = view.findViewById(R.id.recycler_view);
        nameEt = view.findViewById(R.id.name_et);
        //phoneEt = view.findViewById(R.id.phone_et);
    }

    private void initLRecyclerView(){
        mLRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2)); // 不设会不显示
        mAdapter = new BaggageQueryAdapter(mContext);
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
        mPressentre = new BaggageRegistrationPressentre(mContext , this);
    }



    /**
     * 加载数据
     * @param kword
     * @param mobile
     * @param lodelModel
     * @param page
     */
    private void load(String kword , String mobile , int lodelModel , int page){
        if (!TextUtils.isEmpty(kword) || !TextUtils.isEmpty(mobile)) {
            Map<String, String> searchParamsMap = new HashMap<>();
            if (!TextUtils.isEmpty(kword)) {
                searchParamsMap.put(HttpConfig.Field.kword, kword);
            }
//            if (!TextUtils.isEmpty(mobile)) {
//                searchParamsMap.put(HttpConfig.Field.mobile, mobile);
//            }
            mPressentre.loadData(lodelModel, page, searchParamsMap);
        } else {
            mPressentre.loadData(lodelModel, page , null);
        }
    }

    @Override
    public void upDatd(int mode , ArrayList<Deposit> rounds , int pageNumber) {
        mAdapter.addDatas(rounds);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.baggage_layout){ // 登记按钮
            goBgUploadData();
        } else if(id == R.id.query_tv){
            if(TextUtils.isEmpty(nameEt.getText().toString())){
                Toast.makeText(mContext , "请输入关键字" , Toast.LENGTH_SHORT).show();
               return;
            }
            // 隐藏键盘
//            InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
//            if(imm!=null){
//                imm.hideSoftInputFromWindow(phoneEt.getWindowToken() , 0);
//            }
           // load(nameEt.getText().toString() , phoneEt.getText().toString() , BaggageRegistrationAPI.LOAD_MODLE_REFRASH , 1);
            load(nameEt.getText().toString() ,"" , BaggageRegistrationAPI.LOAD_MODLE_REFRASH , 1);
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
    public void onItemClick(Deposit deposit) {
        Intent intent = new Intent(mContext , BaggageUploadDataActvitiy.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(BaggageUploadDataActvitiy.DATA_KEY , deposit);
        intent.putExtras(bundle);
        startActivity(intent);
    }



}
