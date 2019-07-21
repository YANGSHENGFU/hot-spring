package com.restaurant.business.common;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.modulebase.HttpConfig;
import com.modulebase.log.LogF;
import com.modulebase.okhttp.JsonResponseHandler;
import com.modulebase.okhttp.MyOkHttp;
import com.modulebase.toolkit.SharepreFHelp;
import com.modulebase.toolkit.sort.SortTools;
import com.restaurant.toolkit.CacheHandle;
import com.restaurant.ui.bean.FoodCategory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * 共公信息获取类
 */
public class PublicInfoHandle {

    private String TAG = "PublicInfoHandle";

    private Context mContext;

    public PublicInfoHandle(Context context) {
        mContext = context;
    }

    /**
     * 获取区域
     * @param context
     * @param params
     */
    public void getRegion(Context context, Map<String, String> params) {
        String url = HttpConfig.HOST_NAME + HttpConfig.INTERFACE_REGION;
        String userid = SharepreFHelp.getInstance(context).getUserID();
        String userkey = SharepreFHelp.getInstance(context).getUserKey();
        Map<String, String> paer = new HashMap<>();
        paer.put(HttpConfig.Field.mid, userid);
        paer.put(HttpConfig.Field.key, userkey);
        // 额外的条件
        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                paer.put(entry.getKey(), entry.getValue());
            }
        }
        paer.put(HttpConfig.Field.timestamp, String.valueOf(System.currentTimeMillis() / 1000));
        Set<String> keySet = paer.keySet();  //获取set集合
        List<String> sortKey = SortTools.listSort(keySet);
        TreeMap<String, String> parameter = SortTools.getSortMap(sortKey, paer);
        MyOkHttp.get().get(context, url, parameter, new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogF.i(TAG, "onSuccess statusCode = " + statusCode + " statusCode = " + response);
                try {
                    JSONObject resObj = new JSONObject(response);
                    if (resObj.getString("errCode").equals("200")) {
                        JSONArray resDataList = resObj.getJSONArray("DataList");
                        CacheHandle.regionCache.clear();
                        CacheHandle.regionCache.add("");
                        for (int i = 0; i < resDataList.length(); i++) {
                            JSONObject jo = (JSONObject) resDataList.get(i);
                            CacheHandle.regionCache.add(jo.getString("TWS"));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {

                }
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {
                Log.i(TAG, "onFailure statusCode = " + statusCode + " error_msg = " + error_msg);
            }
        });
    }


    /**
     * 获取口味
     * @param context
     * @param params
     */
    public void getFlavor(Context context, Map<String, String> params) {
        String url = HttpConfig.HOST_NAME + HttpConfig.INTERFACE_FLAVOR;
        String userid = SharepreFHelp.getInstance(context).getUserID();
        String userkey = SharepreFHelp.getInstance(context).getUserKey();
        Map<String, String> paer = new HashMap<>();
        paer.put(HttpConfig.Field.mid, userid);
        paer.put(HttpConfig.Field.key, userkey);
        // 额外的条件
        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                paer.put(entry.getKey(), entry.getValue());
            }
        }
        paer.put(HttpConfig.Field.timestamp, String.valueOf(System.currentTimeMillis() / 1000));
        Set<String> keySet = paer.keySet();  //获取set集合
        List<String> sortKey = SortTools.listSort(keySet);
        TreeMap<String, String> parameter = SortTools.getSortMap(sortKey, paer);
        MyOkHttp.get().get(context, url, parameter, new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogF.i(TAG, "onSuccess statusCode = " + statusCode + " statusCode = " + response);
                try {
                    JSONObject resObj = new JSONObject(response);
                    if (resObj.getString("errCode").equals("200")) {
                        JSONArray resDataList = resObj.getJSONArray("DataList");
                        CacheHandle.flavorCache.clear();
                        CacheHandle.flavorCache.add("");
                        for (int i = 0; i < resDataList.length(); i++) {
                            JSONObject jo = (JSONObject) resDataList.get(i);
                            CacheHandle.flavorCache.add(jo.getString("kw_name"));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {

                }
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {
                Log.i(TAG, "onFailure statusCode = " + statusCode + " error_msg = " + error_msg);
            }
        });
    }

    /**
     * 获取加工方法
     * @param context
     * @param params
     */
    public void getProcessingMethod(Context context, Map<String, String> params) {
        String url = HttpConfig.HOST_NAME + HttpConfig.INTERFACE_PROCESSING_METHOD;
        String userid = SharepreFHelp.getInstance(context).getUserID();
        String userkey = SharepreFHelp.getInstance(context).getUserKey();
        Map<String, String> paer = new HashMap<>();
        paer.put(HttpConfig.Field.mid, userid);
        paer.put(HttpConfig.Field.key, userkey);
        // 额外的条件
        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                paer.put(entry.getKey(), entry.getValue());
            }
        }
        paer.put(HttpConfig.Field.timestamp, String.valueOf(System.currentTimeMillis() / 1000));
        Set<String> keySet = paer.keySet();  //获取set集合
        List<String> sortKey = SortTools.listSort(keySet);
        TreeMap<String, String> parameter = SortTools.getSortMap(sortKey, paer);
        MyOkHttp.get().get(context, url, parameter, new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogF.i(TAG, "onSuccess statusCode = " + statusCode + " statusCode = " + response);
                try {
                    JSONObject resObj = new JSONObject(response);
                    if (resObj.getString("errCode").equals("200")) {
                        JSONArray resDataList = resObj.getJSONArray("DataList");
                        CacheHandle.processingMethodCache.clear();
                        CacheHandle.processingMethodCache.add("");
                        for (int i = 0; i < resDataList.length(); i++) {
                            JSONObject jo = (JSONObject) resDataList.get(i);
                            CacheHandle.processingMethodCache.add(jo.getString("zf_name"));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {

                }
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {
                Log.i(TAG, "onFailure statusCode = " + statusCode + " error_msg = " + error_msg);
            }
        });
    }


    /**
     * 获取中餐菜品类别
     * @param context
     * @param params
     */
    public void getChinFoodCalss(Context context, Map<String, String> params) {
        String url = HttpConfig.HOST_NAME + HttpConfig.INTERFACE_FOOD_CHINES_LIST;
        String userid = SharepreFHelp.getInstance(context).getUserID();
        String userkey = SharepreFHelp.getInstance(context).getUserKey();
        Map<String, String> paer = new HashMap<>();
        paer.put(HttpConfig.Field.mid, userid);
        paer.put(HttpConfig.Field.key, userkey);
        // 额外的条件
        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                paer.put(entry.getKey(), entry.getValue());
            }
        }
        paer.put(HttpConfig.Field.timestamp, String.valueOf(System.currentTimeMillis() / 1000));
        Set<String> keySet = paer.keySet();  //获取set集合
        List<String> sortKey = SortTools.listSort(keySet);
        TreeMap<String, String> parameter = SortTools.getSortMap(sortKey, paer);
        MyOkHttp.get().get(context, url, parameter, new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogF.i(TAG, "onSuccess statusCode = " + statusCode + " statusCode = " + response);
                try {
                    JSONObject resObj = new JSONObject(response);
                    if (resObj.getString("errCode").equals("200")) {
                        JSONArray resDataList = resObj.getJSONArray("DataList");
                        CacheHandle.foodCategoryCache.clear();
                        for (int i = 0; i < resDataList.length(); i++) {
                            JSONObject jo = (JSONObject) resDataList.get(i);
                            Gson gson = new Gson();
                            FoodCategory fc = gson.fromJson(jo.toString()  , FoodCategory.class);
                            LogF.d(TAG, " FoodCategory fc toString "+ fc.toString());
                            CacheHandle.foodCategoryCache.add(fc);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {

                }
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {
                Log.i(TAG, "onFailure statusCode = " + statusCode + " error_msg = " + error_msg);
            }
        });
    }

}
