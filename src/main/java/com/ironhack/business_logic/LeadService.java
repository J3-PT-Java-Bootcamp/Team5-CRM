package com.ironhack.business_logic;

import com.ironhack.data.LeadRepository;
import com.ironhack.data.datasources.impl.InMemoryDatasource;
import com.ironhack.domain.Contact;
import com.ironhack.domain.Lead;
import com.ironhack.domain.Opportunity;

import javax.swing.*;

public class LeadService {

    private static LeadService instance;

    private final LeadRepository leadRepository;

    private LeadService(LeadRepository leadRepository) {
        this.leadRepository = leadRepository;
    }

    public static LeadService getInstance(LeadRepository leadRepository) {
        if (instance == null) {
            instance = new LeadService(leadRepository);
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

        return leadRepository.addLead(newLead);
    }

    /**
     * Method that converts a lead into an opportunity, a contact and both into an account
     * TODO: maybe this service will require the account repository, cause even if it creates an opportunity and a contact, both are stored inside an account
     * @param id
     */
    public boolean convert(int id) {
        for(var lead : leadRepository.getAllLeads()) {
            if (lead.getId() == id) {
                JOptionPane.showMessageDialog(null, "New contact added to opportunity" + getContact(lead));
                return true;
            }
        }
        JOptionPane.showMessageDialog(null, "Not lead existences with that ID");
        return false;
    }

    //*** if the id existence, transform to a new Contact FOR A NEW OPPORTUNITY
    public Contact getContact(Lead lead){
        var maxId = InMemoryDatasource.getInstance().getMaxContactId(); // check if the same or not ID Lead
        return  new Contact(maxId, lead.getName(), lead.getPhoneNumber(), lead.getCompanyName());
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
