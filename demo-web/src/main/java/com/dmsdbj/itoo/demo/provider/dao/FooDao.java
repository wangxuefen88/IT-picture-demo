package com.dmsdbj.itoo.demo.provider.dao;

import com.dmsdbj.itoo.demo.entity.FooEntity;
import com.dmsdbj.itoo.demo.model.FooInfoModel;
import com.dmsdbj.itoo.demo.model.FooModel;
import com.dmsdbj.itoo.tool.base.dao.BaseDaoSimplify;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * FooDao接口
 * foo人员表
 *
 * @author 刘亚雯
 * @version 1.0.0
 * @since 1.0.0 2018-10-18 14:35:27
 */
@Repository
public interface FooDao extends BaseDaoSimplify<FooEntity> {

    /**
     * 根据用户名查询用户信息
     *
     * @param userCode 用户名
     * @return 用户信息List
     * @author 刘亚雯
     * @since 1.0.0 2018-10-18 14:35:27
     */
    List<FooEntity> queryByUserCode(String userCode);


    /**
     * 查询所有foo（人员）信息
     *
     * @return foo集合
     * @author 刘亚雯
     * @since 1.0.0 2018-10-18 14:35:27
     */
    List<FooModel> queryAllModel();

    /**
     * 根据姓名模糊查询foo
     *
     * @param fooName fooName
     * @return foo集合
     * @author 刘亚雯
     * @since 1.0.0 2018-10-19 15:55:13
     */
    List<FooModel> queryLikeName(@Param("fooName") String fooName);

    /**
     * 根据fooGroupId查询foo
     *
     * @param fooGroupId fooGroupId
     * @return foo集合
     * @author 刘亚雯
     * @since 1.0.0 2018-10-19 16:43:03
     */
    List<FooModel> queryByGroupId(String fooGroupId);

    /**
     * 根据id查询fooGroup的model
     *
     * @param id 主键
     * @return fooGroup的model集合
     */
    FooModel findModelById(String id);

    /**
     * 根据groupId 和日期范围查询foo信息
     * @param groupId fooGroupId
     * @param startTime 开始日期
     * @param endTime 结束日期
     * @return FooModel集合
     */
    List<FooModel> queryByGroupIdAndDate(@Param("groupId")String groupId, @Param("startTime")Date startTime, @Param("endTime")Date endTime);

    /**
     * 查询最新的1条foo信息
     * @return 最新的foo信息
     */
    FooInfoModel queryNewFoo();
}
