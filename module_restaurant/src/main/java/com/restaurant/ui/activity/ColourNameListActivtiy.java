package com.restaurant.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.modulebase.HttpConfig;
import com.modulebase.log.LogF;
import com.modulebase.ui.activity.BaseActivity;
import com.restaurant.R;
import com.restaurant.business.VarietyDishesAPI;
import com.restaurant.business.chinesefood.VarietyDishesPressenter;
import com.restaurant.toolkit.CacheHandle;
import com.restaurant.ui.adapter.FoodCategoryAdapter;
import com.restaurant.ui.adapter.FoodInfoAdapter;
import com.restaurant.ui.bean.FoodCategory;
import com.restaurant.ui.bean.TableNumber;
import com.restaurant.ui.bean.VarietyDishes;

import java.util.ArrayList;
import java.util.HashMap;

public class ColourNameListActivtiy extends BaseActivity implements VarietyDishesAPI.View<VarietyDishes> ,
        FoodCategoryAdapter.OnItemClickListener ,FoodInfoAdapter.OnItemClickListener {


    private String TAG = "ColourNameListActivtiy" ;

    public static String KEY = "KEY" ;
    private RecyclerView recColourNameList;
    private RecyclerView recColourInfoList;
    private FoodCategoryAdapter foodNameAdapter;
    private VarietyDishesPressenter mPressenter;
    private TableNumber tableNumber;

    private FoodInfoAdapter infoAdapter ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colour_name_list_layout);
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
        recColourNameList = findViewById(R.id.rec_name_list);
        recColourInfoList = findViewById(R.id.rec_info_list);
        recColourNameList.setLayoutManager(new LinearLayoutManager(this));
        recColourInfoList.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initData(){
        mPressenter = new VarietyDishesPressenter(this,this);

        foodNameAdapter = new FoodCategoryAdapter(this);
        foodNameAdapter.setData(CacheHandle.foodCategoryCache);
        recColourNameList.setAdapter(foodNameAdapter);
        foodNameAdapter.setOnItemClickListener(this);
        tableNumber = getIntent().getExtras().getParcelable(KEY);
        if(CacheHandle.foodCategoryCache.size()!=0){
            HashMap<String,String> params = new HashMap<>();
            //params.put(HttpConfig.Field.zxbm , CacheHandle.foodCategoryCache.get(0).getBM());
            params.put(HttpConfig.Field.krbh , tableNumber.getKRBH());
            mPressenter.loadData(VarietyDishesAPI.LOAD_MODLE_REFRASH , 0 ,params );
        }

        infoAdapter = new FoodInfoAdapter(this);
        recColourInfoList.setAdapter(infoAdapter);
    }


    @Override
    public void upDatd(int mode, ArrayList<VarietyDishes> tables, int pageNumber) {
        infoAdapter.addData(tables);
    }


    @Override
    public void openTabelResult(VarietyDishes tabel) {

    }

    @Override
    public void onItmeClick(FoodCategory fc) {
        if(fc!=null){
            LogF.i(TAG , "onItmeClick bm = "+ fc.getBM());
            HashMap<String,String> params = new HashMap<>();
            //params.put(HttpConfig.Field.zxbm , fc.getBM());
            params.put(HttpConfig.Field.krbh , tableNumber.getKRBH());
            mPressenter.loadData(VarietyDishesAPI.LOAD_MODLE_REFRASH , 0 ,params );
        }
    }

    @Override
    public void onItmeClick(VarietyDishes vd, int i) {
        if(vd!=null){

        }
    }
}
