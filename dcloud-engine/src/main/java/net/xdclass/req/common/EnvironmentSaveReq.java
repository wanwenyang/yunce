package net.xdclass.req.common;

import lombok.Data;

import java.util.Date;

/**
 *
 **/
@Data
public class EnvironmentSaveReq {

    private Long projectId;

    private String name;

    private String protocol;

    private String domain;

    private Integer port;

    private String description;

}
