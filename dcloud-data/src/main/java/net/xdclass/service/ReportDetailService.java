package net.xdclass.service;

import net.xdclass.controller.req.ReportDetailPageReq;

import java.util.Map;

/**
 * ReportDetailService接口定义了处理不同类型的报告详情以及分页查询的方法
 */
public interface ReportDetailService {

    /**
     * 处理压力测试报告详情
     *
     * @param topicContent 压力测试报告的主题内容，包含需要处理的信息
     */
    void handleStressReportDetail(String topicContent);

    /**
     * 处理API测试报告详情
     *
     * @param topicContent API测试报告的主题内容，包含需要处理的信息
     */
    void handleApiReportDetail(String topicContent);

    /**
     * 处理UI测试报告详情
     *
     * @param topicContent UI测试报告的主题内容，包含需要处理的信息
     */
    void handleUiReportDetail(String topicContent);

    /**
     * 执行报告详情的分页查询
     *
     * @param req 分页查询请求对象，包含查询所需的各种参数
     * @return 返回一个Map对象，其中包含了查询结果和分页信息
     */
    Map<String, Object> page(ReportDetailPageReq req);
}

