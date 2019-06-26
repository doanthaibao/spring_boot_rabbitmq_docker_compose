package com.springbootdev.samples.producer.core;

import com.springbootdev.samples.producer.model.Item;
import com.springbootdev.samples.producer.model.Order;
import com.springbootdev.samples.producer.model.People;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class Deliver {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(Deliver.class);

    private static final String EXCHANGE = "sales_data_exchange";
    private static final String ROUTING_KEY = "customer.deliveries";

    @Scheduled(fixedRate = 1000)
    public void produce()
    {
        LOGGER.info(" Publishing the Deliveries Order");
        People people = this.getPeople();
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, people);
    }


    private People getPeople()
    {
        People people = new People("Bao", 30);
        return people;
    }
}
