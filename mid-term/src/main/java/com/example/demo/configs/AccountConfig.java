package com.example.demo.configs;

import com.example.demo.ojects.Account;
import com.example.demo.repositories.AccountRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AccountConfig {
    @Bean
    CommandLineRunner commandLineRunnerAccount(AccountRepo repo) {
        return args -> {
            Account tri = new Account("trico@gmail.com","123456","tri");
            Account khoi = new Account("khoibu@gmail.com","123456","khoi");
            Account admin = new Account("admin","admin","admin");
            repo.saveAll(List.of(tri,khoi,admin));
        };
    }
}
