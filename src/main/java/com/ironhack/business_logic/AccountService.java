package com.ironhack.business_logic;

import com.ironhack.data.AccountRepository;
import com.ironhack.domain.Account;
import com.ironhack.domain.Contact;
import com.ironhack.domain.Opportunity;

import java.util.ArrayList;

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
        //var id = accountRepository

        accountRepository.saveAccount(account);
        return account;
    }

}
