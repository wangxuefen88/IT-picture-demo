package com.dmsdbj.itoo.demo.provider.service;

import com.dmsdbj.itoo.demo.entity.FooEntity;
import com.dmsdbj.itoo.demo.model.FooCascadeModel;
import com.dmsdbj.itoo.demo.model.FooInfoModel;
import com.dmsdbj.itoo.demo.model.FooModel;
import com.dmsdbj.itoo.tool.base.service.BaseServiceSimplify;
import com.dmsdbj.itoo.tool.business.ItooResult;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * FooService接口
 * foo人员表
 *
 * @author 刘亚雯
 * @version 1.0.0
 * @since 1.0.0 2018-10-18 14:35:27
 */
public interface FooService extends BaseServiceSimplify<FooEntity> {

    /**
     * 添加FooEntity
     * 约束:userCode必须唯一,数据库中不能存在.
     *
     * @param entity 待插入实体
     * @return 返回正确错误信息
     */
    ItooResult create(FooEntity entity);

    /**
     * 修改FooEntity
     * 约束:userCode必须唯一,不能与数据库中已有的其他值相同
     *
     * @param entity 待保存实体
     * @return 返回正确错误信息
     */
    ItooResult modify(FooEntity entity);

    /**
     * （人员）信息
     *
     * @param pageNo   页码
     * @param pageSize 页大小
     * @return 分页foo
     * @author 刘亚雯
     * @since 1.0.0 2018-10-18 14:35:27
     */
    PageInfo<FooModel> queryAllFoo(Integer pageNo, Integer pageSize);


    /**
     * 根据姓名模糊查询foo
     *
     * @param fooName  fooName
     * @param pageNum  页码
     * @param pageSize 页数
     * @return 分页foo
     * @author 刘亚雯
     * @since 1.0.0 2018-10-19 15:55:13
     */
    PageInfo<FooModel> queryByLikeName(String fooName, Integer pageNum, Integer pageSize);

    /**
     * 根据fooGroupId查询foo
     *
     * @param fooGroupId fooGroup主键
     * @return fooGroupId集合
     * @author 刘亚雯
     * @since 1.0.0 2018-10-19 16:43:03
     */
    List<FooModel> queryByGroupId(String fooGroupId);

    /**
     * 下载模板
     *
     * @param response http回应
     * @return boolean
     * @author 刘亚雯
     * @since 1.0.0 2018-10-19 16:43:03
     */
    boolean downTemplate(HttpServletResponse response);

    /**
     * 批量导入foo
     *
     * @param multipartFile 文件
     * @param request       请求
     * @param response      回应
     * @return ItooResult 批量导入的记过
     * @author 刘亚雯
     * @since 1.0.0 2018-10-19 16:43:03
     */
    ItooResult importTemplate(MultipartFile multipartFile, HttpServletRequest request, HttpServletResponse response);

    /**
     * 根据id查询fooGroup的model
     *
     * @param id 主键
     * @return fooGroupModel
     * @author 刘亚雯
     * @since 1.0.0 2018-10-19 16:43:03
     */
    FooModel queryFooModelById(String id);

    /**
     * 查询最新的1条foo
     *
     * @return 最新的1条foo
     * @author 刘亚雯
     * @since 1.0.0 2018-10-24 15:58:47
     */
    FooInfoModel queryNewFoo();

    /**
     * 根据级联条件查询foo信息
     *
     * @param fooCascadeModel
     * @return FooModel集合
     * @author 刘亚雯
     * @since 1.0.0 2018-10-25 11:00:20
     */
    List<FooModel> queryByFooCascadeModel(FooCascadeModel fooCascadeModel);

    /**
     * 根据FooGroupId导出Foo
     *
     * @param response response
     * @param groupId  groupId
     * @return 导出的结果
     * @author 刘亚雯
     * @since 1.0.0 2018-10-26 23:35:52
     */
    ItooResult exportByGroupId(HttpServletResponse response, String groupId);
}
