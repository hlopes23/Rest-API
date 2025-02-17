package com.example.car_shop.service;

import com.example.car_shop.entity.Account;
import com.example.car_shop.exception.AccountAlreadyExistsException;
import com.example.car_shop.exception.AccountDoesNotExistException;
import com.example.car_shop.model.AccountDTO;
import com.example.car_shop.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    // METHOD TO CREATE NEW ACCOUNT

    public void addNewAccount(AccountDTO accountDTO) {
        Optional<Account> byNifAccount = this.accountRepository.findByNif(accountDTO.getNif());

        if (byNifAccount.isPresent()) {
            throw new AccountAlreadyExistsException("There's already an account with Nif " + accountDTO.getNif());
        }

        Account account = new Account();
        account.setFirstname(accountDTO.getFirstname());
        account.setLastname(accountDTO.getLastname());
        account.setNif(accountDTO.getNif());
        account.setActive(false);
        this.accountRepository.save(account);
    }


    // METHOD TO RETRIEVE ALL ACCOUNTS

    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        this.accountRepository.findAll().forEach(accounts::add);
        return accounts;
    }


    // METHOD TO RETRIEVE ACCOUNT BY ID

    public Account getAccount(Long id) {
        return this.accountRepository.findById(id).orElseThrow(() -> new IllegalStateException("ID doesn't exist."));
    }


    // METHOD TO UPDATE FIRST NAME AND LAST NAME

    public void updateFirstAndLastName(AccountDTO accountDTO, Long id) {
        Optional<Account> accountOpt = this.accountRepository.findById(id);

        if (accountOpt.isEmpty()) {
            throw new AccountDoesNotExistException("This account does not exist in Repository.");
        }

        Account account = accountOpt.get();
        if (accountDTO.getFirstname() != null && !accountDTO.getFirstname().equals("")) {
            account.setFirstname(accountDTO.getFirstname());
        }

        if (accountDTO.getLastname() != null && !accountDTO.getLastname().equals("")) {
            account.setLastname(accountDTO.getLastname());
        }

        this.accountRepository.save(account);
    }


    // METHOD TO UPDATE FULL ACCOUNT DETAILS

    public void updateAccount(AccountDTO accountDTO, Long id) {
        Optional<Account> accountOpt = this.accountRepository.findById(id);

        if (accountOpt.isEmpty()) {
            throw new AccountDoesNotExistException("This account does not exist in Repository.");
        }

        Account account = accountOpt.get();
        if (accountDTO.getFirstname() != null && !accountDTO.getFirstname().equals("")) {
            account.setFirstname(accountDTO.getFirstname());
        }

        if (accountDTO.getLastname() != null && !accountDTO.getLastname().equals("")) {
            account.setLastname(accountDTO.getLastname());
        }

        if (accountDTO.getNif() != null && !accountDTO.getNif().equals("")) {
            account.setNif(accountDTO.getNif());
        }

        if (!account.isActive()) {
            account.setActive(true);
        } else {
            account.setActive(false);
        }

        this.accountRepository.save(account);
    }


    // METHOD TO DELETE ACCOUNT BY ID

    public void deleteAccount(Long id) {
        Optional<Account> accountOpt = this.accountRepository.findById(id);

        if (accountOpt.isEmpty()) {
            throw new AccountDoesNotExistException("This account does not exist in Repository.");
        }
        Account account = accountOpt.get();
        this.accountRepository.delete(account);
    }


    // METHOD TO ACTIVATE ACCOUNT BY ID

    public void activateAccount(Long id) throws AccountDoesNotExistException {
        Optional<Account> accountOpt = this.accountRepository.findById(id);

        if (accountOpt.isEmpty()) {
            throw new AccountDoesNotExistException("This account does not exist in Repository.");
        }

        Account account = accountOpt.get();

        if (!account.isActive()) {
            account.setActive(true);
            this.accountRepository.save(account);
        }
    }


}