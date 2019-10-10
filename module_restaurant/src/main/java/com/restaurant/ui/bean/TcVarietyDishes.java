package com.restaurant.ui.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class TcVarietyDishes implements Parcelable {

    private String rownum;
    private String ID;
    private int selectNum=0;
    private String MARK;
    private String JDBH;
    private String ZXDM;
    private String ZXMC;
    private String CDDM;
    private String DCDM;
    private String MC;
    private String C_name;
    private String DCBZ;
    private String DYCT;
    private String CPLB;
    private String XSBZ;
    private String LSJG;
    private String CBJG;
    private String SL;
    private String ZS;
    private String DZBZ;
    private String SJBZ;
    private String xssl;
    private String a;
    private String a1;
    private String b;
    private String b1;
    private String c;
    private String c1;
    private String d;
    private String d1;
    private String e;
    private String e1;
    private String f;
    private String f1;
    private String g;
    private String g1;
    private String h;
    private String h1;
    private String i;
    private String i1;
    private String j;
    private String j1;
    private String k;
    private String k1;
    private String l;
    private String l1;
    private String m;
    private String m1;
    private String n;
    private String n1;
    private String o;
    private String o1;
    private String p;
    private String p1;
    private String q;
    private String q1;
    private String r;
    private String r1;
    private String s;
    private String s1;
    private String t;
    private String t1;
    private String u;
    private String u1;
    private String v;
    private String v1;
    private String w;
    private String w1;
    private String x;
    private String x1;
    private String y;
    private String y1;
    private String z;
    private String z1;
    private String ff_tag;
    private String picture_path;
    private String SLOrder;

    public TcVarietyDishes() {
    }

    protected TcVarietyDishes(Parcel in) {
        rownum = in.readString();
        ID = in.readString();
        JDBH = in.readString();
        MARK = in.readString();
        ZXDM = in.readString();
        ZXMC = in.readString();
        CDDM = in.readString();
        DCDM = in.readString();
        MC = in.readString();
        C_name = in.readString();
        DCBZ = in.readString();
        DYCT = in.readString();
        CPLB = in.readString();
        XSBZ = in.readString();
        LSJG = in.readString();
        CBJG = in.readString();
        SL = in.readString();
        ZS = in.readString();
        DZBZ = in.readString();
        SJBZ = in.readString();
        xssl = in.readString();
        a = in.readString();
        a1 = in.readString();
        b = in.readString();
        b1 = in.readString();
        c = in.readString();
        c1 = in.readString();
        d = in.readString();
        d1 = in.readString();
        e = in.readString();
        e1 = in.readString();
        f = in.readString();
        f1 = in.readString();
        g = in.readString();
        g1 = in.readString();
        h = in.readString();
        h1 = in.readString();
        i = in.readString();
        i1 = in.readString();
        j = in.readString();
        j1 = in.readString();
        k = in.readString();
        k1 = in.readString();
        l = in.readString();
        l1 = in.readString();
        m = in.readString();
        m1 = in.readString();
        n = in.readString();
        n1 = in.readString();
        o = in.readString();
        o1 = in.readString();
        p = in.readString();
        p1 = in.readString();
        q = in.readString();
        q1 = in.readString();
        r = in.readString();
        r1 = in.readString();
        s = in.readString();
        s1 = in.readString();
        t = in.readString();
        t1 = in.readString();
        u = in.readString();
        u1 = in.readString();
        v = in.readString();
        v1 = in.readString();
        w = in.readString();
        w1 = in.readString();
        x = in.readString();
        x1 = in.readString();
        y = in.readString();
        y1 = in.readString();
        z = in.readString();
        z1 = in.readString();
        ff_tag = in.readString();
        picture_path = in.readString();
        SLOrder = in.readString();
    }

    public static final Creator<TcVarietyDishes> CREATOR = new Creator<TcVarietyDishes>() {
        @Override
        public TcVarietyDishes createFromParcel(Parcel in) {
            return new TcVarietyDishes(in);
        }

        @Override
        public TcVarietyDishes[] newArray(int size) {
            return new TcVarietyDishes[size];
        }
    };

    public String getFf_tag() {
        return ff_tag;
    }

    public void setFf_tag(String ff_tag) {
        this.ff_tag = ff_tag;
    }

    public String getRownum() {
        return rownum;
    }

    public void setRownum(String rownum) {
        this.rownum = rownum;
    }

    public int getSelectNum() {
        return selectNum;
    }

    public void setSelectNum(int selectNum) {
        this.selectNum = selectNum;
    }

    public String getJDBH() {
        return JDBH;
    }

    public void setJDBH(String JDBH) {
        this.JDBH = JDBH;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getMARK() {
        return MARK;
    }

    public void setMARK(String MARK) {
        this.MARK = MARK;
    }

    public String getZXDM() {
        return ZXDM;
    }

    public void setZXDM(String ZXDM) {
        this.ZXDM = ZXDM;
    }

    public String getZXMC() {
        return ZXMC;
    }

    public void setZXMC(String ZXMC) {
        this.ZXMC = ZXMC;
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

    public String getC_name() {
        return C_name;
    }

    public void setC_name(String c_name) {
        C_name = c_name;
    }

    public String getDCBZ() {
        return DCBZ;
    }

    public void setDCBZ(String DCBZ) {
        this.DCBZ = DCBZ;
    }

    public String getDYCT() {
        return DYCT;
    }

    public void setDYCT(String DYCT) {
        this.DYCT = DYCT;
    }

    public String getCPLB() {
        return CPLB;
    }

    public void setCPLB(String CPLB) {
        this.CPLB = CPLB;
    }

    public String getXSBZ() {
        return XSBZ;
    }

    public void setXSBZ(String XSBZ) {
        this.XSBZ = XSBZ;
    }

    public String getLSJG() {
        return LSJG;
    }

    public void setLSJG(String LSJG) {
        this.LSJG = LSJG;
    }

    public String getCBJG() {
        return CBJG;
    }

    public void setCBJG(String CBJG) {
        this.CBJG = CBJG;
    }

    public String getSL() {
        return SL;
    }

    public void setSL(String SL) {
        this.SL = SL;
    }

    public String getZS() {
        return ZS;
    }

    public void setZS(String ZS) {
        this.ZS = ZS;
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

    public String getXssl() {
        return xssl;
    }

    public void setXssl(String xssl) {
        this.xssl = xssl;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getA1() {
        return a1;
    }

    public void setA1(String a1) {
        this.a1 = a1;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getB1() {
        return b1;
    }

    public void setB1(String b1) {
        this.b1 = b1;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getC1() {
        return c1;
    }

    public void setC1(String c1) {
        this.c1 = c1;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getD1() {
        return d1;
    }

    public void setD1(String d1) {
        this.d1 = d1;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public String getE1() {
        return e1;
    }

    public void setE1(String e1) {
        this.e1 = e1;
    }

    public String getF() {
        return f;
    }

    public void setF(String f) {
        this.f = f;
    }

    public String getF1() {
        return f1;
    }

    public void setF1(String f1) {
        this.f1 = f1;
    }

    public String getG() {
        return g;
    }

    public void setG(String g) {
        this.g = g;
    }

    public String getG1() {
        return g1;
    }

    public void setG1(String g1) {
        this.g1 = g1;
    }

    public String getH() {
        return h;
    }

    public void setH(String h) {
        this.h = h;
    }

    public String getH1() {
        return h1;
    }

    public void setH1(String h1) {
        this.h1 = h1;
    }

    public String getI() {
        return i;
    }

    public void setI(String i) {
        this.i = i;
    }

    public String getI1() {
        return i1;
    }

    public void setI1(String i1) {
        this.i1 = i1;
    }

    public String getJ() {
        return j;
    }

    public void setJ(String j) {
        this.j = j;
    }

    public String getJ1() {
        return j1;
    }

    public void setJ1(String j1) {
        this.j1 = j1;
    }

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    public String getK1() {
        return k1;
    }

    public void setK1(String k1) {
        this.k1 = k1;
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }

    public String getL1() {
        return l1;
    }

    public void setL1(String l1) {
        this.l1 = l1;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public String getM1() {
        return m1;
    }

    public void setM1(String m1) {
        this.m1 = m1;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getN1() {
        return n1;
    }

    public void setN1(String n1) {
        this.n1 = n1;
    }

    public String getO() {
        return o;
    }

    public void setO(String o) {
        this.o = o;
    }

    public String getO1() {
        return o1;
    }

    public void setO1(String o1) {
        this.o1 = o1;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public String getP1() {
        return p1;
    }

    public void setP1(String p1) {
        this.p1 = p1;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getQ1() {
        return q1;
    }

    public void setQ1(String q1) {
        this.q1 = q1;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public String getR1() {
        return r1;
    }

    public void setR1(String r1) {
        this.r1 = r1;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getS1() {
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getT1() {
        return t1;
    }

    public void setT1(String t1) {
        this.t1 = t1;
    }

    public String getU() {
        return u;
    }

    public void setU(String u) {
        this.u = u;
    }

    public String getU1() {
        return u1;
    }

    public void setU1(String u1) {
        this.u1 = u1;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getV1() {
        return v1;
    }

    public void setV1(String v1) {
        this.v1 = v1;
    }

    public String getW() {
        return w;
    }

    public void setW(String w) {
        this.w = w;
    }

    public String getW1() {
        return w1;
    }

    public void setW1(String w1) {
        this.w1 = w1;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getX1() {
        return x1;
    }

    public void setX1(String x1) {
        this.x1 = x1;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getY1() {
        return y1;
    }

    public void setY1(String y1) {
        this.y1 = y1;
    }

    public String getZ() {
        return z;
    }

    public void setZ(String z) {
        this.z = z;
    }

    public String getZ1() {
        return z1;
    }

    public void setZ1(String z1) {
        this.z1 = z1;
    }

    public String getPicture_path() {
        return picture_path;
    }

    public void setPicture_path(String picture_path) {
        this.picture_path = picture_path;
    }

    public String getSLOrder() {
        return SLOrder;
    }

    public void setSLOrder(String SLOrder) {
        this.SLOrder = SLOrder;
    }

    @Override
    public String toString() {
        return "VarietyDishes{" +
                "rownum='" + rownum + '\'' +
                ", ID='" + ID + '\'' +
                ", JDBH='"+ JDBH + '\'' +
                ", MARK='" + MARK + '\'' +
                ", ZXDM='" + ZXDM + '\'' +
                ", ZXMC='" + ZXMC + '\'' +
                ", CDDM='" + CDDM + '\'' +
                ", DCDM='" + DCDM + '\'' +
                ", MC='" + MC + '\'' +
                ", C_name='" + C_name + '\'' +
                ", DCBZ='" + DCBZ + '\'' +
                ", DYCT='" + DYCT + '\'' +
                ", CPLB='" + CPLB + '\'' +
                ", XSBZ='" + XSBZ + '\'' +
                ", LSJG='" + LSJG + '\'' +
                ", CBJG='" + CBJG + '\'' +
                ", SL='" + SL + '\'' +
                ", ZS='" + ZS + '\'' +
                ", DZBZ='" + DZBZ + '\'' +
                ", SJBZ='" + SJBZ + '\'' +
                ", xssl='" + xssl + '\'' +
                ", a='" + a + '\'' +
                ", a1='" + a1 + '\'' +
                ", b='" + b + '\'' +
                ", b1='" + b1 + '\'' +
                ", c='" + c + '\'' +
                ", c1='" + c1 + '\'' +
                ", d='" + d + '\'' +
                ", d1='" + d1 + '\'' +
                ", e='" + e + '\'' +
                ", e1='" + e1 + '\'' +
                ", f='" + f + '\'' +
                ", f1='" + f1 + '\'' +
                ", g='" + g + '\'' +
                ", g1='" + g1 + '\'' +
                ", h='" + h + '\'' +
                ", h1='" + h1 + '\'' +
                ", i='" + i + '\'' +
                ", i1='" + i1 + '\'' +
                ", j='" + j + '\'' +
                ", j1='" + j1 + '\'' +
                ", k='" + k + '\'' +
                ", k1='" + k1 + '\'' +
                ", l='" + l + '\'' +
                ", l1='" + l1 + '\'' +
                ", m='" + m + '\'' +
                ", m1='" + m1 + '\'' +
                ", n='" + n + '\'' +
                ", n1='" + n1 + '\'' +
                ", o='" + o + '\'' +
                ", o1='" + o1 + '\'' +
                ", p='" + p + '\'' +
                ", p1='" + p1 + '\'' +
                ", q='" + q + '\'' +
                ", q1='" + q1 + '\'' +
                ", r='" + r + '\'' +
                ", r1='" + r1 + '\'' +
                ", s='" + s + '\'' +
                ", s1='" + s1 + '\'' +
                ", t='" + t + '\'' +
                ", t1='" + t1 + '\'' +
                ", u='" + u + '\'' +
                ", u1='" + u1 + '\'' +
                ", v='" + v + '\'' +
                ", v1='" + v1 + '\'' +
                ", w='" + w + '\'' +
                ", w1='" + w1 + '\'' +
                ", x='" + x + '\'' +
                ", x1='" + x1 + '\'' +
                ", y='" + y + '\'' +
                ", y1='" + y1 + '\'' +
                ", z='" + z + '\'' +
                ", z1='" + z1 + '\'' +
                ", ff_tag='" + ff_tag + '\'' +
                ", picture_path='" + picture_path + '\'' +
                ", SLOrder='" + SLOrder + '\'' +
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
        dest.writeString(MARK);
        dest.writeString(JDBH);
        dest.writeString(ZXDM);
        dest.writeString(ZXMC);
        dest.writeString(CDDM);
        dest.writeString(DCDM);
        dest.writeString(MC);
        dest.writeString(C_name);
        dest.writeString(DCBZ);
        dest.writeString(DYCT);
        dest.writeString(CPLB);
        dest.writeString(XSBZ);
        dest.writeString(LSJG);
        dest.writeString(CBJG);
        dest.writeString(SL);
        dest.writeString(ZS);
        dest.writeString(DZBZ);
        dest.writeString(SJBZ);
        dest.writeString(xssl);
        dest.writeString(a);
        dest.writeString(a1);
        dest.writeString(b);
        dest.writeString(b1);
        dest.writeString(c);
        dest.writeString(c1);
        dest.writeString(d);
        dest.writeString(d1);
        dest.writeString(e);
        dest.writeString(e1);
        dest.writeString(f);
        dest.writeString(f1);
        dest.writeString(g);
        dest.writeString(g1);
        dest.writeString(h);
        dest.writeString(h1);
        dest.writeString(i);
        dest.writeString(i1);
        dest.writeString(j);
        dest.writeString(j1);
        dest.writeString(k);
        dest.writeString(k1);
        dest.writeString(l);
        dest.writeString(l1);
        dest.writeString(m);
        dest.writeString(m1);
        dest.writeString(n);
        dest.writeString(n1);
        dest.writeString(o);
        dest.writeString(o1);
        dest.writeString(p);
        dest.writeString(p1);
        dest.writeString(q);
        dest.writeString(q1);
        dest.writeString(r);
        dest.writeString(r1);
        dest.writeString(s);
        dest.writeString(s1);
        dest.writeString(t);
        dest.writeString(t1);
        dest.writeString(u);
        dest.writeString(u1);
        dest.writeString(v);
        dest.writeString(v1);
        dest.writeString(w);
        dest.writeString(w1);
        dest.writeString(x);
        dest.writeString(x1);
        dest.writeString(y);
        dest.writeString(y1);
        dest.writeString(z);
        dest.writeString(z1);
        dest.writeString(ff_tag);
        dest.writeString(picture_path);
        dest.writeString(SLOrder);
    }
}
