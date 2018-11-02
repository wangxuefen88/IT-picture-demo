package com.dmsdbj.itoo.demo.template;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jplus.hyberbin.excel.annotation.ExcelModelConfig;
import org.jplus.hyberbin.excel.annotation.Lang;
import org.jplus.hyberbin.excel.annotation.input.InputDicConfig;

import java.io.Serializable;
import java.util.Date;

/**
 * Foo model
 * foo人员表
 *
 * @author 刘亚雯
 * @version 1.0.0
 * @since 1.0.0 2018-10-27 21:04:12
 */
@ExcelModelConfig
@ApiModel(value = "FooModel:foo人员表，用于批量导入")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class FooImportTemplate implements Serializable{
    /**
     * 用户名
     */
    @Lang("用户名")
    @InputDicConfig(dicCode = "userCode")
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
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8"
    )
    private Date startTime;


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
