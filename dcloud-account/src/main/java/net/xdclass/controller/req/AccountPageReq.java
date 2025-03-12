package net.xdclass.controller.req;

import lombok.Data;

/**
 *
 **/
@Data
public class AccountPageReq {

    private Long page;

    private Long size;

    /**
     * 支持根据名称搜索
     */
    private String username;
}
