package net.xdclass.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportUpdateReq {

    /**
     * 测试报告id
     */
    private Long id;

    /**
     * 执行状态
     */
    private String executeState;


    /**
     * 结束时间
     */
    private Long endTime;

}
