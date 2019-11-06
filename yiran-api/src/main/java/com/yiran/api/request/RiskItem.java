package com.yiran.api.request;

import java.io.Serializable;

/**
 * 风险控制参数
 * @author Administrator
 *
 */
public class RiskItem implements Serializable{
    private static final long serialVersionUID = -3858207975011241270L;
    private String frms_ware_category;
    private String user_info_mercht_userno;
    private String user_info_dt_register;
    private String user_info_full_name;
    private String user_info_id_no;
    private String user_info_identify_type;
    private String user_info_identify_state;
    private String frms_ip_addr;
    private String frms_bank_name;
    private String user_info_bind_phone;

    public String getUser_info_bind_phone() {
        return user_info_bind_phone;
    }

    public void setUser_info_bind_phone(String user_info_bind_phone) {
        this.user_info_bind_phone = user_info_bind_phone;
    }

    public String getFrms_bank_name() {
        return frms_bank_name;
    }

    public void setFrms_bank_name(String frms_bank_name) {
        this.frms_bank_name = frms_bank_name;
    }

    public String getFrms_ware_category() {
        return frms_ware_category;
    }

    public void setFrms_ware_category(String frms_ware_category) {
        this.frms_ware_category = frms_ware_category;
    }

    public String getUser_info_mercht_userno() {
        return user_info_mercht_userno;
    }

    public void setUser_info_mercht_userno(String user_info_mercht_userno) {
        this.user_info_mercht_userno = user_info_mercht_userno;
    }

    public String getUser_info_dt_register() {
        return user_info_dt_register;
    }

    public void setUser_info_dt_register(String user_info_dt_register) {
        this.user_info_dt_register = user_info_dt_register;
    }

    public String getUser_info_full_name() {
        return user_info_full_name;
    }

    public void setUser_info_full_name(String user_info_full_name) {
        this.user_info_full_name = user_info_full_name;
    }

    public String getUser_info_id_no() {
        return user_info_id_no;
    }

    public void setUser_info_id_no(String user_info_id_no) {
        this.user_info_id_no = user_info_id_no;
    }

    public String getUser_info_identify_type() {
        return user_info_identify_type;
    }

    public void setUser_info_identify_type(String user_info_identify_type) {
        this.user_info_identify_type = user_info_identify_type;
    }

    public String getUser_info_identify_state() {
        return user_info_identify_state;
    }

    public void setUser_info_identify_state(String user_info_identify_state) {
        this.user_info_identify_state = user_info_identify_state;
    }

    public String getFrms_ip_addr() {
        return frms_ip_addr;
    }

    public void setFrms_ip_addr(String frms_ip_addr) {
        this.frms_ip_addr = frms_ip_addr;
    }
}
