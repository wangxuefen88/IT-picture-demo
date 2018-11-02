package com.dmsdbj.itoo.demo.template;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jplus.hyberbin.excel.annotation.ExcelModelConfig;
import org.jplus.hyberbin.excel.annotation.Lang;

import java.io.Serializable;

/**
 * Foo model
 * foo人员表
 *
 * @author 刘亚雯
 * @version 1.0.0
 * @since 1.0.0 2018-10-19 20:50:24
 */
@ExcelModelConfig
@ApiModel(value = "FooModel:foo人员表，用于下载模板和导出")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class FooTemplate implements Serializable {
    /**
     * 用户名
     */
    @Lang("用户名")
    private String userCode;

    /**
     * 姓名
     */
    @Lang("姓名")
    private String userName;

    /**
     * 性别
     */
    @Lang("性别")
    private String sex;

    /**
     * 电话号码
     */
    @Lang("电话号码")
    private String telNum;

    /**
     * 入职日期
     */
    @Lang("入职日期")
    private String startTime;

    /**
     * 离职日期
     */
    @Lang("离职日期")
    private String endTime;

    /**
     * 备注
     */
    @Lang("备注")
    private String remark;

    /**
     * 部门名称
     */
    @Lang("部门名称")
    private String fooGroupName;

    /**
     * 失败原因
     */
    @Lang("失败原因")
    private String failReason;

}
