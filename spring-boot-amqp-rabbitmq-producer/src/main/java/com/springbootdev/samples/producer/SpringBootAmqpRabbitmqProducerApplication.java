package com.springbootdev.samples.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class SpringBootAmqpRabbitmqProducerApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootAmqpRabbitmqProducerApplication.class);

    public static void main(String[] args)
    {
        SpringApplication.run(SpringBootAmqpRabbitmqProducerApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception
    {
        LOGGER.info("application is up and running");
    }

    static final String TOPIC_EXCHANGE = "sales_data_exchange";

    static final String ORDERS_QUEUE = "orders_queue";
    static final String DELIVERIES_QUEUE = "deliveries_queue";

    static final String ORDERS_KEY = "customer.order";
    static final String DELIVERIES_KEY = "customer.deliveries";

    @Bean
    Queue queue1() {
        return new Queue(ORDERS_QUEUE, false);
    }
    @Bean
    Queue queue2() {
        return new Queue(DELIVERIES_QUEUE, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    List<Binding> bindings() {
        return Arrays.asList(BindingBuilder.bind(queue1()).to(exchange()).with(ORDERS_KEY),
                BindingBuilder.bind(queue2()).to(exchange()).with(DELIVERIES_KEY));
    }

}
