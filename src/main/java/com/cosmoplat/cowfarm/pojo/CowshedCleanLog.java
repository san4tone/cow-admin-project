package com.cosmoplat.cowfarm.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="cowshed_clean_log")
public class CowshedCleanLog implements Serializable{

	@Id
	private String id;//



	private String cowshed_id;//牛舍id
	private String material_id;//耗材id
	private Integer material_num;//数量
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date clean_time;//清洁日期
	private String operator_id;//执行人id
	private String operator_name;//执行人
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

	public String getCowshed_id() {
		return cowshed_id;
	}
	public void setCowshed_id(String cowshed_id) {
		this.cowshed_id = cowshed_id;
	}

	public String getMaterial_id() {
		return material_id;
	}
	public void setMaterial_id(String material_id) {
		this.material_id = material_id;
	}

	public Integer getMaterial_num() {
		return material_num;
	}
	public void setMaterial_num(Integer material_num) {
		this.material_num = material_num;
	}

	public java.util.Date getClean_time() {
		return clean_time;
	}
	public void setClean_time(java.util.Date clean_time) {
		this.clean_time = clean_time;
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
