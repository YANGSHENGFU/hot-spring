package com.restaurant.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import com.hotspr.toolkit.FileHandle;
import com.hotspr.ui.bean.User;
import com.modulebase.HttpConfig;
import com.modulebase.log.LogF;
import com.modulebase.toolkit.NetworkUtils;
import com.modulebase.ui.activity.BaseActivity;
import com.modulebase.ui.adapter.SpinnerPopAdapter;
import com.modulebase.view.recyclerview.adapter.LRecyclerViewAdapter;
import com.modulebase.view.recyclerview.recinterface.OnLoadMoreListener;
import com.modulebase.view.recyclerview.recinterface.OnRefreshListener;
import com.modulebase.view.recyclerview.view.LRecyclerView;
import com.restaurant.R;
import com.restaurant.business.TableNumberAPI;
import com.restaurant.business.chinesefood.TableNumberPressenter;
import com.restaurant.business.common.PublicInfoHandle;
import com.restaurant.toolkit.CacheHandle;
import com.restaurant.ui.adapter.DeskNumberAdapter;
import com.restaurant.ui.bean.TableNumber;
import com.restaurant.ui.dialog.OpneTableDialog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DeskNumberActivity extends BaseActivity implements View.OnClickListener , TableNumberAPI.View<TableNumber> , DeskNumberAdapter.OnClickListener {

    private PublicInfoHandle mPublicInfoHandle;
    private EditText regionEt;
    private ImageView regionImg;
    private TextView searchTv;
    private LRecyclerView mLRecyclerView ;
    private TableNumberPressenter mPressenter;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private DeskNumberAdapter mAdapter;
    private int page = 1;
    private int TOLTE_PAGE_NUMBER;
    private OpneTableDialog mDialog;
    private User user ;
    private TableNumber mTableNumber;
    private int mPosition ;// 当前点击的项

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desk_number_layout);
        initData();
        findViewById();
        intiRecycView();
        loadData(TableNumberAPI.LOAD_MODLE_REFRASH , "" , page);
        intiDialog();
    }

    private void findViewById() {
        // 关闭界面
        findViewById(R.id.back_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        regionEt = findViewById(R.id.region_et);
        regionImg = findViewById(R.id.region_img);
        searchTv = findViewById(R.id.search_tv);
        mLRecyclerView = findViewById(R.id.recycler_view);
        regionImg.setOnClickListener(this);
        searchTv.setOnClickListener(this);
    }

    private void initData() {
        mPublicInfoHandle = new PublicInfoHandle(this);
        Map<String, String> map = new HashMap<>();
        map.put(HttpConfig.Field.ctbm, "1");
        mPublicInfoHandle.getRegion(this, map); // 获取区域
        mPublicInfoHandle.getFlavor(this, null);// 获取口味
        mPublicInfoHandle.getProcessingMethod(this, null); // 获取加工方法
        mPublicInfoHandle.getChinFoodCalss(this , null); // 获取菜品类别
        mPressenter = new TableNumberPressenter(this ,this);
        user = FileHandle.getUser();
    }


    private void intiRecycView(){
        mLRecyclerView.setLayoutManager(new GridLayoutManager(this, 4)); // 不设会不显示
        mAdapter = new DeskNumberAdapter(this);
        mAdapter.setOnClickListener(this);
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
                if (!NetworkUtils.isNetworkConnected(DeskNumberActivity.this)) {
                    mLRecyclerView.refreshComplete(0);  //不调用这句就表示没有刷新成功(不会回上去)
                    mAdapter.notifyDataSetChanged();
                    Toast.makeText(DeskNumberActivity.this, "当前网络不可用,请检查网络设置", Toast.LENGTH_SHORT).show();
                    return;
                }
                //mAdapter.upData(new ArrayList<Round>()); // 清空数据
                page = 1;
                loadData(TableNumberAPI.LOAD_MODLE_REFRASH,regionEt.getText().toString(),page);
            }
        });
        // 加载更多
        mLRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (page < TOLTE_PAGE_NUMBER) {
                    loadData(TableNumberAPI.LOAD_MODLE_MORE ,regionEt.getText().toString(),++page);
                } else {
                    mLRecyclerView.setNoMore(true); //没有更多了
                }
            }
        });
    }

    /**
     * 开台
     */
    private void intiDialog(){
        mDialog = new OpneTableDialog(this);
        mDialog.setCancelable(true);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.getOkText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(mDialog.getNumberString()) || mDialog.getNumberString().startsWith("0")){
                    Toast.makeText(DeskNumberActivity.this , "请输入就餐人数", Toast.LENGTH_SHORT).show();
                } else {
                    mDialog.setOkTv(false);
                    Map<String, String> map = new HashMap<>();
                    map.put(HttpConfig.Field.czbm , mTableNumber.getCZBM());
                    map.put(HttpConfig.Field.czmc , mTableNumber.getCZMC());
                    map.put(HttpConfig.Field.pzrdm , user.getU_NAME());
                    map.put(HttpConfig.Field.rs , mDialog.getNumberString());//就餐人数
                    mPressenter.openTable(map); // 台开
                }
            }
        });
    }


   @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id ==R.id.region_img){
            initrtypePopupWindow();
        } else if(id ==R.id.search_tv){
            page = 1; // 复位
            loadData(TableNumberAPI.LOAD_MODLE_REFRASH,regionEt.getText().toString(),page);
        }
    }

    /**
     * 区域选择框
     */
    private void initrtypePopupWindow() {
        View view = LayoutInflater.from(this).inflate(R.layout.popuwindow_open_place_layout, null);
        final PopupWindow regionPopupWindow = new PopupWindow(view, regionEt.getMeasuredWidth() , ViewGroup.LayoutParams.WRAP_CONTENT);
        regionPopupWindow.setOutsideTouchable(true);
        ListView list = view.findViewById(R.id.listView);
        final SpinnerPopAdapter regionTypeAdapter = new SpinnerPopAdapter(this);
        regionTypeAdapter.setDatas(CacheHandle.regionCache);
        list.setAdapter(regionTypeAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String rtype = regionTypeAdapter.getContent(position);
                regionEt.setText(rtype);
                if(!TextUtils.isEmpty(rtype)){
                    regionEt.setSelection(rtype.length());
                }
                regionEt.clearFocus();
                regionPopupWindow.dismiss();
            }
        });
        regionPopupWindow.showAsDropDown(regionEt,10,10);
    }


    /**
     * 加载数据
     */
    private void loadData(int mode , String tws , int page){
       if(!TextUtils.isEmpty(tws)){
            Map<String, String> map = new HashMap<>();
            map.put(HttpConfig.Field.tws, tws);
            mPressenter.loadData(mode,page,map);
        } else {
            mPressenter.loadData(mode,page,null);
        }
    }

    /**
     * 数据跟新
     * @param mode
     * @param rounds
     * @param pageNumber
     */
    @Override
    public void upDatd(int mode , ArrayList<TableNumber> rounds , int pageNumber) {
        if(rounds != null) {
            mLRecyclerView.refreshComplete(rounds.size());  // 不调用这句方法就表示没有刷新成功
            if(mode == TableNumberAPI.LOAD_MODLE_REFRASH){
                mAdapter.upData(rounds);
            } else {
                mAdapter.addData(rounds);
            }
            if(pageNumber >= 0) {
                TOLTE_PAGE_NUMBER = pageNumber;
            }
        }else{
            mLRecyclerView.refreshComplete(0);  // 不调用这句方法就表示没有刷新成功
            mAdapter.upData(new ArrayList<TableNumber>());
            if(mode == TableNumberAPI.LOAD_MODLE_REFRASH){
                page = 1; // 复位
            } else {
                page--;
            }
        }
    }

    /**
     * 开台结果
     * @param tabel
     */
    @Override
    public void openTabelResult(TableNumber tabel) {
        if(mDialog!=null && mDialog.isShowing()){
            mDialog.dismiss();
        }
        mDialog.setOkTv(true);
        LogF.i("DeskNumberActivity", "TableNumber "+ tabel!=null?tabel.toString():"kong");
        if(tabel!=null && tabel.getCZZT().equals("I")){
            mAdapter.onUpData(mPosition , tabel);
            Intent intent = new Intent(this, ColourNameListActivtiy.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable(ColourNameListActivtiy.KEY , tabel);
            intent.putExtras(bundle);
            startActivity(intent);
            // 进入点菜
        }
    }

    @Override
    public void onClickOpenOrder(final TableNumber tableNumber , int position) {
        mPosition = position ;
        if(tableNumber!=null){
            if(tableNumber.getCZZT().equals("V")){ // 开台
                mTableNumber = tableNumber ;
                mDialog.show();
            }  else if(tableNumber.getCZZT().equals("I")){ // 点菜
                Intent intent = new Intent(this, ColourNameListActivtiy.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(ColourNameListActivtiy.KEY , tableNumber);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }
    }

    @Override
    public void onItemClick(TableNumber tableNumber , int position) {
        if(tableNumber!=null && tableNumber.getCZZT().equals("I")){
            Intent intent = new Intent(this, OrderEndPrintingActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable(OrderEndPrintingActivity.KEY_TN , tableNumber);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}
