package com.restaurant.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.modulebase.HttpConfig;
import com.modulebase.log.LogF;
import com.modulebase.okhttp.JsonResponseHandler;
import com.modulebase.okhttp.MyOkHttp;
import com.modulebase.toolkit.SharepreFHelp;
import com.modulebase.toolkit.sort.SortTools;
import com.modulebase.ui.activity.BaseActivity;
import com.restaurant.R;
import com.restaurant.business.VarietyDishesAPI;
import com.restaurant.business.chinesefood.SetMealPressenter;
import com.restaurant.business.chinesefood.VarietyDishesPressenter;
import com.restaurant.toolkit.CacheHandle;
import com.restaurant.ui.adapter.FoodCategoryAdapter;
import com.restaurant.ui.adapter.FoodInfoAdapter;
import com.restaurant.ui.adapter.TcFoodInfoAdapter;
import com.restaurant.ui.bean.SetMeal;
import com.restaurant.ui.bean.TableNumber;
import com.restaurant.ui.bean.TcVarietyDishes;
import com.restaurant.ui.bean.VarietyDishes;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class SetMealActivity extends BaseActivity implements View.OnClickListener, VarietyDishesAPI.View<TcVarietyDishes> , TcFoodInfoAdapter.OnItemClickListener{
    private RecyclerView recColourInfoList;
    private SetMealPressenter mPressenter;
    private TableNumber tableNumber;
    private SetMeal setMeal;
    public static String KEY_VD = "KEY_VD";
    public static String KEY = "KEY" ;
    private TcFoodInfoAdapter infoAdapter ;
    private TextView sendkitchenTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_meal);

        findViewByID();
        initData();
    }

    private void findViewByID(){
        findViewById(R.id.back_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recColourInfoList = findViewById(R.id.rec_info_list);
        recColourInfoList.setLayoutManager(new LinearLayoutManager(this));

        sendkitchenTv = findViewById(R.id.sendkitchen_tv);
        sendkitchenTv.setOnClickListener(this);
    }

    private void initData(){
        mPressenter = new SetMealPressenter(this,this);

        tableNumber = getIntent().getExtras().getParcelable(KEY);
        TextView textView = findViewById(R.id.table_tv);
        textView.setText("台号:"+tableNumber.getCZBM()+"  人数:"+ tableNumber.getRS()+"  时间: "+(tableNumber.getKTRQ()==null?"":tableNumber.getKTRQ()));
        setMeal = getIntent().getExtras().getParcelable(KEY_VD);


        HashMap<String,String> params = new HashMap<>();
        //params.put(HttpConfig.Field.zxbm , CacheHandle.foodCategoryCache.get(0).getBM());
        params.put(HttpConfig.Field.krbh , tableNumber.getKRBH());
        params.put(HttpConfig.Field.tcno , "1");
        mPressenter.loadData(VarietyDishesAPI.LOAD_MODLE_REFRASH , 0 ,params );

        infoAdapter = new TcFoodInfoAdapter(this);
        recColourInfoList.setAdapter(infoAdapter);
        infoAdapter.setListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.sendkitchen_tv) {
            sendKitchen();
            //finish();
        }
    }

    @Override
    public void upDatd(int mode, ArrayList<TcVarietyDishes> tables, int pageNumber) {
        infoAdapter.addData(tables);
    }

    @Override
    public void onItmeClick(TcVarietyDishes vd, int i) {
        if(1==vd.getSelectNum())
            vd.setSelectNum(0);
        else
            vd.setSelectNum(1);
        infoAdapter.upData(vd, i);
    }

    /**
     * 送厨房
     */
    public void sendKitchen() {
        String url = HttpConfig.HOST_NAME + HttpConfig.INTERFACE_FOODCH_SEND_KITCHEN;
        String userid = SharepreFHelp.getInstance(this).getUserID();
        String userName = SharepreFHelp.getInstance(this).getUserName();
        String userkey = SharepreFHelp.getInstance(this).getUserKey();
        Map<String, String> paer = new HashMap<>();
        paer.put(HttpConfig.Field.mid, userid);
        paer.put(HttpConfig.Field.key, userkey);
        // 额外的条件
        HashMap<String, String> params = new HashMap<>();
        params.put(HttpConfig.Field.krbh, tableNumber.getKRBH());
        params.put(HttpConfig.Field.skbh, userid);
        params.put(HttpConfig.Field.skxm, userName);
        params.put(HttpConfig.Field.tcno, setMeal.getTc_no());
        params.put("sl", "1");
        //cddm
        ArrayList<TcVarietyDishes> datas;
        datas = infoAdapter.getDatas();
        String cddms = "";
        boolean isSelected = false;
        String ZS="0";
        int SL=0;
        for(TcVarietyDishes vd: datas){
            if(ZS.equals(vd.getZS())){
                SL+=vd.getSelectNum();
                if(SL>Integer.parseInt(ZS)){
                    Toast.makeText(this,"选择数量超出最大值!",Toast.LENGTH_LONG).show();
                    return;
                }
            }else{
                if("0".equals(ZS)){
                    SL+=vd.getSelectNum();
                }else{
                    if(SL<Integer.parseInt(ZS)){
                        Toast.makeText(this,"选择数量不足!",Toast.LENGTH_LONG).show();
                        return;
                    }
                }
                ZS=vd.getZS();
            }

            if(1==vd.getSelectNum()) {
                cddms = cddms + vd.getCDDM() + ",";
            }
        }
        if(!"".equals(cddms))
            cddms = cddms.substring(0,cddms.length()-1);
        params.put(HttpConfig.Field.cddm, cddms);

        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                paer.put(entry.getKey(), entry.getValue());
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
                TableNumber table = null;
                try {
                    JSONObject resObj = new JSONObject(response);
//                    statusTv.setVisibility(View.VISIBLE);
                    if (resObj.getString("errCode").equals("200")) {
                        Toast.makeText(getApplicationContext(),"已送厨房！",Toast.LENGTH_LONG).show();
                        finish();
//                        statusTv.setTextColor(Color.BLUE);
//                        statusTv.setText("已送厨房！");
                    } else {
                        LogF.i("TAG", "送厨房失败：" + resObj.getString("errCode"));
                        Toast.makeText(getApplicationContext(),"送厨房失败！",Toast.LENGTH_LONG).show();
//                        statusTv.setText("送厨房失败！");
//                        statusTv.setTextColor(Color.RED);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                Log.i("SendKitchen", "onFailure statusCode = " + statusCode + " error_msg = " + error_msg);
                Toast.makeText(getApplicationContext(),"送厨房失败！",Toast.LENGTH_LONG).show();
            }
        });
    }

}
