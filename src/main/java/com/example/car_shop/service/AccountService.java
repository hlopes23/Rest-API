package com.example.car_shop.service;

import com.example.car_shop.entity.Account;
import com.example.car_shop.entity.Vehicle;
import com.example.car_shop.exception.AccountAlreadyExistsException;
import com.example.car_shop.exception.AccountDoesNotExistException;
import com.example.car_shop.model.AccountConverter;
import com.example.car_shop.model.AccountDTO;
import com.example.car_shop.repository.AccountRepository;
import com.example.car_shop.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private AccountRepository accountRepository;
    private VehicleRepository vehicleRepository;

    public AccountService(AccountRepository accountRepository, VehicleRepository vehicleRepository) {
        this.accountRepository = accountRepository;
        this.vehicleRepository = vehicleRepository;
    }

    // METHOD TO CREATE NEW ACCOUNT

    public AccountDTO addNewAccount(AccountDTO accountDTO) {
        Optional<Account> byNifAccount = this.accountRepository.findByNif(accountDTO.getNif());

        if (byNifAccount.isPresent()) {
            throw new AccountAlreadyExistsException("There's already an account with Nif " + accountDTO.getNif());
        }

        Account account = AccountConverter.fromAccountDtoToAccount(accountDTO);
        this.accountRepository.save(account);

        return accountDTO;
    }


    // METHOD TO GET ALL ACCOUNTS

    public List<AccountDTO> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        this.accountRepository.findAll().forEach(accounts::add);

        List<AccountDTO> accountsDTO = new ArrayList<>();

        for (Account account : accounts) {
            accountsDTO.add(AccountConverter.fromAccountToAccountDto(account));
        }
        return accountsDTO;
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
        this.accountRepository.save(account);
    }

    // METHOD TO ACTIVATE ACCOUNT BY ID

    public void activateAccount(Long id) {
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

    // METHOD TO DEACTIVATE ACCOUNT BY ID

    public void deactivateAccount(Long id) {
        Optional<Account> accountOpt = this.accountRepository.findById(id);

        if (accountOpt.isEmpty()) {
            throw new AccountDoesNotExistException("This account does not exist in Repository.");
        }

        Account account = accountOpt.get();

        if (account.isActive()) {
            account.setActive(false);
            this.accountRepository.save(account);
        }
    }

    // METHOD TO GET ALL ACCOUNTS THAT ARE DEACTIVATED

    public List<AccountDTO> getDeactivatedAccounts() {

        Optional<List<Account>> deactivatedAccounts = accountRepository.findByActive(false);

        if (deactivatedAccounts.isEmpty()) {
            throw new AccountDoesNotExistException("There are no deactivated accounts.");
        }

        List<AccountDTO> accountsDTO = new ArrayList<>();

        for (Account account : deactivatedAccounts.get()) {
            accountsDTO.add(AccountConverter.fromAccountToAccountDto(account));
        }
        return accountsDTO;
    }


    public List<AccountDTO> getDeactivatedAccountsWithActiveVehicles() {

        Optional<List<Account>> deactivatedAccounts = this.accountRepository.findByActive(false);

        Optional<List<Vehicle>> vehicles = this.vehicleRepository.findByAccountIn(deactivatedAccounts);

        if (vehicles.isPresent()) {
            List<AccountDTO> deactivatedAccountsActiveVehicles = vehicles.get().stream()
                    .filter(vehicle -> vehicle.isActive())
                    .map(vehicle -> AccountConverter.fromAccountToAccountDto(vehicle.getAccount()))
                    .toList();

            return deactivatedAccountsActiveVehicles;
        }

        return Collections.emptyList();
    }
}