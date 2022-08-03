package com.ironhack.data.datasources.impl;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.ironhack.data.datasources.Datasource;
import com.ironhack.domain.Account;
import com.ironhack.domain.Contact;
import com.ironhack.domain.Lead;
import com.ironhack.domain.Opportunity;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonDatasource implements Datasource {

    private static final int ID_START = 0;

    private static JsonDatasource instance;

    public static final String FILES_DIR_PATH = "files/";
    public static final String LEADS_FILE_PATH = "leads-db.json";
    public static final String ACCOUNTS_FILE_PATH = "accounts-db.json";

    private final File filesDir = new File(FILES_DIR_PATH);
    private final File leadsFile = new File(FILES_DIR_PATH + LEADS_FILE_PATH);
    private final File accountsFile = new File(FILES_DIR_PATH + ACCOUNTS_FILE_PATH);
    private final Gson gson = new Gson();

    private JsonDatasource() throws IOException {
        filesDir.mkdir();
        leadsFile.createNewFile();
        accountsFile.createNewFile();
    }

    public static Datasource getInstance() throws IOException {
        if (instance == null) {
            instance = new JsonDatasource();
        }
        return instance;
    }

    @Override
    public void saveLead(Lead lead) {
        var leads = getAllLeads();
        if (leads != null && !leads.isEmpty()) {
            leads.removeIf(thisLead -> thisLead.equals(lead));
        } else {
            leads = new ArrayList<>();
        }
        leads.add(lead);
        try {
            FileWriter fileWriter = new FileWriter(leadsFile, false);
            gson.toJson(leads, fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteLead(Lead lead) {
        var leads = getAllLeads();
        if (leads != null && !leads.isEmpty()) {
            leads.removeIf(thisLead -> thisLead.equals(lead));
        } else {
            leads = new ArrayList<>();
        }
        try {
            FileWriter fileWriter = new FileWriter(leadsFile, false);
            gson.toJson(leads, fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Lead> getAllLeads() {
        List<Lead> leads = null;
        try {
            FileReader fileReader = new FileReader(leadsFile);
            JsonReader reader = new JsonReader(fileReader);
            Lead[] leadsArray = gson.fromJson(reader, Lead[].class);
            leads = leadsArray != null ? new ArrayList<>(Arrays.asList(leadsArray)) : new ArrayList<>();
            reader.close();
            fileReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return leads;
    }

    @Override
    public int getMaxLeadId() {
        var leads = getAllLeads();
        if(leads.isEmpty()) {
            return ID_START;
        }else{
            int maxId = ID_START;
            for (Lead lead : leads) {
                if(maxId < lead.getId()) maxId = lead.getId();
            }
            return maxId + 1;
        }
    }

    @Override
    public void saveAccount(Account account) {
        List<Account> accounts = getAllAccounts();
        if (accounts != null && !accounts.isEmpty()) {
            accounts.removeIf(thisAccount -> thisAccount.equals(account));
        } else {
            accounts = new ArrayList<>();
        }
        accounts.add(account);
        try {
            FileWriter fileWriter = new FileWriter(accountsFile, false);
            gson.toJson(accounts, fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Account> getAllAccounts() {
        List<Account> accounts = null;
        try {
            FileReader fileReader = new FileReader(accountsFile);
            JsonReader reader = new JsonReader(fileReader);
            Account[] accountsArray = gson.fromJson(reader, Account[].class);
            accounts = accountsArray != null ?  new ArrayList<>(Arrays.asList(accountsArray)) : new ArrayList<>();
            reader.close();
            fileReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return accounts;
    }

    @Override
    public int getMaxAccountId() {
        var accounts = getAllAccounts();
        if(accounts.isEmpty()) {
            return ID_START;
        }else{
            int maxId = ID_START;
            for (Account account : accounts) {
                if(maxId < account.getId()) maxId = account.getId();
            }
            return maxId + 1;
        }
    }

    @Override
    public int getMaxOpportunityId() {
        var accounts = getAllAccounts();
        if(accounts.isEmpty()) {
            return ID_START;
        }else{
            int maxId = ID_START;
            for (Account account : accounts) {
                for (Opportunity opportunity : account.getOpportunityList()) {
                    if(maxId < opportunity.getId()) maxId = opportunity.getId();
                }
            }
            return maxId + 1;
        }
    }

    @Override
    public int getMaxContactId() {
        var accounts = getAllAccounts();
        if(accounts.isEmpty()) {
            return ID_START;
        }else{
            int maxId = ID_START;
            for (Account account : accounts) {
                for (Contact contact : account.getContactList()) {
                    if(maxId < contact.getId()) maxId = contact.getId();
                }
            }
            return maxId + 1;
        }
    }

}
