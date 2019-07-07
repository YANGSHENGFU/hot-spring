package com.hotspr.ui.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Deposit implements Parcelable {

    private String rownum ;
    private String id ;
    private String NAME ;
    private String NUMBER;
    private String CINDATEI;
    private String CINDATEIW;
    private String COUTDATE;
    private String COUTDATEW;
    private String INTIME;
    private String OUTTIME;
    private String ZNAME;
    private String GROUPID;
    private String AGENTID;
    private String CONF;
    private String h_NULL;
    private String ONDUTY;
    private String ONDUTYN;
    private String NATION;
    private String DISC;
    private String FLAG;
    private String FFLAG;
    private String MTH;
    private String FXYFZ;
    private String FFOOD;
    private String STAYS;
    private String CATA;
    private String ONDUTY2;
    private String ONDUTY2N;
    private String TELE;
    private String BDATE;
    private String FSERV;
    private String MEMO1;
    private String MEMO2;
    private String MEMO3;
    private String CINDATE;
    private String ONDUTY3;
    private String ONDUTY3N;
    private String COUTDATEL;
    private String FGRENT;
    private String SOURCE;
    private String FTRAN;
    private String RDATE;
    private String GROUPNO;
    private String GROOMS;
    private String EDITDATE;
    private String EDITTIME;
    private String EDITOR;
    private String dishi;
    private String tage;
    private String u_name;
    private String u_id;
    private String part_id;
    private String y_status;
    private String y_tamount;
    private String y_pamount;
    private String y_djqid;
    private String y_ordertype;
    private String y_ordernumber;
    private String y_bookmoney;
    private String y_ydfs;
    private String tp_no;
    private String ROOM;

    public Deposit(){

    }


    public Deposit(Parcel in) {
        rownum = in.readString();
        id = in.readString();
        NAME = in.readString();
        NUMBER = in.readString();
        CINDATEI = in.readString();
        CINDATEIW = in.readString();
        COUTDATE = in.readString();
        COUTDATEW = in.readString();
        INTIME = in.readString();
        OUTTIME = in.readString();
        ZNAME = in.readString();
        GROUPID = in.readString();
        AGENTID = in.readString();
        CONF = in.readString();
        h_NULL = in.readString();
        ONDUTY = in.readString();
        ONDUTYN = in.readString();
        NATION = in.readString();
        DISC = in.readString();
        FLAG = in.readString();
        FFLAG = in.readString();
        MTH = in.readString();
        FXYFZ = in.readString();
        FFOOD = in.readString();
        STAYS = in.readString();
        CATA = in.readString();
        ONDUTY2 = in.readString();
        ONDUTY2N = in.readString();
        TELE = in.readString();
        BDATE = in.readString();
        FSERV = in.readString();
        MEMO1 = in.readString();
        MEMO2 = in.readString();
        MEMO3 = in.readString();
        CINDATE = in.readString();
        ONDUTY3 = in.readString();
        ONDUTY3N = in.readString();
        COUTDATEL = in.readString();
        FGRENT = in.readString();
        SOURCE = in.readString();
        FTRAN = in.readString();
        RDATE = in.readString();
        GROUPNO = in.readString();
        GROOMS = in.readString();
        EDITDATE = in.readString();
        EDITTIME = in.readString();
        EDITOR = in.readString();
        dishi = in.readString();
        tage = in.readString();
        u_name = in.readString();
        u_id = in.readString();
        part_id = in.readString();
        y_status = in.readString();
        y_tamount = in.readString();
        y_pamount = in.readString();
        y_djqid = in.readString();
        y_ordertype = in.readString();
        y_ordernumber = in.readString();
        y_bookmoney = in.readString();
        y_ydfs = in.readString();
        tp_no = in.readString();
        ROOM = in.readString();
    }

    public static final Creator<Deposit> CREATOR = new Creator<Deposit>() {
        @Override
        public Deposit createFromParcel(Parcel in) {
            return new Deposit(in);
        }

        @Override
        public Deposit[] newArray(int size) {
            return new Deposit[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(rownum);
        dest.writeString(id);
        dest.writeString(NAME);
        dest.writeString(NUMBER);
        dest.writeString(CINDATEI);
        dest.writeString(CINDATEIW);
        dest.writeString(COUTDATE);
        dest.writeString(COUTDATEW);
        dest.writeString(INTIME);
        dest.writeString(OUTTIME);
        dest.writeString(ZNAME);
        dest.writeString(GROUPID);
        dest.writeString(AGENTID);
        dest.writeString(CONF);
        dest.writeString(h_NULL);
        dest.writeString(ONDUTY);
        dest.writeString(ONDUTYN);
        dest.writeString(NATION);
        dest.writeString(DISC);
        dest.writeString(FLAG);
        dest.writeString(FFLAG);
        dest.writeString(MTH);
        dest.writeString(FXYFZ);
        dest.writeString(FFOOD);
        dest.writeString(STAYS);
        dest.writeString(CATA);
        dest.writeString(ONDUTY2);
        dest.writeString(ONDUTY2N);
        dest.writeString(TELE);
        dest.writeString(BDATE);
        dest.writeString(FSERV);
        dest.writeString(MEMO1);
        dest.writeString(MEMO2);
        dest.writeString(MEMO3);
        dest.writeString(CINDATE);
        dest.writeString(ONDUTY3);
        dest.writeString(ONDUTY3N);
        dest.writeString(COUTDATEL);
        dest.writeString(FGRENT);
        dest.writeString(SOURCE);
        dest.writeString(FTRAN);
        dest.writeString(RDATE);
        dest.writeString(GROUPNO);
        dest.writeString(GROOMS);
        dest.writeString(EDITDATE);
        dest.writeString(EDITTIME);
        dest.writeString(EDITOR);
        dest.writeString(dishi);
        dest.writeString(tage);
        dest.writeString(u_name);
        dest.writeString(u_id);
        dest.writeString(part_id);
        dest.writeString(y_status);
        dest.writeString(y_tamount);
        dest.writeString(y_pamount);
        dest.writeString(y_djqid);
        dest.writeString(y_ordertype);
        dest.writeString(y_ordernumber);
        dest.writeString(y_bookmoney);
        dest.writeString(y_ydfs);
        dest.writeString(tp_no);
        dest.writeString(ROOM);
    }

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

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getNUMBER() {
        return NUMBER;
    }

    public void setNUMBER(String NUMBER) {
        this.NUMBER = NUMBER;
    }

    public String getCINDATEI() {
        return CINDATEI;
    }

    public void setCINDATEI(String CINDATEI) {
        this.CINDATEI = CINDATEI;
    }

    public String getCINDATEIW() {
        return CINDATEIW;
    }

    public void setCINDATEIW(String CINDATEIW) {
        this.CINDATEIW = CINDATEIW;
    }

    public String getCOUTDATE() {
        return COUTDATE;
    }

    public void setCOUTDATE(String COUTDATE) {
        this.COUTDATE = COUTDATE;
    }

    public String getCOUTDATEW() {
        return COUTDATEW;
    }

    public void setCOUTDATEW(String COUTDATEW) {
        this.COUTDATEW = COUTDATEW;
    }

    public String getINTIME() {
        return INTIME;
    }

    public void setINTIME(String INTIME) {
        this.INTIME = INTIME;
    }

    public String getOUTTIME() {
        return OUTTIME;
    }

    public void setOUTTIME(String OUTTIME) {
        this.OUTTIME = OUTTIME;
    }

    public String getZNAME() {
        return ZNAME;
    }

    public void setZNAME(String ZNAME) {
        this.ZNAME = ZNAME;
    }

    public String getGROUPID() {
        return GROUPID;
    }

    public void setGROUPID(String GROUPID) {
        this.GROUPID = GROUPID;
    }

    public String getAGENTID() {
        return AGENTID;
    }

    public void setAGENTID(String AGENTID) {
        this.AGENTID = AGENTID;
    }

    public String getCONF() {
        return CONF;
    }

    public void setCONF(String CONF) {
        this.CONF = CONF;
    }

    public String getH_NULL() {
        return h_NULL;
    }

    public void setH_NULL(String h_NULL) {
        this.h_NULL = h_NULL;
    }

    public String getONDUTY() {
        return ONDUTY;
    }

    public void setONDUTY(String ONDUTY) {
        this.ONDUTY = ONDUTY;
    }

    public String getONDUTYN() {
        return ONDUTYN;
    }

    public void setONDUTYN(String ONDUTYN) {
        this.ONDUTYN = ONDUTYN;
    }

    public String getNATION() {
        return NATION;
    }

    public void setNATION(String NATION) {
        this.NATION = NATION;
    }

    public String getDISC() {
        return DISC;
    }

    public void setDISC(String DISC) {
        this.DISC = DISC;
    }

    public String getFLAG() {
        return FLAG;
    }

    public void setFLAG(String FLAG) {
        this.FLAG = FLAG;
    }

    public String getFFLAG() {
        return FFLAG;
    }

    public void setFFLAG(String FFLAG) {
        this.FFLAG = FFLAG;
    }

    public String getMTH() {
        return MTH;
    }

    public void setMTH(String MTH) {
        this.MTH = MTH;
    }

    public String getFXYFZ() {
        return FXYFZ;
    }

    public void setFXYFZ(String FXYFZ) {
        this.FXYFZ = FXYFZ;
    }

    public String getFFOOD() {
        return FFOOD;
    }

    public void setFFOOD(String FFOOD) {
        this.FFOOD = FFOOD;
    }

    public String getSTAYS() {
        return STAYS;
    }

    public void setSTAYS(String STAYS) {
        this.STAYS = STAYS;
    }

    public String getCATA() {
        return CATA;
    }

    public void setCATA(String CATA) {
        this.CATA = CATA;
    }

    public String getONDUTY2() {
        return ONDUTY2;
    }

    public void setONDUTY2(String ONDUTY2) {
        this.ONDUTY2 = ONDUTY2;
    }

    public String getONDUTY2N() {
        return ONDUTY2N;
    }

    public void setONDUTY2N(String ONDUTY2N) {
        this.ONDUTY2N = ONDUTY2N;
    }

    public String getTELE() {
        return TELE;
    }

    public void setTELE(String TELE) {
        this.TELE = TELE;
    }

    public String getBDATE() {
        return BDATE;
    }

    public void setBDATE(String BDATE) {
        this.BDATE = BDATE;
    }

    public String getFSERV() {
        return FSERV;
    }

    public void setFSERV(String FSERV) {
        this.FSERV = FSERV;
    }

    public String getMEMO1() {
        return MEMO1;
    }

    public void setMEMO1(String MEMO1) {
        this.MEMO1 = MEMO1;
    }

    public String getMEMO2() {
        return MEMO2;
    }

    public void setMEMO2(String MEMO2) {
        this.MEMO2 = MEMO2;
    }

    public String getMEMO3() {
        return MEMO3;
    }

    public void setMEMO3(String MEMO3) {
        this.MEMO3 = MEMO3;
    }

    public String getCINDATE() {
        return CINDATE;
    }

    public void setCINDATE(String CINDATE) {
        this.CINDATE = CINDATE;
    }

    public String getONDUTY3() {
        return ONDUTY3;
    }

    public void setONDUTY3(String ONDUTY3) {
        this.ONDUTY3 = ONDUTY3;
    }

    public String getONDUTY3N() {
        return ONDUTY3N;
    }

    public void setONDUTY3N(String ONDUTY3N) {
        this.ONDUTY3N = ONDUTY3N;
    }

    public String getCOUTDATEL() {
        return COUTDATEL;
    }

    public void setCOUTDATEL(String COUTDATEL) {
        this.COUTDATEL = COUTDATEL;
    }

    public String getFGRENT() {
        return FGRENT;
    }

    public void setFGRENT(String FGRENT) {
        this.FGRENT = FGRENT;
    }

    public String getSOURCE() {
        return SOURCE;
    }

    public void setSOURCE(String SOURCE) {
        this.SOURCE = SOURCE;
    }

    public String getFTRAN() {
        return FTRAN;
    }

    public void setFTRAN(String FTRAN) {
        this.FTRAN = FTRAN;
    }

    public String getRDATE() {
        return RDATE;
    }

    public void setRDATE(String RDATE) {
        this.RDATE = RDATE;
    }

    public String getGROUPNO() {
        return GROUPNO;
    }

    public void setGROUPNO(String GROUPNO) {
        this.GROUPNO = GROUPNO;
    }

    public String getGROOMS() {
        return GROOMS;
    }

    public void setGROOMS(String GROOMS) {
        this.GROOMS = GROOMS;
    }

    public String getEDITDATE() {
        return EDITDATE;
    }

    public void setEDITDATE(String EDITDATE) {
        this.EDITDATE = EDITDATE;
    }

    public String getEDITTIME() {
        return EDITTIME;
    }

    public void setEDITTIME(String EDITTIME) {
        this.EDITTIME = EDITTIME;
    }

    public String getEDITOR() {
        return EDITOR;
    }

    public void setEDITOR(String EDITOR) {
        this.EDITOR = EDITOR;
    }

    public String getDishi() {
        return dishi;
    }

    public void setDishi(String dishi) {
        this.dishi = dishi;
    }

    public String getTage() {
        return tage;
    }

    public void setTage(String tage) {
        this.tage = tage;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getPart_id() {
        return part_id;
    }

    public void setPart_id(String part_id) {
        this.part_id = part_id;
    }

    public String getY_status() {
        return y_status;
    }

    public void setY_status(String y_status) {
        this.y_status = y_status;
    }

    public String getY_tamount() {
        return y_tamount;
    }

    public void setY_tamount(String y_tamount) {
        this.y_tamount = y_tamount;
    }

    public String getY_pamount() {
        return y_pamount;
    }

    public void setY_pamount(String y_pamount) {
        this.y_pamount = y_pamount;
    }

    public String getY_djqid() {
        return y_djqid;
    }

    public void setY_djqid(String y_djqid) {
        this.y_djqid = y_djqid;
    }

    public String getY_ordertype() {
        return y_ordertype;
    }

    public void setY_ordertype(String y_ordertype) {
        this.y_ordertype = y_ordertype;
    }

    public String getY_ordernumber() {
        return y_ordernumber;
    }

    public void setY_ordernumber(String y_ordernumber) {
        this.y_ordernumber = y_ordernumber;
    }

    public String getY_bookmoney() {
        return y_bookmoney;
    }

    public void setY_bookmoney(String y_bookmoney) {
        this.y_bookmoney = y_bookmoney;
    }

    public String getY_ydfs() {
        return y_ydfs;
    }

    public void setY_ydfs(String y_ydfs) {
        this.y_ydfs = y_ydfs;
    }

    public String getTp_no() {
        return tp_no;
    }

    public void setTp_no(String tp_no) {
        this.tp_no = tp_no;
    }

    public String getROOM() {
        return ROOM;
    }

    public void setROOM(String ROOM) {
        this.ROOM = ROOM;
    }

    public static Creator<Deposit> getCREATOR() {
        return CREATOR;
    }
}
