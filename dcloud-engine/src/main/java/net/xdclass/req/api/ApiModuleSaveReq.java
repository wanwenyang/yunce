package net.xdclass.req.api;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 *
 **/
@Data
public class ApiModuleSaveReq {

    private Long projectId;

    private String name;
}
