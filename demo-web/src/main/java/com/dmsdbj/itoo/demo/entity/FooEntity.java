package com.dmsdbj.itoo.demo.entity;

import com.dmsdbj.itoo.tool.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * Foo实体
 * foo人员表
 *
 * @author 刘亚雯
 * @version 1.0.0
 * @since 1.0.0 2018-10-18 14:35:27
 */
@ApiModel(value = "FooEntity:foo人员表")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class FooEntity extends BaseEntity implements Serializable {

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

}
