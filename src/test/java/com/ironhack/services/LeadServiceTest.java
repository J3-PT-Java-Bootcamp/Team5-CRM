package com.ironhack.services;

import com.ironhack.data.AccountRepository;
import com.ironhack.data.ContactRepository;
import com.ironhack.data.LeadRepository;
import com.ironhack.data.OpportunityRepository;
import com.ironhack.data.datasources.Datasource;
import com.ironhack.data.datasources.impl.InMemoryDatasource;
import com.ironhack.data.exceptions.DataNotFoundException;
import com.ironhack.domain.Lead;
import com.ironhack.domain.enums.Industry;
import com.ironhack.domain.enums.Product;
import com.ironhack.domain.enums.Status;
import com.ironhack.domain.exceptions.Team5CrmException;
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

    Lead lead1;
    Lead lead2;

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
        var leadCreated = leadService.newLead(lead.getName(), lead.getPhoneNumber(), lead.getEmail(),
                lead.getCompanyName());
        assertEquals(lead.getId(), leadCreated.getId());
        assertEquals(lead.getName(), leadCreated.getName());
        assertEquals(lead.getPhoneNumber(), leadCreated.getPhoneNumber());
        assertEquals(lead.getEmail(), leadCreated.getEmail());
        assertEquals(lead.getCompanyName(), leadCreated.getCompanyName());
    }

    @Test
    void test_convert() {
        addLeadsToDatasource();
        var product = Product.HYBRID;
        var prodQty = 5;
        var industry = Industry.MANUFACTURING;
        var emp = 60;
        var city = "BCN";
        var country = "Spain";

        Team5CrmException exception = null;
        try {
            var account = leadService.convert(this.lead1.getId(), product, prodQty, industry, emp, city, country);
            var oppCreated = account.getOpportunityList().get(0);
            assertNotNull(oppCreated);
            assertNotNull(oppCreated.getDecisionMaker());
            assertEquals(this.lead1.getName(), oppCreated.getDecisionMaker().getName());
            assertEquals(this.lead1.getPhoneNumber(), oppCreated.getDecisionMaker().getPhone());
            assertEquals(this.lead1.getEmail(), oppCreated.getDecisionMaker().getEmail());
            assertEquals(account.getContactList().get(0), oppCreated.getDecisionMaker());
            assertEquals(product, oppCreated.getProduct());
            assertEquals(prodQty, oppCreated.getQuantity());
            assertEquals(Status.OPEN, oppCreated.getStatus());
            assertEquals(industry, account.getIndustry());
            assertEquals(emp, account.getEmployeesCount());
            assertEquals(city, account.getCity());
            assertEquals(country, account.getCountry());
        } catch (DataNotFoundException e) {
            exception = e;
        }
        assertNull(exception);
    }

    @Test
    void test_getAllLeads() {
        addLeadsToDatasource();

        Team5CrmException exception = null;
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

        addLeadsToDatasource();

        Team5CrmException exception = null;
        try {
            var leadFound = leadService.lookUpLead(this.lead2.getId());
            assertEquals(this.lead2.getId(), leadFound.getId());
            assertEquals(this.lead2.getName(), leadFound.getName());
            assertEquals(this.lead2.getPhoneNumber(), leadFound.getPhoneNumber());
            assertEquals(this.lead2.getEmail(), leadFound.getEmail());
            assertEquals(this.lead2.getCompanyName(), leadFound.getCompanyName());
        } catch (EmptyException e) {
            exception = e;
        } catch (DataNotFoundException e) {
            exception = e;
        }
        assertNull(exception);
    }

    private void addLeadsToDatasource() {
        lead1 = leadService.newLead("lead 1", "111111111", "lead1@gmail.com", "company 1");
        lead2 = leadService.newLead("lead 2", "222222222", "lead2@hotmail.com", "company inc 2");
    }
}
