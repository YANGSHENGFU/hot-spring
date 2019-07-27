package com.restaurant.ui.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class OrderResult implements Parcelable {


    private String rownum;
    private String ID;
    private String KRBH;
    private String LSH;
    private String ZXDM;
    private String FLAG;
    private String CDDM;
    private String DCDM;
    private String MC;
    private String CBJG;
    private String LSJG;
    private String DCRQ;
    private String TJRQ;
    private String DCSJ;
    private String SCSJ;
    private String CFBZ;
    private String CCBZ;
    private String CCXS;
    private String PMXS;
    private String SKBH;
    private String SKXM;
    private String JDBH;
    private String JDXM;
    private String JKDM;
    private String SKJE;
    private String SKJEF;
    private String BZ;
    private String FS;
    private String FSMC;
    private String KH;
    private String KH1;
    private String CZDM;
    private String KCM;
    private String QH;
    private String XSBZ;
    private String CTDM;
    private String CTMC;
    private String PZR;
    private String CFCZDM;
    private String CFCZXM;
    private String CFDM;
    private String CFMC;
    private String ZS;
    private String DYBZ;
    private String BH;
    private String SL;
    private String DZBZ;
    private String SJBZ;
    private String JZBZ;
    private String JKDM1;
    private String TJFLAG;
    private String TAGE;

    public OrderResult() {
    }

    protected OrderResult(Parcel in) {
        rownum = in.readString();
        ID = in.readString();
        KRBH = in.readString();
        LSH = in.readString();
        ZXDM = in.readString();
        FLAG = in.readString();
        CDDM = in.readString();
        DCDM = in.readString();
        MC = in.readString();
        CBJG = in.readString();
        LSJG = in.readString();
        DCRQ = in.readString();
        TJRQ = in.readString();
        DCSJ = in.readString();
        SCSJ = in.readString();
        CFBZ = in.readString();
        CCBZ = in.readString();
        CCXS = in.readString();
        PMXS = in.readString();
        SKBH = in.readString();
        SKXM = in.readString();
        JDBH = in.readString();
        JDXM = in.readString();
        JKDM = in.readString();
        SKJE = in.readString();
        SKJEF = in.readString();
        BZ = in.readString();
        FS = in.readString();
        FSMC = in.readString();
        KH = in.readString();
        KH1 = in.readString();
        CZDM = in.readString();
        KCM = in.readString();
        QH = in.readString();
        XSBZ = in.readString();
        CTDM = in.readString();
        CTMC = in.readString();
        PZR = in.readString();
        CFCZDM = in.readString();
        CFCZXM = in.readString();
        CFDM = in.readString();
        CFMC = in.readString();
        ZS = in.readString();
        DYBZ = in.readString();
        BH = in.readString();
        SL = in.readString();
        DZBZ = in.readString();
        SJBZ = in.readString();
        JZBZ = in.readString();
        JKDM1 = in.readString();
        TJFLAG = in.readString();
        TAGE = in.readString();
    }

    public static final Creator<OrderResult> CREATOR = new Creator<OrderResult>() {
        @Override
        public OrderResult createFromParcel(Parcel in) {
            return new OrderResult(in);
        }

        @Override
        public OrderResult[] newArray(int size) {
            return new OrderResult[size];
        }
    };

    public String getRownum() {
        return rownum;
    }

    public void setRownum(String rownum) {
        this.rownum = rownum;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getKRBH() {
        return KRBH;
    }

    public void setKRBH(String KRBH) {
        this.KRBH = KRBH;
    }

    public String getLSH() {
        return LSH;
    }

    public void setLSH(String LSH) {
        this.LSH = LSH;
    }

    public String getZXDM() {
        return ZXDM;
    }

    public void setZXDM(String ZXDM) {
        this.ZXDM = ZXDM;
    }

    public String getFLAG() {
        return FLAG;
    }

    public void setFLAG(String FLAG) {
        this.FLAG = FLAG;
    }

    public String getCDDM() {
        return CDDM;
    }

    public void setCDDM(String CDDM) {
        this.CDDM = CDDM;
    }

    public String getDCDM() {
        return DCDM;
    }

    public void setDCDM(String DCDM) {
        this.DCDM = DCDM;
    }

    public String getMC() {
        return MC;
    }

    public void setMC(String MC) {
        this.MC = MC;
    }

    public String getCBJG() {
        return CBJG;
    }

    public void setCBJG(String CBJG) {
        this.CBJG = CBJG;
    }

    public String getLSJG() {
        return LSJG;
    }

    public void setLSJG(String LSJG) {
        this.LSJG = LSJG;
    }

    public String getDCRQ() {
        return DCRQ;
    }

    public void setDCRQ(String DCRQ) {
        this.DCRQ = DCRQ;
    }

    public String getTJRQ() {
        return TJRQ;
    }

    public void setTJRQ(String TJRQ) {
        this.TJRQ = TJRQ;
    }

    public String getDCSJ() {
        return DCSJ;
    }

    public void setDCSJ(String DCSJ) {
        this.DCSJ = DCSJ;
    }

    public String getSCSJ() {
        return SCSJ;
    }

    public void setSCSJ(String SCSJ) {
        this.SCSJ = SCSJ;
    }

    public String getCFBZ() {
        return CFBZ;
    }

    public void setCFBZ(String CFBZ) {
        this.CFBZ = CFBZ;
    }

    public String getCCBZ() {
        return CCBZ;
    }

    public void setCCBZ(String CCBZ) {
        this.CCBZ = CCBZ;
    }

    public String getCCXS() {
        return CCXS;
    }

    public void setCCXS(String CCXS) {
        this.CCXS = CCXS;
    }

    public String getPMXS() {
        return PMXS;
    }

    public void setPMXS(String PMXS) {
        this.PMXS = PMXS;
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

    public String getJDBH() {
        return JDBH;
    }

    public void setJDBH(String JDBH) {
        this.JDBH = JDBH;
    }

    public String getJDXM() {
        return JDXM;
    }

    public void setJDXM(String JDXM) {
        this.JDXM = JDXM;
    }

    public String getJKDM() {
        return JKDM;
    }

    public void setJKDM(String JKDM) {
        this.JKDM = JKDM;
    }

    public String getSKJE() {
        return SKJE;
    }

    public void setSKJE(String SKJE) {
        this.SKJE = SKJE;
    }

    public String getSKJEF() {
        return SKJEF;
    }

    public void setSKJEF(String SKJEF) {
        this.SKJEF = SKJEF;
    }

    public String getBZ() {
        return BZ;
    }

    public void setBZ(String BZ) {
        this.BZ = BZ;
    }

    public String getFS() {
        return FS;
    }

    public void setFS(String FS) {
        this.FS = FS;
    }

    public String getFSMC() {
        return FSMC;
    }

    public void setFSMC(String FSMC) {
        this.FSMC = FSMC;
    }

    public String getKH() {
        return KH;
    }

    public void setKH(String KH) {
        this.KH = KH;
    }

    public String getKH1() {
        return KH1;
    }

    public void setKH1(String KH1) {
        this.KH1 = KH1;
    }

    public String getCZDM() {
        return CZDM;
    }

    public void setCZDM(String CZDM) {
        this.CZDM = CZDM;
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

    public String getXSBZ() {
        return XSBZ;
    }

    public void setXSBZ(String XSBZ) {
        this.XSBZ = XSBZ;
    }

    public String getCTDM() {
        return CTDM;
    }

    public void setCTDM(String CTDM) {
        this.CTDM = CTDM;
    }

    public String getCTMC() {
        return CTMC;
    }

    public void setCTMC(String CTMC) {
        this.CTMC = CTMC;
    }

    public String getPZR() {
        return PZR;
    }

    public void setPZR(String PZR) {
        this.PZR = PZR;
    }

    public String getCFCZDM() {
        return CFCZDM;
    }

    public void setCFCZDM(String CFCZDM) {
        this.CFCZDM = CFCZDM;
    }

    public String getCFCZXM() {
        return CFCZXM;
    }

    public void setCFCZXM(String CFCZXM) {
        this.CFCZXM = CFCZXM;
    }

    public String getCFDM() {
        return CFDM;
    }

    public void setCFDM(String CFDM) {
        this.CFDM = CFDM;
    }

    public String getCFMC() {
        return CFMC;
    }

    public void setCFMC(String CFMC) {
        this.CFMC = CFMC;
    }

    public String getZS() {
        return ZS;
    }

    public void setZS(String ZS) {
        this.ZS = ZS;
    }

    public String getDYBZ() {
        return DYBZ;
    }

    public void setDYBZ(String DYBZ) {
        this.DYBZ = DYBZ;
    }

    public String getBH() {
        return BH;
    }

    public void setBH(String BH) {
        this.BH = BH;
    }

    public String getSL() {
        return SL;
    }

    public void setSL(String SL) {
        this.SL = SL;
    }

    public String getDZBZ() {
        return DZBZ;
    }

    public void setDZBZ(String DZBZ) {
        this.DZBZ = DZBZ;
    }

    public String getSJBZ() {
        return SJBZ;
    }

    public void setSJBZ(String SJBZ) {
        this.SJBZ = SJBZ;
    }

    public String getJZBZ() {
        return JZBZ;
    }

    public void setJZBZ(String JZBZ) {
        this.JZBZ = JZBZ;
    }

    public String getJKDM1() {
        return JKDM1;
    }

    public void setJKDM1(String JKDM1) {
        this.JKDM1 = JKDM1;
    }

    public String getTJFLAG() {
        return TJFLAG;
    }

    public void setTJFLAG(String TJFLAG) {
        this.TJFLAG = TJFLAG;
    }

    public String getTAGE() {
        return TAGE;
    }

    public void setTAGE(String TAGE) {
        this.TAGE = TAGE;
    }

    @Override
    public String toString() {
        return "OrderResult{" +
                "rownum='" + rownum + '\'' +
                ", ID='" + ID + '\'' +
                ", KRBH='" + KRBH + '\'' +
                ", LSH='" + LSH + '\'' +
                ", ZXDM='" + ZXDM + '\'' +
                ", FLAG='" + FLAG + '\'' +
                ", CDDM='" + CDDM + '\'' +
                ", DCDM='" + DCDM + '\'' +
                ", MC='" + MC + '\'' +
                ", CBJG='" + CBJG + '\'' +
                ", LSJG='" + LSJG + '\'' +
                ", DCRQ='" + DCRQ + '\'' +
                ", TJRQ='" + TJRQ + '\'' +
                ", DCSJ='" + DCSJ + '\'' +
                ", SCSJ='" + SCSJ + '\'' +
                ", CFBZ='" + CFBZ + '\'' +
                ", CCBZ='" + CCBZ + '\'' +
                ", CCXS='" + CCXS + '\'' +
                ", PMXS='" + PMXS + '\'' +
                ", SKBH='" + SKBH + '\'' +
                ", SKXM='" + SKXM + '\'' +
                ", JDBH='" + JDBH + '\'' +
                ", JDXM='" + JDXM + '\'' +
                ", JKDM='" + JKDM + '\'' +
                ", SKJE='" + SKJE + '\'' +
                ", SKJEF='" + SKJEF + '\'' +
                ", BZ='" + BZ + '\'' +
                ", FS='" + FS + '\'' +
                ", FSMC='" + FSMC + '\'' +
                ", KH='" + KH + '\'' +
                ", KH1='" + KH1 + '\'' +
                ", CZDM='" + CZDM + '\'' +
                ", KCM='" + KCM + '\'' +
                ", QH='" + QH + '\'' +
                ", XSBZ='" + XSBZ + '\'' +
                ", CTDM='" + CTDM + '\'' +
                ", CTMC='" + CTMC + '\'' +
                ", PZR='" + PZR + '\'' +
                ", CFCZDM='" + CFCZDM + '\'' +
                ", CFCZXM='" + CFCZXM + '\'' +
                ", CFDM='" + CFDM + '\'' +
                ", CFMC='" + CFMC + '\'' +
                ", ZS='" + ZS + '\'' +
                ", DYBZ='" + DYBZ + '\'' +
                ", BH='" + BH + '\'' +
                ", SL='" + SL + '\'' +
                ", DZBZ='" + DZBZ + '\'' +
                ", SJBZ='" + SJBZ + '\'' +
                ", JZBZ='" + JZBZ + '\'' +
                ", JKDM1='" + JKDM1 + '\'' +
                ", TJFLAG='" + TJFLAG + '\'' +
                ", TAGE='" + TAGE + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(rownum);
        dest.writeString(ID);
        dest.writeString(KRBH);
        dest.writeString(LSH);
        dest.writeString(ZXDM);
        dest.writeString(FLAG);
        dest.writeString(CDDM);
        dest.writeString(DCDM);
        dest.writeString(MC);
        dest.writeString(CBJG);
        dest.writeString(LSJG);
        dest.writeString(DCRQ);
        dest.writeString(TJRQ);
        dest.writeString(DCSJ);
        dest.writeString(SCSJ);
        dest.writeString(CFBZ);
        dest.writeString(CCBZ);
        dest.writeString(CCXS);
        dest.writeString(PMXS);
        dest.writeString(SKBH);
        dest.writeString(SKXM);
        dest.writeString(JDBH);
        dest.writeString(JDXM);
        dest.writeString(JKDM);
        dest.writeString(SKJE);
        dest.writeString(SKJEF);
        dest.writeString(BZ);
        dest.writeString(FS);
        dest.writeString(FSMC);
        dest.writeString(KH);
        dest.writeString(KH1);
        dest.writeString(CZDM);
        dest.writeString(KCM);
        dest.writeString(QH);
        dest.writeString(XSBZ);
        dest.writeString(CTDM);
        dest.writeString(CTMC);
        dest.writeString(PZR);
        dest.writeString(CFCZDM);
        dest.writeString(CFCZXM);
        dest.writeString(CFDM);
        dest.writeString(CFMC);
        dest.writeString(ZS);
        dest.writeString(DYBZ);
        dest.writeString(BH);
        dest.writeString(SL);
        dest.writeString(DZBZ);
        dest.writeString(SJBZ);
        dest.writeString(JZBZ);
        dest.writeString(JKDM1);
        dest.writeString(TJFLAG);
        dest.writeString(TAGE);
    }
}
