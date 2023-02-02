package meena.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import meena.demo.logger.LoggerService;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
public class DebtorService {

    @Autowired
    LoggerService logger;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Value(value = "${kafka.topic_name}")
    private String kafkaTopicName;

    public String sendPain001(String request) throws Exception{
        MDC.put("tx.id", UUID.randomUUID().toString());
        logger.logTemplate("Debtor(User)", "Debtor Service", "Request received from debtor", request);
        ProducerRecord<String, String> record = new ProducerRecord<String, String>(kafkaTopicName, request);
        record.headers().add("tx.id", MDC.get("tx.id").getBytes(StandardCharsets.UTF_8));
        kafkaTemplate.send(record);
        return "{\"response\":\"Message Successfully accepting for processing\"}";
    }
}
