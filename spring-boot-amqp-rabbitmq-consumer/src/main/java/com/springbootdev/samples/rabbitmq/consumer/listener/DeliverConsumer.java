package com.springbootdev.samples.rabbitmq.consumer.listener;

import com.springbootdev.samples.rabbitmq.consumer.model.People;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = {"deliveries_queue"})
public class DeliverConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeliverConsumer.class);

    @RabbitHandler
    public void receiveMessage(People people)
    {
        LOGGER.info("deliveries receive message [" + people.toString() + "] ");
    }
}
