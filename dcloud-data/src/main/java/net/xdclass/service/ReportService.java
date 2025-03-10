package net.xdclass.service;

import net.xdclass.dto.ReportDTO;
import net.xdclass.req.ReportSaveReq;
import net.xdclass.req.ReportUpdateReq;

/**
 * 定义报告服务接口，用于生成各种类型的报告
 * 此接口不包含具体的方法定义，仅作为报告服务的一个标记或承诺
 * 实现此接口的类将负责提供报告生成的具体实现
 */
public interface ReportService {
    /**
     * 保存测试报告
     * @param req
     * @return
     */
    ReportDTO save(ReportSaveReq req);

    /**
     * 更新状态
     * @param req
     */
    void updateReportState(ReportUpdateReq req);
}
