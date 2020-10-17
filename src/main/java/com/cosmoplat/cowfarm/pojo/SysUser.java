package com.cosmoplat.cowfarm.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 实体类
 *
 * @author Administrator
 */
@Entity
@Table(name = "sys_user")
@ToString
public class SysUser implements Serializable {

    @Id
    private String id;//ID

    @ApiModelProperty(value = "用户姓名", example = "张三", required = true)
    private String username;//用户名
    @ApiModelProperty(value = "手机号码", example = "13122225555", required = true)
    private String mobile;//手机号码
    @ApiModelProperty(value = "密码", example = "123456", required = true)
    private String password;//密码
    @ApiModelProperty(value = "昵称", example = "香飘飘", required = true)
    private String nickname;//昵称
    @ApiModelProperty(value = "性别", example = "男", required = true)
    private String sex;//性别
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date birthday;//出生年月日
    private String avatar;//头像
    private String email;//E-Mail
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date regdate;//注册日期
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date updatedate;//修改日期
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date lastdate;//最后登陆日期
    private Integer status;//状态（0：关闭  1：启用）
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date gmt_create;//创建日期
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date gmt_modified;//修改日期


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public java.util.Date getBirthday() {
        return birthday;
    }

    public void setBirthday(java.util.Date birthday) {
        this.birthday = birthday;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public java.util.Date getRegdate() {
        return regdate;
    }

    public void setRegdate(java.util.Date regdate) {
        this.regdate = regdate;
    }

    public java.util.Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(java.util.Date updatedate) {
        this.updatedate = updatedate;
    }

    public java.util.Date getLastdate() {
        return lastdate;
    }

    public void setLastdate(java.util.Date lastdate) {
        this.lastdate = lastdate;
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
