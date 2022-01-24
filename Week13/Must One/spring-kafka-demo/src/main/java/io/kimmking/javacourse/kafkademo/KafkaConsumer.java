package io.kimmking.javacourse.kafkademo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class KafkaConsumer {
    @KafkaListener(topics = {"test"})
    public void consumer(ConsumerRecord<?, ?> record){
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            System.out.println(("KafkaConsumer record =" + record));
            System.out.println(("KafkaConsumer message =" + message));
        }
    }
}