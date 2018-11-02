package com.dmsdbj.itoo.demo.provider.controller;

import com.dmsdbj.itoo.demo.entity.FooEntity;
import com.dmsdbj.itoo.demo.model.FooCascadeModel;
import com.dmsdbj.itoo.demo.model.FooModel;
import com.dmsdbj.itoo.demo.provider.service.FooService;
import com.dmsdbj.itoo.demo.provider.until.DemoConstants;
import com.dmsdbj.itoo.tool.business.ItooResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * FooController接口
 * foo人员表
 *
 * @author 刘亚雯
 * @version 1.0.0
 * @since 1.0.0 2018-10-18 14:35:27
 */
@Api(tags = {"foo表接口"})
@RequestMapping(value = "/foo")
@RestController
public class FooController {

    @Resource
    private FooService fooService;


    //region 模板生成：基本增删改

    /**
     * 添加
     *
     * @param model FooModel
     * @return 添加的结果
     * @author 刘亚雯
     * @since 1.0.0 2018-10-18 14:35:27
     */
    @ApiOperation(value = "添加")
    @PostMapping(value = {"/create"})
    public ItooResult create(@RequestBody FooModel model) {
        if (StringUtils.isEmpty(model.getUserCode())) {
            return ItooResult.build(ItooResult.FAIL, "用户名userCode为空");
        }
        if (StringUtils.isEmpty(model.getUserName())) {
            return ItooResult.build(ItooResult.FAIL, "用户名userName为空");
        }
        FooEntity fooEntity = new FooEntity();
        BeanUtils.copyProperties(model, fooEntity);
        return fooService.create(fooEntity);
    }


    /**
     * 删除
     *
     * @param id 主键id
     * @author 刘亚雯
     * @since 1.0.0 2018-10-18 14:35:27
     */
    @ApiOperation(value = "根据id删除")
    @DeleteMapping(value = {"/delete/{id}"})
    public ItooResult delete(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
        fooService.deleteById(id, DemoConstants.OPERATOR);
        return ItooResult.build(ItooResult.SUCCESS, "删除成功");
    }

    /**
     * 根据ids批量删除用户
     *
     * @param ids ids数组
     * @return 是否删除成功
     * @author 刘亚雯
     * @since 1.0.0 2018-10-19 11:51:59
     */
    @ApiOperation(value = "根据id批量删除")
    @DeleteMapping(value = {"/deleteByIds"})
    @ApiImplicitParam(name = "ids", value = "ids", dataType = "List<String>", required = true)
    public ItooResult deleteByIds(@RequestBody List<String> ids) {
        fooService.deleteByIds(ids, DemoConstants.OPERATOR);
        return ItooResult.build(ItooResult.SUCCESS, "批量删除成功");
    }

    /**
     * 修改
     *
     * @param model FooModel
     * @return 修改后的结果
     * @author 刘亚雯
     * @since 1.0.0 2018-10-18 14:35:27
     */
    @ApiOperation(value = "根据id修改Foo")
    @PutMapping(value = {"/modify"})
    public ItooResult modify(@RequestBody FooModel model) {
        if (StringUtils.isEmpty(model.getUserCode())) {
            return ItooResult.build(ItooResult.FAIL, "用户名userCode为空");
        }
        if (StringUtils.isEmpty(model.getUserName())) {
            return ItooResult.build(ItooResult.FAIL, "用户名userName为空");
        }
        FooEntity fooEntity = new FooEntity();
        BeanUtils.copyProperties(model, fooEntity);
        return fooService.modify(fooEntity);
    }

    /**
     * 根据id查找FooGroup
     *
     * @param id 主键id
     * @return FooGroup 集合
     * @author 刘亚雯
     * @since 1.0.0 2018-10-18 14:35:27
     */
    @ApiOperation(value = "根据id查询", notes = "请输入主键id进行查询")
    @GetMapping(value = {"/findById/{id}"})
    public ItooResult findById(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
        FooEntity fooEntity = fooService.findById(id);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", fooEntity);
    }

    /**
     * 分页查询所有的人员
     *
     * @param pageNo   页码
     * @param pageSize 页数
     * @return 所有的foo
     * @author 刘亚雯
     * @since 1.0.0 2018-10-18 20:08:44
     */
    @ApiOperation(value = "查询所有的foo", notes = "分页查询所有的人员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", dataType = "int", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "记录数", dataType = "int", required = true, example = "10"),
    })
    @GetMapping(value = {"/queryAllFoo/{pageNo}/{pageSize}"})
    public ItooResult queryAllFoo(@PathVariable Integer pageNo, @PathVariable Integer pageSize) {
        PageInfo<FooModel> fooList = fooService.queryAllFoo(pageNo, pageSize);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", fooList);
    }

    //endregion


    /* **************************以下为非模板生成的内容********************************** */


    /**
     * 根据id查找Foo
     *
     * @param id 主键id
     * @return ItooResult Foo集合
     * @author 刘亚雯
     * @since 1.0.0 2018-10-18 14:35:27
     */
    @ApiOperation(value = "根据id查询Foo", notes = "请输入主键id进行查询")
    @GetMapping(value = {"/findFooModelById/{id}"})
    public ItooResult findFooModelById(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
        FooModel fooModel = fooService.queryFooModelById(id);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", fooModel);
    }

    /**
     * 根据姓名模糊查询foo
     *
     * @param fooName  fooName
     * @param pageNo   页码
     * @param pageSize 页数
     * @return 模糊查询的foo
     * @author 刘亚雯
     * @since 1.0.0 2018-10-19 15:55:13
     */
    @ApiOperation(value = "根据姓名模糊查询foo", notes = "分页根据姓名模糊查询foo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", dataType = "int", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "记录数", dataType = "int", required = true, example = "10")
    })
    @GetMapping(value = "queryFooByLikeName/{pageNo}/{pageSize}")
    public ItooResult queryFooByLikeName(@RequestParam(required = false, defaultValue = "") String fooName,
                                         @PathVariable Integer pageNo, @PathVariable Integer pageSize) {
        PageInfo<FooModel> fooList = fooService.queryByLikeName(fooName, pageNo, pageSize);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", fooList);
    }

    /**
     * 根据fooGroupId查询foo
     *
     * @param fooGroupId fooGroupId
     * @return foo集合
     * @author 刘亚雯
     * @since 1.0.0 2018-10-19 16:43:03
     */
    @ApiOperation(value = "根据fooGroupId查询foo", notes = "根据fooGroupId查询foo")
    @GetMapping(value = "{/queryFooByGroupId/{fooGroupId}")
    @ApiImplicitParam(name = "fooGroupId", value = "fooGroupId", dataType = "String", required = true)
    public ItooResult queryFooByGroupId(@PathVariable String fooGroupId) {
        List<FooModel> fooList = fooService.queryByGroupId(fooGroupId);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", fooList);
    }

    /**
     * 下载foo模板，批量导入使用
     *
     * @param response 响应
     * @return 下载模板是否成功
     * @author 刘亚雯
     * @since 1.0.0 2018-10-19 20:59:45
     */
    @ApiOperation(value = "下载foo模板，批量导入使用", notes = "下载foo模板，批量导入使用")
    @GetMapping(value = {"/downFooTemplate"})
    public ItooResult downFooTemplate(HttpServletResponse response) {
        if (fooService.downTemplate(response)) {
            return ItooResult.build(ItooResult.SUCCESS, "下载成功");
        } else {
            return ItooResult.build(ItooResult.FAIL, "下载失败");
        }
    }

    /**
     * 批量导入foo
     *
     * @param multipartFile 批量导入的文件
     * @param request       请求
     * @param response      响应
     * @return ItooResult 批量导入的结果
     * @author 刘亚雯
     * @since 1.0.0 2018-10-19 20:59:45
     */
    @ApiOperation(value = "批量导入foo",notes = "0001:部分成功；0000:成功；1111:失败")
    @PostMapping(value = {"/importFooByTemplate"})
    public ItooResult importFooByTemplate(@RequestParam(value = "file") MultipartFile multipartFile,
                                          HttpServletRequest request, HttpServletResponse response) {
        return fooService.importTemplate(multipartFile, request, response);
    }

    /**
     * 根据FooGroupId导出Foo
     *
     * @param response 响应
     * @param groupId  groupId
     * @return 导出结果信息
     * @author 刘亚雯
     * @since 1.0.0 2018-10-26 23:35:52
     */
    @ApiOperation(value = "根据FooGroupId导出Foo")
    @GetMapping(value = {"/exportFooByGroupId/{groupId}"})
    public ItooResult exportFooByGroupId(HttpServletResponse response,
                                         @ApiParam(name = "groupId", value = "groupId", required = true) @PathVariable String groupId) {
        return fooService.exportByGroupId(response, groupId);

    }

    /**
     * 根据级联条件查询foo信息
     *
     * @param fooCascadeModel fooCascadeModel
     * @return foo信息
     * @author 刘亚雯
     * @since 1.0.0 2018-10-25 11:00:20
     */
    @ApiOperation(value = "根据级联条件查询foo信息")
    @PostMapping(value = {"/queryFooByFooCascadeModel"})
    public ItooResult queryFooByFooCascadeModel(@RequestBody FooCascadeModel fooCascadeModel) {
        if (StringUtils.isEmpty(fooCascadeModel.getFooGroupId())) {
            return ItooResult.build(ItooResult.FAIL, "部门id为空");
        }
        if (StringUtils.isEmpty(fooCascadeModel.getStartTime())) {
            return ItooResult.build(ItooResult.FAIL, "入职日期为空");
        }
        if (StringUtils.isEmpty(fooCascadeModel.getEndTime())) {
            return ItooResult.build(ItooResult.FAIL, "离职日期为空");
        }
        List<FooModel> fooModels = fooService.queryByFooCascadeModel(fooCascadeModel);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", fooModels);
    }

}
