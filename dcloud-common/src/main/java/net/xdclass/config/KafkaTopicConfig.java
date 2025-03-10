package net.xdclass.config;

/**
 * KafkaTopicConfig类用于配置Kafka主题的相关参数
 * 它提供了访问Kafka主题配置的属性和方法，以便在应用程序中更容易地管理和使用这些配置
 **/
public class KafkaTopicConfig {


    /**
     * 压测
     */
    public static final String STRESS_TOPIC_NAME = "stress_report_topic1";

    /**
     * 接口自动化
     */
    public static final String API_TOPIC_NAME = "api_report_topic2";

    /**
     * ui自动化
     */
    public static final String UI_TOPIC_NAME = "ui_report_topic";

    /**
     * 报告状态的topic
     */
    public static final String REPORT_STATE_TOPIC_NAME = "report_state_topic";
}
