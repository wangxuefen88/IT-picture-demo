package com.dmsdbj.itoo.demo.facade;

import com.dmsdbj.itoo.demo.model.FooInfoModel;

/**
 * FooFacade 接口
 *
 * @version 1.0.0
 * @author 刘亚雯
 * @since 1.0.0 2018-10-24 14:23:48
 */
public interface FooFacade {
    /**
     * 查询最新的1条foo信息
     *
     * @return FooInfoModel集合
     * @author 刘亚雯
     * @since 1.0.0 2018-10-24 14:23:48
     */
    FooInfoModel queryNewFoo();
}
