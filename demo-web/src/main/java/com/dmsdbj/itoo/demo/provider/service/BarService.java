package com.dmsdbj.itoo.demo.provider.service;

import com.dmsdbj.itoo.demo.entity.BarEntity;
import com.dmsdbj.itoo.demo.model.BarModel;
import com.dmsdbj.itoo.tool.base.service.BaseServiceSimplify;
import com.github.pagehelper.PageInfo;

import java.util.List;


/**
 * BarService接口
 * bar表
 *
 * @author 刘雅雯
 * @version 1.0.0
 * @since 1.0.0 2018-10-26 09:48:45
 */
public interface BarService extends BaseServiceSimplify<BarEntity> {
	
	//region 模板生成：模糊查询
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
    PageInfo<BarEntity> queryByLikeBarName(String barName, Integer pageNo, Integer pageSize);

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
    List<BarModel> queryByName(String barName);
}
