package com.ironhack.services;

import com.ironhack.data.AccountRepository;
import com.ironhack.data.OpportunityRepository;
import com.ironhack.data.datasources.Datasource;
import com.ironhack.data.datasources.impl.InMemoryDatasource;
import com.ironhack.data.exceptions.DataNotFoundException;
import com.ironhack.domain.Account;
import com.ironhack.domain.Opportunity;
import com.ironhack.domain.enums.Industry;
import com.ironhack.domain.enums.Product;
import com.ironhack.domain.enums.Status;
import com.ironhack.domain.exceptions.Team5CrmException;
import com.ironhack.services.exceptions.EmptyException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 *
 */
class OpportunityServiceTest {

    AccountRepository accountRepository;
    Datasource datasource;
    OpportunityRepository opportunityRepository;
    OpportunityService opportunityService;

    Opportunity opp1;
    Opportunity opp2;

    @BeforeEach
    void setUp() {
        datasource = InMemoryDatasource.getInstance();
        accountRepository = AccountRepository.getInstance(datasource);
        opportunityRepository = OpportunityRepository.getInstance(datasource);
        opportunityService = OpportunityService.getInstance(opportunityRepository);
    }

    @AfterEach
    void tearDown() {
        accountRepository.deleteAllAccounts();
    }

    @Test
    void test_getInstance() {
        var datasource = InMemoryDatasource.getInstance();
        var opportunityRepository = OpportunityRepository.getInstance(datasource);
        assertEquals(opportunityService, OpportunityService.getInstance(opportunityRepository));
    }

    @Test
    void test_getAllOpportunities() {
        addOpportunitiesToDatasource();

        Team5CrmException exception = null;
        try {
            var opportunities = opportunityService.getAllOpportunities();
            assertEquals(2, opportunities.size());
        } catch (EmptyException e) {
            exception = e;
        }
        assertNull(exception);

    }

    @Test
    void test_getAllOpportunities_shouldThrowIfNoLeadsAdded() {
        assertThrowsExactly(EmptyException.class, () -> opportunityService.getAllOpportunities());
    }

    @Test
    void test_lookUpOpportunity() {
        addOpportunitiesToDatasource();

        Team5CrmException exception = null;
        try {
            var oppFound = opportunityService.lookUpOpportunity(opp1.getId());
            assertEquals(opp1.getId(), oppFound.getId());
            assertEquals(opp1.getDecisionMaker(), oppFound.getDecisionMaker());
            assertEquals(opp1.getProduct(), oppFound.getProduct());
            assertEquals(opp1.getQuantity(), oppFound.getQuantity());
            assertEquals(opp1.getStatus(), oppFound.getStatus());
        } catch (DataNotFoundException | EmptyException e) {
            exception = e;
        }
        assertNull(exception);
    }

    @Test
    void test_lookUpOpportunity_shouldThrowIfNoLeadsAdded() {
        assertThrowsExactly(EmptyException.class, () -> opportunityService.lookUpOpportunity(5));
    }

    @Test
    void test_updateOpportunityStatus() {
        addOpportunitiesToDatasource();

        Team5CrmException exception = null;
        try {
            var oppUpdated = opportunityService.updateOpportunityStatus(opp1.getId(), Status.CLOSED_WON);
            assertEquals(opp1.getId(), oppUpdated.getId());
            assertEquals(opp1.getDecisionMaker(), oppUpdated.getDecisionMaker());
            assertEquals(opp1.getProduct(), oppUpdated.getProduct());
            assertEquals(opp1.getQuantity(), oppUpdated.getQuantity());
            assertEquals(Status.CLOSED_WON, oppUpdated.getStatus());
        } catch (DataNotFoundException e) {
            exception = e;
        }
        assertNull(exception);

    }

    private void addOpportunitiesToDatasource() {
        var product = Product.HYBRID;
        var prodQty = 5;

        opp1 = new Opportunity(opportunityRepository.getMaxOpportunityId(), null, Status.OPEN, product, prodQty);

        var industry = Industry.MANUFACTURING;
        var emp = 60;
        var city = "BCN";
        var country = "Spain";
        var account1 = new Account(accountRepository.getMaxAccountId(), industry, emp, city, country, null,
                List.of(opp1));
        accountRepository.saveAccount(account1);

        product = Product.BOX;
        prodQty = 7;

        opp2 = new Opportunity(opportunityRepository.getMaxOpportunityId(), null, Status.OPEN, product, prodQty);

        industry = Industry.PRODUCE;
        emp = 90;
        city = "MAD";
        country = "Spain";

        var account2 = new Account(accountRepository.getMaxAccountId(), industry, emp, city, country, null,
                List.of(opp2));
        accountRepository.saveAccount(account2);
    }
}
