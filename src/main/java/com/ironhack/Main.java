package com.ironhack;

import com.formdev.flatlaf.FlatLightLaf;
import com.ironhack.services.LeadService;
import com.ironhack.services.OpportunityService;
import com.ironhack.data.AccountRepository;
import com.ironhack.data.ContactRepository;
import com.ironhack.data.LeadRepository;
import com.ironhack.data.OpportunityRepository;
import com.ironhack.data.datasources.Datasource;
import com.ironhack.data.datasources.impl.JsonDatasource;
import com.ironhack.ui.Menu;

public class Main {

    public static void main(String[] args) throws Exception {

        FlatLightLaf.setup();

//      Datasource datasource = InMemoryDatasource.getInstance();
//      Uncomment this line to use json instead of memory

        Datasource datasource = JsonDatasource.getInstance();
        OpportunityRepository opportunityRepository = OpportunityRepository.getInstance(datasource);
        OpportunityService opportunityService = OpportunityService.getInstance(opportunityRepository);
        AccountRepository accountRepository = AccountRepository.getInstance(datasource);
        LeadRepository leadRepository = LeadRepository.getInstance(datasource);
        ContactRepository contactRepository = ContactRepository.getInstance(datasource);
        LeadService leadService = LeadService.getInstance(leadRepository, contactRepository, accountRepository, opportunityRepository);


        Menu menu = new Menu(leadService, opportunityService);
        menu.main();
    }
}
