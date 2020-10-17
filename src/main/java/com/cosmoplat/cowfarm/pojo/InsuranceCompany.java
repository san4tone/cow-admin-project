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
@Table(name="insurance_company")
public class InsuranceCompany implements Serializable{

	@Id
	private String id;//



	private String name;//公司名称
	private String gongszch;//工商注册号
	private String website;//网址
	private String province;//省
	private String city;//市
	private String zipcode;//邮编
	private String mail;//邮箱
	private String lianxr;//联系人
	private String lianxdh;//联系电话
	private String address;//地址
	private String remark;//备注
	private java.util.Date operate_time;//操作时间
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

	public String getGongszch() {
		return gongszch;
	}
	public void setGongszch(String gongszch) {
		this.gongszch = gongszch;
	}

	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}

	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getLianxr() {
		return lianxr;
	}
	public void setLianxr(String lianxr) {
		this.lianxr = lianxr;
	}

	public String getLianxdh() {
		return lianxdh;
	}
	public void setLianxdh(String lianxdh) {
		this.lianxdh = lianxdh;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public java.util.Date getOperate_time() {
		return operate_time;
	}
	public void setOperate_time(java.util.Date operate_time) {
		this.operate_time = operate_time;
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
