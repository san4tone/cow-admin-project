package com.cosmoplat.cowfarm.pojo;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 实体类
 *
 * @author Administrator
 */
@Data
@ToString
@Entity
@Table(name = "sys_dict")
public class SysDict implements Serializable {

    @Id
    private String id;//

    /**
     * 字典表名称
     */
    @Column(name = "table_name")
    private String tableName;
    /**
     * 字段名称
     */
    @Column(name = "column_name")
    private String columnName;
    /**
     * 字典表值
     */
    private Integer code;
    /**
     * 字典标签中文名称
     */
    @Column(name = "label_zh")
    private String labelZh;
    /**
     * 字典标签英文名称
     */
    @Column(name = "label_en")
    private String labelEn;


    private Integer status;//状态  0：禁用   1：正常
    private java.util.Date gmt_create;//创建时间
    private java.util.Date gmt_modified;//修改时间


}
