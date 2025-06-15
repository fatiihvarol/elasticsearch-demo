package com.example.elasticsearchdemo.controller;

import org.springframework.web.bind.annotation.*;
import com.example.elasticsearchdemo.model.Product;
import com.example.elasticsearchdemo.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Ürün kaydetme
    @PostMapping
    public Product save(@RequestBody Product product) {
        return productService.save(product);
    }

    // Fuzzy arama
    @GetMapping("/search/fuzzy")
    public List<Product> searchFuzzy(@RequestParam String keyword) {
        return productService.searchFuzzy(keyword);
    }

    // Autocomplete arama
    @GetMapping("/search/autocomplete")
    public List<Product> searchAutocomplete(@RequestParam String prefix) {
        return productService.autocomplete(prefix);
    }
}
