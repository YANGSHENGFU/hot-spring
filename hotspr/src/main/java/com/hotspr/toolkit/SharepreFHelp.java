package com.hotspr.toolkit;

import android.content.Context;
import android.content.SharedPreferences;

public class SharepreFHelp {


    private Context mContext ;
    private static SharepreFHelp g ;
    private SharedPreferences sharedpreferences;
    private SharedPreferences.Editor editor;

    private static String FILE_NAME = "UserInfo";

    private String KEY_USERID = "userid" ;
    private String KEY_USERKEY = "userkey" ;
    private String KEY_U_NAME = "U_NAME" ;

    private String KEY_HOSTKEY = "host" ;
    private String KEY_PIC_HOSTKEY = "pic_host" ;
    private String KEY_KEEP_PASSWORD_STATE = "KeepPasswordState";
    private String KEY_USER_NAME = "username";
    private String KEY_PASSWORD = "password";
    public static  SharepreFHelp getInstance( Context context){
        if(g==null){
            synchronized (SharepreFHelp.class){
                if(g == null ){
                    g = new SharepreFHelp( context );
                }
            }
        }
        return g ;
    }

    private SharepreFHelp( Context context ){
        mContext = context ;
        sharedpreferences = context.getSharedPreferences(FILE_NAME , Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
    }

    public void setUserID(String userID){
        editor.putString(KEY_USERID , userID);
        editor.commit();
    }

    public String getUserID(){
        return sharedpreferences.getString(KEY_USERID , "");
    }

    public void setUserKey(String key){
        editor.putString(KEY_USERKEY , key);
        editor.commit();
    }

    public String getUserKey(){
        return sharedpreferences.getString(KEY_USERKEY , "");
    }
    public String getU_NAME(){
        return sharedpreferences.getString(KEY_U_NAME , "");
    }

    public void setHOSTURL(String url ){
        editor.putString(KEY_HOSTKEY , url);
        editor.commit();
    }

    public String getHOSTURL(){
        return sharedpreferences.getString(KEY_HOSTKEY , "http://47.112.97.81:8006/");
    }

    public void setPICHOSTURL(String url ){
        editor.putString(KEY_PIC_HOSTKEY , url);
        editor.commit();
    }

    public String getPICHOSTURL(){
        return sharedpreferences.getString(KEY_PIC_HOSTKEY , "http://47.112.97.81:8006/");
    }

    /**
     * 获取记住密码状态
     * @return
     */
    public void setKeepPasswordState(boolean b){
        editor.putBoolean(KEY_KEEP_PASSWORD_STATE , b);
        editor.commit();
    }

    public boolean getKeepPasswordState(){
        return sharedpreferences.getBoolean(KEY_KEEP_PASSWORD_STATE , true); // 默认返回true
    }

    public void setUserName(String name){
        editor.putString(KEY_USER_NAME , name);
        editor.commit();
    }

    public String getUserName(){
        return sharedpreferences.getString(KEY_USER_NAME , ""); // 默认返回true
    }

    public void setPassword(String name){
        editor.putString(KEY_PASSWORD , name);
        editor.commit();
    }

    public String getPassword(){
        return sharedpreferences.getString(KEY_PASSWORD , ""); // 默认返回true
    }

}
