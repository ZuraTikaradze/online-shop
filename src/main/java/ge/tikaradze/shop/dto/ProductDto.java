package ge.tikaradze.shop.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {
    private Long id;
    private String title;
    private BigDecimal prices;
    private Long quantities;
    private Boolean isActive;
    private String categoryTitle;
}
