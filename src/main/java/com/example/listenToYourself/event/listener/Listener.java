package com.example.listenToYourself.event.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Component
public abstract class Listener {

    @Autowired
    protected ObjectMapper objectMapper;

    protected void acknowledgeMessage(Map<String, Object> headers) {
        Acknowledgment acknowledgment = (Acknowledgment) headers.get(KafkaHeaders.ACKNOWLEDGMENT);
        if (acknowledgment != null){
            log.info("Committing offset");
            acknowledgment.acknowledge();
        }
    }


    protected <T> T readValue(String payload, Class<T> clz){
        try {
            return objectMapper.readValue(payload,clz);
        } catch (IOException e) {
            log.error(String.format("Not able to covert payload: %s into object %s", payload, clz.getTypeName()));
            throw new IllegalArgumentException(String.format("Not able to covert payload: %s into object %s", payload, clz.getTypeName()),e);
        }
    }
}
