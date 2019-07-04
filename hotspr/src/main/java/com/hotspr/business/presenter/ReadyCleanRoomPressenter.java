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

public class ReadyCleanRoomPressenter implements ArrangCleanAPI.Pressente {


    private String TAG = "ReadyCleanRoomPressenter";

    private Context mContext;
    private ArrangCleanAPI.View mView;
    public String rows;
    public    Map<String, String> mMapData;

    /**
     *
     * @param context 上下文
     * @param MapData 其它参数比如固定的查询条件
     * @param view 接口view
     */
    public ReadyCleanRoomPressenter(Context context,Map<String, String> MapData, ArrangCleanAPI.View view ){
        mContext = context;
        mView = view;
        mMapData=MapData;
    }

    @Override
    public void loadData(final int lodelModel, int page, Map<String, String> params) {
        String url = HttpConfig.HOST_NAME + HttpConfig.INTERFACE_roomClList;
        //handler.sendEmptyMessageDelayed(STOP_LOAD, 15 * 1000); // 每次请求数据最多给15s的时间，15s过当作放弃
        String userid = SharepreFHelp.getInstance(mContext).getUserID();
        String userkey = SharepreFHelp.getInstance(mContext).getUserKey();
        Map<String, String> paer = new HashMap<>();
       // paer.put(HttpConfig.Field.state2, "D");//测试
        if(mMapData.containsKey("state")){
          paer.put("state", mMapData.get("state"));
        }
        if(mMapData.containsKey("state2")){
            paer.put("state2", mMapData.get("state2"));
        }
        if(mMapData.containsKey("sidx")&&mMapData.containsKey("sord")){
            paer.put("sidx", mMapData.get("sidx"));
            paer.put("sord", mMapData.get("sord"));
        }
        paer.put(HttpConfig.Field.mid, userid);
        paer.put(HttpConfig.Field.key, userkey);
        paer.put(HttpConfig.Field.page, String.valueOf(page));

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
                            round.setCLASS(res.getString("CLASS")); //房型
                            round.setRoom_id(res.getString("room_id"));
                            round.setSTATE2(res.getString("STATE2"));//T：停用    D：脏房  L：锁房  R：净房  M：维修S：清扫
                            round.setRoom_wh_id(res.getString("room_wh_id"));
                            round.setROOM(res.getString("ROOM")); //房间号
                            round.setCl_onduty1n(res.getString("cl_onduty1n")); //安排人
                            round.setCl_onduty2n(res.getString("cl_onduty2n")); //服务员
                            round.setCl_date1(res.getString("cl_date1")); //安排日期
                            round.setCl_time1(res.getString("cl_time1")); //安排时间
                            round.setCl_onduty3n(res.getString("cl_onduty3n")); //清洁员
                            round.setCl_state(res.getString("cl_state")); //状态0未完成  1已完成 2已检查
                            round.setCl_class_new(res.getString("cl_class_new")); //状态0未完成  1已完成 2已检查
                            round.setCl_time3(res.getString("cl_time3")); //安排时间
                            round.setCl_check_er(res.getString("cl_check_er")); //安排时间
                            round.setcl_memo1(res.getString("cl_memo1")); //安排时间
                            round.setCl_picture_path(res.getString("cl_picture_path")); //安排时间
                            datas.add(round);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    page = "0";
                } finally {
                    mView.upDatd(lodelModel, datas, Integer.valueOf(page));
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                Log.i(TAG, "onFailure statusCode = " + statusCode + " error_msg = " + error_msg);
                mView.upDatd(lodelModel, new ArrayList<Round>(), -1);
            }
        });
    }


}
