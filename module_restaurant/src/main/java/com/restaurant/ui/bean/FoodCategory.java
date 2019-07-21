package com.restaurant.ui.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class FoodCategory implements Parcelable {

    private String rownum;
    private String id;
    private String BM;
    private String MC;
    private String SORT;
    private String prn_tag;
    private String ff_tag;
    private String total_tag;
    private String total_name;
    private String tasteflag;

    public FoodCategory() {
    }

    protected FoodCategory(Parcel in) {
        rownum = in.readString();
        id = in.readString();
        BM = in.readString();
        MC = in.readString();
        SORT = in.readString();
        prn_tag = in.readString();
        ff_tag = in.readString();
        total_tag = in.readString();
        total_name = in.readString();
        tasteflag = in.readString();
    }

    public static final Creator<FoodCategory> CREATOR = new Creator<FoodCategory>() {
        @Override
        public FoodCategory createFromParcel(Parcel in) {
            return new FoodCategory(in);
        }

        @Override
        public FoodCategory[] newArray(int size) {
            return new FoodCategory[size];
        }
    };

    public String getRownum() {
        return rownum;
    }

    public void setRownum(String rownum) {
        this.rownum = rownum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBM() {
        return BM;
    }

    public void setBM(String BM) {
        this.BM = BM;
    }

    public String getMC() {
        return MC;
    }

    public void setMC(String MC) {
        this.MC = MC;
    }

    public String getSORT() {
        return SORT;
    }

    public void setSORT(String SORT) {
        this.SORT = SORT;
    }

    public String getPrn_tag() {
        return prn_tag;
    }

    public void setPrn_tag(String prn_tag) {
        this.prn_tag = prn_tag;
    }

    public String getFf_tag() {
        return ff_tag;
    }

    public void setFf_tag(String ff_tag) {
        this.ff_tag = ff_tag;
    }

    public String getTotal_tag() {
        return total_tag;
    }

    public void setTotal_tag(String total_tag) {
        this.total_tag = total_tag;
    }

    public String getTotal_name() {
        return total_name;
    }

    public void setTotal_name(String total_name) {
        this.total_name = total_name;
    }

    public String getTasteflag() {
        return tasteflag;
    }

    public void setTasteflag(String tasteflag) {
        this.tasteflag = tasteflag;
    }

    @Override
    public String toString() {
        return "FoodCategory{" +
                "rownum='" + rownum + '\'' +
                ", id='" + id + '\'' +
                ", BM='" + BM + '\'' +
                ", MC='" + MC + '\'' +
                ", SORT='" + SORT + '\'' +
                ", prn_tag='" + prn_tag + '\'' +
                ", ff_tag='" + ff_tag + '\'' +
                ", total_tag='" + total_tag + '\'' +
                ", total_name='" + total_name + '\'' +
                ", tasteflag='" + tasteflag + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(rownum);
        dest.writeString(id);
        dest.writeString(BM);
        dest.writeString(MC);
        dest.writeString(SORT);
        dest.writeString(prn_tag);
        dest.writeString(ff_tag);
        dest.writeString(total_tag);
        dest.writeString(total_name);
        dest.writeString(tasteflag);
    }
}

