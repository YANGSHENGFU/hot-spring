package com.hotspr.business.api;


public class LogingAPI {

    public interface View {
        void showProgressDialog();
        void dismissDialog();
        void lodingResult(boolean isOk, String error_msg);

    }

    public interface Pressente {

        void loging(String n, String p);
    }

}
