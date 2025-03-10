package net.xdclass.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@TableName("api_case")
@Schema(name = "ApiCaseDO", description = "")
public class ApiCaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "项目id")
    @TableField("project_id")
    private Long projectId;

    @Schema(description = "所属API用例模块ID")
    @TableField("module_id")
    private Long moduleId;

    @Schema(description = "API用例名称")
    @TableField("name")
    private String name;

    @Schema(description = "API用例描述")
    @TableField("description")
    private String description;

    @Schema(description = "执行等级 [p0 p1 p2 p3]")
    @TableField("level")
    private String level;

    @Schema(description = "创建时间")
    @TableField("gmt_create")
    private Date gmtCreate;

    @Schema(description = "更新时间")
    @TableField("gmt_modified")
    private Date gmtModified;
}
