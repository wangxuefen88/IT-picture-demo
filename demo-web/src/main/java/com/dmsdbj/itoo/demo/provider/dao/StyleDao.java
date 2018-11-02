package com.dmsdbj.itoo.demo.provider.dao;

import com.dmsdbj.itoo.tool.base.dao.BaseDaoSimplify;
import com.dmsdbj.itoo.demo.entity.StyleEntity;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * StyleDao接口
 * style表
 *
 * @author 刘雅雯
 * @version 1.0.0
 * @since 1.0.0 2018-11-01 16:42:37
 */
@Repository("styleDao")
public interface StyleDao extends BaseDaoSimplify<StyleEntity> {
	
	//region 模板生成：模糊查询
	/**
     * 根据样式表模糊查询style
     *
     * @param style  样式表
     * @return 模糊查询的style集合
     * @author 刘雅雯
     * @since 1.0.0 2018-11-01 16:42:37
     */
    List<StyleEntity> queryLikeStyle(@Param("style") String style);
	//endregion

    /* **********************************以下为非模板生成的内容********************************* */
}
