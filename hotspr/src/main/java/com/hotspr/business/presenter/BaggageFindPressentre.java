package com.hotspr.business.presenter;

import android.content.Context;
import android.util.Log;

import com.modulebase.HttpConfig;
import com.hotspr.business.api.BaggageRegistrationAPI;
import com.modulebase.toolkit.SharepreFHelp;
import com.hotspr.ui.bean.Xl;
import com.modulebase.log.LogF;
import com.modulebase.okhttp.JsonResponseHandler;
import com.modulebase.okhttp.MyOkHttp;
import com.modulebase.toolkit.sort.SortTools;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * 行李寄存处理类
 */
public class BaggageFindPressentre implements BaggageRegistrationAPI.Pressente {

    private String TAG = "BaggageRegistrationPressentre" ;

    private Context mContext ;
    private BaggageRegistrationAPI.View mView ;

    public BaggageFindPressentre(Context context , BaggageRegistrationAPI.View view){
        mContext = context ;
        mView = view ;
    }

    /**
     * 加载数据
     * @param lodelModel
     * @param page
     * @param params
     */
    @Override
    public void loadData(final int lodelModel, int page, Map<String, String> params) {
        String url = HttpConfig.HOST_NAME + HttpConfig.INTERFACE_RESERVATION_FIND;
        //handler.sendEmptyMessageDelayed(STOP_LOAD, 15 * 1000); // 每次请求数据最多给15s的时间，15s过当作放弃
        String userid = SharepreFHelp.getInstance(mContext).getUserID();
        String userkey = SharepreFHelp.getInstance(mContext).getUserKey();
        Map<String, String> paer = new HashMap<>();
        paer.put(HttpConfig.Field.mid, userid);
        paer.put(HttpConfig.Field.key, userkey);
        //paer.put(HttpConfig.Field.page, String.valueOf(page));
        // 额外的条件
        if ( params!=null && !params.isEmpty() ){
            for (Map.Entry<String, String> entry : params.entrySet()){
                paer.put( entry.getKey() , entry.getValue());
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
                ArrayList<Xl> datas = new ArrayList<>();
                String page = "0";
                try {
                    JSONObject resObj = new JSONObject(response);
                    if (resObj.getString("errCode").equals("200")) {
                        Log.i("DataList", resObj.getString("DataList"));
                        JSONArray resDataList = resObj.getJSONArray("DataList");
                        page = resObj.getString("total");
                        for (int i = 0; i < resDataList.length(); i++) {
                            JSONObject res = (JSONObject) resDataList.get(i);
                            Xl deposit = new Xl();
                            deposit.setRownum(res.getString("rownum"));
                            deposit.setXl_id(res.getString("xl_id"));
                            deposit.setOrder_NO(res.getString("order_no"));
                            deposit.setName(res.getString("name"));
                            deposit.setTel(res.getString("tel"));
                            deposit.setRoom(res.getString("room"));
                            deposit.setOnduty1n(res.getString("onduty1n"));
                            deposit.setDate1(res.getString("date1"));
                            deposit.setTime1(res.getString("time1"));
                            deposit.setOnduty2n(res.getString("onduty2n"));
                            deposit.setDate2(res.getString("date2"));
                            deposit.setTime2(res.getString("time2"));
                            deposit.setOnduty3n(res.getString("onduty3n"));
                            deposit.setDate3(res.getString("date3"));
                            deposit.setTime3(res.getString("time3"));
                            deposit.setTag(res.getString("tag"));
                            deposit.setPicture_path(res.getString("picture_path"));
                            deposit.setMemo1(res.getString("memo1"));
                            datas.add(deposit);
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
                mView.upDatd(lodelModel, new ArrayList<Xl>(), -1);
            }
        });
    }


}
