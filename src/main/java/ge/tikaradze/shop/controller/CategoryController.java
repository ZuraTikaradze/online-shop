package ge.tikaradze.shop.controller;

import ge.tikaradze.shop.dto.CategoryDto;
import ge.tikaradze.shop.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("categories")
    public List<CategoryDto> categories() {
        return categoryService.categories();
    }

    @PostMapping("categories")
    public CategoryDto addCategory(@RequestBody CategoryDto categoryDto) {
        return categoryService.addCategory(categoryDto);
    }
}
