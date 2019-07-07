package com.hotspr.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hotspr.R;
import com.hotspr.business.api.BaggageRegistrationAPI;
import com.hotspr.ui.activity.BaggageUploadDataActvitiy;
import com.hotspr.ui.bean.Round;
import com.modulebase.ui.fragment.BaseFragment;
import com.modulebase.view.recyclerview.view.LRecyclerView;

import java.util.ArrayList;

public class BaggageRegistrationFragemnt extends BaseFragment implements View.OnClickListener , BaggageRegistrationAPI.View {

    private Context mContext ;
    private View mView ;
    private LinearLayout baggageLayout ; // 登记
    private LRecyclerView mRecyclerView ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mView == null ){
            mContext = getContext() ;
            mView = LayoutInflater.from(mContext).inflate(R.layout.fragment_baggage_registration_layout ,null);
            findViewByID(mView) ;
        }
        ViewGroup parent = (ViewGroup) mView.getParent();
        if(parent!=null){
            parent.removeView(mView);
        }
        return mView;
    }

    private void findViewByID(View view){
        view.findViewById(R.id.baggage_layout).setOnClickListener(this);
        mRecyclerView = view.findViewById(R.id.recycler_view);
    }

    @Override
    public void upDatd(int mode , ArrayList<Round> rounds , int pageNumber) {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.baggage_layout){ // 登记按钮
            goBgUploadData();
        }
    }

    /**
     * 登记-上传数据
     */
    private void goBgUploadData(){
        Intent intent =  new Intent(mContext , BaggageUploadDataActvitiy.class);
        startActivity(intent);
    }


}
