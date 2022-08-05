package com.ironhack.data.datasources;

import com.ironhack.domain.Account;
import com.ironhack.domain.Lead;

import java.util.List;

public interface Datasource {
    // Json de Leads
    void saveLead(Lead lead);

    void deleteLead(Lead lead);

    List<Lead> getAllLeads();

    void deleteAllLeads();

    int getMaxLeadId();

    // Json de Accounts
    void saveAccount(Account account);

    List<Account> getAllAccounts();

    void deleteAllAccounts();

    int getMaxAccountId();

    int getMaxOpportunityId();

    int getMaxContactId();
}
