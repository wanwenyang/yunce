package net.xdclass.dto;

import lombok.Data;

/**
 *
 **/
@Data
public class UiCaseResultItemDTO {
    /**
     * 报告ID
     */
    private Long reportId;

    /**
     * 执行状态
     */
    private Boolean executeState;

    /**
     * 断言状态
     */
    private Boolean assertionState;

    /**
     * 异常信息
     */
    private String exceptionMsg;

    /**
     * 耗时
     */
    private Long expendTime;

    /**
     * 截图地址
     */
    private String screenshotUrl;

    /**
     * 用例步骤
     */
    private UiCaseStepDTO uiCaseStep;

}
