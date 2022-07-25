package com.ironhack.business_logic;

import com.ironhack.data.LeadRepository;
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
        JOptionPane.showMessageDialog(null, leadRepository.getAllLeads());
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
    public void convert(int id) {
        // TOOD
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
