package net.xdclass.service.common;

import net.xdclass.dto.common.CaseInfoDTO;
import net.xdclass.enums.TestTypeEnum;

/**
 * 结果发送服务接口
 * 该接口定义了发送结果的方法，用于将执行结果发送到指定的接收者
 * 主要用途是为各种任务执行结果提供统一的发送接口，具体实现可能会涉及邮件、消息队列等
 */
public interface ResultSenderService {

    /**
     * 发送测试结果
     * @param caseInfoDTO 用例信息
     * @param testTypeEnum 测试类型
     * @param result 测试结果
     */
    void sendResult(CaseInfoDTO caseInfoDTO, TestTypeEnum testTypeEnum, String result);
}
