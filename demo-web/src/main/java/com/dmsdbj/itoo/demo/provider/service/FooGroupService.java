package com.dmsdbj.itoo.demo.provider.service;

import com.dmsdbj.itoo.demo.entity.FooGroupEntity;
import com.dmsdbj.itoo.demo.model.FooGroupModel;
import com.dmsdbj.itoo.tool.base.service.BaseServiceSimplify;

import java.util.List;


/**
 * FooGroupService接口
 * fooGroup部门表
 *
 * @author 刘亚雯
 * @version 1.0.0
 * @since 1.0.0 2018-10-18 14:35:27
 */
public interface FooGroupService extends BaseServiceSimplify<FooGroupEntity> {

    /**
     * 查询所有fooGroup父类
     *
     * @return fooGroup集合
     * @author 刘亚雯
     * @since 0.0.2 2018-10-18 14:35:27
     */
    List<FooGroupEntity> queryAllRoot();

    /**
     * 根据DeptName查找对象
     * 约束:同名有多个时只去最近创建的那一个.
     *
     * @param deptName 部门名称
     * @return FooGroupEntity结果
     */
    FooGroupEntity findByDeptName(String deptName);

    /**
     * 根据父类Id查询fooGroup
     *
     * @param pid 父类fooGroupId
     * @return fooGroup集合
     * @author 刘亚雯
     * @since 1.0.0 2018-10-19 16:28:41
     */
    List<FooGroupEntity> queryByParentId(String pid);

    /**
     * 查询最新的1条foo属于的fooGroup--dubbo调用演示
     *
     * @return FooGroupModel集合
     */
    FooGroupModel findNewFooGroup();
}
