package ge.tikaradze.shop.dto;

import lombok.Data;

@Data
public class ProductOrderDto {
    private Long id;
    private String orderName;
    private Long appUserId;
    private Long productId;
}
