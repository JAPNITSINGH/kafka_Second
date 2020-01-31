package com.practice.kafkastreams.processor;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EvenNumberProcessor {

    @Value("${kafka.topic.even-output}")
    private String outputEvenTopicName;

    public void process(KStream<String, Long> stream){
        stream.filter((k, v) -> v % 2 != 0)
                .mapValues(v -> {
                    System.out.println("Getting digits in :: " + v);
                    int digits = 0;
                    while(v>=0)
                    {
                    	digits++;
                    	v=v/10;
                    }
                    return digits;
                })
                .to(outputEvenTopicName);
    }
}
