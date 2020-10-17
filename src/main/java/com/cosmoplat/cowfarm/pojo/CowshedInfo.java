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
@Table(name = "cowshed_info")
public class CowshedInfo implements Serializable {

    @Id
    private String cowshed_id;//牛舍编号


    private String cowshed_name;//牛舍名称
    private Integer cowshed_type;//牛舍类别
    private Integer col_num;//栏位数
    private Integer covered_area;//建筑面积
    private Integer status;//状态  0：禁用   1：正常
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date gmt_create;//创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date gmt_modified;//修改时间


    public String getCowshed_id() {
        return cowshed_id;
    }

    public void setCowshed_id(String cowshed_id) {
        this.cowshed_id = cowshed_id;
    }

    public String getCowshed_name() {
        return cowshed_name;
    }

    public void setCowshed_name(String cowshed_name) {
        this.cowshed_name = cowshed_name;
    }

    public Integer getCowshed_type() {
        return cowshed_type;
    }

    public void setCowshed_type(Integer cowshed_type) {
        this.cowshed_type = cowshed_type;
    }

    public Integer getCol_num() {
        return col_num;
    }

    public void setCol_num(Integer col_num) {
        this.col_num = col_num;
    }

    public Integer getCovered_area() {
        return covered_area;
    }

    public void setCovered_area(Integer covered_area) {
        this.covered_area = covered_area;
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
