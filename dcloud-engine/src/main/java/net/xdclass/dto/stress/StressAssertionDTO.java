package net.xdclass.dto.stress;

import lombok.Data;

/**
 *
 **/
@Data
public class StressAssertionDTO {

    /**
     * 断言名称
     */
    private String name;

    /**
     * 断言规则，"contain|equal"
     */
    private String action;

    /**
     * 断言字段类型， "responseCode|responseData|responseHeader"
     */
    private String from;

    /**
     * 断言目标值
     */
    private String value;
}
