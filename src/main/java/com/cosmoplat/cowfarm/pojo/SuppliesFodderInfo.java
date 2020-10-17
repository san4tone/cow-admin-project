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
@Table(name="supplies_fodder_info")
public class SuppliesFodderInfo implements Serializable{

	@Id
	private String id;//



	private String fodder_id;//饲料编号
	private String fodder_name;//饲料名称
	private Integer dry_per;//干物质（%）
	private Double unit_price;//单价（元）
	private Long stock_count;//存储数量
	private Integer stock_unit;//存储单位
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

	public String getFodder_id() {
		return fodder_id;
	}
	public void setFodder_id(String fodder_id) {
		this.fodder_id = fodder_id;
	}

	public String getFodder_name() {
		return fodder_name;
	}
	public void setFodder_name(String fodder_name) {
		this.fodder_name = fodder_name;
	}

	public Integer getDry_per() {
		return dry_per;
	}
	public void setDry_per(Integer dry_per) {
		this.dry_per = dry_per;
	}

	public Double getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(Double unit_price) {
		this.unit_price = unit_price;
	}

	public Long getStock_count() {
		return stock_count;
	}
	public void setStock_count(Long stock_count) {
		this.stock_count = stock_count;
	}

	public Integer getStock_unit() {
		return stock_unit;
	}
	public void setStock_unit(Integer stock_unit) {
		this.stock_unit = stock_unit;
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
