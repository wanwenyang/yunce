package net.xdclass.service;

/**
 * ReportDetailService接口用于处理报告详情相关的操作
 * 它提供了处理不同类型的报告详情的方法，包括压力测试报告和API测试报告
 */
public interface ReportDetailService {

    /**
     * 处理压力测试报告详情
     * 此方法接收一个表示主题内容的字符串参数，根据该内容执行特定的处理逻辑
     *
     * @param topicContent 主题内容的字符串表示，包含处理所需的详细信息
     */
    void handleStressReportDetail(String topicContent);

    /**
     * 处理API测试报告详情
     * 此方法也接收一个表示主题内容的字符串参数，但专注于处理API测试报告的逻辑
     *
     * @param topicContent 主题内容的字符串表示，包含处理所需的详细信息
     */
    void handleApiReportDetail(String topicContent);
}
