package com.ironhack.services;

import com.ironhack.services.exceptions.EmptyException;
import com.ironhack.data.OpportunityRepository;
import com.ironhack.data.exceptions.DataNotFoundException;
import com.ironhack.domain.Opportunity;
import com.ironhack.domain.enums.Status;

import java.util.List;

public class OpportunityService {

    private static OpportunityService instance;
    private final OpportunityRepository opportunityRepository;

    //* CONSTRUCTORS
    //**********************************************
    private OpportunityService(OpportunityRepository opportunityRepository) {
        this.opportunityRepository = opportunityRepository;
    }

    public static OpportunityService getInstance(OpportunityRepository opportunityRepository) {
        if (instance == null) {
            instance = new OpportunityService(opportunityRepository);
        }
        return instance;
    }

    //* METHODS
    //**********************************************

    /**
     * shows all Opportunities stored in the database or gives information if there are no Opportunities in the Database
     */
    public List<Opportunity> getAllOpportunities() throws EmptyException {
        var opportunities = opportunityRepository.getAllOpportunities();
        if (opportunities.size() == 0) {
            throw new EmptyException();
        } else {
            return opportunities;
        }
    }

    /**
     * shows requested opportunity by ID or gives information if the Opportunity doesn't exist in the Database
     */
    public Opportunity lookUpOpportunity(int id) throws DataNotFoundException, EmptyException {
        if (opportunityRepository.getAllOpportunities().size() == 0) {
            throw new EmptyException();
        } else {
            return opportunityRepository.findById(id);
        }
    }

    //* METHODS TO CHANGE THE OPPORTUNITIES STATUS
    //**********************************************

    public Opportunity updateOpportunityStatus(int id, Status status) throws DataNotFoundException {
        var opp = opportunityRepository.findById(id);
        opp.setStatus(status);
        return opportunityRepository.updateOpportunity(opp);
    }

}
