package com.hotspr.business.presenter;

import android.content.Context;
import android.util.Log;

import com.hotspr.HttpConfig;
import com.hotspr.business.api.ArrangCleanAPI;
import com.hotspr.toolkit.SharepreFHelp;
import com.hotspr.ui.bean.Round;
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

public class ReadyInspectRoomPressenter implements ArrangCleanAPI.Pressente {

    private String TAG = "ArrangCleanPressenter";

    private Context mContext;
    private ArrangCleanAPI.View mView;
    public String rows;


    public ReadyInspectRoomPressenter(Context context, ArrangCleanAPI.View view){
        mContext = context;
        mView = view;
        rows = String.valueOf(5000);
    }

    @Override
    public void loadData(final int lodelModel, int page, Map<String, String> params) {
        String url = HttpConfig.HOST_NAME + HttpConfig.INTERFACE_roomClList;
        //handler.sendEmptyMessageDelayed(STOP_LOAD, 15 * 1000); // 每次请求数据最多给15s的时间，15s过当作放弃
        String userid = SharepreFHelp.getInstance(mContext).getUserID();
        String userkey = SharepreFHelp.getInstance(mContext).getUserKey();
        Map<String, String> paer = new HashMap<>();
        paer.put(HttpConfig.Field.arrange, "Y");
        paer.put(HttpConfig.Field.mid, userid);
        paer.put(HttpConfig.Field.key, userkey);
        paer.put(HttpConfig.Field.page, String.valueOf(page));
        paer.put(HttpConfig.Field.rows, rows);
        paer.put(HttpConfig.Field.timestamp, String.valueOf(System.currentTimeMillis() / 1000));
        Set<String> keySet = paer.keySet();  //获取set集合
        List<String> sortKey = SortTools.listSort(keySet);
        TreeMap<String, String> parameter = SortTools.getSortMap(sortKey, paer);
        MyOkHttp.get().get(mContext, url, parameter, new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogF.i(TAG , "success statusCode = "+ statusCode + "response = "+ response);
                ArrayList<Round> datas = new ArrayList<>();
                String page = "0";
                try {
                    JSONObject resObj = new JSONObject(response);
                    if (resObj.getString("errCode").equals("200")) {
                        Log.i("DataList", resObj.getString("DataList"));
                        JSONArray resDataList = resObj.getJSONArray("DataList");
                        page = resObj.getString("total");
                        for (int i = 0; i < resDataList.length(); i++) {
                            JSONObject res = (JSONObject) resDataList.get(i);
                            Round round = new Round();
                            round.setROOM(res.getString("ROOM"));
                            round.setCLASS(res.getString("CLASS"));
                            round.setRoom_id(res.getString("room_id"));
                            round.setLook_id(res.getString("look_id"));
                            datas.add(round);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    page = "0";
                } finally {
                    mView.upDatd(lodelModel , datas , Integer.valueOf(page));
                }
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {
                Log.i(TAG, "onFailure statusCode = " + statusCode + " error_msg = " + error_msg);
                mView.upDatd(lodelModel, new ArrayList<Round>(), -1);
            }
        });
    }

    /**
     * 合格 逻辑处理
     */
    public void qualified(){

    }

    /**
     * 不合格 逻辑处理
     */
    public void unQualified(){

    }


}
