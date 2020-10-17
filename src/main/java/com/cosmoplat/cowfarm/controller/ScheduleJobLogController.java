package com.cosmoplat.cowfarm.controller;

import com.cosmoplat.cowfarm.common.response.PageResult;
import com.cosmoplat.cowfarm.common.response.Result;
import com.cosmoplat.cowfarm.common.response.StatusCode;
import com.cosmoplat.cowfarm.pojo.ScheduleJobLog;
import com.cosmoplat.cowfarm.service.ScheduleJobLogService;
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
@RequestMapping("/scheduleJobLog")
public class ScheduleJobLogController {

	@Autowired
	private ScheduleJobLogService scheduleJobLogService;


	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true,StatusCode.OK,"查询成功",scheduleJobLogService.findAll());
	}

	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		return new Result(true,StatusCode.OK,"查询成功",scheduleJobLogService.findById(id));
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
		Page<ScheduleJobLog> pageList = scheduleJobLogService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<ScheduleJobLog>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",scheduleJobLogService.findSearch(searchMap));
    }

	/**
	 * 增加
	 * @param scheduleJobLog
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody ScheduleJobLog scheduleJobLog  ){
		scheduleJobLogService.add(scheduleJobLog);
		return new Result(true,StatusCode.OK,"增加成功");
	}

	/**
	 * 修改
	 * @param scheduleJobLog
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody ScheduleJobLog scheduleJobLog, @PathVariable String id ){
		scheduleJobLog.setJob_id(id);
		scheduleJobLogService.update(scheduleJobLog);
		return new Result(true,StatusCode.OK,"修改成功");
	}

	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String id ){
		scheduleJobLogService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}

}