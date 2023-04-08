package com.example.demo.services;

import com.example.demo.ojects.*;
import com.example.demo.repositories.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.*;

@Service
public class AccountServices {

    private final AccountRepo accountRepo;

    @Autowired
    public AccountServices(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }


    public Account getAccount(Long id) {
        Account acc = accountRepo.findById(id).get();
        return acc;
    }

    public Account checkAccount(String username, String password) {
        List<Account> lsAcc = accountRepo.findAll();
        for (Account i : lsAcc) {
            if (i.getEmail().equals(username) && i.getPassword().equals(password)) {return i;}
        }
        return null;
    }

    public Account save(Account acc) {
        return accountRepo.save(acc);
    }
}
