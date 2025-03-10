package net.xdclass.req.ui;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UiCaseUpdateReq  {

    private Long id;

    private Long projectId;

    private Long moduleId;

    private String browser;

    private String name;

    private String description;

    private String level;


}
