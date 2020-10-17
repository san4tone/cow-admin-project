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
@Table(name="supplies_drug_info")
public class SuppliesDrugInfo implements Serializable{

	@Id
	private String id;//



	private String drug_id;//药品编号
	private String drug_name;//药品名称
	private Integer drug_type;//药品类别
	private String drug_type_str;//药品类别（str）
	private String norms;//规格
	private Double unit_price;//单价
	private Integer stock_unit;//存储单位
	private String manufacturer;//生产厂家
	private Integer useful_life;//有效期（天）
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

	public String getDrug_id() {
		return drug_id;
	}
	public void setDrug_id(String drug_id) {
		this.drug_id = drug_id;
	}

	public String getDrug_name() {
		return drug_name;
	}
	public void setDrug_name(String drug_name) {
		this.drug_name = drug_name;
	}

	public Integer getDrug_type() {
		return drug_type;
	}
	public void setDrug_type(Integer drug_type) {
		this.drug_type = drug_type;
	}

	public String getDrug_type_str() {
		return drug_type_str;
	}
	public void setDrug_type_str(String drug_type_str) {
		this.drug_type_str = drug_type_str;
	}

	public String getNorms() {
		return norms;
	}
	public void setNorms(String norms) {
		this.norms = norms;
	}

	public Double getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(Double unit_price) {
		this.unit_price = unit_price;
	}

	public Integer getStock_unit() {
		return stock_unit;
	}
	public void setStock_unit(Integer stock_unit) {
		this.stock_unit = stock_unit;
	}

	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public Integer getUseful_life() {
		return useful_life;
	}
	public void setUseful_life(Integer useful_life) {
		this.useful_life = useful_life;
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
