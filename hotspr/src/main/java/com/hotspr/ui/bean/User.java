package com.hotspr.ui.bean;

import java.io.Serializable;

public class User implements Serializable {

    private String key ;
    private String U_NO ; // 登陆编号
    private String user_id ;
    private String C_NAME ; // 操作员职位
    private String U_PART ; // 部门名称
    private String U_NAME ; // 记录名字
    private String SYSTEM ; // 系统维护权限,"T":有,"F":无
    private String SANGNA ; // 错误取消权限,"T":有,"F":无
    private String HOTEL ;  // 客房房务中心权限,"T":有,"F":无
    private String RST ;    // 餐饮开台权限,"T":有,"F":无
    private String ORDER_A ;// 预订下单权限,"T":有,"F":无
    private String MIS ;    // 经理查询权限,"T":有,"F":无
    private String YJ ;     // 业绩查询权限,"T":有,"F":无
    private String SANGNA_IN ; // 温泉登记权限,"T":有,"F":无
    private String SANGNA_OUT ;// 温泉更衣(离场)权限,"T":有,"F":无
    private String SANGNA_LOO ;// 修改价格权限,"T":有,"F":无
    private String SANGNA_JZ ; // 温泉记账权限,"T":有,"F":无
    private String SANGNA_CAS ;// 温泉收银权限,"T":有,"F":无
    private String SANGNA_AM ; // 按摩下单权限,"T":有,"F":无
    private String HOTEL_IN ;  // 客房登记权限权限,"T":有,"F":无
    private String HOTEL_CASH ;// 客房收银权限,"T":有,"F":无
    private String HOTEL_YH ;  // 夜核权限,"T":有,"F":无
    private String TM_CHECK ;  // 普通查询权限,"T":有,"F":无
    private String client_in ; // 客户资料输入权限,"T":有,"F":无
    private String client_ch ; // 客户资料修改权限,"T":有,"F":无
    private String client_look ; // 客户折扣权限权限,"T":有,"F":无
    private String client_rpt ;  // 会员卡发卡权限权限,"T":有,"F":无
    private String client_all ;  //贵宾卡（AR帐）充值或回收权限,"T":有,"F":无


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getU_NO() {
        return U_NO;
    }

    public void setU_NO(String u_NO) {
        U_NO = u_NO;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getC_NAME() {
        return C_NAME;
    }

    public void setC_NAME(String c_NAME) {
        C_NAME = c_NAME;
    }

    public String getU_PART() {
        return U_PART;
    }

    public void setU_PART(String u_PART) {
        U_PART = u_PART;
    }

    public String getU_NAME() {
        return U_NAME;
    }

    public void setU_NAME(String u_NAME) {
        U_NAME = u_NAME;
    }

    public String getSYSTEM() {
        return SYSTEM;
    }

    public void setSYSTEM(String SYSTEM) {
        this.SYSTEM = SYSTEM;
    }

    public String getSANGNA() {
        return SANGNA;
    }

    public void setSANGNA(String SANGNA) {
        this.SANGNA = SANGNA;
    }

    public String getHOTEL() {
        return HOTEL;
    }

    public void setHOTEL(String HOTEL) {
        this.HOTEL = HOTEL;
    }

    public String getRST() {
        return RST;
    }

    public void setRST(String RST) {
        this.RST = RST;
    }

    public String getORDER_A() {
        return ORDER_A;
    }

    public void setORDER_A(String ORDER_A) {
        this.ORDER_A = ORDER_A;
    }

    public String getMIS() {
        return MIS;
    }

    public void setMIS(String MIS) {
        this.MIS = MIS;
    }

    public String getYJ() {
        return YJ;
    }

    public void setYJ(String YJ) {
        this.YJ = YJ;
    }

    public String getSANGNA_IN() {
        return SANGNA_IN;
    }

    public void setSANGNA_IN(String SANGNA_IN) {
        this.SANGNA_IN = SANGNA_IN;
    }

    public String getSANGNA_OUT() {
        return SANGNA_OUT;
    }

    public void setSANGNA_OUT(String SANGNA_OUT) {
        this.SANGNA_OUT = SANGNA_OUT;
    }

    public String getSANGNA_LOO() {
        return SANGNA_LOO;
    }

    public void setSANGNA_LOO(String SANGNA_LOO) {
        this.SANGNA_LOO = SANGNA_LOO;
    }

    public String getSANGNA_JZ() {
        return SANGNA_JZ;
    }

    public void setSANGNA_JZ(String SANGNA_JZ) {
        this.SANGNA_JZ = SANGNA_JZ;
    }

    public String getSANGNA_CAS() {
        return SANGNA_CAS;
    }

    public void setSANGNA_CAS(String SANGNA_CAS) {
        this.SANGNA_CAS = SANGNA_CAS;
    }

    public String getSANGNA_AM() {
        return SANGNA_AM;
    }

    public void setSANGNA_AM(String SANGNA_AM) {
        this.SANGNA_AM = SANGNA_AM;
    }

    public String getHOTEL_IN() {
        return HOTEL_IN;
    }

    public void setHOTEL_IN(String HOTEL_IN) {
        this.HOTEL_IN = HOTEL_IN;
    }

    public String getHOTEL_CASH() {
        return HOTEL_CASH;
    }

    public void setHOTEL_CASH(String HOTEL_CASH) {
        this.HOTEL_CASH = HOTEL_CASH;
    }

    public String getHOTEL_YH() {
        return HOTEL_YH;
    }

    public void setHOTEL_YH(String HOTEL_YH) {
        this.HOTEL_YH = HOTEL_YH;
    }

    public String getTM_CHECK() {
        return TM_CHECK;
    }

    public void setTM_CHECK(String TM_CHECK) {
        this.TM_CHECK = TM_CHECK;
    }

    public String getClient_in() {
        return client_in;
    }

    public void setClient_in(String client_in) {
        this.client_in = client_in;
    }

    public String getClient_ch() {
        return client_ch;
    }

    public void setClient_ch(String client_ch) {
        this.client_ch = client_ch;
    }

    public String getClient_look() {
        return client_look;
    }

    public void setClient_look(String client_look) {
        this.client_look = client_look;
    }

    public String getClient_rpt() {
        return client_rpt;
    }

    public void setClient_rpt(String client_rpt) {
        this.client_rpt = client_rpt;
    }

    public String getClient_all() {
        return client_all;
    }

    public void setClient_all(String client_all) {
        this.client_all = client_all;
    }
}
