package ge.tikaradze.shop.service;

import ge.tikaradze.shop.dto.ProductOrderDto;

public interface OrderService {
    ProductOrderDto placeOrder(ProductOrderDto productOrderDto);
}
