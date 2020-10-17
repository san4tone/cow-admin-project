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
@Table(name="insurance_params")
public class InsuranceParams implements Serializable{

	@Id
	private String id;//



	private String prewarning_name;//参数名称
	private String prewarning_condition_name;//参数描述
	private String prewarning_value_a;//参数a
	private String prewarning_value_b;//参数b
	private Integer status;//状态（0：关闭  1：启用）
	private java.util.Date gmt_create;//创建日期
	private java.util.Date gmt_modified;//修改日期


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getPrewarning_name() {
		return prewarning_name;
	}
	public void setPrewarning_name(String prewarning_name) {
		this.prewarning_name = prewarning_name;
	}

	public String getPrewarning_condition_name() {
		return prewarning_condition_name;
	}
	public void setPrewarning_condition_name(String prewarning_condition_name) {
		this.prewarning_condition_name = prewarning_condition_name;
	}

	public String getPrewarning_value_a() {
		return prewarning_value_a;
	}
	public void setPrewarning_value_a(String prewarning_value_a) {
		this.prewarning_value_a = prewarning_value_a;
	}

	public String getPrewarning_value_b() {
		return prewarning_value_b;
	}
	public void setPrewarning_value_b(String prewarning_value_b) {
		this.prewarning_value_b = prewarning_value_b;
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
