package com.restaurant.ui.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class SetMeal implements Parcelable {

    private String rownum;
    private String tc_id;
    private String tc_no;
    private String tc_unit;
    private String tc_bm;
    private String tc_name;
    private String tc_price;
    private String select_tag;
    private String mark;

    public SetMeal() {
    }

    protected SetMeal(Parcel in) {
        rownum = in.readString();
        tc_id = in.readString();
        tc_no = in.readString();
        tc_unit = in.readString();
        tc_unit = in.readString();
        tc_name = in.readString();
        tc_price = in.readString();
        select_tag = in.readString();
        mark = in.readString();
    }

    public static final Creator<SetMeal> CREATOR = new Creator<SetMeal>() {
        @Override
        public SetMeal createFromParcel(Parcel in) {
            return new SetMeal(in);
        }

        @Override
        public SetMeal[] newArray(int size) {
            return new SetMeal[size];
        }
    };

    public String getRownum() {
        return rownum;
    }

    public void setRownum(String rownum) {
        this.rownum = rownum;
    }

    public String getTc_id() {
        return tc_id;
    }

    public void setTc_id(String tc_id) {
        this.tc_id = tc_id;
    }

    public String getTc_no() {
        return tc_no;
    }

    public void setTc_no(String tc_no) {
        this.tc_no = tc_no;
    }

    public String getTc_unit() {
        return tc_unit;
    }

    public void setTc_unit(String tc_unit) {
        this.tc_unit = tc_unit;
    }

    public String getTc_bm() {
        return tc_bm;
    }

    public void setTc_bm(String tc_bm) {
        this.tc_bm = tc_bm;
    }

    public String getTc_name() {
        return tc_name;
    }

    public void setTc_name(String tc_name) {
        this.tc_name = tc_name;
    }

    public String getTc_price() {
        return tc_price;
    }

    public void setTc_price(String tc_price) {
        this.tc_price = tc_price;
    }

    public String getSelect_tag() {
        return select_tag;
    }

    public void setSelect_tag(String select_tag) {
        this.select_tag = select_tag;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "VarietyDishes{" +
                "rownum='" + rownum + '\'' +
                ", tc_id='" + tc_id + '\'' +
                ", tc_no='" + tc_no + '\'' +
                ", tc_unit='" + tc_unit + '\'' +
                ", tc_bm='" + tc_bm + '\'' +
                ", tc_name='" + tc_name + '\'' +
                ", tc_price='" + tc_price + '\'' +
                ", select_tag='" + select_tag + '\'' +
                ", mark='" + mark + '\''  +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(rownum);
        dest.writeString(tc_id);
        dest.writeString(tc_no);
        dest.writeString(tc_unit);
        dest.writeString(tc_bm);
        dest.writeString(tc_name);
        dest.writeString(tc_price);
        dest.writeString(select_tag);
        dest.writeString(mark);
    }
}
