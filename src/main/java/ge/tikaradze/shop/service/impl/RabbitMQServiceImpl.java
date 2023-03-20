package ge.tikaradze.shop.service.impl;

import ge.tikaradze.shop.service.RabbitMQService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQServiceImpl implements RabbitMQService {
    private final AmqpTemplate rabbitTemplate;

    @Value("${zt.rabbitmq.exchange}")
    private String exchange;

    @Value("${zt.rabbitmq.routingkey}")
    private String routingkey;

    public RabbitMQServiceImpl(AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(exchange, routingkey, message);
    }
}
