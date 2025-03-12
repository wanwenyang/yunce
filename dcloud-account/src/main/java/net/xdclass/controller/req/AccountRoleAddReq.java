package net.xdclass.controller.req;

import lombok.Data;

/**
 *
 **/
@Data
public class AccountRoleAddReq {
    private Long accountId;
    private Long roleId;
}
