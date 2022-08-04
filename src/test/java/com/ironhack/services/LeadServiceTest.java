package com.ironhack.services;

import com.ironhack.data.AccountRepository;
import com.ironhack.data.ContactRepository;
import com.ironhack.data.LeadRepository;
import com.ironhack.data.OpportunityRepository;
import com.ironhack.data.datasources.Datasource;
import com.ironhack.data.datasources.impl.InMemoryDatasource;
import com.ironhack.data.exceptions.DataNotFoundException;
import com.ironhack.domain.Lead;
import com.ironhack.services.exceptions.EmptyException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeadServiceTest {

    Datasource datasource = InMemoryDatasource.getInstance();
    LeadRepository leadRepo = LeadRepository.getInstance(datasource);
    ContactRepository contactRepo = ContactRepository.getInstance(datasource);
    AccountRepository accountRepo = AccountRepository.getInstance(datasource);
    OpportunityRepository opportunityRepo = OpportunityRepository.getInstance(datasource);
    LeadService leadService;

    @BeforeEach
    void setUp() {
        leadService = LeadService.getInstance(leadRepo, contactRepo, accountRepo, opportunityRepo);
    }

    @AfterEach
    void tearDown() {
        leadRepo.deleteAllLeads();
        accountRepo.deleteAllAccounts();
    }

    @Test
    void test_getInstance() {
        var datasource = InMemoryDatasource.getInstance();
        var leadRepo = LeadRepository.getInstance(datasource);
        var contactRepo = ContactRepository.getInstance(datasource);
        var accountRepo = AccountRepository.getInstance(datasource);
        var opportunityRepo = OpportunityRepository.getInstance(datasource);
        assertEquals(leadService, LeadService.getInstance(leadRepo, contactRepo, accountRepo, opportunityRepo));
    }

    @Test
    void test_newLead() {
        var lead = new Lead(leadRepo.maxLeadId(), "test", "666666666", "test@gmail.com", "company");
        var leadCreated = leadService.newLead(lead.getName(), lead.getPhoneNumber(), lead.getEmail(), lead.getCompanyName());
        assertEquals(lead.getId(), leadCreated.getId());
        assertEquals(lead.getName(), leadCreated.getName());
        assertEquals(lead.getPhoneNumber(), leadCreated.getPhoneNumber());
        assertEquals(lead.getEmail(), leadCreated.getEmail());
        assertEquals(lead.getCompanyName(), leadCreated.getCompanyName());
    }

    @Test
    void test_convert() {
    }

    @Test
    void test_getAllLeads() {
        var leadCreated1 = leadService.newLead("lead 1", "111111111", "lead1@gmail.com", "company 1");
        var leadCreated2 = leadService.newLead("lead 2", "222222222", "lead2@hotmail.com", "company inc 2");

        EmptyException exception = null;
        try {
            var leads = leadService.getAllLeads();
            assertEquals(2, leads.size());
        } catch (EmptyException e) {
            exception = e;
        }
        assertNull(exception);

    }

    @Test
    void test_lookUpLead() {
        var leadCreated1 = leadService.newLead("lead 1", "111111111", "lead1@gmail.com", "company 1");
        var leadCreated2 = leadService.newLead("lead 2", "222222222", "lead2@hotmail.com", "company inc 2");

        EmptyException emptyException = null;
        DataNotFoundException dataNotFoundException = null;
        try {
            var leadFound = leadService.lookUpLead(leadCreated2.getId());
            assertEquals(leadCreated2.getId(), leadFound.getId());
            assertEquals(leadCreated2.getName(), leadFound.getName());
            assertEquals(leadCreated2.getPhoneNumber(), leadFound.getPhoneNumber());
            assertEquals(leadCreated2.getEmail(), leadFound.getEmail());
            assertEquals(leadCreated2.getCompanyName(), leadFound.getCompanyName());
        } catch (EmptyException e) {
            emptyException = e;
        } catch (DataNotFoundException e) {
            dataNotFoundException = e;
        }
        assertNull(emptyException);
        assertNull(dataNotFoundException);
    }
}