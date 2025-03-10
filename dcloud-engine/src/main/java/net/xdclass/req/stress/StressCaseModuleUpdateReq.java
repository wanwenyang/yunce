package net.xdclass.req.stress;

import lombok.Data;

import java.io.Serializable;


@Data
public class StressCaseModuleUpdateReq implements Serializable {

    private Long id;

    private Long projectId;

    private String name;

}
