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
@Table(name="insurance_warning")
public class InsuranceWarning implements Serializable{

	@Id
	private String id;//



	private String cow_id;//牛号
	private String cowshed_id;//牛舍
	private Integer cow_type;//牛群
	private String this_warning;//本次预警
	private String next_warning;//下次预警
	private java.util.Date start_time;//保险开始时间
	private java.util.Date end_time;//保险结束时间
	private java.util.Date cow_birth;//出生时间
	private String cow_age;//日龄
	private Integer cow_sex;//性别
	private Integer cow_varieties;//品种
	private Integer policy_effective;//保单是否生效
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

	public String getThis_warning() {
		return this_warning;
	}
	public void setThis_warning(String this_warning) {
		this.this_warning = this_warning;
	}

	public String getNext_warning() {
		return next_warning;
	}
	public void setNext_warning(String next_warning) {
		this.next_warning = next_warning;
	}

	public java.util.Date getStart_time() {
		return start_time;
	}
	public void setStart_time(java.util.Date start_time) {
		this.start_time = start_time;
	}

	public java.util.Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(java.util.Date end_time) {
		this.end_time = end_time;
	}

	public java.util.Date getCow_birth() {
		return cow_birth;
	}
	public void setCow_birth(java.util.Date cow_birth) {
		this.cow_birth = cow_birth;
	}

	public String getCow_age() {
		return cow_age;
	}
	public void setCow_age(String cow_age) {
		this.cow_age = cow_age;
	}

	public Integer getCow_sex() {
		return cow_sex;
	}
	public void setCow_sex(Integer cow_sex) {
		this.cow_sex = cow_sex;
	}

	public Integer getCow_varieties() {
		return cow_varieties;
	}
	public void setCow_varieties(Integer cow_varieties) {
		this.cow_varieties = cow_varieties;
	}

	public Integer getPolicy_effective() {
		return policy_effective;
	}
	public void setPolicy_effective(Integer policy_effective) {
		this.policy_effective = policy_effective;
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
