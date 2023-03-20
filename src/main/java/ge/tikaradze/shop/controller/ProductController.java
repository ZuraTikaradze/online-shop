package ge.tikaradze.shop.controller;

import ge.tikaradze.shop.dto.ProductDto;
import ge.tikaradze.shop.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("products")
    public List<ProductDto> products() {
        return productService.products();
    }

    @PostMapping("products")
    public ProductDto addProduct(@RequestBody ProductDto productDto) {
        return productService.addProduct(productDto);
    }
}
