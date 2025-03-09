package net.xdclass.dto.common;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 *
 **/
@Data
public class ProjectDTO {

    /**
     * 项目的唯一标识符
     */
    private Long id;

    /**
     * 项目管理员的唯一标识符
     */
    private Long projectAdmin;

    /**
     * 项目的名称
     */
    private String name;

    /**
     * 项目的描述信息
     */
    private String description;

    /**
     * 记录创建的时间
     */
    private Date gmtCreate;

    /**
     * 记录最后一次修改的时间
     */
    private Date gmtModified;
}
