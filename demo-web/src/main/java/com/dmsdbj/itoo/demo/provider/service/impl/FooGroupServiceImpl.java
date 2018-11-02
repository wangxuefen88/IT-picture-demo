package com.dmsdbj.itoo.demo.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dmsdbj.itoo.demo.entity.FooGroupEntity;
import com.dmsdbj.itoo.demo.facade.FooFacade;
import com.dmsdbj.itoo.demo.model.FooGroupModel;
import com.dmsdbj.itoo.demo.model.FooInfoModel;
import com.dmsdbj.itoo.demo.provider.dao.FooGroupDao;
import com.dmsdbj.itoo.demo.provider.service.FooGroupService;
import com.dmsdbj.itoo.tool.base.dao.BaseDaoSimplify;
import com.dmsdbj.itoo.tool.base.service.impl.BaseServiceSimplifyImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * FooGroupService接口实现类
 * fooGroup部门表
 *
 * @author 刘亚雯
 * @version 1.0.0
 * @since 1.0.0 2018-10-18 14:35:27
 */
@Service("fooGroupService")
public class FooGroupServiceImpl extends BaseServiceSimplifyImpl<FooGroupEntity> implements FooGroupService {

    @Reference
    private FooFacade fooFacade;

    @Resource
    private FooGroupDao fooGroupDao;

    @Override
    public BaseDaoSimplify<FooGroupEntity> getRealDao() {
        return this.fooGroupDao;
    }

    @Override
    public List<FooGroupEntity> queryAllRoot() {
        return fooGroupDao.queryAllRoot();
    }

    @Override
    public FooGroupEntity findByDeptName(String deptName) {
        return fooGroupDao.findByDeptName(deptName);
    }

    @Override
    public List<FooGroupEntity> queryByParentId(String pid) {
        return fooGroupDao.queryByParentId(pid);
    }

    @Override
    public FooGroupModel findNewFooGroup() {
        FooInfoModel fooInfoModel=fooFacade.queryNewFoo();
        if (fooInfoModel!=null){
            FooGroupModel fooGroupModel=new FooGroupModel();
            FooGroupEntity fooGroupEntity= fooGroupDao.findByDeptName(fooInfoModel.getDeptName());
            BeanUtils.copyProperties(fooGroupEntity,fooGroupModel);
            return fooGroupModel;
        }
        return null;
    }


}
