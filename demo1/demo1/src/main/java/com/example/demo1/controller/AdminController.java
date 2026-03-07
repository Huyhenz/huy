package com.example.demo1.controller;

import com.example.demo1.model.Product;
import com.example.demo1.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/product")
public class AdminController {

    private final ProductService productService;

    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "admin/add-product";
    }

    @PostMapping("/add")
    public String addProduct(Product product) {
        productService.addProduct(product);
        return "redirect:/admin/product";
    }

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "admin/list-product";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@org.springframework.web.bind.annotation.PathVariable Long id, Model model) {
        return productService.getProductById(id)
                .map(product -> {
                    model.addAttribute("product", product);
                    return "admin/edit-product";
                })
                .orElse("redirect:/admin/product");
    }

    @PostMapping("/edit/{id}")
    public String updateProduct(@org.springframework.web.bind.annotation.PathVariable Long id, Product product) {
        productService.updateProduct(id, product);
        return "redirect:/admin/product";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@org.springframework.web.bind.annotation.PathVariable Long id) {
        productService.deleteProductById(id);
        return "redirect:/admin/product";
    }
}
