package com.dmsdbj.itoo.demo.entity;

import com.dmsdbj.itoo.tool.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.*;
import java.io.Serializable;
import javax.persistence.Column;

/**
 * Bar实体
 * bar表缓存示例
 *
 * @author 刘雅雯 
 * @version 1.0.0
 * @since 1.0.0 2018-10-26 09:48:45
 */
@ApiModel(value = "BarEntity:bar表缓存示例")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BarEntity extends BaseEntity implements Serializable {

	/**
	 * 资源名称
	 */
	@ApiModelProperty(value = "资源名称",required = true)
	@Column(name = "bar_name")
	private String barName;

	/**
	 * 描述
	 */
    @ApiModelProperty(value = "描述")
	@Column(name = "description")
	private String description;


}
