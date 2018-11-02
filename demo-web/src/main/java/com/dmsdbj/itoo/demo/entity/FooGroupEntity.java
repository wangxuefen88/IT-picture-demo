package com.dmsdbj.itoo.demo.entity;

import com.dmsdbj.itoo.tool.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * FooGroup实体
 * fooGroup部门表
 *
 * @author 刘亚雯
 * @version 1.0.0
 * @since 1.0.0 2018-10-18 14:35:27
 */
@ApiModel(value = "FooGroupEntity:fooGroup部门表")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class FooGroupEntity extends BaseEntity implements Serializable {

    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称", required = true)
    private String deptName;
    /**
     * 父节点
     */
    @ApiModelProperty(value = "父节点", required = true)
    private String parentId;

}
