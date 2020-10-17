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
@Table(name="ware_info")
public class WareInfo implements Serializable{

	@Id
	private String ware_id;//仓库id



	private String ware_name;//仓库名称
	private Integer ware_type;//仓库类型
	private String ware_type_str;//仓库类型（str）
	private Integer ware_state;//仓库状态
	private String ware_state_str;//仓库状态（str）
	private Integer status;//状态  0：禁用   1：正常
	private java.util.Date gmt_create;//创建时间
	private java.util.Date gmt_modified;//修改时间


	public String getWare_id() {
		return ware_id;
	}
	public void setWare_id(String ware_id) {
		this.ware_id = ware_id;
	}

	public String getWare_name() {
		return ware_name;
	}
	public void setWare_name(String ware_name) {
		this.ware_name = ware_name;
	}

	public Integer getWare_type() {
		return ware_type;
	}
	public void setWare_type(Integer ware_type) {
		this.ware_type = ware_type;
	}

	public String getWare_type_str() {
		return ware_type_str;
	}
	public void setWare_type_str(String ware_type_str) {
		this.ware_type_str = ware_type_str;
	}

	public Integer getWare_state() {
		return ware_state;
	}
	public void setWare_state(Integer ware_state) {
		this.ware_state = ware_state;
	}

	public String getWare_state_str() {
		return ware_state_str;
	}
	public void setWare_state_str(String ware_state_str) {
		this.ware_state_str = ware_state_str;
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
