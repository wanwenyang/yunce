package net.xdclass.dto;

import lombok.Data;

import java.util.List;

/**
 * API测试结果传输对象类，用于封装API测试的结果数据
 */
@Data
public class ApiCaseResultDTO {

    /**
     * 报告ID，用于标识测试报告
     */
    private Long reportId;

    /**
     * 执行状态，表示测试用例是否已执行
     */
    private Boolean executeState;

    /**
     * 开始时间，测试用例开始执行的时间戳
     */
    private Long startTime;

    /**
     * 结束时间，测试用例结束执行的时间戳
     */
    private Long endTime;

    /**
     * 耗时，测试用例执行所花费的时间
     */
    private Long expendTime;

    /**
     * 总数量，参与测试的API用例总数
     */
    private Integer quantity;

    /**
     * 通过数量，测试中通过的API用例数量
     */
    private Integer passQuantity;

    /**
     * 失败数量，测试中失败的API用例数量
     */
    private Integer failQuantity;

    /**
     * 结果列表，包含具体的API测试结果项
     */
    private List<ApiCaseResultItemDTO> list;

}

