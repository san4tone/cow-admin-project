package com.cosmoplat.cowfarm.controller;
import com.cosmoplat.cowfarm.common.response.PageResult;
import com.cosmoplat.cowfarm.common.response.Result;
import com.cosmoplat.cowfarm.common.response.StatusCode;
import com.cosmoplat.cowfarm.pojo.CowshedCleanLog;
import com.cosmoplat.cowfarm.service.CowshedCleanLogService;
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
@RequestMapping("/cowshedCleanLog")
public class CowshedCleanLogController {

	@Autowired
	private CowshedCleanLogService cowshedCleanLogService;


	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true,StatusCode.OK,"查询成功",cowshedCleanLogService.findAll());
	}

	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		return new Result(true,StatusCode.OK,"查询成功",cowshedCleanLogService.findById(id));
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
		Page<CowshedCleanLog> pageList = cowshedCleanLogService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<CowshedCleanLog>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",cowshedCleanLogService.findSearch(searchMap));
    }

	/**
	 * 增加
	 * @param cowshedCleanLog
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody CowshedCleanLog cowshedCleanLog  ){
		cowshedCleanLogService.add(cowshedCleanLog);
		return new Result(true,StatusCode.OK,"增加成功");
	}

	/**
	 * 修改
	 * @param cowshedCleanLog
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody CowshedCleanLog cowshedCleanLog, @PathVariable String id ){
		cowshedCleanLog.setId(id);
		cowshedCleanLogService.update(cowshedCleanLog);
		return new Result(true,StatusCode.OK,"修改成功");
	}

	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String id ){
		cowshedCleanLogService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}

}
