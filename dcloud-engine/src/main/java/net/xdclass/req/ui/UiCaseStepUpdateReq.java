package net.xdclass.req.ui;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
public class UiCaseStepUpdateReq implements Serializable {

    private Long id;

    private Long projectId;

    private Long caseId;

    private Long num;

    private String name;

    private String operationType;

    private String locationType;

    private String locationExpress;

    private Long elementWait;

    private String targetLocationType;

    private String targetLocationExpress;

    private Long targetElementWait;

    private String value;

    private String expectKey;

    private String expectValue;

    private String description;

    private Boolean isContinue;

    private Boolean isScreenshot;


}
