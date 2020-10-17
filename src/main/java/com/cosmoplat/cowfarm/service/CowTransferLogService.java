package com.cosmoplat.cowfarm.service;

import com.cosmoplat.cowfarm.common.response.PageResult;
import com.cosmoplat.cowfarm.common.response.Result;
import com.cosmoplat.cowfarm.common.response.StatusCode;
import com.cosmoplat.cowfarm.dao.CowInfoDao;
import com.cosmoplat.cowfarm.dao.CowTransferLogDao;
import com.cosmoplat.cowfarm.dto.CowTransferLogReqDto;
import com.cosmoplat.cowfarm.mapper.CowInfoMapper;
import com.cosmoplat.cowfarm.mapper.CowTransferLogMapper;
import com.cosmoplat.cowfarm.pojo.CowInfo;
import com.cosmoplat.cowfarm.pojo.CowTransferLog;
import com.cosmoplat.cowfarm.pojo.ext.CowMoveBarExt;
import com.cosmoplat.cowfarm.pojo.ext.CowTransferExt;
import com.cosmoplat.cowfarm.utils.DateUtils;
import com.cosmoplat.cowfarm.utils.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 服务层
 *
 * @author Administrator
 */
@Slf4j
@Service
public class CowTransferLogService {

    @Autowired
    private CowTransferLogDao cowTransferLogDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private CowTransferLogMapper cowTransferLogMapper;

    @Autowired
    private CowInfoMapper cowInfoMapper;

    @Autowired
    private CowInfoDao cowInfoDao;


    /**
     * 查询全部列表
     *
     * @return
     */
    public List<CowTransferLog> findAll() {
        return cowTransferLogMapper.findAll();
//        return cowTransferLogDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<CowTransferLog> findSearch(Map whereMap, int page, int size) {
        Specification<CowTransferLog> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return cowTransferLogDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<CowTransferLog> findSearch(Map whereMap) {
        Specification<CowTransferLog> specification = createSpecification(whereMap);
        return cowTransferLogDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param id
     * @return
     */
    public CowTransferLog findById(String id) {
        return cowTransferLogDao.findById(id).get();
    }

    /**
     * 增加
     *
     * @param cowTransferLog
     */
    public void add(CowTransferLog cowTransferLog) {
        cowTransferLog.setId(idWorker.nextId() + "");
        cowTransferLog.setStatus(1);
        cowTransferLog.setGmt_create(new Date());
        cowTransferLogDao.save(cowTransferLog);

        // cow_transfer_log新增记录后
        // 修改该牛之前cow_transfer_log的记录状态为0
        cowTransferLogMapper.updateOldLogStatusByCowIdAndLogId(cowTransferLog.getId(),cowTransferLog.getCow_id());
        // 更新cow_info中牛和牛舍的关系
        CowInfo cowInfo = cowInfoMapper.findCowInfoByCowId(cowTransferLog.getCow_id());
        if (cowInfo != null){
            cowInfoMapper.updateCowshedIdByCowId(cowTransferLog.getCow_id(),cowTransferLog.getTurn_up_num());
        }else {
            CowInfo cow = new CowInfo();
            cow.setCow_id(cowTransferLog.getCow_id());
            cow.setCowshed_id(cowTransferLog.getTurn_up_num());
            cow.setStatus(1);
            cow.setGmt_create(new Date());
            cowInfoDao.save(cow);
        }
    }

    /**
     * 修改
     *
     * @param cowTransferLog
     */
    public void update(CowTransferLog cowTransferLog) {
        cowTransferLogDao.save(cowTransferLog);
    }

    /**
     * 删除
     *
     * @param id
     */
    public void deleteById(String id) {
        cowTransferLogDao.deleteById(id);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<CowTransferLog> createSpecification(Map searchMap) {

        return new Specification<CowTransferLog>() {

            @Override
            public Predicate toPredicate(Root<CowTransferLog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                //
                if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
                    predicateList.add(cb.like(root.get("id").as(String.class), "%" + (String) searchMap.get("id") + "%"));
                }
                // 牛号
                if (searchMap.get("cow_id") != null && !"".equals(searchMap.get("cow_id"))) {
                    predicateList.add(cb.like(root.get("cow_id").as(String.class), "%" + (String) searchMap.get("cow_id") + "%"));
                }
                // 转出舍编号
                if (searchMap.get("turn_out_num") != null && !"".equals(searchMap.get("turn_out_num"))) {
                    predicateList.add(cb.like(root.get("turn_out_num").as(String.class), "%" + (String) searchMap.get("turn_out_num") + "%"));
                }
                // 转出舍名称
                if (searchMap.get("turn_out_name") != null && !"".equals(searchMap.get("turn_out_name"))) {
                    predicateList.add(cb.like(root.get("turn_out_name").as(String.class), "%" + (String) searchMap.get("turn_out_name") + "%"));
                }
                // 转入舍编号
                if (searchMap.get("turn_up_num") != null && !"".equals(searchMap.get("turn_up_num"))) {
                    predicateList.add(cb.like(root.get("turn_up_num").as(String.class), "%" + (String) searchMap.get("turn_up_num") + "%"));
                }
                // 转入舍名称
                if (searchMap.get("turn_up_name") != null && !"".equals(searchMap.get("turn_up_name"))) {
                    predicateList.add(cb.like(root.get("turn_up_name").as(String.class), "%" + (String) searchMap.get("turn_up_name") + "%"));
                }
                // 转舍原因（str）
                if (searchMap.get("turn_cause_str") != null && !"".equals(searchMap.get("turn_cause_str"))) {
                    predicateList.add(cb.like(root.get("turn_cause_str").as(String.class), "%" + (String) searchMap.get("turn_cause_str") + "%"));
                }
                // 执行人
                if (searchMap.get("turn_up_man") != null && !"".equals(searchMap.get("turn_up_man"))) {
                    predicateList.add(cb.like(root.get("turn_up_man").as(String.class), "%" + (String) searchMap.get("turn_up_man") + "%"));
                }
                // 执行人
                if (searchMap.get("remark") != null && !"".equals(searchMap.get("remark"))) {
                    predicateList.add(cb.like(root.get("remark").as(String.class), "%" + (String) searchMap.get("remark") + "%"));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

    /**
     * 批量导入
     *
     * @param filename
     * @param file
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Result batchImport(String filename, MultipartFile file) throws IOException {

        List<CowTransferLog> list = new ArrayList<>();
        // 验证文件名是否合格
        if (!filename.matches("^.+\\.(?i)(xls)$") && !filename.matches("^.+\\.(?i)(xlsx)$")) {
            return new Result(false, StatusCode.ERROR, "上传文件格式不正确");
        }
        // 根据文件名判断文件是2003版本还是2007版本
        boolean isExcel2003 = true;
        if (filename.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = file.getInputStream();
        Workbook wb = null;
        if (isExcel2003) {
            // 当excel是2003时,创建excel2003
            wb = new HSSFWorkbook(is);
        } else {
            // 当excel是2007时,创建excel2007
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);

        CowTransferLog cowTransferLog;

        // 循环Excel的列
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            cowTransferLog = new CowTransferLog();

            String cowId = row.getCell(0).getStringCellValue();
            if (StringUtils.isBlank(cowId)) {
                return new Result(false, StatusCode.ERROR, "牛号为空");
            }
            Date turnDate = row.getCell(1).getDateCellValue();
            String turnUpNum = row.getCell(2).getStringCellValue();
            if (StringUtils.isBlank(turnUpNum)) {
                return new Result(false, StatusCode.ERROR, "转入舍号为空");
            }
            // 如果是纯数字,比如你写的是25,cell.getNumericCellValue()获得是25.0,通过截取字符串去掉.0获得25
            double turnCause = row.getCell(3).getNumericCellValue();
            String turnUpMan = row.getCell(4).getStringCellValue();
            String remark = row.getCell(5).getStringCellValue();

            cowTransferLog.setId(idWorker.nextId() + "");
            cowTransferLog.setCow_id(cowId);
            cowTransferLog.setTurn_date(turnDate);
            cowTransferLog.setTurn_up_num(turnUpNum);
            String cause = String.valueOf(turnCause);
            cowTransferLog.setTurn_cause(Integer.valueOf(cause.substring(0, cause.length() - 2 > 0 ? cause.length() - 2 : 1)));
            cowTransferLog.setTurn_up_man(turnUpMan);
            cowTransferLog.setRemark(remark);

            // 添加到list
            list.add(cowTransferLog);
        }

        for (CowTransferLog transferLog : list) {
            String cowId = transferLog.getCow_id();
            int cnt = cowTransferLogMapper.selectCountByCowId(cowId);
//            int cnt = cowTransferLogDao.countByCow_id(cowId);
            if (cnt == 0) {
                // status默认为1
                transferLog.setGmt_create(new Date());
                transferLog.setStatus(1);
                cowTransferLogDao.save(transferLog);
            } else {
                CowTransferLog ctf = cowTransferLogMapper.findCowTransferLogByCowId(cowId);
                ctf.setTurn_date(transferLog.getTurn_date());
                ctf.setTurn_up_num(transferLog.getTurn_up_num());
                ctf.setTurn_cause(transferLog.getTurn_cause());
                ctf.setTurn_up_man(transferLog.getTurn_up_man());
                ctf.setRemark(transferLog.getRemark());
                ctf.setGmt_modified(new Date());
                cowTransferLogDao.save(ctf);
            }

            // 更新牛只表
            CowInfo cow = cowInfoMapper.findCowInfoByCowId(cowId);
            if (cow != null) {
                cow.setCowshed_id(transferLog.getTurn_up_num());
                cow.setStatus(1);
                cow.setGmt_modified(new Date());
                cowInfoDao.save(cow);
            } else {
                CowInfo cowInfo = new CowInfo();
                cowInfo.setCow_id(transferLog.getCow_id());
                cowInfo.setCowshed_id(transferLog.getTurn_up_num());
                cowInfo.setStatus(1);
                cowInfo.setGmt_create(new Date());
                cowInfoDao.save(cowInfo);
            }
        }

        is.close();

        return new Result(true, StatusCode.OK, "批量导入成功");

    }

    /**
     * 导出
     *
     * @param dto
     * @return
     */
    public void batchExport(CowTransferLogReqDto dto, HttpServletResponse response) throws IOException {
        List<CowMoveBarExt> transferLogs;
        if (dto == null) {
            transferLogs = cowTransferLogMapper.selectAll();
        } else {
            dto.checkParam();
            if (StringUtils.isNotBlank(dto.getStartAndEndDate())) {
                String[] arr = dto.getStartAndEndDate().split(" 至 ");
                dto.setStartDate(DateUtils.stringToDate(arr[0], DateUtils.DATE_FORMAT));
                dto.setEndDate(DateUtils.stringToDate(arr[1], DateUtils.DATE_FORMAT));
            }
            transferLogs = cowTransferLogMapper.findBySelective(dto);
        }

//        String[] titles = {"牛号", "转出舍编号", "转入舍编号", "转舍原因（编码）", "转舍原因", "转舍日期", "执行人", "备注"};


        // 第一步：定义一个新的工作簿
        XSSFWorkbook wb = new XSSFWorkbook();
        // 第二步：创建一个Sheet页
        XSSFSheet sheet = wb.createSheet("NZZSSJ");
        sheet.setDefaultRowHeight((short) (2 * 256));//设置行高
        sheet.setColumnWidth(0, 4000);//设置列宽
        sheet.setColumnWidth(1, 5500);
        sheet.setColumnWidth(2, 5500);
        sheet.setColumnWidth(3, 5500);
        sheet.setColumnWidth(11, 3000);
        sheet.setColumnWidth(12, 3000);
        sheet.setColumnWidth(13, 3000);
        XSSFFont font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 16);

        XSSFRow row = sheet.createRow(0);
        XSSFCell cell = row.createCell(0);
        cell.setCellValue("牛号 ");
        cell = row.createCell(1);
        cell.setCellValue("转出舍编号 ");
        cell = row.createCell(2);
        cell.setCellValue("转入舍编号");
        cell = row.createCell(3);
        cell.setCellValue("转舍原因（编码）");
        cell = row.createCell(4);
        cell.setCellValue("转舍原因");
        cell = row.createCell(5);
        cell.setCellValue("转舍日期 ");
        cell = row.createCell(6);
        cell.setCellValue("执行人 ");
        cell = row.createCell(7);
        cell.setCellValue("备注 ");
        cell = row.createCell(8);

        XSSFRow rows;
        XSSFCell cells;
        for (int i = 0; i < transferLogs.size(); i++) {
            // 第三步：在这个sheet页里创建一行
            rows = sheet.createRow(i + 1);
            // 第四步：在该行创建一个单元格
            cells = rows.createCell(0);
            // 第五步：在该单元格里设置值
            if (StringUtils.isNotBlank(transferLogs.get(i).getCow_id())) {
                cells.setCellValue(transferLogs.get(i).getCow_id());
            } else {
                cells.setCellValue("");
            }
            cells = rows.createCell(1);
            if (StringUtils.isNotBlank(transferLogs.get(i).getTurn_out_num())) {
                cells.setCellValue(transferLogs.get(i).getTurn_out_num());
            } else {
                cells.setCellValue("");
            }
            cells = rows.createCell(2);
            if (StringUtils.isNotBlank(transferLogs.get(i).getTurn_up_num())) {
                cells.setCellValue(transferLogs.get(i).getTurn_up_num());
            } else {
                cells.setCellValue("");
            }
            cells = rows.createCell(3);
            if (transferLogs.get(i).getTurn_cause() != null) {
                cells.setCellValue(transferLogs.get(i).getTurn_cause());
            } else {
                cells.setCellValue(0);
            }
            cells = rows.createCell(4);
            if (StringUtils.isNotBlank(transferLogs.get(i).getReason())) {
                cells.setCellValue(transferLogs.get(i).getReason());
            } else {
                cells.setCellValue("");
            }
            cells = rows.createCell(5);
            if (transferLogs.get(i).getTurn_date() != null) {
                Date turnDate = transferLogs.get(i).getTurn_date();
                cells.setCellValue(DateUtils.dateToString(turnDate, DateUtils.DATE_FORMAT));
            } else {
                cells.setCellValue("");
            }
            cells = rows.createCell(6);
            if (StringUtils.isNotBlank(transferLogs.get(i).getTurn_up_man())) {
                cells.setCellValue(transferLogs.get(i).getTurn_up_man());
            } else {
                cells.setCellValue("");
            }
            cells = rows.createCell(7);
            if (StringUtils.isNotBlank(transferLogs.get(i).getRemark())) {
                cells.setCellValue(transferLogs.get(i).getRemark());
            } else {
                cells.setCellValue("");
            }
        }

        String date = DateUtils.dateToString(new Date(), DateUtils.DATE_TIME_FORMAT);
        String dateStr = date.replace(" ", "_").replaceAll(":", "_");
        String fileName = "牛只转舍数据" + dateStr + ".xlsx";
        OutputStream out = response.getOutputStream();
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            out = response.getOutputStream();
            wb.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * 分页+条件查询
     *
     * @param dto
     * @return
     */
    public Result searchList(CowTransferLogReqDto dto) {
        if (dto == null) {
            return new Result(false, StatusCode.ERROR, "无效参数");
        }
        dto.checkParam();
        if (StringUtils.isNotBlank(dto.getStartAndEndDate())) {
            String[] arr = dto.getStartAndEndDate().split(" 至 ");
            dto.setStartDate(DateUtils.stringToDate(arr[0], DateUtils.DATE_FORMAT));
            dto.setEndDate(DateUtils.stringToDate(arr[1], DateUtils.DATE_FORMAT));
        }
        List<CowTransferLog> datas = cowTransferLogMapper.selectBySelective(dto);
        int total = cowTransferLogMapper.countSelectBySelective(dto);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<CowTransferLog>((long) total, datas));
    }

    /**
     * 撤销
     *
     * @param list
     * @return
     */
    public Result updateBatch(List<String> list) {
        cowTransferLogMapper.updateBatch(list);
        return new Result(true, StatusCode.OK, "撤销成功");
    }

    /**
     * 导出
     *
     * @param dto
     * @param response
     * @return
     */
    public List<CowTransferExt> getExportData(CowTransferLogReqDto dto, HttpServletResponse response) {
        List<CowTransferExt> transferLogs;
        if (dto == null) {
            transferLogs = cowTransferLogMapper.findAllCowTransferExts();
        } else {
            dto.checkParam();
            if (StringUtils.isNotBlank(dto.getStartAndEndDate())) {
                String[] arr = dto.getStartAndEndDate().split(" 至 ");
                dto.setStartDate(DateUtils.stringToDate(arr[0], DateUtils.DATE_FORMAT));
                dto.setEndDate(DateUtils.stringToDate(arr[1], DateUtils.DATE_FORMAT));
            }
            transferLogs = cowTransferLogMapper.findCowTransferExts(dto);
        }

        return transferLogs;
    }
}
