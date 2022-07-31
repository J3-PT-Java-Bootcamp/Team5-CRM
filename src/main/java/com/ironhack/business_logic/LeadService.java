package com.ironhack.business_logic;

import com.ironhack.data.AccountRepository;
import com.ironhack.data.ContactRepository;
import com.ironhack.data.LeadRepository;
import com.ironhack.data.OpportunityRepository;
import com.ironhack.data.datasources.impl.InMemoryDatasource;
import com.ironhack.domain.Account;
import com.ironhack.domain.Contact;
import com.ironhack.domain.Lead;
import com.ironhack.domain.Opportunity;
import com.ironhack.domain.enums.Industry;
import com.ironhack.domain.enums.Product;
import com.ironhack.domain.enums.Status;

import javax.swing.*;
import java.util.List;

public class LeadService {

    private static LeadService instance;

    private final LeadRepository leadRepository;
    private final ContactRepository contactRepository;

    private final AccountRepository accountRepository;
    private final OpportunityRepository opportunityRepository;

    private LeadService(LeadRepository leadRepository, ContactRepository contactRepository, AccountRepository accountRepository, OpportunityRepository opportunityRepository) {
        this.leadRepository = leadRepository;
        this.contactRepository = contactRepository;
        this.accountRepository = accountRepository;
        this.opportunityRepository = opportunityRepository;
    }

    public static LeadService getInstance(LeadRepository leadRepository, ContactRepository contactRepository, AccountRepository accountRepository, OpportunityRepository opportunityRepository) {
        if (instance == null) {
            instance = new LeadService(leadRepository, contactRepository, accountRepository, opportunityRepository);
        }
        return instance;
    }

    public void showLeads() {
        int x = 1;
        for(var lead : leadRepository.getAllLeads()){
            JOptionPane.showMessageDialog(null, "Leads Storage : \n\n Lead Number : " + x + "\n\nID : "+ lead.getId() + " Name : " + lead.getName() + " Phonenumber : " + lead.getPhoneNumber() +  " Email : " + lead.getEmail() + " Company : " + lead.getCompanyName());
        }
    }

    public Lead newLead(String name, String phone, String email, String company) {
        var maxId = leadRepository.maxLeadId();
        Lead newLead = new Lead(maxId, name, phone, email, company);

        return leadRepository.saveLead(newLead);
    }

    /**
     * Method that converts a lead into an opportunity, a contact and both into an account
     * TODO: maybe this service will require the account repository, cause even if it creates an opportunity and a contact, both are stored inside an account
     * @param id
     */
    public void convert(int id, Product product, int productQuantity, Industry industry, int employees, String city, String country) {
        var lead = leadRepository.findById(id);
        if(lead == null) {

            JOptionPane.showMessageDialog(null, "Not lead existences with that ID");
        }else{
            int maxIdAccount = InMemoryDatasource.getInstance().getMaxAccountId();
            var contactToSave = new Contact(contactRepository.getMaxContactId(), lead.getName(), lead.getPhoneNumber(), lead.getCompanyName());
            var contactList = List.of(contactToSave);
            var opportunityList = List.of(new Opportunity(opportunityRepository.getMaxOpportunityId() ,contactToSave, Status.OPEN, product, productQuantity));
            var accountToSave = new Account(maxIdAccount, industry, employees, city, country, contactList, opportunityList);
            accountRepository.saveAccount(accountToSave);
        }

    }

    public void lookUpLead(int id) {
        for (Lead lead : leadRepository.getAllLeads()) {
            if (lead.getId() == id) {
                JOptionPane.showMessageDialog(null, """
                        Opportunity with ID (" + lead.getId() + ") was looked up:
                        %s
                        """.formatted(lead));
            }
        }
    }

}
