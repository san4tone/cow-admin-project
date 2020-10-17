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
@Table(name="cow_trust_log")
public class CowTrustLog implements Serializable{

	@Id
	private String id;//



	private String farmer_name;//农户名称
	private String cow_id;//牛号
	private String cowshed_id;//牛舍id
	private Integer cow_sex;//性别（牛）
	private java.util.Date cow_birth;//出生日期（牛）
	private Integer out_weight;//离场重量
	private Integer cow_varieties;//品种（牛）
	private java.util.Date trust_start;//托养开始日期
	private java.util.Date trust_end;//托养结束日期
	private java.util.Date operate_date;//操作时间
	private String operate_user_id;//操作人
	private String operate_username;//操作人姓名
	private Integer status;//状态  0：禁用   1：正常
	private java.util.Date gmt_create;//创建时间
	private java.util.Date gmt_modified;//修改时间


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getFarmer_name() {
		return farmer_name;
	}
	public void setFarmer_name(String farmer_name) {
		this.farmer_name = farmer_name;
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

	public Integer getOut_weight() {
		return out_weight;
	}
	public void setOut_weight(Integer out_weight) {
		this.out_weight = out_weight;
	}

	public Integer getCow_varieties() {
		return cow_varieties;
	}
	public void setCow_varieties(Integer cow_varieties) {
		this.cow_varieties = cow_varieties;
	}

	public java.util.Date getTrust_start() {
		return trust_start;
	}
	public void setTrust_start(java.util.Date trust_start) {
		this.trust_start = trust_start;
	}

	public java.util.Date getTrust_end() {
		return trust_end;
	}
	public void setTrust_end(java.util.Date trust_end) {
		this.trust_end = trust_end;
	}

	public java.util.Date getOperate_date() {
		return operate_date;
	}
	public void setOperate_date(java.util.Date operate_date) {
		this.operate_date = operate_date;
	}

	public String getOperate_user_id() {
		return operate_user_id;
	}
	public void setOperate_user_id(String operate_user_id) {
		this.operate_user_id = operate_user_id;
	}

	public String getOperate_username() {
		return operate_username;
	}
	public void setOperate_username(String operate_username) {
		this.operate_username = operate_username;
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
