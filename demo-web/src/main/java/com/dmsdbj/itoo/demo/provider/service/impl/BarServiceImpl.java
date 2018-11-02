package com.dmsdbj.itoo.demo.provider.service.impl;

import com.dmsdbj.itoo.demo.entity.BarEntity;
import com.dmsdbj.itoo.demo.model.BarModel;
import com.dmsdbj.itoo.demo.provider.dao.BarDao;
import com.dmsdbj.itoo.demo.provider.service.BarService;
import com.dmsdbj.itoo.tool.base.dao.BaseDaoSimplify;
import com.dmsdbj.itoo.tool.base.service.impl.BaseServiceSimplifyImpl;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import java.util.List;

/**
 * BarService接口实现类
 * ${base}表
 *
 * @author 刘雅雯
 * @version 1.0.0
 * @since 1.0.0 2018-10-26 09:48:45
 */
@Service("barService")
public class BarServiceImpl extends BaseServiceSimplifyImpl<BarEntity> implements BarService {

    @Resource
    private BarDao barDao;

    //region 模板生成

    @Override
    public BaseDaoSimplify<BarEntity> getRealDao() {
        return this.barDao;
    }
	
	 @Override
    public PageInfo<BarEntity> queryByLikeBarName(String barName, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(barDao.queryLikeBarName(barName));
    }

    //endregion

    /* **********************************以下为非模板生成的内容********************************* */

    @Override
    public List<BarModel> queryByName(String barName) {
        return barDao.queryByName(barName);
    }
}
