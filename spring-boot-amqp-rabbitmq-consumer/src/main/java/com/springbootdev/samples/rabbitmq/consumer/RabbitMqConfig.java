package com.springbootdev.samples.rabbitmq.consumer;
import com.springbootdev.samples.rabbitmq.consumer.model.Order;
import com.springbootdev.samples.rabbitmq.consumer.model.People;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMqConfig {

    @Bean
    public MessageConverter messageConverter()
    {
        Jackson2JsonMessageConverter jsonMessageConverter = new Jackson2JsonMessageConverter();
        jsonMessageConverter.setClassMapper(classMapper());
        return jsonMessageConverter;
    }


    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory)
    {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }

    @Bean
    public DefaultClassMapper classMapper()
    {
                         DefaultClassMapper classMapper = new DefaultClassMapper();
       // DefaultJackson2JavaTypeMapper classMapper = new DefaultJackson2JavaTypeMapper();

        Map<String, Class<?>> idClassMapping = new HashMap<>();
        idClassMapping.put("com.springbootdev.samples.producer.model.Order", Order.class);
        idClassMapping.put("com.springbootdev.samples.producer.model.People", People.class);
        classMapper.setIdClassMapping(idClassMapping);
        return classMapper;
    }
}
