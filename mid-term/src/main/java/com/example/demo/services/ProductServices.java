package com.example.demo.services;

import com.example.demo.ojects.Product;
import com.example.demo.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServices {

    private final ProductRepo productRepo;
    @Autowired
    public ProductServices(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> getProducts() {
        return productRepo.findAll();
    }

    public Product getProductById(Long id) {
        return productRepo.getReferenceById(id);
    }

    public List<Product> getProducts(String name) {
        return productRepo.findName(name);
    }

    public List<Product> getProductByBrand(String brand) {
        return productRepo.findBrand(brand);
    }

    public List<Product> getProductByCategory(String category) {
        return productRepo.findCategory(category);
    }

    public List<Product> getProductByColor(String color) {
        return productRepo.findColor(color);
    }

    public List<Product> getProductByPrice(Long up, Long down) {
        if(up == -1){
            up = Long.valueOf(9999999);
        }
        return productRepo.findPrice(up, down);
    }

    public List<String> getColor() {
        return productRepo.getColor();
    }

    public List<String> getBrand() {
        return productRepo.getBrand();
    }

    public List<String> getCategory() {
        return productRepo.getCategory();
    }

    public Product save(Product product){return productRepo.save(product);}

    public void deleteById(Long id) {
        productRepo.deleteById(id);
    }
}
