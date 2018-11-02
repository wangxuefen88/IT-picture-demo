package com.dmsdbj.itoo.demo.entity;

import com.dmsdbj.itoo.tool.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.*;
import java.io.Serializable;
import javax.persistence.Column;

/**
 * Style实体
 * 富文本样式表
 *
 * @author 刘雅雯 
 * @version 1.0.0
 * @since 1.0.0 2018-11-01 16:42:37
 */
@ApiModel(value = "StyleEntity:富文本样式表")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class StyleEntity extends BaseEntity implements Serializable {

	/**
	 * 样式表
	 */
	@ApiModelProperty(value = "样式表",required = true)
	@Column(name = "style")
	private byte[] style;

}
