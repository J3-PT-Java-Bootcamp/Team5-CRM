package com.ironhack;

import com.ironhack.business_logic.AccountService;
import com.ironhack.business_logic.LeadService;
import com.ironhack.business_logic.OpportunityService;
import com.ironhack.data.AccountRepository;
import com.ironhack.data.LeadRepository;
import com.ironhack.data.OpportunityRepository;
import com.ironhack.data.datasources.Datasource;
import com.ironhack.data.datasources.impl.InMemoryDatasource;
import com.ironhack.data.datasources.impl.JsonDatasource;
import com.ironhack.ui.MenuController;
import com.ironhack.ui.Menu;

public class Main {

    public static void main(String[] args) throws Exception {

//        Datasource datasource = InMemoryDatasource.getInstance();

        // Uncomment this line to use json instead of memory
        Datasource datasource = JsonDatasource.getInstance();
        OpportunityRepository opportunityRepository = OpportunityRepository.getInstance(datasource);
        OpportunityService opportunityService = OpportunityService.getInstance(opportunityRepository);
        AccountRepository accountRepository = AccountRepository.getInstance(datasource);
        AccountService accountService = AccountService.getInstance(accountRepository);
        LeadRepository leadRepository = LeadRepository.getInstance(datasource);
        LeadService leadService = LeadService.getInstance(leadRepository);


        MenuController menuController = new MenuController(accountService, leadService, opportunityService);
        Menu menu = new Menu(menuController);
        menu.main();
    }

}
