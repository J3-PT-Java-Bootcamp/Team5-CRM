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

        // Setup datasources

        // Uncomment this next line to use json instead of memory
        // Datasource datasource = InMemoryDatasource.getInstance();

        Datasource datasource = JsonDatasource.getInstance();

        // Setup repositories
        OpportunityRepository opportunityRepository = OpportunityRepository.getInstance(datasource);
        AccountRepository accountRepository = AccountRepository.getInstance(datasource);
        LeadRepository leadRepository = LeadRepository.getInstance(datasource);

        // Setup services
        OpportunityService opportunityService = OpportunityService.getInstance(opportunityRepository);
        ContactRepository contactRepository = ContactRepository.getInstance(datasource);
        LeadService leadService = LeadService.getInstance(leadRepository, contactRepository, accountRepository,
                opportunityRepository);

        // Setup UI
        Menu menu = new Menu(leadService, opportunityService);

        // Start UI
        menu.main();
    }
}
