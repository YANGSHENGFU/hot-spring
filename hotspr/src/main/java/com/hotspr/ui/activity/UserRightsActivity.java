package com.hotspr.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.hotspr.HttpConfig;
import com.hotspr.R;
import com.hotspr.business.presenter.LoginPresenter;
import com.hotspr.toolkit.FileHandle;
import com.hotspr.toolkit.SharepreFHelp;
import com.hotspr.ui.bean.User;
import com.modulebase.log.LogF;
import com.modulebase.okhttp.JsonResponseHandler;
import com.modulebase.okhttp.MyOkHttp;
import com.hotspr.toolkit.CacheHandle;
import com.modulebase.toolkit.sort.SortTools;
import com.modulebase.ui.activity.BaseActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class UserRightsActivity extends BaseActivity implements View.OnClickListener{

    private String TAG = "UserRightsActivity" ;

    private TextView backTv ;
    private GridView menuGv ;
    private User user ;
    ArrayList<Menu> menus = new ArrayList<>() ;
    private MenuAdapter menuAdapter ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState) ;
        setContentView(R.layout.activity_user_rights_layout);
        backTv = findViewById(R.id.back_tv) ;
        menuGv = findViewById(R.id.menu_gv) ;
        backTv.setOnClickListener(this) ;
        menuGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position>=0 && position<menus.size()){
                    Menu menu = menus.get(position);
                    if(menu==null){
                        return;
                    }
                    if(menu.title.equals("查看房间")){
                        goChekRoom() ;
                    } else if(menu.title.equals("清洁房间")){
                        goCleanRoom() ;
                    } else if(menu.title.equals("安排清洁")){
                        goArrangeCleaning() ;
                    } else if(menu.title.equals("行李登记")){
                        goBaggageRegistra() ;
                    } else if(menu.title.equals("行李查寻")){
                        goBaggageFind() ;
                    }
                }
            }
        });
        initData();
        loadingBaseDataArr();
    }

    private void initData(){
        user = FileHandle.getUser() ;

        if(user==null){
            user = LoginPresenter.mUser ;
        }
        if(user != null){
            menuAdapter = new MenuAdapter(this);
            if(user.getHOTEL().equals("T")){
                Menu menu = new Menu();
                menu.rid = R.drawable.d_ward_round ;
                menu.title = "查看房间";
                menus.add(menu);
         }
            if(user.getC_NAME().equals("客房保洁")){
                Menu menu = new Menu();
                menu.rid = R.drawable.d_cleaning ;
                menu.title = "清洁房间";
                menus.add(menu);
            } else {
                Menu menu = new Menu();
                menu.rid = R.drawable.d_arrange_cleaning ;
                menu.title = "安排清洁";
                menus.add(menu);
            }

            if(user.getHOTEL().equals("T")){


                Menu m1 = new Menu();
                m1.rid = R.drawable.bagregi ;
                m1.title = "行李登记";
                menus.add(m1);
//
                Menu m2 = new Menu();
                m2.rid = R.drawable.bagfind ;
                m2.title = "行李查寻";
                menus.add(m2);
            }
            menuGv.setAdapter(menuAdapter);
            menuAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.back_tv){
            finish();
        }
    }


    /**
     * 查看房间
     */
    private void goChekRoom(){
        Intent intent = new Intent(this, WardRoundListInfoActivity.class);
        startActivity(intent);
    }

    /**
     * 清洁房间
     */
    private void goCleanRoom(){
        Intent intent = new Intent(this , CleanRoomListIfnoActivity.class);
        startActivity(intent);

    }

    /**
     * 安排清洁
     */
    private void goArrangeCleaning(){
        Intent intent = new Intent(this ,  ArrangeCleanListInfoActivity.class);
        startActivity(intent);
    }

    /**
     * 行李登记
     */
    private void goBaggageRegistra(){
        Intent intent = new Intent(this ,  BaggageRegistrationActivity.class);
        startActivity(intent);
    }

    /**
     * 行李查询
     */
    private void goBaggageEnquiry(){
        Intent intent = new Intent(this ,  BaggageEnquiryActivity.class);
        startActivity(intent);
    }
    /**
     * 行李登记
     */
    private void goBaggageFind(){
        Intent intent = new Intent(this ,  BaggageFindActivity.class);
        startActivity(intent);
    }


    /**
     * 适配器类
     */
    class MenuAdapter extends BaseAdapter{

        private Context context ;


        public MenuAdapter(Context context){
            this.context = context ;
        }

        @Override
        public int getCount() {
            return menus!=null?menus.size():0;
        }

        @Override
        public Menu getItem(int position) {
            return menus.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position ;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHodle viewHodle = null ;
            if(convertView == null ){
                convertView = LayoutInflater.from(context).inflate(R.layout.item_grid_user_menu_layout , parent , false);
                viewHodle = new ViewHodle(convertView);
                convertView.setTag(viewHodle);
            }else{
                viewHodle = (ViewHodle) convertView.getTag();
            }
            viewHodle.img.setImageResource(menus.get(position).rid);
            viewHodle.tv.setText(menus.get(position).title);
            return convertView ;
        }

        class ViewHodle{
            ImageView img  ;
            TextView tv ;
            ViewHodle(View view){
                img = view.findViewById(R.id.img);
                tv = view.findViewById(R.id.tv);
            }
        }
    }

    class Menu {

        int rid ;
        String title ;
        String tage ;
    }

    /**
     * 加载基本数据集，房型，楼号，清洁人员
     */
    public  void loadingBaseDataArr(){

        String url = HttpConfig.HOST_NAME + HttpConfig.INTERFACE_floorList;

        String userid = SharepreFHelp.getInstance(this).getUserID();
        String userkey = SharepreFHelp.getInstance(this).getUserKey();

        Map<String, String> paer = new HashMap<>();
        paer.put(HttpConfig.Field.mid, userid);
        paer.put(HttpConfig.Field.key, userkey);
        paer.put(HttpConfig.Field.timestamp, String.valueOf(System.currentTimeMillis() / 1000));

        Set<String> keySet = paer.keySet();  //获取set集合
        List<String> sortKey = SortTools.listSort(keySet);
        TreeMap<String, String> parameter = SortTools.getSortMap(sortKey, paer);
        //楼号开始
        MyOkHttp.get().get(this, url, parameter, new JsonResponseHandler() {
            @Override
            public void onFailure(int statusCode, String error_msg) {
                LogF.e(TAG , "获取楼号 onFailure statusCode = "+ statusCode + " error_msg = "+ error_msg);
            }
            @Override
            public void onSuccess(int statusCode, String response) {
                try {
                    LogF.i(TAG , "获取楼号 onSuccess statusCode = "+ statusCode + " response = "+ response);
                    JSONObject resObj = new JSONObject(response);
                    if (resObj.getString("errCode").equals("200")) {
                        JSONArray resDataList = resObj.getJSONArray("DataList");
                        CacheHandle.buildingNumberCach.clear();
                        CacheHandle.buildingNumberCach.add("");
                        for (int i = 0; i < resDataList.length(); i++) {
                            JSONObject res = (JSONObject) resDataList.get(i);
                            CacheHandle.buildingNumberCach.add(res.getString("FLOOR"));
                        }
                    }else{
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        //房型开始
        url = HttpConfig.HOST_NAME + HttpConfig.INTERFACE_rpriceList;
        keySet = paer.keySet();  //获取set集合
        sortKey = SortTools.listSort(keySet);
        parameter = SortTools.getSortMap(sortKey, paer);
        MyOkHttp.get().get(this , url , parameter , new JsonResponseHandler() {
            @Override
            public void onFailure(int statusCode , String error_msg) {
                LogF.e(TAG , "获取房型 onFailure statusCode = "+ statusCode + " error_msg = "+ error_msg);
            }
            @Override
            public void onSuccess(int statusCode, String response) {
                try {
                    LogF.i(TAG , "获取房型 onSuccess statusCode = "+ statusCode + " response = "+ response);
                    JSONObject resObj = new JSONObject(response);
                    if (resObj.getString("errCode").equals("200")) {
                        JSONArray resDataList = resObj.getJSONArray("DataList");
                        CacheHandle.roomTypeCach.clear();
                        CacheHandle.roomTypeCach.add("");
                        for (int i = 0; i < resDataList.length(); i++) {
                            JSONObject res = (JSONObject) resDataList.get(i);
                            CacheHandle.roomTypeCach.add(res.getString("CLASS"));
                        }
                    }else{
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        //清洁人员开始
        url = HttpConfig.HOST_NAME + HttpConfig.INTERFACE_GetUserList;
       // paer.put("authcode", "HOTEL");
        paer.put("staff", "Y");
        keySet = paer.keySet();  //获取set集合
        sortKey = SortTools.listSort(keySet);
        parameter = SortTools.getSortMap(sortKey , paer);

        MyOkHttp.get().get(this.getBaseContext() , url , parameter , new JsonResponseHandler() {
            @Override
            public void onFailure(int statusCode, String error_msg) {
                LogF.e(TAG , "获取清洁人员 onFailure statusCode = "+ statusCode + " error_msg = "+ error_msg);
            }
            @Override
            public void onSuccess(int statusCode, String response) {
                try {
                    LogF.i(TAG , "获取清洁人员 onSuccess statusCode = "+ statusCode + " response = "+ response);
                    JSONObject resObj = new JSONObject(response);
                    if (resObj.getString("errCode").equals("200")) {
                        JSONArray resDataList = resObj.getJSONArray("DataList");
                        CacheHandle.cleanerCach.clear();
                        CacheHandle.cleanerCach.add("");
                        for (int i = 0; i < resDataList.length(); i++) {
                            JSONObject res = (JSONObject) resDataList.get(i);
                            CacheHandle.cleanerCach.add(res.getString("U_NAME"));
                        }
                    }else{
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
