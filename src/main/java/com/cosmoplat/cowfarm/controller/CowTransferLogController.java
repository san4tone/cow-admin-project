package com.cosmoplat.cowfarm.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.cosmoplat.cowfarm.common.response.Result;
import com.cosmoplat.cowfarm.common.response.StatusCode;
import com.cosmoplat.cowfarm.dto.CowTransferLogReqDto;
import com.cosmoplat.cowfarm.pojo.CowTransferLog;
import com.cosmoplat.cowfarm.pojo.ext.CowTransferExt;
import com.cosmoplat.cowfarm.service.CowTransferLogService;
import com.cosmoplat.cowfarm.utils.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 控制器层
 *
 * @author Administrator
 */
@RestController
@CrossOrigin
@RequestMapping("/cowTransferLog")
@Api(value = "牛只转舍", description = "牛只转舍接口")
public class CowTransferLogController extends BaseController {

    @Autowired
    private CowTransferLogService cowTransferLogService;

    @ApiOperation(value = "批量导入牛只转舍Excel")
    @PostMapping("/import")
    public Result importCowMoveBar(@RequestParam("file") MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        return cowTransferLogService.batchImport(filename, file);
    }

    @ApiOperation(value = "导出")
    @PostMapping("/export")
    public void exportCowMoveBar(@RequestBody CowTransferLogReqDto dto) {
        List<CowTransferExt> data = cowTransferLogService.getExportData(dto, response);
        if (data != null && data.size() > 0) {
            try {
                ExportParams params = new ExportParams("牛只转舍数据", null, "牛只转舍数据");
                Workbook workbook = ExcelExportUtil.exportExcel(params, CowTransferExt.class, data);

                String date = DateUtils.dateToString(new Date(), DateUtils.DATE_TIME_FORMAT);
                String dateStr = date.replace(" ", "_").replaceAll(":", "_");
                String fileName = "牛只转舍数据" + dateStr + ".xls";

//                response.setCharacterEncoding("UTF-8");
//                response.setHeader("content-Type", "application/vnd.ms-excel");
//                response.setHeader("Content-Disposition",
//                        "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
//                workbook.write(response.getOutputStream());

                File targetFile = new File(fileName);
                FileOutputStream fos = new FileOutputStream(targetFile);
                workbook.write(fos);
                fos.close();


//            FileWithExcelUtil.exportExcel(data, "牛只转舍数据", "牛只转舍", CowTransferExt.class, "牛只转舍数据.xls", response);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 查询全部数据
     *
     * @return
     */
    @ApiOperation(value = "查询全部")
    @GetMapping("/search")
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", cowTransferLogService.findAll());
    }

    /**
     * 分页+多条件查询
     *
     * @param dto
     * @return
     */
    @ApiOperation(value = "条件分页查询")
    @PostMapping("/searchlist")
    public Result findSearch(@RequestBody CowTransferLogReqDto dto) {
        if (dto == null) {
            return new Result(false, StatusCode.ERROR, "无效参数");
        }
        return cowTransferLogService.searchList(dto);
    }

    /**
     * 增加
     *
     * @param cowTransferLog
     */
    @ApiOperation(value = "新增")
    @PostMapping("/add")
    public Result add(@RequestBody CowTransferLog cowTransferLog) {
        cowTransferLogService.add(cowTransferLog);
        return new Result(true, StatusCode.OK, "增加成功");
    }


    /**
     * 撤销
     *
     * @param params
     */
    @ApiOperation(value = "撤销")
    @PostMapping(value = "/deletebatch")
    public Result delete(@RequestBody String[] params) {
        List<String> list = Arrays.asList(params);
        return cowTransferLogService.updateBatch(list);
    }

}
