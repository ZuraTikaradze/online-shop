package ge.tikaradze.shop.service.impl;

import ge.tikaradze.shop.dto.ProductOrderDto;
import ge.tikaradze.shop.entity.ProductOrder;
import ge.tikaradze.shop.repository.ProductOrderRepository;
import ge.tikaradze.shop.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final ProductOrderRepository productOrderRepository;

    private final ModelMapper modelMapper;

    private final RabbitMQServiceImpl rabbitMQService;


    public OrderServiceImpl(ProductOrderRepository productOrderRepository, ModelMapper modelMapper, RabbitMQServiceImpl rabbitMQService) {
        this.productOrderRepository = productOrderRepository;
        this.modelMapper = modelMapper;
        this.rabbitMQService = rabbitMQService;
    }

    @Override
    public ProductOrderDto placeOrder(ProductOrderDto productOrderDto) {
        ProductOrder productOrder = new ProductOrder();
        productOrder.setOrderName(productOrderDto.getOrderName());
        productOrder.setAppUserId(productOrderDto.getAppUserId());
        productOrder.setProductId(productOrderDto.getProductId());
        productOrder = productOrderRepository.save(productOrder);
        rabbitMQService.sendMessage(productOrder.getAppUserId() + " " + " " + productOrder.getProductId() + productOrder.getOrderName());
        return toDto(productOrder);
    }

    private ProductOrderDto toDto(ProductOrder productOrder) {
        return modelMapper.map(productOrder, ProductOrderDto.class);
    }

}
