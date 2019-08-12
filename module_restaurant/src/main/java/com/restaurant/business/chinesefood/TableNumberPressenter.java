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
import com.restaurant.business.TableNumberAPI;
import com.restaurant.ui.bean.TableNumber;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class TableNumberPressenter implements TableNumberAPI.Pressente {

    private String TAG = "TableNumberPressenter";

    public Context mContext;
    public String rows;
    private TableNumberAPI.View mView;

    public TableNumberPressenter(Context context , TableNumberAPI.View view){
        mContext = context ;
        rows = "1000";
        mView = view ;
    }

    @Override
    public void loadData(final int lodelModel , int page , Map<String, String> params) {
        String url = HttpConfig.HOST_NAME + HttpConfig.INTERFACE_TABLE_INFO_CHIN;
        String userid = SharepreFHelp.getInstance(mContext).getUserID();
        String userkey = SharepreFHelp.getInstance(mContext).getUserKey();
        Map<String, String> paer = new HashMap<>();
        paer.put(HttpConfig.Field.mid, userid);
        paer.put(HttpConfig.Field.key, userkey);
        //paer.put(HttpConfig.Field.rows, rows);
        paer.put(HttpConfig.Field.ctbm, "1");
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
                ArrayList<TableNumber> datas = new ArrayList<>();
                String page = "0";
                try {
                    JSONObject resObj = new JSONObject(response);
                    if (resObj.getString("errCode").equals("200")) {
                        Log.i("DataList", resObj.getString("DataList"));
                        JSONArray resDataList = resObj.getJSONArray("DataList");
                        page = resObj.getString("total");
                        for (int i = 0; i < resDataList.length(); i++) {
                            JSONObject res = (JSONObject) resDataList.get(i);
                            TableNumber table = new TableNumber();
                            table.setCZBM(res.getString("CZBM"));
                            table.setCZZT(res.getString("CZZT"));
                            table.setCZMC(res.getString("CZMC"));
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

    /**
     * 开台
     * @param params
     */
    public void openTable( Map<String, String> params){
        String url = HttpConfig.HOST_NAME + HttpConfig.INTERFACE_OPEN_TABLE;
        String userid = SharepreFHelp.getInstance(mContext).getUserID();
        String userkey = SharepreFHelp.getInstance(mContext).getUserKey();
        Map<String, String> paer = new HashMap<>();
        paer.put(HttpConfig.Field.mid, userid);
        paer.put(HttpConfig.Field.key, userkey);
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
                TableNumber table= null ;
                try {
                    JSONObject resObj = new JSONObject(response);
                    if (resObj.getString("errCode").equals("200")) {
                        Log.i("DataList", resObj.getString("Data"));
                        JSONObject jo = resObj.getJSONObject("Data");
                        Gson gson = new Gson();
                        table = gson.fromJson(jo.toString(), TableNumber.class);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                   mView.openTabelResult(table);
                }
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {
                Log.i(TAG, "onFailure statusCode = " + statusCode + " error_msg = " + error_msg);
                mView.openTabelResult(null);
            }
        });
    }
}
