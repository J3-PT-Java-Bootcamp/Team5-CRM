package com.ironhack.services;

import com.ironhack.services.exceptions.EmptyException;
import com.ironhack.data.AccountRepository;
import com.ironhack.data.ContactRepository;
import com.ironhack.data.LeadRepository;
import com.ironhack.data.OpportunityRepository;
import com.ironhack.data.exceptions.DataNotFoundException;
import com.ironhack.domain.Account;
import com.ironhack.domain.Contact;
import com.ironhack.domain.Lead;
import com.ironhack.domain.Opportunity;
import com.ironhack.domain.enums.Industry;
import com.ironhack.domain.enums.Product;
import com.ironhack.domain.enums.Status;

import java.util.List;

public class LeadService {

    private static LeadService instance;
    private final LeadRepository leadRepository;
    private final ContactRepository contactRepository;
    private final AccountRepository accountRepository;
    private final OpportunityRepository opportunityRepository;

    // * CONSTRUCTORS
    // **********************************************
    private LeadService(LeadRepository leadRepository, ContactRepository contactRepository,
                        AccountRepository accountRepository, OpportunityRepository opportunityRepository) {
        this.leadRepository = leadRepository;
        this.contactRepository = contactRepository;
        this.accountRepository = accountRepository;
        this.opportunityRepository = opportunityRepository;
    }

    public static LeadService getInstance(LeadRepository leadRepository, ContactRepository contactRepository,
                                          AccountRepository accountRepository, OpportunityRepository opportunityRepository) {
        if (instance == null) {
            instance = new LeadService(leadRepository, contactRepository, accountRepository, opportunityRepository);
        }
        return instance;
    }

    public Lead newLead(String name, String phone, String email, String company) {
        var maxId = leadRepository.maxLeadId();
        Lead newLead = new Lead(maxId, name, phone, email, company);

        return leadRepository.saveLead(newLead);
    }

    /**
     * Method that converts a lead into an opportunity, a contact and both into an
     * account
     *
     * @param id
     */
    public void convert(int id, Product product, int productQuantity, Industry industry, int employees, String city,
                        String country) throws DataNotFoundException {
        var lead = leadRepository.findById(id);
        int maxIdAccount = accountRepository.getMaxAccountId();
        var contactToSave = new Contact(contactRepository.getMaxContactId(), lead.getName(), lead.getPhoneNumber(),
                lead.getEmail());
        var contactList = List.of(contactToSave);
        var opportunityList = List.of(new Opportunity(opportunityRepository.getMaxOpportunityId(), contactToSave,
                Status.OPEN, product, productQuantity));
        var accountToSave = new Account(maxIdAccount, industry, employees, city, country, contactList, opportunityList);
        accountRepository.saveAccount(accountToSave);
        leadRepository.deleteLead(id);
    }

    /**
     * Method that shows all available Leads in the database
     */
    public List<Lead> getAllLeads() throws EmptyException {
        var leads = leadRepository.getAllLeads();
        if (leads.size() != 0) {
            return leads;
        } else {
            throw new EmptyException();
        }
    }

    /**
     * Method that shows the requested Lead by ID
     */
    public Lead lookUpLead(int id) throws EmptyException, DataNotFoundException {
        if (leadRepository.getAllLeads().size() != 0) {
            return leadRepository.findById(id);
        } else {
            throw new EmptyException();
        }
    }

}
