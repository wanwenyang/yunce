package net.xdclass.dto;

import lombok.Data;

/**
 *
 **/
@Data
public class UiOperationResultDTO {

    /**
     * 操作的状态
     */
    private Boolean operationState;

    /**
     * 操作类型
     */
    private String operationType;


    /**
     * 期望内容
     */
    private Object expectValue;

    /**
     * 实际内容
     */
    private Object actualValue;

}
