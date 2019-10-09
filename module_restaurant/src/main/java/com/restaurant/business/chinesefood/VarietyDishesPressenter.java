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
import com.restaurant.ui.bean.VarietyDishes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class VarietyDishesPressenter implements VarietyDishesAPI.Pressente {

    private String TAG = "VarietyDishesPressenter";

    private Context mContext;
    private VarietyDishesAPI.View mView ;
    private SetMealAPI.View sView ;

    public VarietyDishesPressenter(Context context , VarietyDishesAPI.View view, SetMealAPI.View view2) {
        mContext = context ;
        mView = view;
        sView = view2;
    }

    @Override
    public void loadData(final int lodelModel , int page , Map<String, String> params) {
        String zxmc = params.get("zxmc");
        boolean isTC = false;
        if("套餐".equals(zxmc))   isTC = true;
        String url = HttpConfig.HOST_NAME + HttpConfig.INTERFACE_FOOD_CHINES_VARIETY_OF_DISHES_LIST;
        if(isTC)
            url = HttpConfig.HOST_NAME + HttpConfig.INTERFACE_FOOD_TC_LIST;
        String userid = SharepreFHelp.getInstance(mContext).getUserID();
        String userkey = SharepreFHelp.getInstance(mContext).getUserKey();
        Map<String, String> paer = new HashMap<>();
        paer.put(HttpConfig.Field.mid, userid);
        paer.put(HttpConfig.Field.key, userkey);
        if(isTC)
            paer.put("tcbm","1");
        else
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
                ArrayList<VarietyDishes> datas = new ArrayList<>();
                String page = "0";
                try {
                    JSONObject resObj = new JSONObject(response);
                    if (resObj.getString("errCode").equals("200")) {
                        Log.i("DataList", resObj.getString("DataList"));
                        JSONArray resDataList = resObj.getJSONArray("DataList");
                        page = resObj.getString("total");
                        for (int i = 0; i < resDataList.length(); i++) {
                            JSONObject res = (JSONObject) resDataList.get(i);
                            VarietyDishes table = new VarietyDishes();
                            table.setMC(res.getString("MC"));
                            table.setPicture_path(res.getString("picture_path"));
                            table.setSLOrder(res.getString("SLOrder"));
                            table.setCDDM(res.getString("CDDM"));
                            table.setLSJG(res.getString("LSJG"));//LQL
                            table.setFf_tag(res.getString("ff_tag"));
                            datas.add(table);
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

        JsonResponseHandler jsonResponseHandlerTC = new JsonResponseHandler(){
            @Override
            public void onSuccess(int statusCode, String response) {
                LogF.i(TAG , "onSuccess statusCode = "+ statusCode + " statusCode = "+ response);
                ArrayList<SetMeal> datas = new ArrayList<>();
                String page = "0";
                try {
                    JSONObject resObj = new JSONObject(response);
                    if (resObj.getString("errCode").equals("200")) {
                        Log.i("DataList", resObj.getString("DataList"));
                        JSONArray resDataList = resObj.getJSONArray("DataList");
                        page = resObj.getString("total");
                        for (int i = 0; i < resDataList.length(); i++) {
                            JSONObject res = (JSONObject) resDataList.get(i);
                            SetMeal table = new SetMeal();
                            table.setTc_id(res.getString("tc_id"));
                            table.setTc_no(res.getString("tc_no"));
                            table.setTc_unit(res.getString("tc_unit"));
                            table.setTc_bm(res.getString("tc_bm"));
                            table.setTc_name(res.getString("tc_name"));//LQL
                            table.setTc_price(res.getString("tc_price"));
                            table.setSelect_tag(res.getString("select_tag"));//LQL
                            table.setMark(res.getString("mark"));
                            datas.add(table);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    page = "0";
                } finally {
                    sView.upDatd2(lodelModel, datas , Integer.valueOf(page));
                }
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {
                Log.i(TAG, "onFailure statusCode = " + statusCode + " error_msg = " + error_msg);
                sView.upDatd2(lodelModel, null, -1);
            }
        };

        if(isTC)
            MyOkHttp.get().get(mContext, url, parameter, jsonResponseHandlerTC);
        else
            MyOkHttp.get().get(mContext, url, parameter, jsonResponseHandler);



        if(false)
        MyOkHttp.get().get(mContext, url, parameter, new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogF.i(TAG , "onSuccess statusCode = "+ statusCode + " statusCode = "+ response);
                ArrayList<VarietyDishes> datas = new ArrayList<>();
                String page = "0";
                try {
                    JSONObject resObj = new JSONObject(response);
                    if (resObj.getString("errCode").equals("200")) {
                        Log.i("DataList", resObj.getString("DataList"));
                        JSONArray resDataList = resObj.getJSONArray("DataList");
                        page = resObj.getString("total");
                        for (int i = 0; i < resDataList.length(); i++) {
                            JSONObject res = (JSONObject) resDataList.get(i);
                            VarietyDishes table = new VarietyDishes();
                            table.setMC(res.getString("MC"));
                            table.setPicture_path(res.getString("picture_path"));
                            table.setSLOrder(res.getString("SLOrder"));
                            table.setCDDM(res.getString("CDDM"));
                            table.setLSJG(res.getString("LSJG"));//LQL
                            table.setFf_tag(res.getString("ff_tag"));
                            datas.add(table);
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
        });
    }

}
