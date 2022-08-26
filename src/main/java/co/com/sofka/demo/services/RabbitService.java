package co.com.sofka.demo.services;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitService {

    private AmqpTemplate amqpTemplate;

    public RabbitService(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void sendMessage(String exchange, String piso, String messageData){
        amqpTemplate.convertAndSend(exchange, piso, messageData);
    }
}
