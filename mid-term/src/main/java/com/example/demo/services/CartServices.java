package com.example.demo.services;

import com.example.demo.ojects.Cart;
import com.example.demo.repositories.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServices {

    private final CartRepo cartRepo;

    @Autowired
    public CartServices(CartRepo cartRepo) {
        this.cartRepo = cartRepo;
    }

    public Cart save(Cart cart) {
        return cartRepo.save(cart);
    }

    public List<Cart> getCart(Long id) {
        return cartRepo.findByAccountId(id);
    }

    public Long getTotal(Long id) {
        Long sum = Long.valueOf(0);
        for( Cart i: getCart(id)) {
            sum += i.getProductPrice();
        }
        return sum;
    }

    public int getCartSize(Long id) {
        return getCart(id).size();
    }

    public void deleteById(Long id) {
        cartRepo.deleteById(id);
    }

    public void deleteCart(Cart cart) {
        cartRepo.delete(cart);
    }
}
