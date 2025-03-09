package net.xdclass.req.api;

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
public class ApiSaveReq {


    private Long projectId;

    private Long moduleId;

    private Long environmentId;

    private String name;

    private String description;


    private String level;


    private String path;


    private String method;

    private String query;


    private String header;


    private String body;

    private String bodyType;


}
