package com.dmsdbj.itoo.demo.provider.dao;

import com.dmsdbj.itoo.demo.entity.FooGroupEntity;
import com.dmsdbj.itoo.tool.base.dao.BaseDaoSimplify;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * FooGroupDao接口
 * fooGroup部门表
 *
 * @author 刘亚雯
 * @version 1.0.0
 * @since 1.0.0 2018-10-18 14:35:27
 */
@Repository
public interface FooGroupDao extends BaseDaoSimplify<FooGroupEntity> {

    /**
     * 根据DeptName查找对象
     * 约束:同名有多个时只取最近创建的那一个.
     *
     * @param deptName 部门名称
     * @return FooGroupEntity结果
     */
    FooGroupEntity findByDeptName(String deptName);

    /**
     * 查询所有fooGroup父类
     *
     * @return fooGroup集合
     * @author 刘亚雯
     * @since 1.0.0 2018-10-18 14:35:27
     */
    List<FooGroupEntity> queryAllRoot();

    /**
     * 根据父类Id查询fooGroup
     *
     * @param parentId 父类fooGroupId
     * @return fooGroup集合
     * @author 刘亚雯
     * @since 1.0.0 2018-10-19 16:28:41
     */
    List<FooGroupEntity> queryByParentId(String parentId);


}
