package com.novacart.product.service;

import com.novacart.product.dto.ProductRequest;
import com.novacart.product.entity.Product;
import com.novacart.product.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() { return productRepository.findAll(); }
    public Optional<Product> getProductById(String id) { return productRepository.findById(id); }

    public Product createProduct(ProductRequest request) {
        return productRepository.save(toProduct(request));
    }

    public Product updateProduct(String id, ProductRequest request) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(request.name());
        product.setDescription(request.description());
        product.setCategory(request.category());
        product.setPrice(request.price());
        product.setStockQuantity(request.stockQuantity());
        return productRepository.save(product);
    }

    public void deleteProduct(String id) {
        if (!productRepository.existsById(id)) throw new RuntimeException("Product not found");
        productRepository.deleteById(id);
    }

    public void seedDemoProducts() {
        if (productRepository.count() == 0) {
            productRepository.saveAll(List.of(
                new Product("Wireless Noise-Cancelling Headphones", "Immersive sound with all-day comfort and a 30-hour battery.", "Electronics", 129.99, 24),
                new Product("Minimalist Everyday Backpack", "Water-resistant backpack with a padded laptop sleeve and organized pockets.", "Accessories", 59.99, 40),
                new Product("Stainless Steel Travel Bottle", "Double-wall insulated bottle that keeps drinks cold for 24 hours.", "Home & Lifestyle", 24.99, 75)
            ));
        }
    }

    private Product toProduct(ProductRequest request) {
        return new Product(request.name(), request.description(), request.category(), request.price(), request.stockQuantity());
    }
}
