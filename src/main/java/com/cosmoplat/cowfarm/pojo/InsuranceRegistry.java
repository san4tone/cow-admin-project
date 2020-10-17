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
@Table(name="insurance_registry")
public class InsuranceRegistry implements Serializable{

	@Id
	private String id;//



	private String cow_id;//牛号
	private String cowshed_id;//牛舍id
	private Integer cow_type;//牛群
	private String company;//保险公司
	private String policy_no;//保单号
	private String agent_name;//保险业务员
	private String insured_amount;//保险金额
	private Integer has_bxd_file;//有无保险单文件
	private String operator_name;//操作人
	private java.util.Date operator_date;//操作日期
	private String bxd_file;//保险单文件（地址）
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

	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}

	public String getPolicy_no() {
		return policy_no;
	}
	public void setPolicy_no(String policy_no) {
		this.policy_no = policy_no;
	}

	public String getAgent_name() {
		return agent_name;
	}
	public void setAgent_name(String agent_name) {
		this.agent_name = agent_name;
	}

	public String getInsured_amount() {
		return insured_amount;
	}
	public void setInsured_amount(String insured_amount) {
		this.insured_amount = insured_amount;
	}

	public Integer getHas_bxd_file() {
		return has_bxd_file;
	}
	public void setHas_bxd_file(Integer has_bxd_file) {
		this.has_bxd_file = has_bxd_file;
	}

	public String getOperator_name() {
		return operator_name;
	}
	public void setOperator_name(String operator_name) {
		this.operator_name = operator_name;
	}

	public java.util.Date getOperator_date() {
		return operator_date;
	}
	public void setOperator_date(java.util.Date operator_date) {
		this.operator_date = operator_date;
	}

	public String getBxd_file() {
		return bxd_file;
	}
	public void setBxd_file(String bxd_file) {
		this.bxd_file = bxd_file;
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
