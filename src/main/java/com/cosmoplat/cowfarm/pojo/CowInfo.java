package com.cosmoplat.cowfarm.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 实体类
 *
 * @author Administrator
 */
@Entity
@Table(name = "cow_info")
public class CowInfo implements Serializable {

    @Id
    private String cow_id;//牛号

    private String collar_id;//项圈编号
    private String cowshed_id;//牛舍编号
    private Integer varieties_code;//牛种编码
    private Integer sex;//性别【0->母  1->公】
    private Integer herd_state;//牛群状态
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date birthday;//出生日期
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date admission_time;//入场日期
    private Integer group_state;//在群状态
    private Integer birth_weight;//出生重量
    private Integer admission_weight;//入场重量
    private String insurance_id;//保险号
    private String loan_id;//贷款编号
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date leave_time;//离场日期
    private String source_address;//来源地
    private String source_batch;//来源批次
    private Double unit_price;//采购单价（元）
    private String supplier;//供应商
    private Integer health_state;//疾病状态
    private Integer spay_date;//去势状态
    private Integer horn_state;//去角状态
    private Integer immunity_state;//免疫状态
    private Integer trust_state;//托管状态
    private String left_img;//照片-牛-左侧
    private String left_thumbnail;//缩略图-牛-左侧
    private String right_img;//照片-牛-右侧
    private String right_thumbnail;//缩略图-牛-右侧
    private String mid_img;//照片-牛-头
    private String mid_thumbnail;//缩略图-牛-头
    private String remark;//备注
    private Integer status;//状态  0：禁用   1：正常
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date gmt_create;//创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date gmt_modified;//修改时间


    public String getCow_id() {
        return cow_id;
    }

    public void setCow_id(String cow_id) {
        this.cow_id = cow_id;
    }

    public String getCollar_id() {
        return collar_id;
    }

    public void setCollar_id(String collar_id) {
        this.collar_id = collar_id;
    }

    public String getCowshed_id() {
        return cowshed_id;
    }

    public void setCowshed_id(String cowshed_id) {
        this.cowshed_id = cowshed_id;
    }

    public Integer getVarieties_code() {
        return varieties_code;
    }

    public void setVarieties_code(Integer varieties_code) {
        this.varieties_code = varieties_code;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getHerd_state() {
        return herd_state;
    }

    public void setHerd_state(Integer herd_state) {
        this.herd_state = herd_state;
    }

    public java.util.Date getBirthday() {
        return birthday;
    }

    public void setBirthday(java.util.Date birthday) {
        this.birthday = birthday;
    }

    public java.util.Date getAdmission_time() {
        return admission_time;
    }

    public void setAdmission_time(java.util.Date admission_time) {
        this.admission_time = admission_time;
    }

    public Integer getGroup_state() {
        return group_state;
    }

    public void setGroup_state(Integer group_state) {
        this.group_state = group_state;
    }

    public Integer getBirth_weight() {
        return birth_weight;
    }

    public void setBirth_weight(Integer birth_weight) {
        this.birth_weight = birth_weight;
    }

    public Integer getAdmission_weight() {
        return admission_weight;
    }

    public void setAdmission_weight(Integer admission_weight) {
        this.admission_weight = admission_weight;
    }

    public String getInsurance_id() {
        return insurance_id;
    }

    public void setInsurance_id(String insurance_id) {
        this.insurance_id = insurance_id;
    }

    public String getLoan_id() {
        return loan_id;
    }

    public void setLoan_id(String loan_id) {
        this.loan_id = loan_id;
    }

    public java.util.Date getLeave_time() {
        return leave_time;
    }

    public void setLeave_time(java.util.Date leave_time) {
        this.leave_time = leave_time;
    }

    public String getSource_address() {
        return source_address;
    }

    public void setSource_address(String source_address) {
        this.source_address = source_address;
    }

    public String getSource_batch() {
        return source_batch;
    }

    public void setSource_batch(String source_batch) {
        this.source_batch = source_batch;
    }

    public Double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(Double unit_price) {
        this.unit_price = unit_price;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Integer getHealth_state() {
        return health_state;
    }

    public void setHealth_state(Integer health_state) {
        this.health_state = health_state;
    }

    public Integer getSpay_date() {
        return spay_date;
    }

    public void setSpay_date(Integer spay_date) {
        this.spay_date = spay_date;
    }

    public Integer getHorn_state() {
        return horn_state;
    }

    public void setHorn_state(Integer horn_state) {
        this.horn_state = horn_state;
    }

    public Integer getImmunity_state() {
        return immunity_state;
    }

    public void setImmunity_state(Integer immunity_state) {
        this.immunity_state = immunity_state;
    }

    public Integer getTrust_state() {
        return trust_state;
    }

    public void setTrust_state(Integer trust_state) {
        this.trust_state = trust_state;
    }

    public String getLeft_img() {
        return left_img;
    }

    public void setLeft_img(String left_img) {
        this.left_img = left_img;
    }

    public String getLeft_thumbnail() {
        return left_thumbnail;
    }

    public void setLeft_thumbnail(String left_thumbnail) {
        this.left_thumbnail = left_thumbnail;
    }

    public String getRight_img() {
        return right_img;
    }

    public void setRight_img(String right_img) {
        this.right_img = right_img;
    }

    public String getRight_thumbnail() {
        return right_thumbnail;
    }

    public void setRight_thumbnail(String right_thumbnail) {
        this.right_thumbnail = right_thumbnail;
    }

    public String getMid_img() {
        return mid_img;
    }

    public void setMid_img(String mid_img) {
        this.mid_img = mid_img;
    }

    public String getMid_thumbnail() {
        return mid_thumbnail;
    }

    public void setMid_thumbnail(String mid_thumbnail) {
        this.mid_thumbnail = mid_thumbnail;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public java.util.Date getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(java.util.Date gmt_create) {
        this.gmt_create = gmt_create;
    }

    public java.util.Date getGmt_modified() {
        return gmt_modified;
    }

    public void setGmt_modified(java.util.Date gmt_modified) {
        this.gmt_modified = gmt_modified;
    }


}
