package com.cosmoplat.cowfarm.controller;
import com.cosmoplat.cowfarm.common.response.PageResult;
import com.cosmoplat.cowfarm.common.response.Result;
import com.cosmoplat.cowfarm.common.response.StatusCode;
import com.cosmoplat.cowfarm.pojo.SuppliesDrugChangeLog;
import com.cosmoplat.cowfarm.service.SuppliesDrugChangeLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/suppliesDrugChangeLog")
public class SuppliesDrugChangeLogController {

	@Autowired
	private SuppliesDrugChangeLogService suppliesDrugChangeLogService;


	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true,StatusCode.OK,"查询成功",suppliesDrugChangeLogService.findAll());
	}

	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		return new Result(true,StatusCode.OK,"查询成功",suppliesDrugChangeLogService.findById(id));
	}


	/**
	 * 分页+多条件查询
	 * @param searchMap 查询条件封装
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页结果
	 */
	@RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
	public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
		Page<SuppliesDrugChangeLog> pageList = suppliesDrugChangeLogService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<SuppliesDrugChangeLog>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",suppliesDrugChangeLogService.findSearch(searchMap));
    }

	/**
	 * 增加
	 * @param suppliesDrugChangeLog
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody SuppliesDrugChangeLog suppliesDrugChangeLog  ){
		suppliesDrugChangeLogService.add(suppliesDrugChangeLog);
		return new Result(true,StatusCode.OK,"增加成功");
	}

	/**
	 * 修改
	 * @param suppliesDrugChangeLog
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody SuppliesDrugChangeLog suppliesDrugChangeLog, @PathVariable String id ){
		suppliesDrugChangeLog.setId(id);
		suppliesDrugChangeLogService.update(suppliesDrugChangeLog);
		return new Result(true,StatusCode.OK,"修改成功");
	}

	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String id ){
		suppliesDrugChangeLogService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}

}