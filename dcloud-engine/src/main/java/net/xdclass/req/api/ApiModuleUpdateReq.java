package net.xdclass.req.api;

import lombok.Data;

/**
 *
 **/
@Data
public class ApiModuleUpdateReq {

    private Long id;

    private Long projectId;

    private String name;
}
