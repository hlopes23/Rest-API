package com.example.car_shop.controller;

import com.example.car_shop.entity.Account;
import com.example.car_shop.exception.AccountDoesNotExistException;
import com.example.car_shop.exception.VehicleAssociatedToAccount;
import com.example.car_shop.model.AccountDTO;
import com.example.car_shop.model.AccountNamesDTO;
import com.example.car_shop.model.ErrorDTO;
import com.example.car_shop.service.AccountService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/car-shop/v1")
public class AccountController {

    Logger logger = LoggerFactory.getLogger(AccountController.class);

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @PostMapping(path = "/accounts")
    public ResponseEntity<?> addAccount(@Valid @RequestBody AccountDTO accountDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.accountService.addNewAccount(accountDTO);
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
    public ResponseEntity<?> updateFirstAndLastName(@Valid @RequestBody AccountNamesDTO accountNamesDTO, @PathVariable("accountId") Long id, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.accountService.updateFirstAndLastName(accountNamesDTO, id);
        return ResponseEntity.status(200).body("First name: " + accountNamesDTO.getFirstname() + '\n' + "Last name: " + accountNamesDTO.getLastname());
    }


    @PutMapping(path = "/accounts/{accountId}")
    public ResponseEntity<?> updateAccount(@Valid @RequestBody AccountDTO accountDTO, @PathVariable("accountId") Long id, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.accountService.updateAccount(accountDTO, id);
        return ResponseEntity.status(200).body(accountDTO);
    }


    @DeleteMapping(path = "/accounts/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable("accountId") Long id) {

        logger.info("Passei aqui.");
        try {
            this.accountService.deleteAccount(id);
        } catch (AccountDoesNotExistException | VehicleAssociatedToAccount e) {
            ErrorDTO errorDTO = new ErrorDTO(e.getMessage());
            return ResponseEntity.status(404).body(errorDTO.getMessage());
        }
        return ResponseEntity.status(200).body("Account " + id + " deleted!");
    }


    @PatchMapping(path = "accounts/activate/{id}")
    public ResponseEntity<?> activateAccount(@PathVariable Long id) {

        this.accountService.activateAccount(id);
        return ResponseEntity.status(200).body("Account " + id + " activated!");
    }


    @PatchMapping(path = "accounts/deactivate/{id}")
    public ResponseEntity<?> deactivateAccount(@PathVariable Long id) {

        this.accountService.deactivateAccount(id);
        return ResponseEntity.status(200).body("Account " + id + " deactivated!");
    }


    @GetMapping(path = "/accounts/deactivated")
    public ResponseEntity<?> getDeactivatedAccounts() {
        return ResponseEntity.status(200).body(accountService.getDeactivatedAccounts());
    }


    @GetMapping(path = "/accounts/deactivated/vehicles/active")
    public ResponseEntity<?> getDeactivatedAccountsWithActiveVehicles() {

        return ResponseEntity.ok().body(this.accountService.getDeactivatedAccountsWithActiveVehicles());
    }

    @GetMapping(path = "/accounts/deactivated/names")
    public ResponseEntity<?> getFirstAndLastNameDeactivatedAccounts() {
        return ResponseEntity.status(200).body(accountService.getFirstAndLastNameDeactivatedAccounts());
    }
}