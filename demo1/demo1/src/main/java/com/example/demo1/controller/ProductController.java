package com.example.demo1.controller;

// import com.example.demo1.model.Product;
// import com.example.demo1.service.CategoryService;
// import com.example.demo1.service.ProductService;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
// import jakarta.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {
    /*
     * @Autowired
     * private ProductService productService;
     * 
     * @Autowired
     * private CategoryService categoryService;
     * 
     * @GetMapping
     * public String showProductList(Model model) {
     * model.addAttribute("products", productService.getAllProducts());
     * // Phải khớp với đường dẫn: templates/products/products-list.html
     * return "products/products-list";
     * }
     * 
     * @GetMapping("/add")
     * public String showAddForm(Model model) {
     * model.addAttribute("product", new Product());
     * model.addAttribute("categories", categoryService.getAllCategories());
     * return "products/add-product";
     * }
     * 
     * @PostMapping("/add")
     * public String addProduct(@Valid Product product, BindingResult result) {
     * if (result.hasErrors()) {
     * return "products/add-product";
     * }
     * productService.addProduct(product);
     * return "redirect:/products";
     * }
     * 
     * @GetMapping("/delete/{id}")
     * public String deleteProduct(@PathVariable Long id) {
     * productService.deleteProductById(id);
     * return "redirect:/products";
     * }
     */
}