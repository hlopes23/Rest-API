package com.example.car_shop.service;

import com.example.car_shop.entity.Account;
import com.example.car_shop.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        this.accountRepository.findAll().forEach(accounts::add);
        return accounts;
    }

    public void saveOrUpdateAccount(Account account) {
        this.accountRepository.save(account);
    }

    public Account getAccount(Long id) {
        return this.accountRepository.findById(id).orElseThrow(() -> new IllegalStateException("ID doesn't exist."));
    }
}
