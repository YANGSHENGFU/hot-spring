package com.hotspr.business.presenter;



import android.content.Context;

import com.google.gson.Gson;
import com.hotspr.HttpConfig;
import com.hotspr.business.api.LogingAPI;
import com.hotspr.toolkit.FileHandle;
import com.hotspr.toolkit.SharepreFHelp;
import com.hotspr.ui.bean.User;
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

public class LoginPresenter implements LogingAPI.Pressente {


    private String TAG = "LoginPresenter" ;
    private Context mContent ;
    private LogingAPI.View mView ;

    public LoginPresenter(Context context , LogingAPI.View view){
        mContent = context ;
        mView = view ;
    }


    @Override
    public void loging(String n, String p) {
        Map< String , String > paer = new HashMap<>();
        paer.put(HttpConfig.Field.user , n ) ;
        paer.put(HttpConfig.Field.pwd , p ) ;
        Set<String> key = paer.keySet() ;  //获取key的set集合
        List<String> sortKey = SortTools.listSort( key ) ;
        TreeMap<String ,String > parameter = SortTools.getSortMap(sortKey , paer ) ;
        mView.showProgressDialog();
        MyOkHttp.get().post( mContent , HttpConfig.CURRENT_HOST + HttpConfig.INTERFACE_LODINF , parameter , new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode , final String response ) {
                try {
                    LogF.i(TAG , "onSuccess statusCode = "+statusCode + "\n response = "+ response ) ;
                    final JSONObject res = new JSONObject(response);
                    if (res.getString("errCode").equals("200")){
                        User user = new Gson().fromJson(response , User.class);
                        if(user !=null ){
                            SharepreFHelp.getInstance(mContent).setUserID(user.getUser_id());
                            SharepreFHelp.getInstance(mContent).setUserKey(user.getKey());
                            FileHandle.saveUser(user);
                            mView.lodingResult(true , null);
                        } else {
                            mView.lodingResult(false , "用户不存在");
                        }
                    }else{
                        mView.lodingResult(false , res.getString("errTxt"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    mView.lodingResult(false , "解析数据错误" );
                }
            }
            @Override
            public void onFailure(int statusCode, final String error_msg) {
                mView.lodingResult(false , error_msg );
            }
        });
    }
}


