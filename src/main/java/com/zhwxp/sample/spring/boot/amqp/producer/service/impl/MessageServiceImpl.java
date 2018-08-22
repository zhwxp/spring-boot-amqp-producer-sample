package com.zhwxp.sample.spring.boot.amqp.producer.service.impl;

import java.time.LocalDateTime;

import com.zhwxp.sample.spring.boot.amqp.producer.config.MessageProducerConfig;
import com.zhwxp.sample.spring.boot.amqp.producer.model.Message;
import com.zhwxp.sample.spring.boot.amqp.producer.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageServiceImpl.class);

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public MessageServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendMessage() {
        Message message = new Message();
        message.setTitle("message title");
        message.setBody("message body");
        message.setDate(LocalDateTime.now());
        LOGGER.info("Send message: {}", message);
        rabbitTemplate.convertAndSend(MessageProducerConfig.EXCHANGE_NAME, MessageProducerConfig.ROUTING_KEY, message);
    }

}
