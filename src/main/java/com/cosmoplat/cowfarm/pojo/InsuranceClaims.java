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
@Table(name="insurance_claims")
public class InsuranceClaims implements Serializable{

	@Id
	private String id;//



	private String cow_id;//牛号
	private String cowshed_id;//牛舍
	private Integer cow_type;//牛群
	private String voucher_no;//赔/退款凭证号
	private String electricbxh;//电子保险号/生物识别码
	private java.util.Date start_time;//保险起始日期
	private java.util.Date end_time;//保险终止日期
	private String policy_no;//保单号
	private String registrar;//登记人
	private String compensate;//赔付金额
	private java.util.Date operator_date;//操作日期
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

	public String getVoucher_no() {
		return voucher_no;
	}
	public void setVoucher_no(String voucher_no) {
		this.voucher_no = voucher_no;
	}

	public String getElectricbxh() {
		return electricbxh;
	}
	public void setElectricbxh(String electricbxh) {
		this.electricbxh = electricbxh;
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

	public String getPolicy_no() {
		return policy_no;
	}
	public void setPolicy_no(String policy_no) {
		this.policy_no = policy_no;
	}

	public String getRegistrar() {
		return registrar;
	}
	public void setRegistrar(String registrar) {
		this.registrar = registrar;
	}

	public String getCompensate() {
		return compensate;
	}
	public void setCompensate(String compensate) {
		this.compensate = compensate;
	}

	public java.util.Date getOperator_date() {
		return operator_date;
	}
	public void setOperator_date(java.util.Date operator_date) {
		this.operator_date = operator_date;
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
