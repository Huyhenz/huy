package com.example.demo1.service;

import com.example.demo1.model.Product;
import com.example.demo1.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    // Retrieve all products from the database
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Retrieve a product by its id
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // Add a new product to the database
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    // Update an existing product
    public Product updateProduct(Long id, @NotNull Product product) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Product with ID " + id + " does not exist."));

        existingProduct.setName(product.getName());
        existingProduct.setCurrentPrice(product.getCurrentPrice());
        existingProduct.setOriginalPrice(product.getOriginalPrice());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setImageUrl(product.getImageUrl());
        existingProduct.setDiscountPercent(product.getDiscountPercent());
        existingProduct.setScreenSize(product.getScreenSize());
        existingProduct.setRam(product.getRam());
        existingProduct.setStorage(product.getStorage());
        existingProduct.setBadgeText(product.getBadgeText());
        existingProduct.setHot(product.getHot());
        existingProduct.setCategory(product.getCategory());

        return productRepository.save(existingProduct);
    }

    // Delete a product by its id
    public void deleteProductById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new IllegalStateException("Product with ID " + id + " does not exist.");
        }
        productRepository.deleteById(id);
    }
}
