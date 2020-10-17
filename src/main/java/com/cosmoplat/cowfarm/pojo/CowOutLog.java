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
@Table(name="cow_out_log")
public class CowOutLog implements Serializable{

	@Id
	private String id;//



	private String cow_id;//牛号
	private String cowshed_id;//牛舍编号
	private Integer cow_type;//牛群
	private Integer cow_sex;//性别（牛）
	private java.util.Date cow_birth;//出生日期
	private java.util.Date out_date;//离场日期
	private Integer out_type;//离场方式
	private Integer out_weight;//离场重量
	private String out_reason;//离场原因
	private String out_destination;//去向
	private String operator_id;//执行人id
	private String operator_name;//执行人name
	private Integer out_insurance;//是否出险
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

	public Integer getCow_sex() {
		return cow_sex;
	}
	public void setCow_sex(Integer cow_sex) {
		this.cow_sex = cow_sex;
	}

	public java.util.Date getCow_birth() {
		return cow_birth;
	}
	public void setCow_birth(java.util.Date cow_birth) {
		this.cow_birth = cow_birth;
	}

	public java.util.Date getOut_date() {
		return out_date;
	}
	public void setOut_date(java.util.Date out_date) {
		this.out_date = out_date;
	}

	public Integer getOut_type() {
		return out_type;
	}
	public void setOut_type(Integer out_type) {
		this.out_type = out_type;
	}

	public Integer getOut_weight() {
		return out_weight;
	}
	public void setOut_weight(Integer out_weight) {
		this.out_weight = out_weight;
	}

	public String getOut_reason() {
		return out_reason;
	}
	public void setOut_reason(String out_reason) {
		this.out_reason = out_reason;
	}

	public String getOut_destination() {
		return out_destination;
	}
	public void setOut_destination(String out_destination) {
		this.out_destination = out_destination;
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

	public Integer getOut_insurance() {
		return out_insurance;
	}
	public void setOut_insurance(Integer out_insurance) {
		this.out_insurance = out_insurance;
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
