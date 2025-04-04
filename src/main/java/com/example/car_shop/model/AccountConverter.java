package com.example.car_shop.model;

import com.example.car_shop.entity.Account;

public class AccountConverter {

    public static AccountDTO fromAccountToAccountDto(Account account) {
        return AccountDTO.builder()
                .nif(account.getNif())
                .firstname(account.getFirstname())
                .lastname(account.getLastname())
                .active(account.isActive())
                .build();
    }

    public static Account fromAccountDtoToAccount(AccountDTO accountDTO) {
        return Account.builder()
                .nif(accountDTO.getNif())
                .firstname(accountDTO.getFirstname())
                .lastname(accountDTO.getLastname())
                .active(accountDTO.isActive())
                .build();
    }


    public static Account fromAccountNamesDtoToAccount(AccountNamesDTO accountNamesDTO) {
        return Account.builder()
                .firstname(accountNamesDTO.getFirstname())
                .lastname(accountNamesDTO.getLastname())
                .build();
    }

    public static AccountNamesDTO fromAccountToAccountNamesDto(Account account) {
        return AccountNamesDTO.builder()
                .firstname(account.getFirstname())
                .lastname(account.getLastname())
                .build();
    }
}
