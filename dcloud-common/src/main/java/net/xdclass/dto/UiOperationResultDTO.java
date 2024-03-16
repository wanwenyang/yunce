package net.xdclass.dto;

import lombok.Data;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
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
