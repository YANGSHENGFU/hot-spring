package com.restaurant.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.modulebase.HttpConfig;
import com.modulebase.log.LogF;
import com.modulebase.ui.activity.BaseActivity;
import com.restaurant.R;
import com.restaurant.business.VarietyDishesAPI;
import com.restaurant.business.SetMealAPI;
import com.restaurant.business.chinesefood.VarietyDishesPressenter;
import com.restaurant.toolkit.CacheHandle;
import com.restaurant.ui.adapter.FoodCategoryAdapter;
import com.restaurant.ui.adapter.FoodInfoAdapter;
import com.restaurant.ui.bean.FoodCategory;
import com.restaurant.ui.bean.TableNumber;
import com.restaurant.ui.bean.VarietyDishes;
import com.restaurant.ui.bean.SetMeal;
import java.util.ArrayList;
import java.util.HashMap;

public class ColourNameListActivtiy extends BaseActivity implements VarietyDishesAPI.View<VarietyDishes> , SetMealAPI.View<SetMeal>,
        FoodCategoryAdapter.OnItemClickListener ,FoodInfoAdapter.OnItemClickListener {

    public static String KEY_VD = "KEY_VD";
    public static String KEY_I = "KEY_I";


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
        mPressenter = new VarietyDishesPressenter(this,this,this);

        foodNameAdapter = new FoodCategoryAdapter(this);
        foodNameAdapter.setData(CacheHandle.foodCategoryCache);
        recColourNameList.setAdapter(foodNameAdapter);
        foodNameAdapter.setOnItemClickListener(this);
        tableNumber = getIntent().getExtras().getParcelable(KEY);
        TextView textView = findViewById(R.id.table_tv);
        textView.setText("台号:"+tableNumber.getCZBM()+"  人数:"+ tableNumber.getRS()+"  时间: "+(tableNumber.getKTRQ()==null?"":tableNumber.getKTRQ()));


        if(CacheHandle.foodCategoryCache.size()!=0){
            HashMap<String,String> params = new HashMap<>();
            //params.put(HttpConfig.Field.zxbm , CacheHandle.foodCategoryCache.get(0).getBM());
            params.put(HttpConfig.Field.krbh , tableNumber.getKRBH());
            mPressenter.loadData(VarietyDishesAPI.LOAD_MODLE_REFRASH , 0 ,params );
        }

        infoAdapter = new FoodInfoAdapter(this);
        recColourInfoList.setAdapter(infoAdapter);
        infoAdapter.setListener(this);
    }


    @Override
    public void upDatd(int mode, ArrayList<VarietyDishes> tables, int pageNumber) {
        infoAdapter.addData(tables);
    }

    @Override
    public void upDatd2(int mode, ArrayList<SetMeal> tables, int pageNumber) {
        infoAdapter.addSetMeal(tables);
    }

    @Override
    public void onItmeClick(FoodCategory fc) {
         if(fc!=null){
            LogF.i(TAG , "onItmeClick bm = "+ fc.getBM());
            HashMap<String,String> params = new HashMap<>();
            //Toast.makeText(this,fc.getBM(),Toast.LENGTH_LONG).show();
            params.put("zxdm" , fc.getBM());
            params.put(HttpConfig.Field.krbh , tableNumber.getKRBH());
            params.put("zxmc" , fc.getMC());
            mPressenter.loadData(VarietyDishesAPI.LOAD_MODLE_REFRASH , 0 ,params );
        }
    }

    @Override
    public void onItmeClick(Object vd, int i, boolean isSetMeal) {
        if(vd!=null&&!isSetMeal){
            Intent intent = new Intent(this,OrderActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable(OrderActivity.KEY_VD , (VarietyDishes)vd);
            bundle.putInt(OrderActivity.KEY_I , i);
            bundle.putString(OrderActivity.KEY_KRBH, tableNumber.getKRBH());
            intent.putExtras(bundle);
            startActivityForResult(intent , 500);
        }else if(vd!=null&&isSetMeal){
            Intent intent = new Intent(this,SetMealActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable(SetMealActivity.KEY_VD , (SetMeal)vd);
            bundle.putParcelable(SetMealActivity.KEY , tableNumber);
            bundle.putInt(OrderActivity.KEY_I , i);
            bundle.putString(OrderActivity.KEY_KRBH, tableNumber.getKRBH());
            intent.putExtras(bundle);
            startActivityForResult(intent , 500);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 500 && resultCode == Activity.RESULT_OK){
            if(data!=null){
                Bundle bundle = data.getExtras() ;
                int i = bundle.getInt(KEY_I);
                VarietyDishes vd = bundle.getParcelable(KEY_VD);
                infoAdapter.upData(vd, i);
            }
        }
    }
}
