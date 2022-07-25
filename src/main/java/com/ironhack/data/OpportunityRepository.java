package com.ironhack.data;

import com.ironhack.data.datasources.Datasource;
import com.ironhack.data.exceptions.DataNotFoundException;
import com.ironhack.domain.Account;
import com.ironhack.domain.Opportunity;

import java.util.ArrayList;
import java.util.List;


/**
 * This Repository is mainly a helper, so you don't have to query every account to search and update the opportunities in a very difficult way
 * It will be used for everything you need to do with the opportunities
 */
public class OpportunityRepository  {

    private static OpportunityRepository instance;

    private final Datasource datasource;

    private OpportunityRepository(Datasource datasource) {
        this.datasource = datasource;
    }

    public static OpportunityRepository getInstance(Datasource datasource) {
        if(instance == null) {
            instance = new OpportunityRepository(datasource);
        }
        return instance;
    }

    public List<Opportunity> getAllOpportunities() {
        var opportunities = new ArrayList<Opportunity>();

        for (Account account : datasource.getAllAccounts()) {
            opportunities.addAll(account.getOpportunityList());
        }

        return opportunities;
    }

    public void updateOpportunity(Opportunity opportunity) throws DataNotFoundException {
        var accounts = datasource.getAllAccounts();

        Account foundAccount = null;
        for (Account account : accounts) {
            if(account.getOpportunityList().contains(opportunity)) {
                foundAccount = account;
                break;
            }
        }

        if(foundAccount == null) {
            throw new DataNotFoundException("Cannot update opportunity with id %s as it was not found on the database".formatted(opportunity.getId()));
        }else{
            foundAccount.getOpportunityList().remove(opportunity);
            foundAccount.getOpportunityList().add(opportunity);
            datasource.saveAccount(foundAccount);
        }

    }
}
