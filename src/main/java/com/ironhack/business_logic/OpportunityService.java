package com.ironhack.business_logic;

import com.ironhack.data.OpportunityRepository;
import com.ironhack.data.datasources.impl.InMemoryDatasource;
import com.ironhack.data.exceptions.DataNotFoundException;
import com.ironhack.domain.Contact;
import com.ironhack.domain.Opportunity;
import com.ironhack.domain.enums.Product;
import com.ironhack.domain.enums.Status;

import javax.swing.*;

public class OpportunityService {

    private static OpportunityService instance;
    private final OpportunityRepository opportunityRepository;

    //* CONSTRUCTORS
    //**********************************************
    private OpportunityService(OpportunityRepository opportunityRepository) {
        this.opportunityRepository = opportunityRepository;
    }

    public static OpportunityService getInstance(OpportunityRepository opportunityRepository) {
        if(instance == null) {
            instance = new OpportunityService(opportunityRepository);
        }
        return instance;
    }

    //* METHODS
    //**********************************************

    /** creates a new opportunity
     *  */
    public Opportunity newOpportunity(Contact decisionMaker, Status status, Product product, int quantity) throws DataNotFoundException {
        var maxId = InMemoryDatasource.getInstance().getMaxOpportunityId();
        Opportunity opportunity = new Opportunity(maxId, decisionMaker, status, product, quantity);
        JOptionPane.showMessageDialog(null, "Opportunity added");
        return opportunity;
    }

    /** Method intended to be used when updating the opportunity status
     * @param// opportunity
     * @throws DataNotFoundException
     */
    public void updateOpportunity(Opportunity opportunity) throws DataNotFoundException {
        opportunityRepository.updateOpportunity(opportunity);
    }


    /** shows all Opportunities stored in the database or gives information if there are no Opportunities in the Database */
    public void showOpportunities() {
        if(opportunityRepository.getAllOpportunities().size() != 0){
            String output = "Following Opportunities where found in the Database: \n";
            output += "********************************************************    \n";
            for(var opp : opportunityRepository.getAllOpportunities()) {
               output += opp.toString()+ "\n";
            }
            JOptionPane.showMessageDialog(null,output);
        } else {
            JOptionPane.showMessageDialog(null, "❌ - No Opportunities in Database!");
        }
    }

    /** shows requested opportunity by ID or gives information if the Opportunity doesn't exist in the Database */
    public void lookUpOpportunity(int id) {
        if (opportunityRepository.getAllOpportunities().size() != 0){
            boolean found = false;
            for (Opportunity opportunity: opportunityRepository.getAllOpportunities()) {
                if (opportunity.getId() == id) {
                    JOptionPane.showMessageDialog(null, opportunity);
                    found = true;
                }
            }
            if (found != true){
                JOptionPane.showMessageDialog(null,"❌ - Opportunitiy with ID "+ id +" where not found in the Database!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "❌ - No Opportunities in Database!");
        }
    }

    //* METHODS TO CHANGE THE OPPORTUNITIES STATUS
    //**********************************************

    public void openOpportunity(int id) {
        for(var opportunity : opportunityRepository.getAllOpportunities()){
            if(opportunity.getId() == id){
                opportunity.setStatus(Status.OPEN);
                JOptionPane.showMessageDialog(null, "✏️ Opportunity Status is now 'OPEN': \n" + opportunity);
            }
        }
    }

    public void closeLostOpportunity(int id) {
        for(var opportunity : opportunityRepository.getAllOpportunities()){
            if(opportunity.getId() == id){
                opportunity.setStatus(Status.CLOSED_LOST);
                JOptionPane.showMessageDialog(null, "🆑 Opportunity Status is now 'CLOSE_LOST': \n" + opportunity);
            }
        }
    }

    public void closeWonOpportunity(int id) {
        for(var opportunity : opportunityRepository.getAllOpportunities()){
            if(opportunity.getId() == id){
                opportunity.setStatus(Status.CLOSED_WON);
                JOptionPane.showMessageDialog(null, "✅ Opportunity Status is now 'CLOSE_WON': \n" + opportunity);
            }
        }
    }
}
