package com.example.demo.repositories;

import com.example.demo.ojects.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {

    @Query("SELECT c FROM Cart c WHERE c.accId = :accId")
    List<Cart> findByAccountId(@Param("accId")  Long id);

}
