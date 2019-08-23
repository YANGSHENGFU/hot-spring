package com.restaurant.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.modulebase.HttpConfig;
import com.modulebase.log.LogF;
import com.modulebase.okhttp.JsonResponseHandler;
import com.modulebase.okhttp.MyOkHttp;
import com.modulebase.toolkit.SharepreFHelp;
import com.modulebase.toolkit.sort.SortTools;
import com.modulebase.ui.activity.BaseActivity;
import com.restaurant.R;
import com.restaurant.business.OrderResultAPI;
import com.restaurant.business.chinesefood.OrderResultPressenter;
import com.restaurant.ui.adapter.OrdreResultAdapter;
import com.restaurant.ui.bean.OrderResult;
import com.restaurant.ui.bean.TableNumber;
import com.sunmi.utils.AidlUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class OrderEndPrintingActivity extends BaseActivity implements  View.OnClickListener ,
        OrderResultAPI.View , OrdreResultAdapter.OnClickListener{


    public static String KEY_TN = "KEY_TN" ;

    private RecyclerView recView;
    private TextView prinitTv ;
    private TextView sendkitchenTv ;
    private TextView statusTv ;
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
        sendkitchenTv = findViewById(R.id.sendkitchen_tv);
        statusTv = findViewById(R.id.status_tv);
        prinitTv.setOnClickListener(this);
        sendkitchenTv.setOnClickListener(this);
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
        }else if (id == R.id.sendkitchen_tv) {
            sendKitchen();
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

    /**
     * 送厨房
     */
    public void sendKitchen(){
        String url = HttpConfig.HOST_NAME + HttpConfig.INTERFACE_SEND_KITCHEN;
        String userid = SharepreFHelp.getInstance(this).getUserID();
        String userName = SharepreFHelp.getInstance(this).getUserName();
        String userkey = SharepreFHelp.getInstance(this).getUserKey();
        Map<String, String> paer = new HashMap<>();
        paer.put(HttpConfig.Field.mid, userid);
        paer.put(HttpConfig.Field.key, userkey);
        // 额外的条件
        HashMap<String ,String> params = new HashMap<>();
        params.put(HttpConfig.Field.krbh,tableNumber.getKRBH());
        params.put(HttpConfig.Field.jdxm,userName);
        if ( params!=null && !params.isEmpty() ){
            for (Map.Entry<String, String> entry : params.entrySet()){
                paer.put(entry.getKey(),entry.getValue());
            }
        }
        paer.put(HttpConfig.Field.timestamp, String.valueOf(System.currentTimeMillis() / 1000));
        Set<String> keySet = paer.keySet();  //获取set集合
        List<String> sortKey = SortTools.listSort(keySet);
        TreeMap<String, String> parameter = SortTools.getSortMap(sortKey, paer);

        MyOkHttp.get().get(this, url, parameter, new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                //LogF.i(TAG , "onSuccess statusCode = "+ statusCode + " statusCode = "+ response);
                TableNumber table= null ;
                try {
                    JSONObject resObj = new JSONObject(response);
                    statusTv.setVisibility(View.VISIBLE);
                    if (resObj.getString("errCode").equals("200")) {
                        statusTv.setTextColor(Color.BLUE);
                        statusTv.setText("已送厨房！");
                    }else{
                        LogF.i("TAG", "送厨房失败："+resObj.getString("errCode"));
                        statusTv.setText("送厨房失败！");
                        statusTv.setTextColor(Color.RED);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                }
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {
                Log.i("SendKitchen", "onFailure statusCode = " + statusCode + " error_msg = " + error_msg);
                statusTv.setVisibility(View.VISIBLE);
                statusTv.setText("送厨房失败！");
                statusTv.setTextColor(Color.RED);
            }
        });
    }
}
