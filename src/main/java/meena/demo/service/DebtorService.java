package meena.demo.service;

import meena.demo.logger.LoggerService;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger loggerFactory = LoggerFactory.getLogger(this.getClass());

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Value(value = "${kafka.topic_name}")
    private String kafkaTopicName;

    private static final String TRANSACTION_ID = "tx.id";

    public String sendPain001(String request) {
        MDC.put(TRANSACTION_ID, UUID.randomUUID().toString());
        logger.logTemplate(
                "Debtor(User)",
                "Debtor Service",
                "Request received from debtor",
                request);
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(kafkaTopicName, request);
        producerRecord.headers().add(TRANSACTION_ID, MDC.get(TRANSACTION_ID).getBytes(StandardCharsets.UTF_8));
        kafkaTemplate.send(producerRecord);
        return "{\"response\":\"Message Successfully accepting for processing\"}";
    }
}
