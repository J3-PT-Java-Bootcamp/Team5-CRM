package com.ironhack.business_logic;

import com.ironhack.data.AccountRepository;
import com.ironhack.data.datasources.impl.InMemoryDatasource;
import com.ironhack.data.exceptions.DataNotFoundException;
import com.ironhack.domain.Account;
import com.ironhack.domain.Contact;
import com.ironhack.domain.Opportunity;
import com.ironhack.domain.enums.Product;
import com.ironhack.domain.enums.Status;

import javax.swing.*;
import java.util.ArrayList;

public class AccountService {

    private static AccountService instance;

    private final AccountRepository accountRepository;

    private ArrayList <Contact> contacts = new ArrayList<>();
    private ArrayList <Opportunity> opportunities = new ArrayList<>();
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


    // new methods added
    public ArrayList contactList(Contact contact){
        contacts.add(contact);
        return contacts;
    }

    public ArrayList opportunityList(Opportunity opportunity){
        opportunities.add(opportunity);
        return opportunities;
    }

}
