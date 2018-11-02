package com.dmsdbj.itoo.demo.provider.controller;

import com.dmsdbj.itoo.demo.entity.BarEntity;
import com.dmsdbj.itoo.demo.model.BarModel;
import com.dmsdbj.itoo.demo.provider.service.BarService;
import com.dmsdbj.itoo.tool.business.ItooResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.dmsdbj.itoo.demo.provider.until.DemoConstants.OPERATOR;


/**
 * BarController
 * bar表
 *
 * @author 刘雅雯
 * @version 1.0.0
 * @since 1.0.0 2018-10-26 09:48:45
 */
@Api(tags = {"bar表接口"})
@RequestMapping(value = "/bar")
@RestController
public class BarController {

    @Resource
    private BarService barService;

    @Resource
    private RedisTemplate redisTemplate;

    //region 模板生成：基本增删改

    /**
     * 添加
     *
     * @param model BarModel
     * @return 添加的结果
     * @author 刘雅雯
     * @since 1.0.0 2018-10-26 09:48:45
     */
    @ApiOperation(value = "添加")
    @PostMapping(value = {"/create"})
    @CacheEvict(value = {"queryByName"},allEntries = true)
    public ItooResult create(@RequestBody BarModel model) {
        if (StringUtils.isEmpty(model.getBarName())) {
            return ItooResult.build(ItooResult.FAIL, "barName为空");
        }
        BarEntity barEntity = new BarEntity();
        BeanUtils.copyProperties(model, barEntity);
        barService.insert(barEntity);
        return ItooResult.build(ItooResult.SUCCESS, "添加成功");
    }

    /**
     * 删除
     *
     * @param id 主键id
     * @return ItooResult 是否删除成功
     * @author 刘雅雯
     * @since 1.0.0 2018-10-26 09:48:45
     */
    @ApiOperation(value = "根据id删除（单个）")
    @DeleteMapping(value = {"/delete/{id}"})
    public ItooResult delete(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
        barService.deleteById(id, OPERATOR);
        return ItooResult.build(ItooResult.SUCCESS, "删除成功");
    }

    /**
     * 批量删除
     *
     * @param ids ids
     * @return ItooResult 批量删除是否成功结果
     * @author 刘雅雯
     * @since 1.0.0 2018-10-26 09:48:45
     */
    @ApiOperation(value = "根据id批量删除")
    @DeleteMapping(value = {"/deleteByIds"})
    @ApiImplicitParam(name = "ids", value = "ids", dataType = "List<String>", required = true)
    @CacheEvict(value = {"queryByName"},allEntries = true)
    public ItooResult deleteByIds(@RequestBody List<String> ids) {
        barService.deleteByIds(ids, OPERATOR);
        return ItooResult.build(ItooResult.SUCCESS, "批量删除成功");
    }

    /**
     * 修改
     *
     * @param model BarModel
     * @return 修改后的结果
     * @author 刘雅雯
     * @since 1.0.0 2018-10-26 09:48:45
     */
    @ApiOperation(value = "根据id修改bar")
    @PutMapping(value = {"/modify"})
    @CacheEvict(value = {"queryByName"},allEntries = true)
    public ItooResult modify(@RequestBody BarModel model) {
        if (StringUtils.isEmpty(model.getBarName())) {
            return ItooResult.build(ItooResult.FAIL, "barName为空");
        }
        BarEntity barEntity = new BarEntity();
        BeanUtils.copyProperties(model, barEntity);
        barService.updateById(barEntity);
        return ItooResult.build(ItooResult.SUCCESS, "修改成功");
    }

    /**
     * 根据id查找Bar
     *
     * @param id 主键id
     * @author 刘雅雯
     * @since 1.0.0 2018-10-26 09:48:45
     */
    @ApiOperation(value = "根据id查询")
    @GetMapping(value = {"/findById/{id}"})
    public ItooResult findById(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
        BarEntity barEntity = barService.findById(id);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", barEntity);
    }

    /**
     * 分页查询所有Bar
     *
     * @param pageNo   页码
     * @param pageSize 每页条数
     * @author 刘雅雯
     * @since 1.0.0 2018-10-26 09:48:45
     */
    @ApiOperation(value = "分页查询所有Bar")
    @GetMapping(value = {"/queryPageAll/{pageNo}/{pageSize}"})
    public ItooResult queryPageAll(@ApiParam(name = "pageNo", value = "页码", required = true, example = "1") @PathVariable Integer pageNo,
                                   @ApiParam(name = "pageSize", value = "页数", required = true, example = "10") @PathVariable Integer pageSize) {
        PageInfo<BarEntity> bars = barService.queryPageAll(pageNo, pageSize);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", bars);
    }

    /**
     * 根据资源名称模糊查询bar
     *
     * @param barName  资源名称
     * @param pageNo   页码
     * @param pageSize 页数
     * @return 模糊查询的bar
     * @author 刘雅雯
     * @since 1.0.0 2018-10-26 09:48:45
     */
    @ApiOperation(value = "根据资源名称模糊查询bar", notes = "分页根据资源名称模糊查询bar")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", dataType = "int", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "记录数", dataType = "int", required = true, example = "10")
    })
    @GetMapping(value = "queryBarByLikeBarName/{pageNo}/{pageSize}")
    public ItooResult queryBarByLikeBarName(@RequestParam(required = false, defaultValue = "") String barName,
                                            @PathVariable Integer pageNo, @PathVariable Integer pageSize) {
        PageInfo<BarEntity> barList = barService.queryByLikeBarName(barName, pageNo, pageSize);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", barList);
    }
    //endregion

    /* **********************************以下为非模板生成的内容********************************* */

    /**
     * 根据BarName查询bar--缓存示例
     *
     * @param barName 资源名称
     * @return BarModel集合
     * @author 刘雅雯
     * @since 1.0.0 2018-10-26 09:48:45
     */
    @ApiOperation(value = "根据BarName查询bar--缓存示例")
    @GetMapping(value = "queryBarByName/{barName}")
    public ItooResult queryBarByName(@ApiParam(name = "barName", value = "资源名称", required = true) @PathVariable String barName) {
        List<BarModel> barModels = barService.queryByName(barName);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", barModels);
    }

    /**
     * 将bar放入指定的分组--缓存示例
     *
     * @param group 分组
     * @param barName barName
     * @return 分组中的bar信息
     * @author 刘雅雯
     * @since 1.0.0 2018-10-26 16:54:42
     */
    @ApiOperation(value = "根据barName查出bar放入指定的分组--缓存示例")
    @GetMapping(value = "testRedis/{group}/{barName}")
    public ItooResult queryBarGroup(@ApiParam(name ="group",value ="分组",required = true)@PathVariable String group,
                                    @ApiParam(name ="barName",value ="资源名称",required = true)@PathVariable String barName){
        List<BarModel> barModels = barService.queryByName(barName);
        long size=redisTemplate.opsForZSet().size(group);
        for(BarModel barModel:barModels){
            redisTemplate.opsForZSet().add(group,barModel,size+1);
            redisTemplate.expire(group,60, TimeUnit.SECONDS);
        }
        Set set=redisTemplate.opsForZSet().range(group,0,-1);
        return ItooResult.build(ItooResult.SUCCESS,"查询成功",set);
    }
}    
