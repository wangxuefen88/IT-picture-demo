package com.dmsdbj.itoo.demo.facade.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dmsdbj.itoo.demo.facade.FooFacade;
import com.dmsdbj.itoo.demo.model.FooInfoModel;
import com.dmsdbj.itoo.demo.provider.service.FooService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 *FooFacade 实现
 *
 *@author 刘亚雯
 *@version 1.0.0
 *@since 1.0.0 2018-10-24 14:23:48
 */
@Component("FooFacade")
@Service
class FooFacadeImpl implements FooFacade {

    @Resource
    private FooService fooService;

    @Override
    public FooInfoModel queryNewFoo() {
        return fooService.queryNewFoo();
    }
}
