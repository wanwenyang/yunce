package net.xdclass.req.ui;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class UiCaseSaveReq {

    private Long projectId;

    private Long moduleId;

    private String browser;

    private String name;

    private String description;

    private String level;

    private List<UiCaseStepSaveReq> list;

}
