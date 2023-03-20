package ge.tikaradze.shop.service;


import ge.tikaradze.shop.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> products();

    ProductDto addProduct(ProductDto productDto);
}
