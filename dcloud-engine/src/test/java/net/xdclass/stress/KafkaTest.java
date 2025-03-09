package net.xdclass.stress;

import jakarta.annotation.Resource;
import net.xdclass.config.KafkaTopicConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

/**
 *
 **/
@SpringBootTest
public class KafkaTest {

    @Resource
    private KafkaTemplate<String,String> kafkaTemplate;


    @Test
    public void testSendMsg(){
        kafkaTemplate.send(KafkaTopicConfig.STRESS_TOPIC_NAME,"case_id_"+1,"test 6666 8888 xdclass");
    }

}

