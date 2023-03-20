package ge.tikaradze.shop.controller;

import ge.tikaradze.shop.dto.ProductOrderDto;
import ge.tikaradze.shop.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ProductOrderDto placeOrder(ProductOrderDto productOrderDto) {
        return orderService.placeOrder(productOrderDto);
    }

}
