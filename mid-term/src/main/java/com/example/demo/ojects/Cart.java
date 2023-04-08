package com.example.demo.ojects;

import javax.persistence.*;
import java.util.*;

@Entity
@Table
public class Cart {
    @Id
    @SequenceGenerator(name = "cart_sequence", sequenceName = "cart_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_sequence")
    private Long id;

    private  String productName;
    private  Long productPrice;
    private Long accId;

    public Cart() {
    }

    public Cart(Long id, String productName, Long productPrice, Long accId) {
        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.accId = accId;
    }

    public Cart(String productName, Long productPrice, Long accId) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.accId = accId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Long productPrice) {
        this.productPrice = productPrice;
    }

    public Long getAccId() {
        return accId;
    }

    public void setAccId(Long accId) {
        this.accId = accId;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", accId=" + accId +
                '}';
    }
}
