package com.example.car_shop.controller;

import com.example.car_shop.entity.Account;
import com.example.car_shop.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/accounts")
public class AccountController {
    @Autowired
    AccountService accountService;


    @PostMapping(path = "/createaccount")
    public Long addNewAccount(@RequestBody Account account) {

        this.accountService.saveOrUpdateAccount(account);
        return account.getId();
    }

    @GetMapping
    public List<Account> getAccounts() {
        return this.accountService.getAllAccounts();
    }

    @GetMapping(path = "{AccountID}")
    public Account getAccount(@PathVariable("AccountID") Long id) {
        return this.accountService.getAccount(id);
    }
}