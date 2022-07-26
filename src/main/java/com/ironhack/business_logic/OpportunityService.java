package com.ironhack.business_logic;

import com.ironhack.data.OpportunityRepository;
import com.ironhack.data.datasources.impl.InMemoryDatasource;
import com.ironhack.data.exceptions.DataNotFoundException;
import com.ironhack.domain.Contact;
import com.ironhack.domain.Lead;
import com.ironhack.domain.Opportunity;
import com.ironhack.domain.enums.Product;
import com.ironhack.domain.enums.Status;

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
     * @param// opportunity
     * @throws DataNotFoundException
     */


    public Opportunity newOpportunity(Contact decisionMaker, Status status, Product product, int quantity) throws DataNotFoundException {
        var maxId = InMemoryDatasource.getInstance().getMaxOpportunityId();
        Opportunity opportunity = new Opportunity(maxId, decisionMaker, status, product, quantity);
        JOptionPane.showMessageDialog(null, "Opportunity added");
        return opportunity;
    }

    public void updateOpportunity(Opportunity opportunity) throws DataNotFoundException {
        opportunityRepository.updateOpportunity(opportunity);
    }


    /**
     * Method that shows the current opporunities stored
     */
    public void showOpportunities() {
        if(opportunityRepository.getAllOpportunities().size() != 0){
            for(var opp : opportunityRepository.getAllOpportunities()) {
                JOptionPane.showMessageDialog(null, "Opportunities in the storage \n\n ID : " + opp.getId() + " DecisionMaker : " + opp.getDecisionMaker() + " Status : " + opp.getStatus() + " Product : " + opp.getProduct() + " Quantity : " + opp.getQuantity());
            }
        } else {
            JOptionPane.showMessageDialog(null, "-> None.");// check the print on the pane in the CRM Method
        }
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

    public void openOpportunity(int id) {
        for(var opp : opportunityRepository.getAllOpportunities()){
            if(opp.getId() == id){
                opp.setStatus(Status.OPEN);
                JOptionPane.showMessageDialog(null, "The Status of Opportunity change to 'OPEN', the new opportunity values are : " + opp.toString());
            }
        }
    }

    public void closeLostOpportunity(int id) {
        for(var opp : opportunityRepository.getAllOpportunities()){
            if(opp.getId() == id){
                opp.setStatus(Status.CLOSED_LOST);
                JOptionPane.showMessageDialog(null, "The Status of Opportunity change to 'CLOSE-LOST', the new opportunity values are : " + opp.toString());
            }
        }
    }

    public void closeWonOpportunity(int id) {
        for(var opp : opportunityRepository.getAllOpportunities()){
            if(opp.getId() == id){
                opp.setStatus(Status.CLOSED_WON);
                JOptionPane.showMessageDialog(null, "The Status of Opportunity change to 'CLOSE-WON', the new opportunity values are : " + opp.toString());
            }
        }
    }

}
