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
@Table(name="cow_transfer_log")
public class CowTransferLog implements Serializable{

	@Id
	private String id;//



	private String cow_id;//牛号
	private String turn_out_num;//转出舍编号
	private String turn_out_name;//转出舍名称
	private String turn_up_num;//转入舍编号
	private String turn_up_name;//转入舍名称
	private Integer turn_cause;//转舍原因（code）
	private String turn_cause_str;//转舍原因（str）
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date turn_date;//转舍日期
	private String turn_up_man;//执行人
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

	public String getTurn_out_num() {
		return turn_out_num;
	}
	public void setTurn_out_num(String turn_out_num) {
		this.turn_out_num = turn_out_num;
	}

	public String getTurn_out_name() {
		return turn_out_name;
	}
	public void setTurn_out_name(String turn_out_name) {
		this.turn_out_name = turn_out_name;
	}

	public String getTurn_up_num() {
		return turn_up_num;
	}
	public void setTurn_up_num(String turn_up_num) {
		this.turn_up_num = turn_up_num;
	}

	public String getTurn_up_name() {
		return turn_up_name;
	}
	public void setTurn_up_name(String turn_up_name) {
		this.turn_up_name = turn_up_name;
	}

	public Integer getTurn_cause() {
		return turn_cause;
	}
	public void setTurn_cause(Integer turn_cause) {
		this.turn_cause = turn_cause;
	}

	public String getTurn_cause_str() {
		return turn_cause_str;
	}
	public void setTurn_cause_str(String turn_cause_str) {
		this.turn_cause_str = turn_cause_str;
	}

	public java.util.Date getTurn_date() {
		return turn_date;
	}
	public void setTurn_date(java.util.Date turn_date) {
		this.turn_date = turn_date;
	}

	public String getTurn_up_man() {
		return turn_up_man;
	}
	public void setTurn_up_man(String turn_up_man) {
		this.turn_up_man = turn_up_man;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
