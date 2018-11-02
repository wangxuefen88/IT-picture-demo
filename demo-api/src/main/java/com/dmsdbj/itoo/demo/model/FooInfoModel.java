package com.dmsdbj.itoo.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 *FooInfoModel 外部服务调用model
 *
 *@author 刘亚雯
 *@version 1.0.0
 *@since 1.0.0 2018-10-24 14:23:48
 */
@ApiModel(value = "FooModel:foo扩展")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class FooInfoModel {
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", required = true)
    private String userCode;

    /**
     * 姓名
     */

    @ApiModelProperty(value = "姓名", required = true)
    private String userName;

    /**
     * fooGroup的名称
     */
    @ApiModelProperty(value = "fooGroup的名称")
    private String deptName;

    /**
     * 入职日期
     */
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8"
    )
    @ApiModelProperty(value = "入职日期")
    private Date startTime;
}
