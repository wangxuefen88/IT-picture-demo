package com.dmsdbj.itoo.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * Foo model
 * foo人员表
 *
 * @author 刘亚雯
 * @version 1.0.0
 * @since 1.0.0 2018-10-20 20:12:27
 */
@ApiModel(value = "FooModel:foo扩展")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class FooModel implements Serializable {

    //region 模板逆向生产来自FooEntity的属性
    /**
     * id
     */
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
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
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private String sex;

    /**
     * 电话号码
     */
    @ApiModelProperty(value = "电话号码")
    private String telNum;

    /**
     * 部门id
     */
    @ApiModelProperty(value = "部门id")
    private String fooGroupId;

    /**
     * 入职日期
     */
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8"
    )
    @ApiModelProperty(value = "入职日期")
    private Date startTime;

    /**
     * 离职日期
     */
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8"
    )
    @ApiModelProperty(value = "离职日期")
    private Date endTime;
    //endregion


     /* *****************************以下是非模板生成的内容************************************ */

    /**
     * fooGroup的名称
     */
    @ApiModelProperty(value = "fooGroup的名称")
    private String deptName;

}
