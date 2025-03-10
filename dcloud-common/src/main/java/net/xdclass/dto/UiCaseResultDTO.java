/**
 * UI测试用例结果传输对象
 * 用于封装UI测试用例的执行结果，包括执行状态、耗时、通过和失败的数量等信息
 */
package net.xdclass.dto;

import lombok.Data;

import java.util.List;

/**
 * UI测试用例结果DTO类
 *
 * 该类通过@Data注解引入了Lombok库的功能，自动生成getter和setter方法，简化了代码编写
 * 主要用于UI测试结果的封装和传输，提供了对测试报告基本信息的存储结构
 */
@Data
public class UiCaseResultDTO {

    /**
     * 报告ID
     * 用于唯一标识一次UI测试的结果报告
     */
    private Long reportId;

    /**
     * 执行状态
     * 表示UI测试用例是否执行成功，true表示成功，false表示失败
     */
    private Boolean executeState;

    /**
     * 开始时间
     * 记录UI测试用例开始执行的时间戳
     */
    private Long startTime;

    /**
     * 结束时间
     * 记录UI测试用例执行结束的时间戳
     */
    private Long endTime;

    /**
     * 耗费时间
     * 计算UI测试用例执行总共耗费的时间，单位为毫秒
     */
    private Long expendTime;

    /**
     * 数量
     * 表示本次UI测试中包含的用例总数
     */
    private Integer quantity;

    /**
     * 通过数量
     * 表示UI测试中通过的用例数量
     */
    private Integer passQuantity;

    /**
     * 失败数量
     * 表示UI测试中失败的用例数量
     */
    private Integer failQuantity;

    /**
     * UI测试用例结果列表
     * 包含了本次UI测试中每个用例的详细执行结果
     */
    private List<UiCaseResultItemDTO> list;
}
