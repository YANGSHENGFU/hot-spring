package com.hotspr.ui.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Xl implements Parcelable {

    private String rownum ;

    protected Xl(Parcel in) {
        rownum = in.readString();
        xl_id = in.readString();
        order_NO = in.readString();
        order_id = in.readString();
        tel = in.readString();
        name = in.readString();
        date1 = in.readString();
        time1 = in.readString();
        room = in.readString();
        sl = in.readString();
        date2 = in.readString();
        time2 = in.readString();
        onduty1n = in.readString();
        onduty2n = in.readString();
        onduty3n = in.readString();
        date3 = in.readString();
        time3 = in.readString();
        position = in.readString();
        picture_path = in.readString();
        memo1 = in.readString();
        tag = in.readString();
        room_in_id = in.readString();
    }

    public static final Creator<Xl> CREATOR = new Creator<Xl>() {
        @Override
        public Xl createFromParcel(Parcel in) {
            return new Xl(in);
        }

        @Override
        public Xl[] newArray(int size) {
            return new Xl[size];
        }
    };

    public String getRownum() {
        return rownum;
    }

    public Xl() {
        super();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    public String getXl_id() {
        return xl_id;
    }

    public String getOrder_NO() {
        return order_NO;
    }

    public String getOrder_id() {
        return order_id;
    }

    public String getTel() {
        return tel;
    }

    public String getName() {
        return name;
    }

    public String getDate1() {
        return date1;
    }

    public String getTime1() {
        return time1;
    }

    public String getRoom() {
        return room;
    }

    public String getSl() {
        return sl;
    }

    public String getDate2() {
        return date2;
    }

    public String getTime2() {
        return time2;
    }

    public String getOnduty1n() {
        return onduty1n;
    }

    public String getOnduty2n() {
        return onduty2n;
    }

    public String getOnduty3n() {
        return onduty3n;
    }

    public String getDate3() {
        return date3;
    }

    public String getTime3() {
        return time3;
    }

    public String getPosition() {
        return position;
    }

    public String getPicture_path() {
        return picture_path;
    }

    public String getMemo1() {
        return memo1;
    }

    public String getTag() {
        return tag;
    }

    public String getRoom_in_id() {
        return room_in_id;
    }

    public void setRownum(String rownum) {
        this.rownum = rownum;
    }

    public void setXl_id(String xl_id) {
        this.xl_id = xl_id;
    }

    public void setOrder_NO(String order_NO) {
        this.order_NO = order_NO;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }

    @Override
    public String toString() {
        return "Xl{" +
                "rownum='" + rownum + '\'' +
                ", xl_id='" + xl_id + '\'' +
                ", order_NO='" + order_NO + '\'' +
                ", order_id='" + order_id + '\'' +
                ", tel='" + tel + '\'' +
                ", name='" + name + '\'' +
                ", date1='" + date1 + '\'' +
                ", time1='" + time1 + '\'' +
                ", room='" + room + '\'' +
                ", sl='" + sl + '\'' +
                ", date2='" + date2 + '\'' +
                ", time2='" + time2 + '\'' +
                ", onduty1n='" + onduty1n + '\'' +
                ", onduty2n='" + onduty2n + '\'' +
                ", onduty3n='" + onduty3n + '\'' +
                ", date3='" + date3 + '\'' +
                ", time3='" + time3 + '\'' +
                ", position='" + position + '\'' +
                ", picture_path='" + picture_path + '\'' +
                ", memo1='" + memo1 + '\'' +
                ", tag='" + tag + '\'' +
                ", room_in_id='" + room_in_id + '\'' +
                '}';
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }

    public void setTime2(String time2) {
        this.time2 = time2;
    }

    public void setOnduty1n(String onduty1n) {
        this.onduty1n = onduty1n;
    }

    public void setOnduty2n(String onduty2n) {
        this.onduty2n = onduty2n;
    }

    public void setOnduty3n(String onduty3n) {
        this.onduty3n = onduty3n;
    }

    public void setDate3(String date3) {
        this.date3 = date3;
    }

    public void setTime3(String time3) {
        this.time3 = time3;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setPicture_path(String picture_path) {
        this.picture_path = picture_path;
    }

    public void setMemo1(String memo1) {
        this.memo1 = memo1;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setRoom_in_id(String room_in_id) {
        this.room_in_id = room_in_id;
    }



    private String xl_id;//	 ID
    private String order_NO;//	预订单号
    private String order_id;//	预订表_id
    private String tel;//	手机号码
    private String name;//	寄存人
    private String date1;//	预订日期
    private String time1;//	预订时间
    private String room;//	房间号码
    private String sl;//	行李建数
    private String date2;//	寄存日期
    private String time2;//	寄存时间
    private String onduty1n;//	收领人
    private String onduty2n;//	送去人
    private String onduty3n;//	领取人
    private String date3;//	领取日期
    private String time3;//	领取时间
    private String position;//	存放位置
    private String picture_path;//	图片路径
    private String memo1;//	备注
    private String tag;//	"标记2：登记1：寄存0：领取"
    private String room_in_id;//

    public Xl(String rownum, String xl_id, String order_NO, String order_id, String tel, String name, String date1, String time1, String room, String sl, String date2, String time2, String onduty1n, String onduty2n, String onduty3n, String date3, String time3, String position, String picture_path, String memo1, String tag, String room_in_id) {
        this.rownum = rownum;
        this.xl_id = xl_id;
        this.order_NO = order_NO;
        this.order_id = order_id;
        this.tel = tel;
        this.name = name;
        this.date1 = date1;
        this.time1 = time1;
        this.room = room;
        this.sl = sl;
        this.date2 = date2;
        this.time2 = time2;
        this.onduty1n = onduty1n;
        this.onduty2n = onduty2n;
        this.onduty3n = onduty3n;
        this.date3 = date3;
        this.time3 = time3;
        this.position = position;
        this.picture_path = picture_path;
        this.memo1 = memo1;
        this.tag = tag;
        this.room_in_id = room_in_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(rownum);
        parcel.writeString(xl_id);
        parcel.writeString(order_NO);
        parcel.writeString(order_id);
        parcel.writeString(tel);
        parcel.writeString(name);
        parcel.writeString(date1);
        parcel.writeString(time1);
        parcel.writeString(room);
        parcel.writeString(sl);
        parcel.writeString(date2);
        parcel.writeString(time2);
        parcel.writeString(onduty1n);
        parcel.writeString(onduty2n);
        parcel.writeString(onduty3n);
        parcel.writeString(date3);
        parcel.writeString(time3);
        parcel.writeString(position);
        parcel.writeString(picture_path);
        parcel.writeString(memo1);
        parcel.writeString(tag);
        parcel.writeString(room_in_id);
    }
}
