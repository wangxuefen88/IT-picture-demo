package com.dmsdbj.itoo.demo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.*;
import java.io.Serializable;
import javax.persistence.Column;

/**
 * BarModel
 * bar表缓存示例
 *
 * @author 刘雅雯 
 * @version 1.0.0
 * @since 1.0.0 2018-10-26 09:48:45
 */
@ApiModel(value = "BarModel:bar表缓存示例")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class BarModel implements Serializable {

    //region 模板逆向生产来自BarEntity的属性
	/**
	 * id
	 */
	@ApiModelProperty(value = "bar主键")
	@Column(name = "id")
	private String id;
   	/**
	 * 资源名称
	 */
	@ApiModelProperty(value = "资源名称",required = true)
	private String barName;

	/**
	 * 描述
	 */
    @ApiModelProperty(value = "描述")
	private String description;

    //endregion

     /* *****************************以下是非模板生成的内容************************************ */
}
