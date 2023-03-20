package ge.tikaradze.shop.service.impl;

import ge.tikaradze.shop.dto.ProductDto;
import ge.tikaradze.shop.entity.Product;
import ge.tikaradze.shop.exception.NotFoundException;
import ge.tikaradze.shop.repository.ProductRepository;
import ge.tikaradze.shop.service.CategoryService;
import ge.tikaradze.shop.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
    }

    @Override
    public List<ProductDto> products() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            throw new NotFoundException("Users Not Found");
        }
        return products.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        Product product=productRepository.save(toEntity(productDto));
        product.setCategory(categoryService.getCategoryByTitle(productDto.getTitle()));
        return toDto(product);
    }

    private ProductDto toDto(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }

    private Product toEntity(ProductDto productDto) {
        return modelMapper.map(productDto, Product.class);
    }
}
