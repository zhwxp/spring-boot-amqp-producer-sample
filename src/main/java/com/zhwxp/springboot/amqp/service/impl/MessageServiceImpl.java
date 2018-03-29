package com.zhwxp.springboot.amqp.service.impl;

import com.zhwxp.springboot.amqp.config.MessageProducerConfig;
import com.zhwxp.springboot.amqp.model.Message;
import com.zhwxp.springboot.amqp.service.MessageService;
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
        LOGGER.info("Sending message: {}", message);
        rabbitTemplate.convertAndSend(MessageProducerConfig.EXCHANGE_NAME, MessageProducerConfig.ROUTING_KEY, message);
    }

}
