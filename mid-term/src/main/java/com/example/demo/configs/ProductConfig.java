package com.example.demo.configs;

import com.example.demo.ojects.Product;
import com.example.demo.repositories.ProductRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ProductConfig {
    @Bean
    CommandLineRunner commandLineRunnerProduct(ProductRepo repo) {
        return args -> {
            Product jordan = new Product("jordan 4",Long.valueOf(1200),"sneaker","nike", "white");
            Product timber = new Product("timberland",Long.valueOf(1500),"boot","the wolf", "brown");
            Product flip = new Product("flip",Long.valueOf(20),"normal", "newB", "black");
            repo.saveAll(List.of(jordan,timber,flip));
        };
    }

}
