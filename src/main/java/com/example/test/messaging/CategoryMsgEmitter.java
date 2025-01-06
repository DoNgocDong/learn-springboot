package com.example.test.messaging;

import com.example.test.model.Category;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CategoryMsgEmitter {
    private final StreamBridge streamBridge;
    private final ObjectMapper objectMapper;

    @Value("${spring.cloud.stream.bindings.category-out-0.destination}")
    private String topic;

    public void send(Category category) {
        try {
            String jsonData = objectMapper.writeValueAsString(category);

            Message<String> msg = MessageBuilder
                    .withPayload(jsonData)
                    .build();

            streamBridge.send("category-out-0", msg);
            log.info("Sending a category [{}] message to topic [{}]", category.getName(), topic);
        }
        catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
