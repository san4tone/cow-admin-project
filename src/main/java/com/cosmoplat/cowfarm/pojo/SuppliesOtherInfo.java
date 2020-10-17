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
@Table(name="supplies_other_info")
public class SuppliesOtherInfo implements Serializable{

	@Id
	private String id;//



	private String name;//耗材名称
	private String no;//耗材编号
	private Integer type;//耗材类别
	private String type_str;//耗材类别（str）
	private String norms;//规格
	private Integer stock_unit;//存储单位
	private String unit_str;//存储单位（str）
	private Integer stock_count;//库存量
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

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}

	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}

	public String getType_str() {
		return type_str;
	}
	public void setType_str(String type_str) {
		this.type_str = type_str;
	}

	public String getNorms() {
		return norms;
	}
	public void setNorms(String norms) {
		this.norms = norms;
	}

	public Integer getStock_unit() {
		return stock_unit;
	}
	public void setStock_unit(Integer stock_unit) {
		this.stock_unit = stock_unit;
	}

	public String getUnit_str() {
		return unit_str;
	}
	public void setUnit_str(String unit_str) {
		this.unit_str = unit_str;
	}

	public Integer getStock_count() {
		return stock_count;
	}
	public void setStock_count(Integer stock_count) {
		this.stock_count = stock_count;
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
