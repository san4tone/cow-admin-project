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
@Table(name = "sys_role")
public class SysRole implements Serializable {

    @Id
    private String role_id;//

    private String role_name;//角色名称
    private String remark;//备注
    private Integer status;//状态（0：关闭  1：启用）
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date gmt_create;//创建日期
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date gmt_modified;//修改日期


    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
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
