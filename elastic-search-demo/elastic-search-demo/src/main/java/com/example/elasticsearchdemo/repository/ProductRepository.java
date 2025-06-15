package com.example.elasticsearchdemo.repository;
import com.example.elasticsearchdemo.model.Product;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import java.util.List;

public interface ProductRepository extends ElasticsearchRepository<Product, String> {
    List<Product> findByName(String name);
     @Query("{\"match\": {\"name\": {\"query\": \"?0\", \"fuzziness\": \"AUTO\"}}}")
    List<Product> searchByNameFuzzy(String keyword);

    @Query("{\"match_phrase_prefix\": {\"name\": \"?0\"}}")
    List<Product> autocomplete(String prefix);
}
