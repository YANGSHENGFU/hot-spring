package com.restaurant.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.modulebase.HttpConfig;
import com.modulebase.log.LogF;
import com.modulebase.ui.activity.BaseActivity;
import com.restaurant.R;
import com.restaurant.business.VarietyDishesAPI;
import com.restaurant.business.chinesefood.SetMealPressenter;
import com.restaurant.business.chinesefood.VarietyDishesPressenter;
import com.restaurant.toolkit.CacheHandle;
import com.restaurant.ui.adapter.FoodCategoryAdapter;
import com.restaurant.ui.adapter.FoodInfoAdapter;
import com.restaurant.ui.adapter.TcFoodInfoAdapter;
import com.restaurant.ui.bean.TableNumber;
import com.restaurant.ui.bean.TcVarietyDishes;
import com.restaurant.ui.bean.VarietyDishes;

import java.util.ArrayList;
import java.util.HashMap;

public class SetMealActivity extends BaseActivity implements VarietyDishesAPI.View<TcVarietyDishes> , TcFoodInfoAdapter.OnItemClickListener{
    private RecyclerView recColourInfoList;
    private SetMealPressenter mPressenter;
    private TableNumber tableNumber;
    public static String KEY = "KEY" ;
    private TcFoodInfoAdapter infoAdapter ;

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
    }

    private void initData(){
        mPressenter = new SetMealPressenter(this,this);

        tableNumber = getIntent().getExtras().getParcelable(KEY);
        TextView textView = findViewById(R.id.table_tv);
        textView.setText("台号:"+tableNumber.getCZBM()+"  人数:"+ tableNumber.getRS()+"  时间: "+(tableNumber.getKTRQ()==null?"":tableNumber.getKTRQ()));


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
    public void upDatd(int mode, ArrayList<TcVarietyDishes> tables, int pageNumber) {
        infoAdapter.addData(tables);
    }

    @Override
    public void onItmeClick(TcVarietyDishes vd, int i) {
    }
}
