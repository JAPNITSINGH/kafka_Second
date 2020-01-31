package com.practice.kafkastreams.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class NumberProducer {

    @Value("${kafka.topic.input}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, Long> kafkaTemplate;
    //Random r = new Random();
    private Long counter = 0L;

    @Scheduled(fixedRate = 2000)
    public void produce() {
    	counter = ThreadLocalRandom.current().nextLong(0, 100); // counter = r.nextLong()*100;
        System.out.println("Produced :: " + counter);
        this.kafkaTemplate.send(topicName, counter);
    }
}
