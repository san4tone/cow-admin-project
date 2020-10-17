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
@Table(name="farm_info")
public class FarmInfo implements Serializable{

	@Id
	private String farm_id;//牛场id



	private String standard_id;//标准场号
	private String farm_name;//牛场名称
	private String principal;//负责人
	private String mobile;//手机号
	private String province;//省
	private String city;//市
	private String country;//县/区
	private String township;//乡镇
	private String village;//村庄
	private String longitude;//经度
	private String latitude;//纬度
	private String distance;//距离
	private Integer feeding_scale;//饲养规模
	private Integer breed_mode;//养殖方式
	private Integer status;//状态  0：禁用   1：正常
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date gmt_create;//创建时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date gmt_modified;//修改时间


	public String getFarm_id() {
		return farm_id;
	}
	public void setFarm_id(String farm_id) {
		this.farm_id = farm_id;
	}

	public String getStandard_id() {
		return standard_id;
	}
	public void setStandard_id(String standard_id) {
		this.standard_id = standard_id;
	}

	public String getFarm_name() {
		return farm_name;
	}
	public void setFarm_name(String farm_name) {
		this.farm_name = farm_name;
	}

	public String getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	public String getTownship() {
		return township;
	}
	public void setTownship(String township) {
		this.township = township;
	}

	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}

	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}

	public Integer getFeeding_scale() {
		return feeding_scale;
	}
	public void setFeeding_scale(Integer feeding_scale) {
		this.feeding_scale = feeding_scale;
	}

	public Integer getBreed_mode() {
		return breed_mode;
	}
	public void setBreed_mode(Integer breed_mode) {
		this.breed_mode = breed_mode;
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
