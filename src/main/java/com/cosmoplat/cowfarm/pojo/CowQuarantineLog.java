package com.cosmoplat.cowfarm.pojo;

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
@Table(name="cow_quarantine_log")
public class CowQuarantineLog implements Serializable{

	@Id
	private String id;//



	private String cow_id;//牛号
	private String cowshed_id;//牛舍编号
	private Integer cow_type;//牛群
	private Integer quarantine_type;//检疫方式
	private String quarantine_name;//检疫名称
	private Integer quarantine_result;//检疫结果
	private java.util.Date quarantine_date;//检疫日期
	private String operator_id;//执行人id
	private String operator_name;//执行人名称
	private String remark;//备注
	private Integer status;//状态  0：禁用   1：正常
	private java.util.Date gmt_create;//创建时间
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

	public Integer getQuarantine_type() {
		return quarantine_type;
	}
	public void setQuarantine_type(Integer quarantine_type) {
		this.quarantine_type = quarantine_type;
	}

	public String getQuarantine_name() {
		return quarantine_name;
	}
	public void setQuarantine_name(String quarantine_name) {
		this.quarantine_name = quarantine_name;
	}

	public Integer getQuarantine_result() {
		return quarantine_result;
	}
	public void setQuarantine_result(Integer quarantine_result) {
		this.quarantine_result = quarantine_result;
	}

	public java.util.Date getQuarantine_date() {
		return quarantine_date;
	}
	public void setQuarantine_date(java.util.Date quarantine_date) {
		this.quarantine_date = quarantine_date;
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
