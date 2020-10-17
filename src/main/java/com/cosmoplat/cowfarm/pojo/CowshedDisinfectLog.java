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
@Table(name="cowshed_disinfect_log")
public class CowshedDisinfectLog implements Serializable{

	@Id
	private String id;//



	private String cowshed_id;//牛舍id
	private Integer cow_num;//牛只数量
	private Integer disinfect_type;//消毒方式
	private String disinfectant;//消毒液
	private String ratio;//配比
	private String remark;//备注
	private String operator_id;//执行人id
	private String operator_name;//执行人
	private java.util.Date disinfect_time;//消毒日期
	private Integer status;//状态  0：禁用   1：正常
	private java.util.Date gmt_create;//创建时间
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

	public Integer getCow_num() {
		return cow_num;
	}
	public void setCow_num(Integer cow_num) {
		this.cow_num = cow_num;
	}

	public Integer getDisinfect_type() {
		return disinfect_type;
	}
	public void setDisinfect_type(Integer disinfect_type) {
		this.disinfect_type = disinfect_type;
	}

	public String getDisinfectant() {
		return disinfectant;
	}
	public void setDisinfectant(String disinfectant) {
		this.disinfectant = disinfectant;
	}

	public String getRatio() {
		return ratio;
	}
	public void setRatio(String ratio) {
		this.ratio = ratio;
	}

	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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

	public java.util.Date getDisinfect_time() {
		return disinfect_time;
	}
	public void setDisinfect_time(java.util.Date disinfect_time) {
		this.disinfect_time = disinfect_time;
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
