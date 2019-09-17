package com.restaurant.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hotspr.business.presenter.LoginPresenter;
import com.hotspr.toolkit.FileHandle;
import com.hotspr.ui.bean.User;
import com.modulebase.HttpConfig;
import com.modulebase.log.LogF;
import com.modulebase.ui.activity.BaseActivity;
import com.restaurant.R;
import com.restaurant.business.OrderAPI;
import com.restaurant.business.chinesefood.OrderPressenter;
import com.restaurant.toolkit.CacheHandle;
import com.restaurant.ui.adapter.GridViewAdapter;
import com.restaurant.ui.bean.VarietyDishes;

import java.util.HashMap;

public class OrderActivity extends BaseActivity implements View.OnClickListener, OrderAPI.View<VarietyDishes> {


    public static String KEY_VD = "KEY_VD";
    public static String KEY_I = "KEY_I";
    public static String KEY_KRBH = "KEY_KRBH";

    private EditText flavorEt;
    private GridView flavorList;
    private EditText handleEt;
    private GridView handleList;
    private TextView delTV;
    private TextView addTv;
    private TextView numberTv;
    private TextView orderTv;
    private LinearLayout flavor_ll, handle_ll;
    private GridViewAdapter mFlavorAdapter;
    private GridViewAdapter mHandleAdapter;

    private OrderPressenter mPressenter;

    private int mNumber = 1;
    private int i;
    private String krbh;
    private VarietyDishes vd;
    private User user;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_layout);
        findViewByID();
        init();

        if (vd.getFf_tag() != null && "t".equals(vd.getFf_tag())) {
            flavorList.setVisibility(View.VISIBLE);
            handleList.setVisibility(View.VISIBLE);
            flavor_ll.setVisibility(View.VISIBLE);
            handle_ll.setVisibility(View.VISIBLE);
        }
    }

    private void findViewByID() {
        findViewById(R.id.back_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        flavorEt = findViewById(R.id.flavor_et);
        flavorList = findViewById(R.id.flavor_list);
        handleEt = findViewById(R.id.handle_et);
        handleList = findViewById(R.id.handle_list);
        delTV = findViewById(R.id.del_tv);
        addTv = findViewById(R.id.add_tv);
        numberTv = findViewById(R.id.number_tv);
        orderTv = findViewById(R.id.order_tv);
        flavor_ll = findViewById(R.id.flavor_ll);
        handle_ll = findViewById(R.id.handle_ll);

        numberTv.setText(String.valueOf(mNumber));

        delTV.setOnClickListener(this);
        addTv.setOnClickListener(this);
        orderTv.setOnClickListener(this);
    }

    private void init() {
        Bundle bundle = getIntent().getExtras();
        vd = bundle.getParcelable(KEY_VD);
        i = bundle.getInt(KEY_I);
        krbh = bundle.getString(KEY_KRBH);
        mFlavorAdapter = new GridViewAdapter(this);
        flavorList.setAdapter(mFlavorAdapter);
        mFlavorAdapter.addDatas(CacheHandle.flavorCache);
        mHandleAdapter = new GridViewAdapter(this);
        handleList.setAdapter(mHandleAdapter);
        mHandleAdapter.addDatas(CacheHandle.processingMethodCache);


        flavorList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                flavorEt.setText(mFlavorAdapter.getItem(position));
            }
        });
        handleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                handleEt.setText(mHandleAdapter.getItem(position));
            }
        });
        mPressenter = new OrderPressenter(this, this);
        user = FileHandle.getUser();
        if (user == null) {
            user = LoginPresenter.mUser;
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.del_tv) {
            if (mNumber == 1) {
                mNumber = 1;
            } else {
                mNumber--;
            }
            numberTv.setText(String.valueOf(mNumber));
        } else if (id == R.id.add_tv) {
            mNumber++;
            numberTv.setText(String.valueOf(mNumber));
        } else if (id == R.id.order_tv) {

            if (mNumber <= 0) {

            } else {
                orderTv.setClickable(false);
                HashMap<String, String> params = new HashMap<>();
                params.put(HttpConfig.Field.cddm, vd.getCDDM());
                params.put(HttpConfig.Field.krbh, krbh);
                params.put(HttpConfig.Field.sl, String.valueOf(mNumber));
                params.put(HttpConfig.Field.skbh, user.getUser_id());
                params.put(HttpConfig.Field.skxm, user.getU_NAME());

                if (!TextUtils.isEmpty(flavorEt.getText().toString())) {
                    params.put(HttpConfig.Field.kw, flavorEt.getText().toString());
                }
                if (!TextUtils.isEmpty(handleEt.getText().toString())) {
                    params.put(HttpConfig.Field.zf, handleEt.getText().toString());
                }
                mPressenter.loadData(params);
            }
        }
    }

    @Override
    public void upDatd(VarietyDishes tables) {
        if (tables == null) {
            Toast.makeText(this, "点菜失败，请重试", Toast.LENGTH_SHORT).show();
            return;
        }
        orderTv.setClickable(false);
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ColourNameListActivtiy.KEY_VD, tables);
        bundle.putInt(ColourNameListActivtiy.KEY_I, i);
        intent.putExtras(bundle);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

}
