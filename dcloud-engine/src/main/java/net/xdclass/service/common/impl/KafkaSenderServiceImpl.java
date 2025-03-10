package net.xdclass.service.common.impl;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.xdclass.config.KafkaTopicConfig;
import net.xdclass.dto.common.CaseInfoDTO;
import net.xdclass.enums.TestTypeEnum;
import net.xdclass.service.common.ResultSenderService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * 实现结果发送服务，使用Kafka作为消息队列
 * 该服务负责将处理结果发送到Kafka主题，以便其他系统可以消费这些消息
 * 选择Kafka作为消息队列是因为它具有高吞吐量、实时性和可扩展性的特点，适合用于大规模数据传输
 **/
@Service
@Slf4j
public class KafkaSenderServiceImpl implements ResultSenderService {

    private static final String TOPIC_KEY = "case_id_";

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void sendResult(CaseInfoDTO caseInfoDTO, TestTypeEnum testTypeEnum, String result) {
        if (caseInfoDTO == null) {
            throw new IllegalArgumentException("caseInfoDTO cannot be null");
        }

        // 根据reportTypeEnum选择不同的发送方法
        switch (testTypeEnum) {
            case STRESS:
                sendStressResult(caseInfoDTO, result);
                break;
            case API:
                sendApiResult(caseInfoDTO, result);
                break;
            case UI:
                sendUiResult(caseInfoDTO, result);
                break;
            default:
                log.error("未知的测试类型");
                break;
        }
    }


    /**
     * 发送压测结果明细
     *
     * @param caseInfoDTO
     * @param result
     */
    private void sendStressResult(CaseInfoDTO caseInfoDTO, String result) {
        kafkaTemplate.send(KafkaTopicConfig.STRESS_TOPIC_NAME, TOPIC_KEY + caseInfoDTO.getId(), result);
    }

    /**
     * 发送接口测试结果明细
     *
     * @param caseInfoDTO
     * @param result
     */
    private void sendApiResult(CaseInfoDTO caseInfoDTO, String result) {
        kafkaTemplate.send(KafkaTopicConfig.API_TOPIC_NAME, TOPIC_KEY + caseInfoDTO.getId(), result);
    }

    /**
     * 发送UI测试结果明细
     *
     * @param caseInfoDTO
     * @param result
     */
    private void sendUiResult(CaseInfoDTO caseInfoDTO, String result) {
        kafkaTemplate.send(KafkaTopicConfig.UI_TOPIC_NAME, TOPIC_KEY + caseInfoDTO.getId(), result);
    }


}
