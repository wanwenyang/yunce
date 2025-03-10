package net.xdclass.listener;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.xdclass.config.KafkaTopicConfig;
import net.xdclass.req.ReportUpdateReq;
import net.xdclass.service.ReportDetailService;
import net.xdclass.service.ReportService;
import net.xdclass.util.JsonUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * 监听消息队列的类
 * 本类的作用是监听特定消息队列中的消息，并对收到的消息进行处理
 * 使用Spring的@Component注解，使其成为Spring容器中的一个Bean
 * 使用@Slf4j生成日志对象，便于记录日志信息
 **/
@Component
@Slf4j
public class MQListener {


    @Resource
    private ReportDetailService reportDetailService;


    @Resource
    private ReportService reportService;

    /**
     * 消费监听，压测日志详情
     * @param record
     * @param ack
     * @param topic
     */
    @KafkaListener(topics = {KafkaTopicConfig.STRESS_TOPIC_NAME},groupId = "xdclass-stress-test-gp")
    public void onStressReportDetailMessage(ConsumerRecord<?,?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic){
        //打印消息
        log.info("消费主题：{},分区：{} 收到消息：{}",record.topic(),record.partition(),record.value());
        reportDetailService.handleStressReportDetail(record.value().toString());
        ack.acknowledge();
    }



    /**
     * 消费监听，接口自动化测试日志详情
     * @param record
     * @param ack
     * @param topic
     */
    @KafkaListener(topics = {KafkaTopicConfig.API_TOPIC_NAME},groupId = "xdclass-api-test-gp")
    public void onApiReportDetailMessage(ConsumerRecord<?,?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic){
        //打印消息
        log.info("消费主题：{},分区：{} 收到消息：{}",record.topic(),record.partition(),record.value());
        reportDetailService.handleApiReportDetail(record.value().toString());
        ack.acknowledge();
    }





    /**
     * 消费监听，处理报告的状态
     * @param record
     * @param ack
     * @param topic
     */
    @KafkaListener(topics = {KafkaTopicConfig.REPORT_STATE_TOPIC_NAME},groupId = "xdclass-report-test-gp")
    public void onStressReportStateMessage(ConsumerRecord<?,?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic){
        //打印消息
        log.info("消费主题：{},分区：{} 收到消息：{}",record.topic(),record.partition(),record.value());
        ReportUpdateReq reportUpdateReq = JsonUtil.json2Obj(record.value().toString(), ReportUpdateReq.class);
        reportService.updateReportState(reportUpdateReq);
        ack.acknowledge();
    }

}
