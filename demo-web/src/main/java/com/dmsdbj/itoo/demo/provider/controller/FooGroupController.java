package com.dmsdbj.itoo.demo.provider.controller;

import com.dmsdbj.itoo.demo.entity.FooGroupEntity;
import com.dmsdbj.itoo.demo.model.FooGroupModel;
import com.dmsdbj.itoo.demo.provider.service.FooGroupService;
import com.dmsdbj.itoo.tool.business.ItooResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static com.dmsdbj.itoo.demo.provider.until.DemoConstants.OPERATOR;


/**
 * FooGroupController接口
 * fooGroup表
 *
 * @author 刘亚雯
 * @version 1.0.0
 * @since 1.0.0 2018-10-18 14:35:27
 */
@Api(tags = {"fooGroup表接口"})
@RequestMapping(value = "/fooGroup")
@RestController
public class FooGroupController {

    @Resource
    private FooGroupService fooGroupService;

    //region 模板生成：基本增删改

    /**
     * 添加
     *
     * @param model FooGroupModel
     * @return 添加的结果
     * @author 刘亚雯
     * @since 1.0.0 2018-10-18 14:35:27
     */
    @ApiOperation(value = "添加")
    @PostMapping(value = {"/create"})
    public ItooResult create(@RequestBody FooGroupModel model) {
        FooGroupEntity fooGroupEntity = new FooGroupEntity();
        BeanUtils.copyProperties(model, fooGroupEntity);
        fooGroupService.insert(fooGroupEntity);
        return ItooResult.build(ItooResult.SUCCESS, "添加成功");
    }

    /**
     * 删除
     *
     * @param id 主键id
     * @return ItooResult 是否删除成功          
     * @author 刘亚雯
     * @since 1.0.0 2018-10-18 14:35:27
     */
    @ApiOperation(value = "删除")
    @DeleteMapping(value = {"/delete/{id}"})
    public ItooResult delete(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
        fooGroupService.deleteById(id, OPERATOR);
        return ItooResult.build(ItooResult.SUCCESS, "删除成功");
    }

    /**
     * 批量删除
     *
     * @param ids ids
     * @return ItooResult 批量删除是否成功结果
     * @author 刘亚雯
     * @since 1.0.0 2018-10-18 14:35:27
     */
    @ApiOperation(value = "根据id批量删除")
    @DeleteMapping(value = {"/deleteByIds"})
    @ApiImplicitParam(name = "ids", value = "ids", dataType = "List<String>", required = true)
    public ItooResult deleteByIds(@RequestBody List<String> ids) {
        fooGroupService.deleteByIds(ids, OPERATOR);
        return ItooResult.build(ItooResult.SUCCESS, "批量删除成功");
    }

    /**
     * 修改
     *
     * @param model FooGroupModel
     * @return 修改后的结果
     * @author 刘亚雯
     * @since 1.0.0 2018-10-18 14:35:27
     */
    @ApiOperation(value = "修改")
    @PutMapping(value = {"/modify"})
    public ItooResult modify(@RequestBody FooGroupModel model) {
        FooGroupEntity fooGroupEntity = new FooGroupEntity();
        BeanUtils.copyProperties(model, fooGroupEntity);
        fooGroupService.updateById(fooGroupEntity);
        return ItooResult.build(ItooResult.SUCCESS, "修改成功");
    }

    /**
     * 根据id查找FooGroup
     *
     * @param id 主键id
     * @author 刘亚雯
     * @since 1.0.0 2018-10-18 14:35:27
     */
    @ApiOperation(value = "根据id查询", notes = "请输入主键id进行查询")
    @GetMapping(value = {"/findById/{id}"})
    public ItooResult findById(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
        FooGroupEntity entity = fooGroupService.findById(id);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", entity);
    }

    /**
     * 分页查询所有FooGroup
     *
     * @param pageNo   页码
     * @param pageSize 每页条数
     * @author 刘亚雯
     * @since 1.0.0 2018-10-18 14:35:27
     */
    @ApiOperation(value = "根据id查询", notes = "请输入主键id进行查询")
    @GetMapping(value = {"/queryPageAll/{pageNo}/{pageSize}"})
    public ItooResult queryPageAll(@PathVariable Integer pageNo, @PathVariable Integer pageSize) {
        PageInfo<FooGroupEntity> fooGroups = fooGroupService.queryPageAll(pageNo, pageSize);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", fooGroups);
    }
    //endregion

    /* **********************************以下为非模板生成的内容********************************* */

    /**
     * 查询所有fooGroup父类
     *
     * @return fooGroup集合
     * @author 刘亚雯
     * @since 1.0.0 2018-10-18 14:35:27
     */
    @ApiOperation(value = "查询所有fooGroup父类", notes = "查询所有fooGroup父类")
    @GetMapping(value = {"/queryAllRoot"})
    public ItooResult queryAllRoot() {
        List<FooGroupEntity> fooGroupEntityList = fooGroupService.queryAllRoot();
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", fooGroupEntityList);
    }

    /**
     * 根据父id查询所有子fooGroup
     *
     * @param parentId 父类fooGroupId
     * @return fooGroup集合
     * @author 刘亚雯
     * @since 1.0.0 2018-10-19 16:28:41
     */
    @ApiOperation(value = "根据父类Id查询fooGroup", notes = "根据父类Id查询fooGroup")
    @GetMapping(value = {"/queryFooGroupByPid/{parentId}"})
    @ApiImplicitParam(name = "parentId", value = "父类fooGroupId", type = "String", required = true)
    public ItooResult queryFooGroupByPid(@PathVariable String parentId) {
        List<FooGroupEntity> fooGroupEntityList = fooGroupService.queryByParentId(parentId);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", fooGroupEntityList);
    }

    /**
     * 查询最新的1条foo属于的fooGroup--dubbo调用演示
     *
     * @return FooGroupModel集合
     * @author 刘亚雯
     * @since 1.0.0 2018-10-25 18:30:37
     */
    @ApiOperation(value = "查询最新的1条foo属于的fooGroup--dubbo调用演示")
    @GetMapping(value = {"/queryNewFooGroup"})
    public ItooResult findNewFooGroup(){
        FooGroupModel fooInfoModel=fooGroupService.findNewFooGroup();
        return ItooResult.build(ItooResult.SUCCESS,"查询成功",fooInfoModel);
    }
}    
