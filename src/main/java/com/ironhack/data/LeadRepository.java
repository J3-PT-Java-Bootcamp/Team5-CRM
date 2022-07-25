package com.ironhack.data;

import com.ironhack.data.datasources.Datasource;
import com.ironhack.domain.Lead;

import java.util.List;

public class LeadRepository {

    private static LeadRepository instance;
    private final Datasource datasource;

    private LeadRepository(Datasource datasource) {
        this.datasource = datasource;
    }

    public static LeadRepository getInstance(Datasource datasource) {
        if(instance == null) {
            instance = new LeadRepository(datasource);
        }
        return instance;
    }

    public int maxLeadId() {
        return datasource.getMaxLeadId();
    }

    public Lead addLead(Lead lead) {
        datasource.saveLead(lead);
        return lead;
    }


    public List<Lead> getAllLeads() {
        return datasource.getAllLeads();
    }
}
