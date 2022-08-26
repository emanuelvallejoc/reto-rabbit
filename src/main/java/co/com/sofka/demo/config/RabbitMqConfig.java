package co.com.sofka.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Bean
    Queue pisoUno() {
        return new Queue("queue.imp.piso1", false);
    }

    @Bean
    Queue pisoDos() {
        return new Queue("queue.par.piso2", false);
    }


    @Bean
    DirectExchange exchange() {
        return new DirectExchange("direct-exchange");
    }

    @Bean
    TopicExchange exchangeImpar() {
        return new TopicExchange("topic-exchange");
    }

    @Bean
    Binding pisoUnoBinding(Queue pisoUno, DirectExchange exchange) {
        return BindingBuilder.bind(pisoUno).to(exchange).with("pisouno");
    }

    @Bean
    Binding pisoDosBinding(Queue pisoDos, DirectExchange exchange) {
        return BindingBuilder.bind(pisoDos).to(exchange).with("pisodos");
    }


    @Bean
    Binding pisoUnoTopicBinding(Queue pisoUno, TopicExchange exchange) {
        return BindingBuilder.bind(pisoUno).to(exchange).with("imp");
    }

    @Bean
    Binding pisoTresTopicBinding(Queue pisoDos, TopicExchange exchange) {
        return BindingBuilder.bind(pisoDos).to(exchange).with("par");
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        return simpleMessageListenerContainer;
    }

    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
