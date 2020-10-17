package com.cosmoplat.cowfarm.pojo.ext;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @description:
 * @author: san4tone
 * @date: 2020/10/14 16:53
 */
@Data
@ExcelTarget("cowTransferExt")
@ToString
@AllArgsConstructor
public class CowTransferExt implements Serializable {

    @Excel(name = "牛号", orderNum = "1", mergeVertical = true, isImportField = "cowId")
    private String cowId;
    @Excel(name = "转出舍编号", orderNum = "2", mergeVertical = true, isImportField = "turnOutNum")
    private String turnOutNum;
    @Excel(name = "转入舍编号", orderNum = "3", mergeVertical = true, isImportField = "turnUpNum")
    private String turnUpNum;
    @Excel(name = "转舍原因", orderNum = "4", mergeVertical = true, isImportField = "reason")
    private String reason;
    @Excel(name = "转舍日期", orderNum = "5", mergeVertical = true, isImportField = "turnDateStr")
    private String turnDateStr;
    @Excel(name = "执行人", orderNum = "6", mergeVertical = true, isImportField = "turnUpMan")
    private String turnUpMan;
    @Excel(name = "备注", orderNum = "7", mergeVertical = true, isImportField = "remark")
    private String remark;
}
