package net.xdclass.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * ReportDTO类是用于表示报告信息的数据传输对象(DTO)。
 * 它使用Lombok注解来简化构造函数和常用方法的编写。
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO{

    /**
     * 报告的唯一标识符。
     */
    private Long id;

    /**
     * 项目ID，关联报告所属的项目。
     */
    private Long projectId;

    /**
     * 用例ID，关联报告所属的用例。
     */
    private Long caseId;

    /**
     * 报告类型，描述报告的种类。
     */
    private String type;

    /**
     * 报告名称，用于标识报告。
     */
    private String name;

    /**
     * 执行状态，描述报告的执行情况。
     */
    private String executeState;

    /**
     * 报告摘要，提供报告的简要说明。
     */
    private String summary;

    /**
     * 开始时间，记录报告执行的开始时间戳。
     */
    private Long startTime;

    /**
     * 结束时间，记录报告执行的结束时间戳。
     */
    private Long endTime;

    /**
     * 扩展时间，用于记录额外的时间信息。
     */
    private Long expandTime;

    /**
     * 数量，表示报告中包含的项的数量。
     */
    private Long quantity;

    /**
     * 通过数量，记录成功通过的项的数量。
     */
    private Long passQuantity;

    /**
     * 失败数量，记录失败的项的数量。
     */
    private Long failQuantity;

    /**
     * 创建时间，记录报告的创建时间。
     */
    private Date gmtCreate;

    /**
     * 修改时间，记录报告的最后修改时间。
     */
    private Date gmtModified;
}
