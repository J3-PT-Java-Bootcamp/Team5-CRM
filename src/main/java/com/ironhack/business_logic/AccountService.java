package com.ironhack.business_logic;

import com.ironhack.data.AccountRepository;
import com.ironhack.domain.Account;

public class AccountService {

    private static AccountService instance;

    private final AccountRepository accountRepository;

    private AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public static AccountService getInstance(AccountRepository accountRepository) {
        if(instance == null) {
            instance = new AccountService(accountRepository);
        }
        return instance;
    }

    public Account saveAccount(Account account) {
        accountRepository.saveAccount(account);
        return account;
    }

}
