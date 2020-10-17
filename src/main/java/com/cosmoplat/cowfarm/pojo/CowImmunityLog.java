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
@Table(name="cow_immunity_log")
public class CowImmunityLog implements Serializable{

	@Id
	private String id;//



	private String cow_id;//牛号
	private String cowshed_id;//牛舍编号
	private Integer cow_type;//牛群
	private Integer immune_mode;//免疫方式
	private Integer immune_name;//免疫名称
	private String vaccine_name;//疫苗名称
	private String injection_volume;//注射量
	private String batch_num;//批号
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date immune_date;//免疫日期
	private String operator_id;//执行人id
	private String operator_name;//执行人姓名
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

	public Integer getCow_type() {
		return cow_type;
	}
	public void setCow_type(Integer cow_type) {
		this.cow_type = cow_type;
	}

	public Integer getImmune_mode() {
		return immune_mode;
	}
	public void setImmune_mode(Integer immune_mode) {
		this.immune_mode = immune_mode;
	}

	public Integer getImmune_name() {
		return immune_name;
	}
	public void setImmune_name(Integer immune_name) {
		this.immune_name = immune_name;
	}

	public String getVaccine_name() {
		return vaccine_name;
	}
	public void setVaccine_name(String vaccine_name) {
		this.vaccine_name = vaccine_name;
	}

	public String getInjection_volume() {
		return injection_volume;
	}
	public void setInjection_volume(String injection_volume) {
		this.injection_volume = injection_volume;
	}

	public String getBatch_num() {
		return batch_num;
	}
	public void setBatch_num(String batch_num) {
		this.batch_num = batch_num;
	}

	public java.util.Date getImmune_date() {
		return immune_date;
	}
	public void setImmune_date(java.util.Date immune_date) {
		this.immune_date = immune_date;
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
