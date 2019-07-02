package com.hotspr;

public class HttpConfig {

    public static  String HOST_NAME = "http://47.112.97.81:8006/";
    public static  String PIC_HOST_NAME = "http://47.112.97.81:8006/";

    public static String CURRENT_HOST = HOST_NAME;
    //public static String CURRENT_HOST = PIC_HOST_NAME;

    public static String INTERFACE_LODINF  = "UserInter/GetUserKey?" ;
    public static String INTERFACE  = "Room/RoomLookList?";
    public static String INTERFACE_uploadPhoto  = "Room/RoomLookUpImg?";
    public static String INTERFACE_checkOut = "Room/RoomLookUp?";
    public static String INTERFACE_roomClList = "Room/RoomClList?";
    public static String INTERFACE_roomClUp = "Room/RoomClUp?";
    public static String INTERFACE_arrangeCleanRoom = "Room/RoomClAdd?";
    public static String INTERFACE_CL_uploadPhoto  = "Room/RoomClUpImg?";
    public static String INTERFACE_floorList = "Room/FloorList?";
    public static String INTERFACE_rpriceList = "Room/RpriceList?";
    public static String INTERFACE_GetUserList = "UserInter/GetUserList?";
    public static String INTERFACE_RoomClAdd = "Room/RoomClAdd?";



    public static class Field{

        public static String user = "user" ;
        public static String pwd = "pwd" ;
        public static String mid = "mid";
        public static String key = "key";
        public static String rows = "rows";
        public static String timestamp = "timestamp";
        public static String page = "page";
        public static String look_id = "look_id";
        public static String img = "img";
        public static String room = "room";
        public static String floor = "floor";
        public static String type_class = "class";


        public static String tage = "tage";
        public static String hserver_name = "hserver_name";
        public static String server_memo = "server_memo";

        public static String arrange = "arrange"; //是否安排清洁
        public static String state = "state"; //清洁房间状态
        public static String state2 = "state2"; //清洁房间状态
        public static String onduty1n = "onduty1n"; // 安排清洁的人名字
        public static String onduty3n = "onduty3n"; // 被安排清洁的人名字
        public static String class_new = "class_new" ; // 房间类型



        public static String errCode = "errCode" ;


    }

}
