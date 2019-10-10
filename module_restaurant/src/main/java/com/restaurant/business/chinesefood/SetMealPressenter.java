package com.restaurant.business.chinesefood;

import android.content.Context;
import android.util.Log;

import com.modulebase.HttpConfig;
import com.modulebase.log.LogF;
import com.modulebase.okhttp.JsonResponseHandler;
import com.modulebase.okhttp.MyOkHttp;
import com.modulebase.toolkit.SharepreFHelp;
import com.modulebase.toolkit.sort.SortTools;
import com.restaurant.business.SetMealAPI;
import com.restaurant.business.VarietyDishesAPI;
import com.restaurant.ui.bean.SetMeal;
import com.restaurant.ui.bean.TcVarietyDishes;
import com.restaurant.ui.bean.VarietyDishes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class SetMealPressenter implements VarietyDishesAPI.Pressente {

    private String TAG = "VarietyDishesPressenter";

    private Context mContext;
    private VarietyDishesAPI.View mView ;

    public SetMealPressenter(Context context , VarietyDishesAPI.View view) {
        mContext = context ;
        mView = view;
    }

    @Override
    public void loadData(final int lodelModel , int page , Map<String, String> params) {
        String url = HttpConfig.HOST_NAME + HttpConfig.INTERFACE_FOOD_CHINES_TCINFO_LIST;
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


        JsonResponseHandler jsonResponseHandler = new JsonResponseHandler(){
            @Override
            public void onSuccess(int statusCode, String response) {
                LogF.i(TAG , "onSuccess statusCode = "+ statusCode + " statusCode = "+ response);
                final ArrayList<TcVarietyDishes> datas = new ArrayList<>();
                String page = "0";
                try {
                    JSONObject resObj = new JSONObject(response);
                    if (resObj.getString("errCode").equals("200")) {
                        Log.i("DataList", resObj.getString("DataList"));
                        JSONArray resDataList = resObj.getJSONArray("DataList");
                        page = resObj.getString("total");
                        Map maps = new HashMap();
                        for (int i = 0; i < resDataList.length(); i++) {
                            JSONObject res = (JSONObject) resDataList.get(i);
                            TcVarietyDishes table = new TcVarietyDishes();
                            String key = res.getString("ZS");
                            ArrayList<TcVarietyDishes> vds = null;
                            vds = (ArrayList<TcVarietyDishes>)maps.get(key);
                            if(vds==null) {
                                vds = new ArrayList<TcVarietyDishes>();
                                maps.put(key, vds);
                            }else{
                                vds = (ArrayList<TcVarietyDishes>) maps.get(key);
                            }

                            table.setMC(res.getString("MC"));
                            table.setPicture_path(res.getString("picture_path"));
                            table.setSLOrder(res.getString("SLOrder"));
                            table.setCDDM(res.getString("CDDM"));
                            table.setLSJG(res.getString("LSJG"));//LQL
                            table.setJDBH(res.getString("JDBH"));
                            table.setSL(res.getString("SL"));
                            table.setZS(res.getString("ZS"));
                            table.setFf_tag(res.getString("ff_tag"));
                            vds.add(table);
                        }

                        for (Object v : maps.values()) {
                            datas.addAll((ArrayList<TcVarietyDishes>)v);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    page = "0";
                } finally {
                    mView.upDatd(lodelModel, datas , Integer.valueOf(page));
                }
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {
                Log.i(TAG, "onFailure statusCode = " + statusCode + " error_msg = " + error_msg);
                mView.upDatd(lodelModel, null, -1);
            }
        };

        MyOkHttp.get().get(mContext, url, parameter, jsonResponseHandler);

    }

}
