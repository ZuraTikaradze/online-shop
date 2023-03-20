package ge.tikaradze.shop.service.impl;

import ge.tikaradze.shop.dto.CategoryDto;
import ge.tikaradze.shop.entity.Category;
import ge.tikaradze.shop.exception.NotFoundException;
import ge.tikaradze.shop.repository.CategoryRepository;
import ge.tikaradze.shop.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CategoryDto> categories() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            throw new NotFoundException("Users Not Found");
        }
        return categories.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        return toDto(categoryRepository.save(toEntity(categoryDto)));
    }

    @Override
    public Category getCategoryByTitle(String title) {
        return categoryRepository.findFirstByTitle(title);
    }

    private CategoryDto toDto(Category category) {
        return modelMapper.map(category, CategoryDto.class);
    }

    private Category toEntity(CategoryDto categoryDto) {
        return modelMapper.map(categoryDto, Category.class);
    }
}
