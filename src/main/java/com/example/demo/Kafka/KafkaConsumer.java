package com.example.demo.Kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    private final KafkaProducer kafkaProducer;

    @Autowired
    public KafkaConsumer(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @KafkaListener(topics = "in")
    public void listen(ConsumerRecord<String, String> record) {
        System.out.println("Received message:");
        System.out.println("Key: " + record.key());
        System.out.println("Value: " + record.value());

        kafkaProducer.sendMessage("out", "uuid", "OK");
    }
}
