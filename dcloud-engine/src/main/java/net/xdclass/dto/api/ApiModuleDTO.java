package net.xdclass.dto.api;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 *
 **/
@Data
public class ApiModuleDTO {
    private Long id;

    private Long projectId;

    private String name;


    private List<ApiDTO> list;

    private Date gmtCreate;

    private Date gmtModified;
}
