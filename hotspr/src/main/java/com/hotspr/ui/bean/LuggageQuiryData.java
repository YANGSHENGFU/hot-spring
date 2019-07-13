package com.hotspr.ui.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class LuggageQuiryData implements Parcelable {

    String rownum;
    String xl_id;
    String order_id;
    String order_no;
    String tel;
    String name;
    String date1;
    String time1;
    String room;
    String sl;
    String date2;
    String time2;
    String onduty1n;
    String onduty2n;
    String onduty3n;
    String date3;
    String time3;
    String position;
    String picture_path;
    String memo1;
    String room_in_id;
    String tag;

    public LuggageQuiryData(){

    }

    public String getRownum() {
        return rownum;
    }

    public void setRownum(String rownum) {
        this.rownum = rownum;
    }

    public String getXl_id() {
        return xl_id;
    }

    public void setXl_id(String xl_id) {
        this.xl_id = xl_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getSl() {
        return sl;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }

    public String getDate2() {
        return date2;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }

    public String getTime2() {
        return time2;
    }

    public void setTime2(String time2) {
        this.time2 = time2;
    }

    public String getOnduty1n() {
        return onduty1n;
    }

    public void setOnduty1n(String onduty1n) {
        this.onduty1n = onduty1n;
    }

    public String getOnduty2n() {
        return onduty2n;
    }

    public void setOnduty2n(String onduty2n) {
        this.onduty2n = onduty2n;
    }

    public String getOnduty3n() {
        return onduty3n;
    }

    public void setOnduty3n(String onduty3n) {
        this.onduty3n = onduty3n;
    }

    public String getDate3() {
        return date3;
    }

    public void setDate3(String date3) {
        this.date3 = date3;
    }

    public String getTime3() {
        return time3;
    }

    public void setTime3(String time3) {
        this.time3 = time3;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPicture_path() {
        return picture_path;
    }

    public void setPicture_path(String picture_path) {
        this.picture_path = picture_path;
    }

    public String getMemo1() {
        return memo1;
    }

    public void setMemo1(String memo1) {
        this.memo1 = memo1;
    }

    public String getRoom_in_id() {
        return room_in_id;
    }

    public void setRoom_in_id(String room_in_id) {
        this.room_in_id = room_in_id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public static Creator<LuggageQuiryData> getCREATOR() {
        return CREATOR;
    }

    public LuggageQuiryData(Parcel in) {
        rownum = in.readString();
        xl_id = in.readString();
        order_id = in.readString();
        order_no = in.readString();
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
        room_in_id = in.readString();
        tag = in.readString();
    }

    public static final Creator<LuggageQuiryData> CREATOR = new Creator<LuggageQuiryData>() {
        @Override
        public LuggageQuiryData createFromParcel(Parcel in) {
            return new LuggageQuiryData(in);
        }

        @Override
        public LuggageQuiryData[] newArray(int size) {
            return new LuggageQuiryData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(rownum);
        dest.writeString(xl_id);
        dest.writeString(order_id);
        dest.writeString(order_no);
        dest.writeString(tel);
        dest.writeString(name);
        dest.writeString(date1);
        dest.writeString(time1);
        dest.writeString(room);
        dest.writeString(sl);
        dest.writeString(date2);
        dest.writeString(time2);
        dest.writeString(onduty1n);
        dest.writeString(onduty2n);
        dest.writeString(onduty3n);
        dest.writeString(date3);
        dest.writeString(time3);
        dest.writeString(position);
        dest.writeString(picture_path);
        dest.writeString(memo1);
        dest.writeString(room_in_id);
        dest.writeString(tag);
    }
}
