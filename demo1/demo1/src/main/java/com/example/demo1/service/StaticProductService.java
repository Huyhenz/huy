package com.example.demo1.service;

import com.example.demo1.model.StaticProduct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class StaticProductService {

        private final List<StaticProduct> products = new ArrayList<>();
        private final AtomicLong nextId = new AtomicLong(1);

        public StaticProductService() {
                // Updated data to match real TGDD aesthetic
                products.add(new StaticProduct(
                                nextId.getAndIncrement(),
                                "iPhone 15 Pro Max 256GB",
                                34990000,
                                32990000,
                                "-5%",
                                "https://cdn.tgdd.vn/Products/Images/42/305658/iphone-15-pro-max-blue-thumbnew-600x600.jpg",
                                "6.7\"",
                                "8 GB",
                                "256 GB",
                                "Mới",
                                true));

                products.add(new StaticProduct(
                                nextId.getAndIncrement(),
                                "Samsung Galaxy S24 Ultra 5G 256GB",
                                33990000,
                                29990000,
                                "-11%",
                                "https://cdn.tgdd.vn/Products/Images/42/307174/samsung-galaxy-s24-ultra-grey-thumb-600x600.jpg",
                                "6.8\"",
                                "12 GB",
                                "256 GB",
                                "Trả góp 0%",
                                true));

                products.add(new StaticProduct(
                                nextId.getAndIncrement(),
                                "OPPO Reno11 5G 256GB",
                                10990000,
                                9890000,
                                "-10%",
                                "https://cdn.tgdd.vn/Products/Images/42/319803/oppo-reno11-5g-xanh-thumb-1-600x600.jpg",
                                "6.7\"",
                                "8 GB",
                                "256 GB",
                                "",
                                false));

                products.add(new StaticProduct(
                                nextId.getAndIncrement(),
                                "Xiaomi Redmi Note 13 Pro 5G",
                                9490000,
                                8290000,
                                "-12%",
                                "https://cdn.tgdd.vn/Products/Images/42/319989/redmi-note-13-pro-den-thumb-600x600.jpg",
                                "6.67\"",
                                "8 GB",
                                "256 GB",
                                "Độc quyền",
                                false));

                products.add(new StaticProduct(
                                nextId.getAndIncrement(),
                                "MacBook Air 15 inch M2 2023",
                                32990000,
                                29590000,
                                "-10%",
                                "https://cdn.tgdd.vn/Products/Images/44/309016/macbook-air-15-inch-m2-2023-starlight-thumb-600x600.jpg",
                                "15.3\"",
                                "8 GB",
                                "256 GB",
                                "Online giá rẻ",
                                true));
        }

        public List<StaticProduct> getAllProducts() {
                return products;
        }

        public Optional<StaticProduct> getProductById(Long id) {
                return products.stream()
                                .filter(p -> p.getId().equals(id))
                                .findFirst();
        }

        // New method to allow adding items manually
        public void addProduct(StaticProduct product) {
                product.setId(nextId.getAndIncrement());
                products.add(product);
        }

        // Method to update an existing item
        public void updateProduct(Long id, StaticProduct updatedProduct) {
                for (int i = 0; i < products.size(); i++) {
                        if (products.get(i).getId().equals(id)) {
                                updatedProduct.setId(id);
                                products.set(i, updatedProduct);
                                return;
                        }
                }
        }

        // Method to delete an item
        public void deleteProductById(Long id) {
                products.removeIf(p -> p.getId().equals(id));
        }
}
