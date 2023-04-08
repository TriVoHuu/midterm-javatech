package com.example.demo.repositories;

import com.example.demo.ojects.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    @Query("SELECT u FROM Product u WHERE u.name LIKE %:name% ")
    List<Product> findName(@Param("name") String name);

    @Query("SELECT u FROM Product u WHERE u.brand = :brand ")
    List<Product> findBrand(@Param("brand") String brand);

    @Query("SELECT u FROM Product u WHERE u.category = :category ")
    List<Product> findCategory(@Param("category") String category);

    @Query("SELECT u FROM Product u WHERE u.color = :color ")
    List<Product> findColor(@Param("color") String color);

    @Query("SELECT u FROM Product u WHERE u.price <= :up AND u.price >= :down ")
    List<Product> findPrice(@Param("up") Long up, @Param("down") Long down);

    @Query("SELECT p.color FROM Product p GROUP BY p.color")
    List<String> getColor();

    @Query("SELECT p.brand FROM Product p GROUP BY p.brand")
    List<String> getBrand();

    @Query("SELECT p.category FROM Product p GROUP BY p.category")
    List<String> getCategory();
}
