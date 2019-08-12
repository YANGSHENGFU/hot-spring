package com.restaurant.ui.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class TableNumber implements Parcelable {

    private String rownum;
    private String CTBM;
    private String CZBM;
    private String CZMC;
    private String CZZT;
    private String CTMC;
    private String KCM;
    private String QH;
    private String RS;
    private String JDBM;
    private String JDXM;
    private String BH;
    private String KRSM;
    private String KTSJ;
    private String KTRQ;
    private String TJRQ;
    private String KRBH;
    private String ZKM1;
    private String ZKM;
    private String DZDAH;
    private String PZR;
    private String PZRDM;
    private String ZHZT;
    private String KRZT;
    private String MFWF;
    private String MCWF;
    private String MZDXF;
    private String FWFBL;
    private String CWFJE;
    private String ZDXFJE;
    private String FWF;
    private String CWF;
    private String ZDXF;
    private String ZXF;
    private String TOTAL;
    private String FKFS;
    private String FKFSM;
    private String BZ;
    private String SKJE;
    private String TKJE;
    private String SKBH;
    private String SKXM;
    private String KH;
    private String ZH;
    private String ZHMC;
    private String JZBZ;
    private String JZRQ;
    private String JZSJ;
    private String TJFLAG;
    private String key_in;
    private String key_out;
    private String call_time;
    private String memo;
    private String TAGE;

    public TableNumber(){

    }


    protected TableNumber(Parcel in) {
        rownum = in.readString();
        CTBM = in.readString();
        CZBM = in.readString();
        CZMC = in.readString();
        CZZT = in.readString();
        CTMC = in.readString();
        KCM = in.readString();
        QH = in.readString();
        RS = in.readString();
        JDBM = in.readString();
        JDXM = in.readString();
        BH = in.readString();
        KRSM = in.readString();
        KTSJ = in.readString();
        KTRQ = in.readString();
        TJRQ = in.readString();
        KRBH = in.readString();
        ZKM1 = in.readString();
        ZKM = in.readString();
        DZDAH = in.readString();
        PZR = in.readString();
        PZRDM = in.readString();
        ZHZT = in.readString();
        KRZT = in.readString();
        MFWF = in.readString();
        MCWF = in.readString();
        MZDXF = in.readString();
        FWFBL = in.readString();
        CWFJE = in.readString();
        ZDXFJE = in.readString();
        FWF = in.readString();
        CWF = in.readString();
        ZDXF = in.readString();
        ZXF = in.readString();
        TOTAL = in.readString();
        FKFS = in.readString();
        FKFSM = in.readString();
        BZ = in.readString();
        SKJE = in.readString();
        TKJE = in.readString();
        SKBH = in.readString();
        SKXM = in.readString();
        KH = in.readString();
        ZH = in.readString();
        ZHMC = in.readString();
        JZBZ = in.readString();
        JZRQ = in.readString();
        JZSJ = in.readString();
        TJFLAG = in.readString();
        key_in = in.readString();
        key_out = in.readString();
        call_time = in.readString();
        memo = in.readString();
        TAGE = in.readString();
    }

    public static final Creator<TableNumber> CREATOR = new Creator<TableNumber>() {
        @Override
        public TableNumber createFromParcel(Parcel in) {
            return new TableNumber(in);
        }

        @Override
        public TableNumber[] newArray(int size) {
            return new TableNumber[size];
        }
    };

    public String getRownum() {
        return rownum;
    }

    public void setRownum(String rownum) {
        this.rownum = rownum;
    }

    public String getCTBM() {
        return CTBM;
    }

    public void setCTBM(String CTBM) {
        this.CTBM = CTBM;
    }

    public String getCZBM() {
        return CZBM;
    }

    public void setCZBM(String CZBM) {
        this.CZBM = CZBM;
    }

    public String getCZMC() {
        return CZMC;
    }

    public void setCZMC(String CZMC) {
        this.CZMC = CZMC;
    }

    public String getCZZT() {
        return CZZT;
    }

    public void setCZZT(String CZZT) {
        this.CZZT = CZZT;
    }

    public String getCTMC() {
        return CTMC;
    }

    public void setCTMC(String CTMC) {
        this.CTMC = CTMC;
    }

    public String getKCM() {
        return KCM;
    }

    public void setKCM(String KCM) {
        this.KCM = KCM;
    }

    public String getQH() {
        return QH;
    }

    public void setQH(String QH) {
        this.QH = QH;
    }

    public String getRS() {
        return RS;
    }

    public void setRS(String RS) {
        this.RS = RS;
    }

    public String getJDBM() {
        return JDBM;
    }

    public void setJDBM(String JDBM) {
        this.JDBM = JDBM;
    }

    public String getJDXM() {
        return JDXM;
    }

    public void setJDXM(String JDXM) {
        this.JDXM = JDXM;
    }

    public String getBH() {
        return BH;
    }

    public void setBH(String BH) {
        this.BH = BH;
    }

    public String getKRSM() {
        return KRSM;
    }

    public void setKRSM(String KRSM) {
        this.KRSM = KRSM;
    }

    public String getKTSJ() {
        return KTSJ;
    }

    public void setKTSJ(String KTSJ) {
        this.KTSJ = KTSJ;
    }

    public String getKTRQ() {
        return KTRQ;
    }

    public void setKTRQ(String KTRQ) {
        this.KTRQ = KTRQ;
    }

    public String getTJRQ() {
        return TJRQ;
    }

    public void setTJRQ(String TJRQ) {
        this.TJRQ = TJRQ;
    }

    public String getKRBH() {
        return KRBH;
    }

    public void setKRBH(String KRBH) {
        this.KRBH = KRBH;
    }

    public String getZKM1() {
        return ZKM1;
    }

    public void setZKM1(String ZKM1) {
        this.ZKM1 = ZKM1;
    }

    public String getZKM() {
        return ZKM;
    }

    public void setZKM(String ZKM) {
        this.ZKM = ZKM;
    }

    public String getDZDAH() {
        return DZDAH;
    }

    public void setDZDAH(String DZDAH) {
        this.DZDAH = DZDAH;
    }

    public String getPZR() {
        return PZR;
    }

    public void setPZR(String PZR) {
        this.PZR = PZR;
    }

    public String getPZRDM() {
        return PZRDM;
    }

    public void setPZRDM(String PZRDM) {
        this.PZRDM = PZRDM;
    }

    public String getZHZT() {
        return ZHZT;
    }

    public void setZHZT(String ZHZT) {
        this.ZHZT = ZHZT;
    }

    public String getKRZT() {
        return KRZT;
    }

    public void setKRZT(String KRZT) {
        this.KRZT = KRZT;
    }

    public String getMFWF() {
        return MFWF;
    }

    public void setMFWF(String MFWF) {
        this.MFWF = MFWF;
    }

    public String getMCWF() {
        return MCWF;
    }

    public void setMCWF(String MCWF) {
        this.MCWF = MCWF;
    }

    public String getMZDXF() {
        return MZDXF;
    }

    public void setMZDXF(String MZDXF) {
        this.MZDXF = MZDXF;
    }

    public String getFWFBL() {
        return FWFBL;
    }

    public void setFWFBL(String FWFBL) {
        this.FWFBL = FWFBL;
    }

    public String getCWFJE() {
        return CWFJE;
    }

    public void setCWFJE(String CWFJE) {
        this.CWFJE = CWFJE;
    }

    public String getZDXFJE() {
        return ZDXFJE;
    }

    public void setZDXFJE(String ZDXFJE) {
        this.ZDXFJE = ZDXFJE;
    }

    public String getFWF() {
        return FWF;
    }

    public void setFWF(String FWF) {
        this.FWF = FWF;
    }

    public String getCWF() {
        return CWF;
    }

    public void setCWF(String CWF) {
        this.CWF = CWF;
    }

    public String getZDXF() {
        return ZDXF;
    }

    public void setZDXF(String ZDXF) {
        this.ZDXF = ZDXF;
    }

    public String getZXF() {
        return ZXF;
    }

    public void setZXF(String ZXF) {
        this.ZXF = ZXF;
    }

    public String getTOTAL() {
        return TOTAL;
    }

    public void setTOTAL(String TOTAL) {
        this.TOTAL = TOTAL;
    }

    public String getFKFS() {
        return FKFS;
    }

    public void setFKFS(String FKFS) {
        this.FKFS = FKFS;
    }

    public String getFKFSM() {
        return FKFSM;
    }

    public void setFKFSM(String FKFSM) {
        this.FKFSM = FKFSM;
    }

    public String getBZ() {
        return BZ;
    }

    public void setBZ(String BZ) {
        this.BZ = BZ;
    }

    public String getSKJE() {
        return SKJE;
    }

    public void setSKJE(String SKJE) {
        this.SKJE = SKJE;
    }

    public String getTKJE() {
        return TKJE;
    }

    public void setTKJE(String TKJE) {
        this.TKJE = TKJE;
    }

    public String getSKBH() {
        return SKBH;
    }

    public void setSKBH(String SKBH) {
        this.SKBH = SKBH;
    }

    public String getSKXM() {
        return SKXM;
    }

    public void setSKXM(String SKXM) {
        this.SKXM = SKXM;
    }

    public String getKH() {
        return KH;
    }

    public void setKH(String KH) {
        this.KH = KH;
    }

    public String getZH() {
        return ZH;
    }

    public void setZH(String ZH) {
        this.ZH = ZH;
    }

    public String getZHMC() {
        return ZHMC;
    }

    public void setZHMC(String ZHMC) {
        this.ZHMC = ZHMC;
    }

    public String getJZBZ() {
        return JZBZ;
    }

    public void setJZBZ(String JZBZ) {
        this.JZBZ = JZBZ;
    }

    public String getJZRQ() {
        return JZRQ;
    }

    public void setJZRQ(String JZRQ) {
        this.JZRQ = JZRQ;
    }

    public String getJZSJ() {
        return JZSJ;
    }

    public void setJZSJ(String JZSJ) {
        this.JZSJ = JZSJ;
    }

    public String getTJFLAG() {
        return TJFLAG;
    }

    public void setTJFLAG(String TJFLAG) {
        this.TJFLAG = TJFLAG;
    }

    public String getKey_in() {
        return key_in;
    }

    public void setKey_in(String key_in) {
        this.key_in = key_in;
    }

    public String getKey_out() {
        return key_out;
    }

    public void setKey_out(String key_out) {
        this.key_out = key_out;
    }

    public String getCall_time() {
        return call_time;
    }

    public void setCall_time(String call_time) {
        this.call_time = call_time;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getTAGE() {
        return TAGE;
    }

    public void setTAGE(String TAGE) {
        this.TAGE = TAGE;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(rownum);
        dest.writeString(CTBM);
        dest.writeString(CZBM);
        dest.writeString(CZMC);
        dest.writeString(CZZT);
        dest.writeString(CTMC);
        dest.writeString(KCM);
        dest.writeString(QH);
        dest.writeString(RS);
        dest.writeString(JDBM);
        dest.writeString(JDXM);
        dest.writeString(BH);
        dest.writeString(KRSM);
        dest.writeString(KTSJ);
        dest.writeString(KTRQ);
        dest.writeString(TJRQ);
        dest.writeString(KRBH);
        dest.writeString(ZKM1);
        dest.writeString(ZKM);
        dest.writeString(DZDAH);
        dest.writeString(PZR);
        dest.writeString(PZRDM);
        dest.writeString(ZHZT);
        dest.writeString(KRZT);
        dest.writeString(MFWF);
        dest.writeString(MCWF);
        dest.writeString(MZDXF);
        dest.writeString(FWFBL);
        dest.writeString(CWFJE);
        dest.writeString(ZDXFJE);
        dest.writeString(FWF);
        dest.writeString(CWF);
        dest.writeString(ZDXF);
        dest.writeString(ZXF);
        dest.writeString(TOTAL);
        dest.writeString(FKFS);
        dest.writeString(FKFSM);
        dest.writeString(BZ);
        dest.writeString(SKJE);
        dest.writeString(TKJE);
        dest.writeString(SKBH);
        dest.writeString(SKXM);
        dest.writeString(KH);
        dest.writeString(ZH);
        dest.writeString(ZHMC);
        dest.writeString(JZBZ);
        dest.writeString(JZRQ);
        dest.writeString(JZSJ);
        dest.writeString(TJFLAG);
        dest.writeString(key_in);
        dest.writeString(key_out);
        dest.writeString(call_time);
        dest.writeString(memo);
        dest.writeString(TAGE);
    }

    @Override
    public String toString() {
        return "TableNumber{" +
                "rownum='" + rownum + '\'' +
                ", CTBM='" + CTBM + '\'' +
                ", CZBM='" + CZBM + '\'' +
                ", CZMC='" + CZMC + '\'' +
                ", CZZT='" + CZZT + '\'' +
                ", CTMC='" + CTMC + '\'' +
                ", KCM='" + KCM + '\'' +
                ", QH='" + QH + '\'' +
                ", RS='" + RS + '\'' +
                ", JDBM='" + JDBM + '\'' +
                ", JDXM='" + JDXM + '\'' +
                ", BH='" + BH + '\'' +
                ", KRSM='" + KRSM + '\'' +
                ", KTSJ='" + KTSJ + '\'' +
                ", KTRQ='" + KTRQ + '\'' +
                ", TJRQ='" + TJRQ + '\'' +
                ", KRBH='" + KRBH + '\'' +
                ", ZKM1='" + ZKM1 + '\'' +
                ", ZKM='" + ZKM + '\'' +
                ", DZDAH='" + DZDAH + '\'' +
                ", PZR='" + PZR + '\'' +
                ", PZRDM='" + PZRDM + '\'' +
                ", ZHZT='" + ZHZT + '\'' +
                ", KRZT='" + KRZT + '\'' +
                ", MFWF='" + MFWF + '\'' +
                ", MCWF='" + MCWF + '\'' +
                ", MZDXF='" + MZDXF + '\'' +
                ", FWFBL='" + FWFBL + '\'' +
                ", CWFJE='" + CWFJE + '\'' +
                ", ZDXFJE='" + ZDXFJE + '\'' +
                ", FWF='" + FWF + '\'' +
                ", CWF='" + CWF + '\'' +
                ", ZDXF='" + ZDXF + '\'' +
                ", ZXF='" + ZXF + '\'' +
                ", TOTAL='" + TOTAL + '\'' +
                ", FKFS='" + FKFS + '\'' +
                ", FKFSM='" + FKFSM + '\'' +
                ", BZ='" + BZ + '\'' +
                ", SKJE='" + SKJE + '\'' +
                ", TKJE='" + TKJE + '\'' +
                ", SKBH='" + SKBH + '\'' +
                ", SKXM='" + SKXM + '\'' +
                ", KH='" + KH + '\'' +
                ", ZH='" + ZH + '\'' +
                ", ZHMC='" + ZHMC + '\'' +
                ", JZBZ='" + JZBZ + '\'' +
                ", JZRQ='" + JZRQ + '\'' +
                ", JZSJ='" + JZSJ + '\'' +
                ", TJFLAG='" + TJFLAG + '\'' +
                ", key_in='" + key_in + '\'' +
                ", key_out='" + key_out + '\'' +
                ", call_time='" + call_time + '\'' +
                ", memo='" + memo + '\'' +
                ", TAGE='" + TAGE + '\'' +
                '}';
    }
}
