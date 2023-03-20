package ge.tikaradze.shop.service;

public interface RabbitMQService {
    void sendMessage(String message);
}
