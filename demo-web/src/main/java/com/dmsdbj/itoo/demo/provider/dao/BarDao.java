package com.dmsdbj.itoo.demo.provider.dao;

import com.dmsdbj.itoo.demo.model.BarModel;
import com.dmsdbj.itoo.tool.base.dao.BaseDaoSimplify;
import com.dmsdbj.itoo.demo.entity.BarEntity;
import com.dmsdbj.itoo.tool.redis.CacheExpire;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * BarDao接口
 * ${base}表
 *
 * @author 刘雅雯
 * @version 1.0.0
 * @since 1.0.0 2018-10-26 09:48:45
 */
@Repository("barDao")
public interface BarDao extends BaseDaoSimplify<BarEntity> {
	
	//region 模板生成：模糊查询
	/**
     * 根据资源名称模糊查询bar
     *
     * @param barName  资源名称
     * @return 模糊查询的bar集合
     * @author 刘雅雯
     * @since 1.0.0 2018-10-26 09:48:45
     */
    List<BarEntity> queryLikeBarName(@Param("barName") String barName);

    //endregion

    /* **********************************以下为非模板生成的内容********************************* */

    /**
     * 根据BarName查询bar--缓存示例
     *
     * @param barName 资源名称
     * @return BarModel集合
     * @author 刘雅雯
     * @since 1.0.0 2018-10-26 10:28:47
     */
    @Cacheable( value = "queryByName")
    @CacheExpire(expire = 120)
    List<BarModel> queryByName(String barName);
}
