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
@Table(name = "cow_feed_log")
public class CowFeedLog implements Serializable {

    @Id
    private String id;//


    private String cowshed_id;//牛舍id
    private Integer col_num;//存栏量
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date feed_time;//饲喂时间
    private Integer feed_count;//次数
    private String operator_id;//执行人id
    private String operator_name;//执行人名称
    private String feed_formula;//配方
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

    public String getCowshed_id() {
        return cowshed_id;
    }

    public void setCowshed_id(String cowshed_id) {
        this.cowshed_id = cowshed_id;
    }

    public Integer getCol_num() {
        return col_num;
    }

    public void setCol_num(Integer col_num) {
        this.col_num = col_num;
    }

    public java.util.Date getFeed_time() {
        return feed_time;
    }

    public void setFeed_time(java.util.Date feed_time) {
        this.feed_time = feed_time;
    }

    public Integer getFeed_count() {
        return feed_count;
    }

    public void setFeed_count(Integer feed_count) {
        this.feed_count = feed_count;
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

    public String getFeed_formula() {
        return feed_formula;
    }

    public void setFeed_formula(String feed_formula) {
        this.feed_formula = feed_formula;
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
