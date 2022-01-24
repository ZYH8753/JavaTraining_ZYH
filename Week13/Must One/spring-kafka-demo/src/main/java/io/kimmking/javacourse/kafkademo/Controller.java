package io.kimmking.javacourse.kafkademo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Controller {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping("kafkaSend")
    public String kafkaSend(@RequestParam String msg) {
        kafkaTemplate.send("test", msg);
        return "ok";
    }
}
