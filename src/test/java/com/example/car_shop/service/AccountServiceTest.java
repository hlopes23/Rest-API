package com.example.car_shop.service;

import com.example.car_shop.entity.Account;
import com.example.car_shop.exception.AccountAlreadyExistsException;
import com.example.car_shop.model.AccountConverter;
import com.example.car_shop.model.AccountDTO;
import com.example.car_shop.repository.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    AccountService accountService;


    @Test
    void when_addNewAccountAndNifDoesNotExist_then_getAccountDto() {
        when(accountRepository.findByNif(any(String.class))).thenReturn(Optional.empty());

        AccountDTO accountDTO = AccountDTO.builder()
                .firstname("John")
                .nif("1234567789")
                .lastname("Doe")
                .build();

        accountService.addNewAccount(accountDTO);

        verify(accountRepository, times(1)).save(any(Account.class));
    }


    @Test
    void when_addNewAccountAndNifExists_then_throwException() {
        Account account = Account.builder()
                .firstname("John")
                .lastname("Doe")
                .nif("12313123")
                .build();

        when(accountRepository.findByNif(any(String.class))).thenReturn(Optional.of(account));

        Assertions.assertThrows(AccountAlreadyExistsException.class, () -> accountService.addNewAccount(AccountConverter.fromAccountToAccountDto(account)));
    }


    @Test
    void when_thereAreAccounts_then_getList() {
        List<Account> accounts = new ArrayList<>();
        accounts.add((Account.builder().firstname("John").lastname("Doe").nif("124512").build()));
        accounts.add((Account.builder().firstname("Jane").lastname("Doe").nif("909090").build()));

        when(accountRepository.findAll()).thenReturn(accounts);

        List<AccountDTO> result = accountService.getAllAccounts();

        assertNotNull(result);
        assertEquals(2, result.size());
    }


    @Test
    void when_thereAreNoAccounts_then_getEmptyList() {

        when(accountRepository.findAll()).thenReturn(new ArrayList<>());

        List<AccountDTO> accountDTOS = accountService.getAllAccounts();

        assertNotNull(accountDTOS);
        assertTrue(accountDTOS.isEmpty());
    }
}