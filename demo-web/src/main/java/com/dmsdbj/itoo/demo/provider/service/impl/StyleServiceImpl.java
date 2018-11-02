package com.dmsdbj.itoo.demo.provider.service.impl;

import com.dmsdbj.itoo.demo.entity.StyleEntity;
import com.dmsdbj.itoo.demo.provider.dao.StyleDao;
import com.dmsdbj.itoo.demo.provider.service.StyleService;
import com.dmsdbj.itoo.tool.base.dao.BaseDaoSimplify;
import com.dmsdbj.itoo.tool.base.service.impl.BaseServiceSimplifyImpl;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import java.util.List;

/**
 * StyleService接口实现类
 * ${base}表
 *
 * @author 刘雅雯
 * @version 1.0.0
 * @since 1.0.0 2018-11-01 16:42:37
 */
@Service("styleService")
public class StyleServiceImpl extends BaseServiceSimplifyImpl<StyleEntity> implements StyleService {
	
	//region 模板生成
    @Resource
    private StyleDao styleDao;

    @Override
    public BaseDaoSimplify<StyleEntity> getRealDao() {
        return this.styleDao;
    }
	
	 @Override
    public PageInfo<StyleEntity> queryByLikeStyle(String style, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(styleDao.queryLikeStyle(style));
    }
	//endregion

    /* **********************************以下为非模板生成的内容********************************* */
}
