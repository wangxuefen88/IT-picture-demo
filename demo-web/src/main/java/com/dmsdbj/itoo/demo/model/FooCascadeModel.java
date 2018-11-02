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
 * FooCascadeModel model
 * 级联查询foo的条件model
 *
 * @author 刘亚雯
 * @version 1.0.0
 * @since 1.0.0 2018-10-25 10:20:01
 */
@ApiModel(value = "FooCascadeModel:级联查询foo的条件")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class FooCascadeModel implements Serializable {
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
