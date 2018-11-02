package com.dmsdbj.itoo.demo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Foo实体
 * foo人员表
 *
 * @author 刘亚雯
 * @version 1.0.0
 * @since 1.0.0 2018-10-18 14:35:27
 */
@ApiModel(value = "FooGroupModel:fooGroup部门表")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class FooGroupModel implements Serializable {

    //region 模板逆向生产来自FooGroupEntity的属性
    /**
     * id
     */
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
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
    //endregion

     /* *****************************以下是非模板生成的内容************************************ */
}
