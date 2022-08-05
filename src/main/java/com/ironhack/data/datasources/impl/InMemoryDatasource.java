package com.ironhack.data.datasources.impl;

import com.ironhack.data.datasources.Datasource;
import com.ironhack.domain.Account;
import com.ironhack.domain.Contact;
import com.ironhack.domain.Lead;
import com.ironhack.domain.Opportunity;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class InMemoryDatasource implements Datasource {
    private static InMemoryDatasource instance;
    private static final int ID_START = 0;
    static ArrayList<Lead> leadsList = new ArrayList<>();
    static ArrayList<Account> accountsList = new ArrayList<>();
    public static ArrayList<Lead> getLeadsList() {
        return leadsList;
    }
    public static ArrayList<Account> getAccountsList() {
        return accountsList;
    }


    private InMemoryDatasource() {
    }

    public static InMemoryDatasource getInstance() {
        if(instance == null ) {
            instance = new InMemoryDatasource();
        }
        return instance;
    }

    @Override
    public void saveLead(Lead lead) {
        boolean exists = false;
        for (var l : leadsList) {
            if (l.getId() == lead.getId()) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            InMemoryDatasource.leadsList.add(lead);
        }
    }

    @Override
    public void deleteLead(Lead lead) {
        leadsList.removeIf(lead1 -> lead1.equals(lead));
    }

    @Override
    public List<Lead> getAllLeads() {
        return leadsList;
    }

    @Override
    public void deleteAllLeads() {
        leadsList = new ArrayList<>();
    }

    @Override
    public int getMaxLeadId() {
        if(leadsList.isEmpty()) {
            return ID_START;
        }else{
            int maxId = ID_START;
            for (Lead lead : leadsList) {
                if(maxId < lead.getId()) maxId = lead.getId();
            }
            return maxId + 1;
        }
    }

    @Override
    public void saveAccount(Account account) {
        boolean exists = false;
        for (Account a : accountsList) {
            if (a.getId() == account.getId()) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            InMemoryDatasource.accountsList.add(account);
        }
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountsList;
    }

    @Override
    public void deleteAllAccounts() {
        accountsList = new ArrayList<>();
    }

    @Override
    public int getMaxAccountId() {
        if(accountsList.isEmpty()) {
            return ID_START;
        }else{
            int maxId = ID_START;
            for (Account account : accountsList) {
                if(maxId < account.getId()) maxId = account.getId();
            }
            return maxId + 1;
        }
    }

    @Override
    public int getMaxOpportunityId() {
        if(accountsList.isEmpty()) {
            return ID_START;
        }else{
            int maxId = ID_START;
            for (Account account : accountsList) {
                for (Opportunity opportunity : account.getOpportunityList()) {
                    if(maxId < opportunity.getId()) maxId = opportunity.getId();
                }
            }
            return maxId + 1;
        }
    }

    @Override
    public int getMaxContactId() {
        if(accountsList.isEmpty()) {
            return ID_START;
        }else{
            int maxId = ID_START;
            for (Account account : accountsList) {
                for (Contact contact : account.getContactList()) {
                    if(maxId < contact.getId()) maxId = contact.getId();
                }
            }
            return maxId + 1;
        }
    }
}
