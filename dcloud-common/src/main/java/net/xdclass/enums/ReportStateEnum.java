package net.xdclass.enums;

/**
 * 报告状态枚举类，用于定义报告可能处于的不同状态
 * 这个枚举类帮助统一报告状态的表示，使得状态管理更为清晰和易于维护
 **/
public enum ReportStateEnum {

    /**
     * 执行中
     */
    EXECUTING,

    /**
     * 统计报告
     */
    COUNTING_REPORT,

    /**
     * 执行成功
     */
    EXECUTE_SUCCESS,

    /**
     * 执行失败
     */
    EXECUTE_FAIL;
}
