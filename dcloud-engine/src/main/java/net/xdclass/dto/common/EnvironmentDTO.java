package net.xdclass.dto.common;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 *
 **/
@Data
public class EnvironmentDTO {

    /**
     * 该类表示项目的接口信息
     * 它封装了接口的基本属性，如ID、项目ID、名称、协议、域、端口、描述以及创建和修改时间
     */
    private Long id; // 接口的唯一标识符

    private Long projectId; // 关联项目的标识符

    private String name; // 接口的名称

    private String protocol; // 接口使用的协议，如HTTP、HTTPS等

    private String domain; // 接口所在的域

    private Integer port; // 接口服务的端口号

    private String description; // 对接口的描述，提供额外的说明信息

    private Date gmtCreate; // 接口的创建时间

    private Date gmtModified; // 接口的最后修改时间
}
