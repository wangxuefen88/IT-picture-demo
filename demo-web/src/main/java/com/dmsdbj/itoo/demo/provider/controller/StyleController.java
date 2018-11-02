package com.dmsdbj.itoo.demo.provider.controller;

import com.dmsdbj.itoo.demo.entity.StyleEntity;
import com.dmsdbj.itoo.demo.model.StyleModel;
import com.dmsdbj.itoo.demo.provider.service.StyleService;
import com.dmsdbj.itoo.tool.business.ItooResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.sql.rowset.serial.SerialBlob;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.dmsdbj.itoo.demo.provider.until.DemoConstants.OPERATOR;


/**
 * StyleController
 * style表
 *
 * @author 刘雅雯
 * @version 1.0.0
 * @since 1.0.0 2018-11-01 16:42:37
 */
@Api(tags = {"style表接口"})
@RequestMapping(value = "/style")
@RestController
public class StyleController {

    @Resource
    private StyleService styleService;

    //region 模板生成：基本增删改

    /**
     * 添加
     *
     * @param model StyleModel
     * @return 添加的结果
     * @author 刘雅雯
     * @since 1.0.0 2018-11-01 16:42:37
     */
    @ApiOperation(value = "添加")
    @PostMapping(value = {"/create"})
    public ItooResult create(@RequestBody StyleModel model) throws UnsupportedEncodingException, SQLException {
		if (StringUtils.isEmpty(model.getStyle())){
            return ItooResult.build(ItooResult.FAIL, "style为空");
        }
        StyleEntity styleEntity = new StyleEntity();
        byte[] bytes = model.getStyle().getBytes();
        styleEntity.setStyle(bytes);
        styleService.insert(styleEntity);
        return ItooResult.build(ItooResult.SUCCESS, "添加成功");
    }

    /**
     * 删除
     *
     * @param id 主键id
     * @return ItooResult 是否删除成功          
     * @author 刘雅雯
     * @since 1.0.0 2018-11-01 16:42:37
     */
    @ApiOperation(value = "根据id删除（单个）")
    @DeleteMapping(value = {"/delete/{id}"})
    public ItooResult delete(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
        styleService.deleteById(id, OPERATOR);
        return ItooResult.build(ItooResult.SUCCESS, "删除成功");
    }

    /**
     * 批量删除
     *
     * @param ids ids
     * @return ItooResult 批量删除是否成功结果
     * @author 刘雅雯
     * @since 1.0.0 2018-11-01 16:42:37
     */
    @ApiOperation(value = "根据id批量删除")
    @DeleteMapping(value = {"/deleteByIds"})
    @ApiImplicitParam(name = "ids", value = "ids", dataType = "List<String>", required = true)
    public ItooResult deleteByIds(@RequestBody List<String> ids) {
        styleService.deleteByIds(ids, OPERATOR);
        return ItooResult.build(ItooResult.SUCCESS, "批量删除成功");
    }


    /**
     * 修改
     *
     * @param model StyleModel
     * @return 修改后的结果
     * @author 刘雅雯
     * @since 1.0.0 2018-11-01 16:42:37
     */
    @ApiOperation(value = "根据id修改style")
    @PutMapping(value = {"/modify"})
    public ItooResult modify(@RequestBody StyleModel model) {
		if (StringUtils.isEmpty(model.getStyle())){
            return ItooResult.build(ItooResult.FAIL, "style为空");
        }
        StyleEntity styleEntity = new StyleEntity();
        BeanUtils.copyProperties(model, styleEntity);
        styleService.updateById(styleEntity);
        return ItooResult.build(ItooResult.SUCCESS, "修改成功");
    }

    /**
     * 根据id查找Style
     *
     * @param id 主键id
     * @author 刘雅雯
     * @since 1.0.0 2018-11-01 16:42:37
     */
    @ApiOperation(value = "根据id查询")
    @GetMapping(value = {"/findById/{id}"})
    public ItooResult findById(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
        StyleEntity styleEntity = styleService.findById(id);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", styleEntity);
    }

    /**
     * 分页查询所有Style
     *
     * @param pageNo   页码
     * @param pageSize 每页条数
     * @author 刘雅雯
     * @since 1.0.0 2018-11-01 16:42:37
     */
    @ApiOperation(value = "分页查询所有Style")
    @GetMapping(value = {"/queryPageAll/{pageNo}/{pageSize}"})
    public ItooResult queryPageAll(@ApiParam(name = "pageNo",value = "页码",required = true,example = "1")@PathVariable Integer pageNo, 
								   @ApiParam(name = "pageSize",value = "页数",required = true,example = "10")@PathVariable Integer pageSize) {
        PageInfo<StyleEntity> styles = styleService.queryPageAll(pageNo, pageSize);
        List<String> liststyle=new ArrayList<>();
        for(StyleEntity str1:styles.getList()){
            byte[] string1=  str1.getStyle();
            String s = new String(string1);
            liststyle.add(s);
        }
        System.out.println(liststyle);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", liststyle);
    }
	
	/**
     * 根据样式表模糊查询style
     *
     * @param style  样式表
     * @param pageNo   页码
     * @param pageSize 页数
     * @return 模糊查询的style
     * @author 刘雅雯
     * @since 1.0.0 2018-11-01 16:42:37
     */
    @ApiOperation(value = "根据样式表模糊查询style", notes = "分页根据样式表模糊查询style")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", dataType = "int", required = true, example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "记录数", dataType = "int", required = true, example = "10")
    })
    @GetMapping(value = "queryStyleByLikeStyle/{pageNo}/{pageSize}")
    public ItooResult queryStyleByLikeStyle(@RequestParam(required = false, defaultValue = "") String style,
                                         @PathVariable Integer pageNo, @PathVariable Integer pageSize) {
        PageInfo<StyleEntity> styleList = styleService.queryByLikeStyle(style, pageNo, pageSize);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", styleList);
    }
    //endregion

    /* **********************************以下为非模板生成的内容********************************* */

   
}    
