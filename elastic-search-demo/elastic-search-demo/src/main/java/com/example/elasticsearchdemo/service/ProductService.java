package com.example.elasticsearchdemo.service;

import com.example.elasticsearchdemo.model.Product;
import com.example.elasticsearchdemo.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> searchFuzzy(String keyword) {
        return productRepository.searchByNameFuzzy(keyword);
    }

    public List<Product> autocomplete(String prefix) {
        return productRepository.autocomplete(prefix);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }
}
