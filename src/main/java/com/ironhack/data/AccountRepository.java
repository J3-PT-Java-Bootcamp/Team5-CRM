package com.ironhack.data;

import com.ironhack.data.datasources.Datasource;
import com.ironhack.domain.Account;
import com.ironhack.domain.Contact;
import com.ironhack.domain.Opportunity;

import java.util.ArrayList;

/**
 * You should only use this repository when converting a lead and creating the account with the opportunities an contacts
 */
public class AccountRepository {

    private static AccountRepository instance;
    private final Datasource datasource;

    private AccountRepository(Datasource datasource) {
        this.datasource = datasource;
    }

    public static AccountRepository getInstance(Datasource datasource) {
        if(instance == null) {
            instance = new AccountRepository(datasource);
        }
        return instance;
    }

    public Account saveAccount(Account account) {
        datasource.saveAccount(account);
        return account;
    }
}
