package com.dmsdbj.itoo.demo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.*;
import java.io.Serializable;
import javax.persistence.Column;

/**
 * StyleModel
 * 富文本样式表
 *
 * @author 刘雅雯 
 * @version 1.0.0
 * @since 1.0.0 2018-11-01 16:42:37
 */
@ApiModel(value = "StyleModel:富文本样式表")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class StyleModel implements Serializable {

    //region 模板逆向生产来自StyleEntity的属性
	/**
	 * id
	 */
	@ApiModelProperty(value = "style主键")
	@Column(name = "id")
	private String id;
   	/**
	 * 样式表
	 */
	@ApiModelProperty(value = "样式表",required = true)
	private String style;

    //endregion

     /* *****************************以下是非模板生成的内容************************************ */
}
