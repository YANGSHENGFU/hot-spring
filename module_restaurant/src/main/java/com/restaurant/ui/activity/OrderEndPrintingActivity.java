package com.restaurant.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.modulebase.HttpConfig;
import com.modulebase.log.Log;
import com.modulebase.log.LogF;
import com.modulebase.ui.activity.BaseActivity;
import com.restaurant.R;
import com.restaurant.business.OrderResultAPI;
import com.restaurant.business.chinesefood.OrderResultPressenter;
import com.restaurant.ui.adapter.OrdreResultAdapter;
import com.restaurant.ui.bean.OrderResult;
import com.restaurant.ui.bean.TableNumber;
import com.sunmi.utils.AidlUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderEndPrintingActivity extends BaseActivity implements  View.OnClickListener ,
        OrderResultAPI.View , OrdreResultAdapter.OnClickListener{


    public static String KEY_TN = "KEY_TN" ;

    private RecyclerView recView;
    private TextView prinitTv ;
    private OrdreResultAdapter mAdapter;
    private OrderResultPressenter mPressenter ;

    private TableNumber tableNumber ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderendprinting_layout);

        findViewByID();
        init();
    }

    private void findViewByID(){
        findViewById(R.id.back_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recView = findViewById(R.id.recyc_view);
        prinitTv = findViewById(R.id.print_tv);
        prinitTv.setOnClickListener(this);
    }

    private void init(){
        tableNumber = getIntent().getExtras().getParcelable(KEY_TN);
        mAdapter = new OrdreResultAdapter(this);
        recView.setLayoutManager(new LinearLayoutManager(this));
        recView.setAdapter(mAdapter);
        mAdapter.setOnClickListener(this);
        mPressenter = new OrderResultPressenter(this,this);


        HashMap<String ,String> params = new HashMap<>();
        params.put(HttpConfig.Field.krbh,tableNumber.getKRBH());

        mPressenter.loadData(OrderResultAPI.LOAD_MODLE_REFRASH , 0 , params);
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        if (id == R.id.print_tv) {
            // 调用打印方法
            String str_title = String.format("%s,名称:%s,台号:%s", tableNumber.getCTMC(), tableNumber.getCZMC(), tableNumber.getCZBM());
            AidlUtil.getInstance().printText5(str_title, 30, false, false, 1);
            String str_print_content = "";
            for (OrderResult data_item : mAdapter.datas) {
                str_print_content = str_print_content + data_item.getSL() + " X ";
                str_print_content = str_print_content + data_item.getMC();

//            str_print_content=str_print_content+String.format("%-13S X ",data_item.getMC());
//            str_print_content=str_print_content+data_item.getSL();

                str_print_content = str_print_content + "\r\n";
                //  str_print_content=str_print_content
                //AidlUtil.getInstance().printText(data_item.getSL() , 30 , false , false );
            }
            AidlUtil.getInstance().printText5(str_print_content, 25, false, false, 0);
            // AidlUtil.getInstance().printText("预约号：\r\n夏洪" , 30 , false , false );

            AidlUtil.getInstance().print5Line();
        }
    }

    @Override
    public void onClickPintItem(OrderResult ordata, int position) {
        String str_title = String.format("%s,台号:%s", ordata.getCTMC(), ordata.getCZDM());
        AidlUtil.getInstance().printText5(str_title, 30, false, false, 1);

        String str_print_content = "";

        str_print_content = str_print_content + ordata.getSL() + " X ";
        str_print_content = str_print_content + ordata.getMC();
        str_print_content = str_print_content + "\r\n";
        str_print_content = str_print_content + "\r\n";
        str_print_content = str_print_content + "\r\n";
        AidlUtil.getInstance().printText5(str_print_content, 25, false, false, 0);
    }

    @Override
    public void onClickPintItem() {
        // 调用打印方法
        String str_title = String.format("%s,名称:%s,台号:%s", tableNumber.getCTMC(), tableNumber.getCZMC(), tableNumber.getCZBM());
        AidlUtil.getInstance().printText5(str_title, 30, false, false, 1);
    }

    @Override
    public void upDatd(int mode, ArrayList tables, int pageNumber) {
        if(tables==null){
            LogF.i("TAG", "获取数据失败");
            return;
        }
        mAdapter.addDatas(tables);
    }
}
