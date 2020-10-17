package com.cosmoplat.cowfarm.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 实体类
 *
 * @author Administrator
 */
@Entity
@Table(name = "cow_health_log")
public class CowHealthLog implements Serializable {

    @Id
    private String id;//


    private String cow_id;//牛号
    private String cowshed_id;//牛舍编号
    private Integer illness_id;//疾病id
    private String illness_name;//疾病名称
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date disease_date;//发病日期
    private String prescription;//处方id
    private Integer treatment_effect;//治疗效果
    private String operator_id;//执行人id
    private String operator_name;//执行人姓名
    private String to_cowshed_id;//调往牛舍
    private String remark;//备注
    private Integer status;//状态  0：禁用   1：正常
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date gmt_create;//创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date gmt_modified;//修改时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCow_id() {
        return cow_id;
    }

    public void setCow_id(String cow_id) {
        this.cow_id = cow_id;
    }

    public String getCowshed_id() {
        return cowshed_id;
    }

    public void setCowshed_id(String cowshed_id) {
        this.cowshed_id = cowshed_id;
    }

    public Integer getIllness_id() {
        return illness_id;
    }

    public void setIllness_id(Integer illness_id) {
        this.illness_id = illness_id;
    }

    public String getIllness_name() {
        return illness_name;
    }

    public void setIllness_name(String illness_name) {
        this.illness_name = illness_name;
    }

    public Date getDisease_date() {
        return disease_date;
    }

    public void setDisease_date(Date disease_date) {
        this.disease_date = disease_date;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public Integer getTreatment_effect() {
        return treatment_effect;
    }

    public void setTreatment_effect(Integer treatment_effect) {
        this.treatment_effect = treatment_effect;
    }

    public String getOperator_id() {
        return operator_id;
    }

    public void setOperator_id(String operator_id) {
        this.operator_id = operator_id;
    }

    public String getOperator_name() {
        return operator_name;
    }

    public void setOperator_name(String operator_name) {
        this.operator_name = operator_name;
    }

    public String getTo_cowshed_id() {
        return to_cowshed_id;
    }

    public void setTo_cowshed_id(String to_cowshed_id) {
        this.to_cowshed_id = to_cowshed_id;
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

    public Date getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(Date gmt_create) {
        this.gmt_create = gmt_create;
    }

    public Date getGmt_modified() {
        return gmt_modified;
    }

    public void setGmt_modified(Date gmt_modified) {
        this.gmt_modified = gmt_modified;
    }
}
