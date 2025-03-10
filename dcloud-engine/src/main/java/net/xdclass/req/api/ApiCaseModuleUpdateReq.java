package net.xdclass.req.api;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ApiCaseModuleUpdateReq {

    private Long id;

    private Long projectId;

    private String name;

}
