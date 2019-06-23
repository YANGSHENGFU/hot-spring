package com.hotspr.ui.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Round implements Parcelable {

    private String rownum ;
    private String room_id ;
    private String ROOM ;
    private String CLASS ;
    private String STATE1 ;
    private String RESE ;
    private String FLOOR ;
    private String LHMC ;
    private String NAME ;
    private String HKP ;
    private String DIRE ;
    private String DIR1 ;
    private String STANNUM ;
    /**
     * 房间卫生状态T：停用    D：脏房  L：锁房  R：净房  M：维修S：清扫
     */
    private String STATE2 ;
    private String STATE3 ;
    private String RENT1 ;
    private String STATE4 ;
    private String STATE5 ;
    private String CONF ;
    private String in_date ;
    private String in_time ;
    private String out_date ;
    private String days ;
    private String zcp ;
    private String room_s ;
    private String room_n ;
    private String look_id ;
    /**
     * 清洁房间ID
     */
    private String room_wh_id ;
    private String wx_id ;
    private String tage ;
    private String look_cash_name ;
    private String look_date_call ;
    private String look_hotel_name ;
    private String look_time_call ;
    private String look_time_answer ;
    private String look_server_name ;
    private String look_time_out ;
    private String look_server_memo ;
    private String look_picture_path ;
    private String look_tage ;

    public Round(){

    }


    protected Round(Parcel in) {
        rownum = in.readString();
        room_id = in.readString();
        ROOM = in.readString();
        CLASS = in.readString();
        STATE1 = in.readString();
        RESE = in.readString();
        FLOOR = in.readString();
        LHMC = in.readString();
        NAME = in.readString();
        HKP = in.readString();
        DIRE = in.readString();
        DIR1 = in.readString();
        STANNUM = in.readString();
        STATE2 = in.readString();
        STATE3 = in.readString();
        RENT1 = in.readString();
        STATE4 = in.readString();
        STATE5 = in.readString();
        CONF = in.readString();
        in_date = in.readString();
        in_time = in.readString();
        out_date = in.readString();
        days = in.readString();
        zcp = in.readString();
        room_s = in.readString();
        room_n = in.readString();
        look_id = in.readString();
        room_wh_id = in.readString();
        wx_id = in.readString();
        tage = in.readString();
        look_cash_name = in.readString();
        look_date_call = in.readString();
        look_hotel_name = in.readString();
        look_time_call = in.readString();
        look_time_answer = in.readString();
        look_server_name = in.readString();
        look_time_out = in.readString();
        look_server_memo = in.readString();
        look_picture_path = in.readString();
        look_tage = in.readString();
        cl_class_old = in.readString();
        cl_class_n_old = in.readString();
        cl_room = in.readString();
        cl_onduty1n = in.readString();
        cl_onduty2n = in.readString();
        cl_date1 = in.readString();
        cl_time1 = in.readString();
        cl_onduty4n = in.readString();
        cl_date2 = in.readString();
        cl_time2 = in.readString();
        cl_onduty3n = in.readString();
        cl_class_new = in.readString();
        cl_class_n_new = in.readString();
        cl_date3 = in.readString();
        cl_time3 = in.readString();
        cl_check_er = in.readString();
        cl_check_time = in.readString();
        cl_picture_path = in.readString();
        cl_vido_path = in.readString();
        cl_memo1 = in.readString();
        cl_state = in.readString();
    }

    public static final Creator<Round> CREATOR = new Creator<Round>() {
        @Override
        public Round createFromParcel(Parcel in) {
            return new Round(in);
        }

        @Override
        public Round[] newArray(int size) {
            return new Round[size];
        }
    };

    public String getRownum() {
        return rownum;
    }

    public void setRownum(String rownum) {
        this.rownum = rownum;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getROOM() {
        return ROOM;
    }

    public void setROOM(String ROOM) {
        this.ROOM = ROOM;
    }

    public String getCLASS() {
        return CLASS;
    }

    public void setCLASS(String CLASS) {
        this.CLASS = CLASS;
    }

    public String getSTATE1() {
        return STATE1;
    }

    public void setSTATE1(String STATE1) {
        this.STATE1 = STATE1;
    }

    public String getRESE() {
        return RESE;
    }

    public void setRESE(String RESE) {
        this.RESE = RESE;
    }

    public String getFLOOR() {
        return FLOOR;
    }

    public void setFLOOR(String FLOOR) {
        this.FLOOR = FLOOR;
    }

    public String getLHMC() {
        return LHMC;
    }

    public void setLHMC(String LHMC) {
        this.LHMC = LHMC;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getHKP() {
        return HKP;
    }

    public void setHKP(String HKP) {
        this.HKP = HKP;
    }

    public String getDIRE() {
        return DIRE;
    }

    public void setDIRE(String DIRE) {
        this.DIRE = DIRE;
    }

    public String getDIR1() {
        return DIR1;
    }

    public void setDIR1(String DIR1) {
        this.DIR1 = DIR1;
    }

    public String getSTANNUM() {
        return STANNUM;
    }

    public void setSTANNUM(String STANNUM) {
        this.STANNUM = STANNUM;
    }

    public String getSTATE2() {
        return STATE2;
    }

    public void setSTATE2(String STATE2) {
        this.STATE2 = STATE2;
    }

    public String getSTATE3() {
        return STATE3;
    }

    public void setSTATE3(String STATE3) {
        this.STATE3 = STATE3;
    }

    public String getRENT1() {
        return RENT1;
    }

    public void setRENT1(String RENT1) {
        this.RENT1 = RENT1;
    }

    public String getSTATE4() {
        return STATE4;
    }

    public void setSTATE4(String STATE4) {
        this.STATE4 = STATE4;
    }

    public String getSTATE5() {
        return STATE5;
    }

    public void setSTATE5(String STATE5) {
        this.STATE5 = STATE5;
    }

    public String getCONF() {
        return CONF;
    }

    public void setCONF(String CONF) {
        this.CONF = CONF;
    }

    public String getIn_date() {
        return in_date;
    }

    public void setIn_date(String in_date) {
        this.in_date = in_date;
    }

    public String getIn_time() {
        return in_time;
    }

    public void setIn_time(String in_time) {
        this.in_time = in_time;
    }

    public String getOut_date() {
        return out_date;
    }

    public void setOut_date(String out_date) {
        this.out_date = out_date;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getZcp() {
        return zcp;
    }

    public void setZcp(String zcp) {
        this.zcp = zcp;
    }

    public String getRoom_s() {
        return room_s;
    }

    public void setRoom_s(String room_s) {
        this.room_s = room_s;
    }

    public String getRoom_n() {
        return room_n;
    }

    public void setRoom_n(String room_n) {
        this.room_n = room_n;
    }

    public String getLook_id() {
        return look_id;
    }

    public void setLook_id(String look_id) {
        this.look_id = look_id;
    }

    public String getRoom_wh_id() {
        return room_wh_id;
    }

    public void setRoom_wh_id(String room_wh_id) {
        this.room_wh_id = room_wh_id;
    }

    public String getWx_id() {
        return wx_id;
    }

    public void setWx_id(String wx_id) {
        this.wx_id = wx_id;
    }

    public String getTage() {
        return tage;
    }

    public void setTage(String tage) {
        this.tage = tage;
    }

    public String getLook_cash_name() {
        return look_cash_name;
    }

    public void setLook_cash_name(String look_cash_name) {
        this.look_cash_name = look_cash_name;
    }

    public String getLook_date_call() {
        return look_date_call;
    }

    public void setLook_date_call(String look_date_call) {
        this.look_date_call = look_date_call;
    }

    public String getLook_hotel_name() {
        return look_hotel_name;
    }

    public void setLook_hotel_name(String look_hotel_name) {
        this.look_hotel_name = look_hotel_name;
    }

    public String getLook_time_call() {
        return look_time_call;
    }

    public void setLook_time_call(String look_time_call) {
        this.look_time_call = look_time_call;
    }

    public String getLook_time_answer() {
        return look_time_answer;
    }

    public void setLook_time_answer(String look_time_answer) {
        this.look_time_answer = look_time_answer;
    }

    public String getLook_server_name() {
        return look_server_name;
    }

    public void setLook_server_name(String look_server_name) {
        this.look_server_name = look_server_name;
    }

    public String getLook_time_out() {
        return look_time_out;
    }

    public void setLook_time_out(String look_time_out) {
        this.look_time_out = look_time_out;
    }

    public String getLook_server_memo() {
        return look_server_memo;
    }

    public void setLook_server_memo(String look_server_memo) {
        this.look_server_memo = look_server_memo;
    }

    public String getLook_picture_path() {
        return look_picture_path;
    }

    public void setLook_picture_path(String look_picture_path) {
        this.look_picture_path = look_picture_path;
    }

    public String getLook_tage() {
        return look_tage;
    }

    public void setLook_tage(String look_tage) {
        this.look_tage = look_tage;
    }

    /**
     *原房类型编号
     */
    private String cl_class_old;

    /**
     * 取原房类型编号
     * @return
     */
    public String Getcl_class_old() { return cl_class_old; }
    /**
     * 设置原房类型编号
     */
    public void Setcl_class_old(String cl_class_old) { this.cl_class_old = cl_class_old; }
    /**
     *原房类型名称
     */
    private String cl_class_n_old;

    /**
     * 取原房类型名称
     * @return
     */
    public String Getcl_class_n_old() { return cl_class_n_old; }
    /**
     * 设置原房类型名称
     */
    public void Setcl_class_n_old(String cl_class_n_old) { this.cl_class_n_old = cl_class_n_old; }

    /**
     *房间号
     */
    private String cl_room;

    /**
     * 取房间号
     * @return
     */
    public String Getcl_room() { return cl_room; }
    /**
     * 设置房间号
     */
    public void Setcl_room(String cl_room) { this.cl_room = cl_room; }

    /**
     *安排人
     */
    private String cl_onduty1n;

    /**
     * 取安排人
     * @return
     */
    public String Getcl_onduty1n() { return cl_onduty1n; }
    /**
     * 设置安排人
     */
    public void Setcl_onduty1n(String cl_onduty1n) { this.cl_onduty1n = cl_onduty1n; }



    /**
     *服务员
     */
    private String cl_onduty2n;

    /**
     * 取服务员
     * @return
     */
    public String Getcl_onduty2n() { return cl_onduty2n; }
    /**
     * 设置服务员
     */
    public void Setcl_onduty2n(String cl_onduty2n) { this.cl_onduty2n = cl_onduty2n; }

    /**
     *安排日期
     */
    private String cl_date1;

    /**
     * 取安排日期
     * @return
     */
    public String Getcl_date1() { return cl_date1; }
    /**
     * 设置安排日期
     */
    public void Setcl_date1(String cl_date1) { this.cl_date1 = cl_date1; }

    /**
     *安排时间
     */
    private String cl_time1;

    /**
     * 取原安排时间
     * @return
     */
    public String Getcl_time1() { return cl_time1; }
    /**
     * 设置安排时间
     */
    public void Setcl_time1(String cl_time1) { this.cl_time1 = cl_time1; }


    /**
     *房型修改人
     */
    private String cl_onduty4n;

    /**
     * 取房型修改人
     * @return
     */
    public String Getcl_onduty4n() { return cl_onduty4n; }
    /**
     * 设置房型修改人
     */
    public void Setcl_onduty4n(String cl_onduty4n) { this.cl_onduty4n = cl_onduty4n; }

    /**
     *修改日期
     */
    private String cl_date2;

    /**
     * 取修改日期
     * @return
     */
    public String Getcl_date2() { return cl_date2; }
    /**
     * 设置修改日期
     */
    public void Setcl_date2(String cl_date2) { this.cl_date2 = cl_date2; }

    /**
     *修改时间
     */
    private String cl_time2;

    /**
     * 取修改时间
     * @return
     */
    public String Getcl_time2() { return cl_time2; }
    /**
     * 设置修改时间
     */
    public void Setcl_time2(String cl_time2) { this.cl_time2 = cl_time2; }
    /**
     *清洁员
     */
    private String cl_onduty3n;

    /**
     * 取清洁员
     * @return
     */
    public String Getcl_onduty3n() { return cl_onduty3n; }
    /**
     * 设置清洁员
     */
    public void Setcl_onduty3n(String cl_onduty3n) { this.cl_onduty3n = cl_onduty3n; }

    /**
     *变换类型
     */
    private String cl_class_new;

    /**
     * 取变换类型
     * @return
     */
    public String Getcl_class_new() { return cl_class_new; }
    /**
     * 设置变换类型
     */
    public void Setcl_class_new(String cl_class_new) { this.cl_class_new = cl_class_new; }

    /**
     *变换后类型名称
     */
    private String cl_class_n_new;

    /**
     * 取变换后类型名称
     * @return
     */
    public String Getcl_class_n_new() { return cl_class_n_new; }
    /**
     * 设置变换后类型名称
     */
    public void Setcl_class_n_new(String cl_class_n_new) { this.cl_class_n_new = cl_class_n_new; }

    /**
     *完成日期
     */
    private String cl_date3;

    /**
     * 取完成日期
     * @return
     */
    public String Getcl_date3() { return cl_date3; }
    /**
     * 设置完成日期
     */
    public void Setcl_date3(String cl_date3) { this.cl_date3 = cl_date3; }

    /**
     *完成时间
     */
    private String cl_time3;

    /**
     * 取完成时间
     * @return
     */
    public String Getcl_time3() { return cl_time3; }
    /**
     * 设置完成时间
     */
    public void Setcl_time3(String cl_time3) { this.cl_time3 = cl_time3; }

    /**
     *房间检查人
     */
    private String cl_check_er;

    /**
     * 取房间检查人
     * @return
     */
    public String Getcl_check_er() { return cl_check_er; }
    /**
     * 设置房间检查人
     */
    public void Setcl_check_er(String cl_check_er) { this.cl_check_er = cl_check_er; }

    /**
     *检查时间
     */
    private String cl_check_time;

    /**
     * 取检查时间
     * @return
     */
    public String Getcl_check_time() { return cl_check_time; }
    /**
     * 设置检查时间
     */
    public void Setcl_check_time(String cl_check_time) { this.cl_check_time = cl_check_time; }

    /**
     *图片路径
     */
    private String cl_picture_path;

    /**
     * 取图片路径
     * @return
     */
    public String Getcl_picture_path() { return cl_picture_path; }
    /**
     * 设置图片路径
     */
    public void Setcl_picture_path(String cl_picture_path) { this.cl_picture_path = cl_picture_path; }

    /**
     *视频路径
     */
    private String cl_vido_path;

    /**
     * 取视频路径
     * @return
     */
    public String Getcl_vido_path() { return cl_vido_path; }
    /**
     * 设置视频路径
     */
    public void Setcl_vido_path(String cl_vido_path) { this.cl_vido_path = cl_vido_path; }

    /**
     *备注
     */
    private String cl_memo1;

    /**
     * 取备注
     * @return
     */
    public String Getcl_memo1() { return cl_memo1; }
    /**
     * 设置备注
     */
    public void Setcl_memo1(String cl_memo1) { this.cl_memo1 = cl_memo1; }


    /**
     *状态0未完成  1已完成 2已检查
     */
    private String cl_state;

    /**
     * 取备注
     * @return
     */
    public String Getcl_state() { return cl_state; }
    /**
     * 设置备注
     */
    public void Setcl_state(String cl_state) { this.cl_state = cl_state; }


    /**
     * 取清洁房间ID
     * @return
     */
    public String Getroom_wh_id() { return room_wh_id; }
    /**
     * 设置清洁房间ID
     */
    public void Setroom_wh_id(String room_wh_id) { this.room_wh_id = room_wh_id; }




    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(rownum);
        dest.writeString(room_id);
        dest.writeString(ROOM);
        dest.writeString(CLASS);
        dest.writeString(STATE1);
        dest.writeString(RESE);
        dest.writeString(FLOOR);
        dest.writeString(LHMC);
        dest.writeString(NAME);
        dest.writeString(HKP);
        dest.writeString(DIRE);
        dest.writeString(DIR1);
        dest.writeString(STANNUM);
        dest.writeString(STATE2);
        dest.writeString(STATE3);
        dest.writeString(RENT1);
        dest.writeString(STATE4);
        dest.writeString(STATE5);
        dest.writeString(CONF);
        dest.writeString(in_date);
        dest.writeString(in_time);
        dest.writeString(out_date);
        dest.writeString(days);
        dest.writeString(zcp);
        dest.writeString(room_s);
        dest.writeString(room_n);
        dest.writeString(look_id);
        dest.writeString(room_wh_id);
        dest.writeString(wx_id);
        dest.writeString(tage);
        dest.writeString(look_cash_name);
        dest.writeString(look_date_call);
        dest.writeString(look_hotel_name);
        dest.writeString(look_time_call);
        dest.writeString(look_time_answer);
        dest.writeString(look_server_name);
        dest.writeString(look_time_out);
        dest.writeString(look_server_memo);
        dest.writeString(look_picture_path);
        dest.writeString(look_tage);
        dest.writeString(cl_class_old);
        dest.writeString(cl_class_n_old);
        dest.writeString(cl_room);
        dest.writeString(cl_onduty1n);
        dest.writeString(cl_onduty2n);
        dest.writeString(cl_date1);
        dest.writeString(cl_time1);
        dest.writeString(cl_onduty4n);
        dest.writeString(cl_date2);
        dest.writeString(cl_time2);
        dest.writeString(cl_onduty3n);
        dest.writeString(cl_class_new);
        dest.writeString(cl_class_n_new);
        dest.writeString(cl_date3);
        dest.writeString(cl_time3);
        dest.writeString(cl_check_er);
        dest.writeString(cl_check_time);
        dest.writeString(cl_picture_path);
        dest.writeString(cl_vido_path);
        dest.writeString(cl_memo1);
        dest.writeString(cl_state);
    }
}
