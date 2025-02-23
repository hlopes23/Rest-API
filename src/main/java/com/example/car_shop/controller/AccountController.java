package com.example.car_shop.controller;

import com.example.car_shop.entity.Account;
import com.example.car_shop.exception.AccountAlreadyExistsException;
import com.example.car_shop.exception.AccountDoesNotExistException;
import com.example.car_shop.model.AccountDTO;
import com.example.car_shop.model.ErrorDTO;
import com.example.car_shop.service.AccountService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping(path = "/car-shop/v1")
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(path = "/accounts")
    public ResponseEntity<?> addAccount(@RequestBody AccountDTO accountDTO) {

        try {
            this.accountService.addNewAccount(accountDTO);
        } catch (AccountAlreadyExistsException e) {
            ErrorDTO errorDTO = new ErrorDTO(e.getMessage());
            return ResponseEntity.status(400).body(errorDTO.getMessage());
        }
        return ResponseEntity.status(201).body("Account created successfully!");
    }


    @GetMapping(path = "/accounts")
    public ResponseEntity<?> getAccounts() {

        List<AccountDTO> accounts = this.accountService.getAllAccounts();

        return ResponseEntity.status(200).body(accounts);
    }


    @GetMapping(path = "/accounts/{accountId}")
    public Account getAccountById(@PathVariable("accountId") Long id) {
        return this.accountService.getAccount(id);
    }


    @PatchMapping(path = "/accounts/{accountId}")
    public ResponseEntity<?> updateFirstAndLastName(@RequestBody AccountDTO accountDTO, @PathVariable("accountId") Long id) {

        try {
            this.accountService.updateFirstAndLastName(accountDTO, id);
        } catch (AccountDoesNotExistException e) {
            ErrorDTO errorDTO = new ErrorDTO(e.getMessage());
            return ResponseEntity.status(404).body(errorDTO);
        }
        return ResponseEntity.status(200).body("First name: " + accountDTO.getFirstname() + '\n' + "Last name: " + accountDTO.getLastname());
    }


    @PutMapping(path = "/accounts/{accountId}")
    public ResponseEntity<?> updateAccount(@RequestBody AccountDTO accountDTO, @PathVariable("accountId") Long id) {
        try {
            this.accountService.updateAccount(accountDTO, id);
        } catch (AccountDoesNotExistException e) {
            ErrorDTO errorDTO = new ErrorDTO(e.getMessage());
            return ResponseEntity.status(404).body(errorDTO);
        }
        return ResponseEntity.status(200).body(accountDTO);
    }


    @DeleteMapping(path = "/accounts/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable("accountId") Long id) {

        try {
            this.accountService.deleteAccount(id);
        } catch (AccountDoesNotExistException e) {
            ErrorDTO errorDTO = new ErrorDTO(e.getMessage());
            return ResponseEntity.status(404).body(errorDTO.getMessage());
        }
        return ResponseEntity.status(200).body("Account " + id + " deleted!");
    }


    @PatchMapping(path = "accounts/activate/{id}")
    public ResponseEntity<?> activateAccount(@PathVariable Long id) {
        try {
            this.accountService.activateAccount(id);
        } catch (AccountDoesNotExistException e) {
            ErrorDTO errorDTO = new ErrorDTO(e.getMessage());
            return ResponseEntity.status(400).body(errorDTO);
        }
        return ResponseEntity.status(200).body("Account " + id + " activated!");
    }


    @PatchMapping(path = "accounts/deactivate/{id}")
    public ResponseEntity<?> deactivateAccount(@PathVariable Long id) {
        try {
            this.accountService.deactivateAccount(id);
        } catch (AccountDoesNotExistException e) {
            ErrorDTO errorDTO = new ErrorDTO(e.getMessage());
            return ResponseEntity.status(400).body(errorDTO);
        }
        return ResponseEntity.status(200).body("Account " + id + " deactivated!");
    }


    @GetMapping(path = "/accounts/deactivated")
    public ResponseEntity<?> getDeactivatedAccounts() {
        return ResponseEntity.status(200).body(accountService.getDeactivatedAccounts());
    }


    @GetMapping(path = "/accounts/deactivated/activevehicles")
    public ResponseEntity<?> getDeactivatedAccountsWithActiveVehicles() {
        
        return ResponseEntity.ok().body(this.accountService.getDeactivatedAccountsWithActiveVehicles());
    }
}