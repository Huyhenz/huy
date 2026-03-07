package com.example.demo1.controller;

import com.example.demo1.model.Category;
import com.example.demo1.service.CategoryService;
// import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalControllerAdvice {

    private final CategoryService categoryService;

    public GlobalControllerAdvice(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @ModelAttribute("menuCategories")
    public Map<String, List<Category>> getMenuCategories() {
        List<Category> categories = categoryService.getAllCategories();

        // Group by groupName, if null or empty put them in 'Khác'
        return categories.stream().collect(Collectors.groupingBy(
                c -> (c.getGroupName() != null && !c.getGroupName().trim().isEmpty()) ? c.getGroupName() : "Khác"));
    }
}
