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

import com.hotspr.R;
import com.hotspr.business.api.BaggageRegistrationAPI;
import com.hotspr.business.presenter.BaggageFindPressentre;
import com.hotspr.ui.activity.BaggageFindDataActvitiy;
import com.hotspr.ui.adapter.BaggageFindAdapter;
import com.hotspr.ui.bean.Xl;
import com.modulebase.HttpConfig;
import com.modulebase.log.LogF;
import com.modulebase.ui.fragment.BaseFragment;
import com.modulebase.view.recyclerview.adapter.LRecyclerViewAdapter;
import com.modulebase.view.recyclerview.view.LRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BaggageFindFragemnt extends BaseFragment implements View.OnClickListener , BaggageRegistrationAPI.View <Xl>, BaggageFindAdapter.OnItemClickListener {

    private String TAG = BaggageFindFragemnt.class.getSimpleName() ;

    private Context mContext ;
    private View mView ;
    private EditText nameEt ;
    private BaggageFindPressentre mPressentre ;
    private LRecyclerView mLRecyclerView ;
    private LRecyclerViewAdapter mLRecyclerViewAdapter ;
    private BaggageFindAdapter mAdapter ;

    private int listItem = 1 ;
    private int oneItem = 2 ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mView == null ){
            mContext = getContext();
            mView = LayoutInflater.from(mContext).inflate(R.layout.fragment_baggage_find_layout ,null);
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
    }

    private void initLRecyclerView(){
        mLRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2)); // 不设会不显示
        mAdapter = new BaggageFindAdapter(mContext);
        mAdapter.setOnItemClickListener(this);
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mAdapter);
        mLRecyclerView.setAdapter(mLRecyclerViewAdapter);
        mLRecyclerView.setPullRefreshEnabled(false);
        mLRecyclerView.setLoadMoreEnabled(false);
        mLRecyclerView.setHasFixedSize(true);                    //这是item的固定大小
    }

    private void initData(){
        mPressentre = new BaggageFindPressentre(mContext , this);
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
    public void upDatd(int mode , ArrayList<Xl> rounds , int pageNumber) {
        if(mode == listItem){
            mAdapter.addDatas(rounds);
        } else if(mode == oneItem && rounds!=null && rounds.size()==1){
            onItemClick(rounds.get(0));
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.baggage_layout){ // 登记按钮
            //隐藏键盘
            InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            if(imm!=null){
                imm.hideSoftInputFromWindow(nameEt.getWindowToken() , 0);
            }
            nameEt.setText("");
            nameEt.clearFocus();
            initScan();
        } else if(id == R.id.query_tv){
            if(TextUtils.isEmpty(nameEt.getText().toString())){
                Toast.makeText(mContext , "请输入关键字" , Toast.LENGTH_SHORT).show();
               return;
            }
            //隐藏键盘
            InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            if(imm!=null){
                imm.hideSoftInputFromWindow(nameEt.getWindowToken() , 0);
            }
            nameEt.clearFocus();
            load(nameEt.getText().toString() ,"" , listItem , 1);
        }
    }

    @Override
    public void onItemClick(Xl deposit) {
        Intent intent = new Intent(mContext , BaggageFindDataActvitiy.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(BaggageFindDataActvitiy.DATA_KEY , deposit);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 初始化扫码代码
     */
    private void initScan() {
        Intent intent = new Intent("com.summi.scan");
        intent.setPackage("com.sunmi.sunmiqrcodescanner");
        intent.putExtra("CURRENT_PPI", 0X0003);//当前分辨率
        //M1和V1的最佳是800*480,PPI_1920_1080 = 0X0001;PPI_1280_720 =
        //0X0002;PPI_BEST = 0X0003;
        intent.putExtra("PLAY_SOUND", true);// 扫描完成声音提示  默认true
        intent.putExtra("PLAY_VIBRATE", false);
        //扫描完成震动,默认false，目前M1硬件支持震动可用该配置，V1不支持
        intent.putExtra("IDENTIFY_INVERSE_QR_CODE", true);// 识别反色二维码，默认true
        intent.putExtra("IDENTIFY_MORE_CODE", false);// 识别画面中多个二维码，默认false
        intent.putExtra("IS_SHOW_SETTING", true);// 是否显示右上角设置按钮，默认true
        intent.putExtra("IS_SHOW_ALBUM", true);// 是否显示从相册选择图片按钮，默认true
        startActivityForResult(intent, 2);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && data != null) {
            Bundle bundle = data.getExtras();
            ArrayList<HashMap<String, String>> result = (ArrayList<HashMap<String, String>>) bundle.getSerializable("data");
            Iterator<HashMap<String, String>> it = result.iterator();
            LogF.i(TAG , "onActivityResult 扫码成功" );
            while (it.hasNext()) {
                HashMap<String, String> hashMap = it.next();
                String xl_id = hashMap.get("VALUE");
                LogF.i(TAG , "onActivityResult value " + xl_id );
                if(!TextUtils.isEmpty(xl_id)){
                    // 请求网络
                    load("" ,xl_id , oneItem , 1);
                    return;
                }
            }
        }
    }
}
