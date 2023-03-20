package ge.tikaradze.shop.service;


import ge.tikaradze.shop.dto.CategoryDto;
import ge.tikaradze.shop.entity.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> categories();
    CategoryDto addCategory(CategoryDto categoryDto);

    Category getCategoryByTitle(String title);
}
