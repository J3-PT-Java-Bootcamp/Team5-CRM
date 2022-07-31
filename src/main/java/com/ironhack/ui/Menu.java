package com.ironhack.ui;

import com.ironhack.business_logic.AccountService;
import com.ironhack.business_logic.LeadService;
import com.ironhack.business_logic.OpportunityService;
import com.ironhack.data.datasources.impl.InMemoryDatasource;
import com.ironhack.domain.Account;
import com.ironhack.domain.Lead;
import com.ironhack.domain.enums.Industry;
import com.ironhack.domain.enums.Product;
import com.ironhack.domain.enums.Status;

import javax.swing.*;
import java.util.*;

public class Menu implements ConsoleOperations {

    private final AccountService accountService;
    private final LeadService leadService;
    private final OpportunityService opportunityService;

    private static final Scanner scanner = new Scanner(System.in);

    public Menu(AccountService accountService, LeadService leadService, OpportunityService opportunityService) {
        this.accountService = accountService;
        this.leadService = leadService;
        this.opportunityService = opportunityService;
    }

    public void main() throws Exception {
        String input;
        do {
            var mainMenu = """
                    Welcome to CRM Manager
                    Available Operations
                                     
                    ===============
                    command -> 'new lead' for add a new Lead
                    command -> 'convert' plus the ID of Lead for convert to new Opportunity
                    command -> 'show leads' for show all leads
                    command -> 'lookup lead' plus the ID for see a lookup lead
                    command -> 'show opportunities' for show all oportunities
                    command -> 'lookup opportunity' plus the ID for see a lookup opportunity
                    command -> 'open' for changes the sales states to open
                    command -> 'close-lost' for changes the sales states to CLOSE / LOST
                    command -> 'close-won' for changes the sales states to CLOSE / WON

                    [EXIT] - Exit CRM Manager
                    ===============
                    Write your COMMAND:
                    """;
            input = JOptionPane.showInputDialog(mainMenu).trim().toLowerCase();
            var inputSplit = input.split(" ");

            switch (inputSplit[0]) {
                case NEW -> newMenu(inputSplit);
                case CONVERT -> convertMenu(inputSplit);
                case SHOW -> showMenu(inputSplit);
                case LOOKUP -> lookupMenu(inputSplit);
                case OPEN -> openMenu(inputSplit);
                case CLOSE_LOST -> closeLostMenu(inputSplit);
                case CLOSE_WON -> closeWonMenu(inputSplit);
                case "exit" -> {
                    System.out.println("Adeu");
                    System.exit(1);
                }
                default -> System.out.println("Command not recognized!");
            }
        } while (!input.equals("exit"));
    }

    private void closeLostMenu(String[] inputSplit) throws Exception {
        if (inputSplit.length <= 1) {
            throw new Exception();
        }
        int id = Integer.parseInt(inputSplit[1]);

        opportunityService.closeLostOpportunity(id);
    }

    private void closeWonMenu(String[] inputSplit) throws Exception {
        if (inputSplit.length <= 1) {
            throw new Exception();
        }
        int id = Integer.parseInt(inputSplit[1]);

        opportunityService.closeWonOpportunity(id);
    }

    private void openMenu(String[] inputSplit) throws Exception {
        if (inputSplit.length <= 1) {
            throw new Exception();
        }
        int id = Integer.parseInt(inputSplit[1]);

        opportunityService.openOpportunity(id);
    }

    private void lookupMenu(String[] inputSplit) throws Exception {
        if (inputSplit.length <= 2) {
            throw new Exception();
        }
        int id = Integer.parseInt(inputSplit[2]);
        switch (inputSplit[1]) {
            case ConsoleOperationEntities.LEAD -> leadService.lookUpLead(id);
            case ConsoleOperationEntities.OPPORTUNITY -> opportunityService.lookUpOpportunity(id);
            default -> throw new Exception();
        }
    }

    private void showMenu(String[] inputSplit) throws Exception {
        if (inputSplit.length <= 1) {
            throw new Exception();
        }
        switch (inputSplit[1]) {
            case ConsoleOperationEntities.LEADS -> leadService.showLeads();
            case ConsoleOperationEntities.OPPORTUNITIES -> opportunityService.showOpportunities();
            default -> throw new Exception();
        }
    }

    private void convertMenu(String[] inputSplit) throws Exception {
        if (inputSplit.length <= 1) {
            throw new Exception();
        }
        int id = Integer.parseInt(inputSplit[1]);

        var product = getProduct();
        int productQty = Integer.parseInt((String)getValues("Quantity?").get(0));
        var industry = getIndustry();
        int employees = Integer.parseInt((String)getValues("Number of employees?").get(0));
        String city = (String)getValues("City?").get(0);
        String country = (String)getValues("Country?").get(0);
        leadService.convert(id, product, productQty, industry, employees, city, country);

        JOptionPane.showMessageDialog(null, "Lead Succesfully converted");


    }

    private void newMenu(String[] inputSplit) throws Exception {
        if (inputSplit.length <= 1) {
            throw new Exception();
        }
        switch (inputSplit[1]) {
            case ConsoleOperationEntities.LEAD -> {
                List<Object> values = getValues("Name :\n", "Phone number : \n", "Email : \n", "Company : ");
                Lead lead = leadService.newLead((String) values.get(0), (String) values.get(1), (String) values.get(2), (String) values.get(3));
                JOptionPane.showMessageDialog(null, "Lead Succesfully added \n\nLead Register : \n\nID : " + lead.getId() + "\nName : " + lead.getName() + "\nPhonenumber : " + lead.getPhoneNumber() + "\nEmail :" + lead.getEmail() + "\nCompany :" + lead.getCompanyName());
            }
            default -> throw new Exception();
        }
    }

    //******************* USING VARARGS FOR REUSING METHODS
    public static List<Object> getValues(Object... values) throws Exception {
        List<Object> value = new ArrayList<>();
        for (var i : values) {
            try {  // --> dont work, check
                value.add(JOptionPane.showInputDialog(i).trim().toLowerCase());
            } catch (InputMismatchException e) {
                throw new Exception("1");
            }
        }
        return value;
    }

    // ***************************   ENUMS
    public Status getStats() {
        String stats;

        String message = "Status client commands  -> \n" + "'open' for 'OPEN'\n" + "'close-lost' for 'CLOSE/LOSE'\n" + "'close-won' for 'CLOSE/WON'\n" + "'exit' for abort";
        stats = JOptionPane.showInputDialog(message).trim().toLowerCase();

        switch (stats) {
            case CLOSE_LOST -> {
                return Status.CLOSED_LOST;
            }
            case OPEN -> {
                return Status.OPEN;
            }
            case CLOSE_WON -> {
                return Status.CLOSED_WON;
            }
            default -> JOptionPane.showMessageDialog(null, "Only a real status");
        }
        return null;
    }

    public Product getProduct() {
        String product;
        String message = "Stock Products : \n\n 'Hybrid' \n 'Flatbed' \n 'Box' \n";

        product = JOptionPane.showInputDialog(message).trim().toLowerCase();
        switch (product) {
            case "hybrid" -> {
                return Product.HYBRID;
            }
            case "flatbed" -> {
                return Product.FLATBED;
            }
            case "box" -> {
                return Product.BOX;
            }
            default -> JOptionPane.showMessageDialog(null, "Only existents products");
        }
        return null;
    }

    public Industry getIndustry() {
        String industry;
        String message = "Type of Industry : \n\n 'Produce'\n 'Ecommerce'\n 'Manufacturing'\n 'Medical'\n 'Other'\n";
        industry = JOptionPane.showInputDialog(message).trim().toLowerCase();

        switch (industry) {
            case "produce" -> {
                return Industry.PRODUCE;
            }
            case "ecommerce" -> {
                return Industry.ECOMMERCE;
            }
            case "manufacturing" -> {
                return Industry.MANUFACTURING;
            }
            case "medical" -> {
                return Industry.MEDICAL;
            }
            case "other" -> {
                return Industry.OTHER;
            }
            default -> JOptionPane.showInputDialog("Only existents Industry options");

        }
        return null;

    }


}
