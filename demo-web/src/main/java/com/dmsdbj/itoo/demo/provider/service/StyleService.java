package com.dmsdbj.itoo.demo.provider.service;

import com.dmsdbj.itoo.demo.entity.StyleEntity;
import com.dmsdbj.itoo.tool.base.service.BaseServiceSimplify;
import com.github.pagehelper.PageInfo;


/**
 * StyleService接口
 * style表
 *
 * @author 刘雅雯
 * @version 1.0.0
 * @since 1.0.0 2018-11-01 16:42:37
 */
public interface StyleService extends BaseServiceSimplify<StyleEntity> {
	
	//region 模板生成：模糊查询
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
    PageInfo<StyleEntity> queryByLikeStyle(String style, Integer pageNo, Integer pageSize);
	//endregion

    /* **********************************以下为非模板生成的内容********************************* */
}
