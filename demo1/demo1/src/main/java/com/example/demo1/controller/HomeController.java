package com.example.demo1.controller;

import com.example.demo1.service.CartService;
import com.example.demo1.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    private final ProductService productService;
    private final CartService cartService;

    public HomeController(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    // Pass cart size to all pages via @ModelAttribute
    @ModelAttribute("cartQuantity")
    public int cartQuantity(HttpSession session) {
        return cartService.getCart(session).stream().mapToInt(item -> item.getQuantity()).sum();
    }

    @GetMapping({ "/", "/home" })
    public String index(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "home/index";
    }

    @GetMapping("/product/{id}")
    public String productDetail(@PathVariable Long id, Model model) {
        return productService.getProductById(id)
                .map(product -> {
                    model.addAttribute("product", product);
                    return "product/detail";
                })
                .orElse("redirect:/");
    }
}
