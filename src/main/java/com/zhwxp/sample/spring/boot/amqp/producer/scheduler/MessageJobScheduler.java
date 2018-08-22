package com.zhwxp.sample.spring.boot.amqp.producer.scheduler;

import com.zhwxp.sample.spring.boot.amqp.producer.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MessageJobScheduler {

    private final MessageService messageService;

    @Autowired
    public MessageJobScheduler(MessageService messageService) {
        this.messageService = messageService;
    }

    @Scheduled(fixedDelay = 5000L)
    public void sendMessageJob() {
        messageService.sendMessage();
    }

}
