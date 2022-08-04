package com.ironhack.services;

import com.ironhack.data.AccountRepository;
import com.ironhack.data.OpportunityRepository;
import com.ironhack.data.datasources.Datasource;
import com.ironhack.data.datasources.impl.InMemoryDatasource;
import com.ironhack.domain.Opportunity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 */
class OpportunityServiceTest {

    AccountRepository accountRepository;
    Datasource datasource;
    OpportunityRepository opportunityRepository;
    OpportunityService opportunityService;

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
    }

    @Test
    void test_lookUpOpportunity() {
    }

    @Test
    void test_updateOpportunityStatus() {
    }
}