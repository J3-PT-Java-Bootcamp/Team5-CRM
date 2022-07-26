package com.ironhack.business_logic;

import com.ironhack.data.OpportunityRepository;
import com.ironhack.data.exceptions.DataNotFoundException;
import com.ironhack.domain.Opportunity;

import javax.swing.*;

public class OpportunityService {

    private static OpportunityService instance;

    private final OpportunityRepository opportunityRepository;

    private OpportunityService(OpportunityRepository opportunityRepository) {
        this.opportunityRepository = opportunityRepository;
    }

    public static OpportunityService getInstance(OpportunityRepository opportunityRepository) {
        if(instance == null) {
            instance = new OpportunityService(opportunityRepository);
        }
        return instance;
    }

    /**
     * Method intended to be used when updating the opportunity status
     * @param opportunity
     * @throws DataNotFoundException
     */
    public void updateOpportunity(Opportunity opportunity) throws DataNotFoundException {
        opportunityRepository.updateOpportunity(opportunity);
    }


    /**
     * Method that shows the current opporunities stored
     */
    public void showOpportunities() {

        JOptionPane.showMessageDialog(null, opportunityRepository.getAllOpportunities());// check the print on the pane in the CRM Method
//        System.out.println("+ Following Opportunities are available: ");
//        var opportunities = opportunityRepository.getAllOpportunities();
//        if (opportunities.isEmpty()) {
//            System.out.println("-> None.");
//        } else {
//            for (Opportunity opp : opportunities) {
//                System.out.println("-> " + opp.toString());
//            }
//        }

    }

    public void lookUpOpportunity(int id) {
        for (Opportunity opp : opportunityRepository.getAllOpportunities()) {
            if (opp.getId() == id) {
                JOptionPane.showMessageDialog(null, """
                        Opportunity with ID (" + opp.getId() + ") was looked up:
                        %s
                        """.formatted(opp));
            }
        }
    }

}
