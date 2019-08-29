package com.modulebase.toolkit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.modulebase.HttpConfig;
import com.modulebase.log.LogF;
import com.modulebase.okhttp.JsonResponseHandler;
import com.modulebase.okhttp.MyOkHttp;
import com.modulebase.toolkit.sort.SortTools;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class MessageTimer {
    private Context context = null;
    private long time = 1000;
    private Class look;
    private Class cl;
    private Handler handler = null;
    private Long lookID = 0L;
    private Long clID = 0L;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    public MessageTimer(Context context) {
        SharedPreferences sp = context.getSharedPreferences("setting", Context.MODE_PRIVATE);
        editor = sp.edit();
        lookID = sp.getLong("lookID", 0L);
        clID = sp.getLong("clID", 0L);

        handler = new Handler() {
            public void handleMessage(Message msg) {
                // todo
                getUserMessage();
                super.handleMessage(msg);
            }
        };
    }

    public MessageTimer(Context context, long time, Class look, Class cl) {
        this(context);
        this.context = context;
        this.time = time;
        this.look = look;
        this.cl = cl;

    }

    public void start() {
        new Thread(new MyThread()).start();
    }

    public class MyThread implements Runnable {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            while (true) {
                try {
                    Thread.sleep(10000);// 线程暂停10秒
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);// 发送消息
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 信息推送
     */
    public void getUserMessage() {
        String url = HttpConfig.HOST_NAME + HttpConfig.INTERFACE_USER_MESSAGE;
        String userid = SharepreFHelp.getInstance(context).getUserID();
        String userName = SharepreFHelp.getInstance(context).getUserName();
        String userkey = SharepreFHelp.getInstance(context).getUserKey();
        Map<String, String> paer = new HashMap<>();
        paer.put(HttpConfig.Field.mid, userid);
        paer.put(HttpConfig.Field.key, userkey);
        // 额外的条件
        HashMap<String, String> params = new HashMap<>();
        //params.put(HttpConfig.Field.krbh,tableNumber.getKRBH());
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
                LogF.i("getUserMessage", "onSuccess statusCode = " + statusCode + " statusCode = " + response);
                try {
                    JSONObject resObj = new JSONObject(response);
                    if (resObj.getString("errCode").equals("200")) {
                        Log.i("getUserMessage", resObj.getString("look"));

                        if (resObj.getJSONObject("look") != null) {
                            Long id = resObj.getJSONObject("look").getLong("ID");
                            if (id > lookID) {
                                lookID = id;
                                editor.putLong("lookID", lookID);
                                editor.apply();
                                editor.commit();

                                Intent intent = new Intent(context, look);
                                context.startActivity(intent);
                            }
                        } else if (resObj.getJSONObject("cl") != null) {
                            Long id = resObj.getJSONObject("cl").getLong("room_wh_id");
                            if (id > clID) {
                                clID = id;
                                editor.putLong("clID", clID);
                                editor.apply();
                                editor.commit();

                                Intent intent = new Intent(context, cl);
                                context.startActivity(intent);
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                Log.i("getUserMessage", "onFailure statusCode = " + statusCode + " error_msg = " + error_msg);
            }
        });
    }
}
