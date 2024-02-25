package net.xdclass.service;

import net.xdclass.dto.ReportDTO;
import net.xdclass.req.ReportSaveReq;
import net.xdclass.req.ReportUpdateReq;

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
