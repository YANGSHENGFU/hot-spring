package com.restaurant.business.chinesefood;


import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.modulebase.HttpConfig;
import com.modulebase.log.LogF;
import com.modulebase.okhttp.JsonResponseHandler;
import com.modulebase.okhttp.MyOkHttp;
import com.modulebase.toolkit.SharepreFHelp;
import com.modulebase.toolkit.sort.SortTools;
import com.restaurant.business.OrderAPI;
import com.restaurant.ui.bean.VarietyDishes;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class OrderPressenter implements OrderAPI.Pressente {

    private String TAG = "OrderPressenter" ;

    private Context mContext ;
    private OrderAPI.View mView;

    public OrderPressenter(Context context ,OrderAPI.View  view){
        mContext = context;
        mView = view ;
    }

    @Override
    public void loadData( Map<String, String> params) {
        String url = HttpConfig.HOST_NAME + HttpConfig.INTERFACE_FOOD_CHINES_ORDER;
        String userid = SharepreFHelp.getInstance(mContext).getUserID();
        String userkey = SharepreFHelp.getInstance(mContext).getUserKey();
        Map<String, String> paer = new HashMap<>();
        paer.put(HttpConfig.Field.mid, userid);
        paer.put(HttpConfig.Field.key, userkey);
        //paer.put(HttpConfig.Field.rows, rows);
        //paer.put(HttpConfig.Field.page, String.valueOf(page));
        // 额外的条件
        if ( params!=null && !params.isEmpty() ){
            for (Map.Entry<String, String> entry : params.entrySet()){
                paer.put(entry.getKey(),entry.getValue());
            }
        }
        paer.put(HttpConfig.Field.timestamp, String.valueOf(System.currentTimeMillis() / 1000));
        Set<String> keySet = paer.keySet();  //获取set集合
        List<String> sortKey = SortTools.listSort(keySet);
        TreeMap<String, String> parameter = SortTools.getSortMap(sortKey, paer);

        MyOkHttp.get().get(mContext, url, parameter, new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogF.i(TAG , "onSuccess statusCode = "+ statusCode + " statusCode = "+ response);
                VarietyDishes datas = null ;
                try {
                    JSONObject resObj = new JSONObject(response);
                    if (resObj.getString("errCode").equals("200")) {
                        Log.i("Datat", resObj.getString("Data"));
                        JSONObject object = resObj.getJSONObject("Data");
                       if(object!=null){
                           Gson gson = new Gson();
                          datas = gson.fromJson(object.toString() , VarietyDishes.class);
       //                   LogF.i("xiahong",datas.toString());

//                           datas.setMC(object.getString("MC"));
//                           datas.setPicture_path(object.getString("picture_path"));
//                           datas.setSLOrder(object.getString("SLOrder"));
//                           datas.setCDDM(object.getString("CDDM"));

                       }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    mView.upDatd( datas );
                }
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {
                Log.i(TAG, "onFailure statusCode = " + statusCode + " error_msg = " + error_msg);
                mView.upDatd(null);
            }
        });
    }


}
