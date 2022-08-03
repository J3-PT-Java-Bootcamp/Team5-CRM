package com.ironhack.ui;

import com.ironhack.business_logic.LeadService;
import com.ironhack.business_logic.OpportunityService;
import com.ironhack.business_logic.exceptions.EmptyException;
import com.ironhack.data.exceptions.DataNotFoundException;
import com.ironhack.domain.Lead;
import com.ironhack.domain.enums.Industry;
import com.ironhack.domain.enums.Product;
import com.ironhack.domain.enums.Status;

import javax.swing.*;
import java.util.*;

public class Menu implements ConsoleOperations {

    private final LeadService leadService;
    private final OpportunityService opportunityService;

    public Menu(LeadService leadService, OpportunityService opportunityService) {
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
                    [new lead] -> to add a new Lead
                                        
                    [show leads] -> to show all leads
                                        
                    [lookup lead id] -> replace 'id' with a number to look up a lead by ID
                                        
                    [show opportunities] -> to show all available opportunities
                                        
                    [lookup opportunity] -> 'to look up an opportunity by it's ID
                                        
                    [convert id] -> replace 'id' with a number to convert a selected lead by ID into a new Opportunity          

                    [open] -> to set the opportunity status to open
                                        
                    [close-lost] -> to set the opportunity status to CLOSE / LOST
                                        
                    [close-won] -> to set the opportunity status to CLOSE / WON

                    [EXIT] - to Exit CRM
                    ===============
                                        
                    Write your COMMAND:
                                        
                                        
                    """;
            input = JOptionPane.showInputDialog(mainMenu).trim().toLowerCase();
            var inputSplit = input.split(" ");

            switch (inputSplit[0]) {
                case NEW -> newMenu(inputSplit);
                case SHOW -> showMenu(inputSplit);
                case LOOKUP -> lookupMenu(inputSplit);
                case CONVERT -> convertMenu(inputSplit);
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

    private void openMenu(String[] inputSplit) throws Exception {
        if (inputSplit.length <= 1) {
            throw new Exception();
        }
        int id = Integer.parseInt(inputSplit[1]);

        var opportunity = opportunityService.updateOpportunityStatus(id, Status.OPEN);
        JOptionPane.showMessageDialog(null, "✏️ Opportunity Status is now 'OPEN': \n" + opportunity);
    }

    private void closeLostMenu(String[] inputSplit) throws Exception {
        if (inputSplit.length <= 1) {
            throw new Exception();
        }
        int id = Integer.parseInt(inputSplit[1]);

        var opportunity = opportunityService.updateOpportunityStatus(id, Status.CLOSED_LOST);
        JOptionPane.showMessageDialog(null, "🆑 Opportunity Status is now 'CLOSE_LOST': \n" + opportunity);
    }

    private void closeWonMenu(String[] inputSplit) throws Exception {
        if (inputSplit.length <= 1) {
            throw new Exception();
        }
        int id = Integer.parseInt(inputSplit[1]);

        var opportunity = opportunityService.updateOpportunityStatus(id, Status.CLOSED_WON);
        JOptionPane.showMessageDialog(null, "✅ Opportunity Status is now 'CLOSE_WON': \n" + opportunity);
    }


    private void lookupMenu(String[] inputSplit) throws Exception {
        if (inputSplit.length <= 2) {
            throw new Exception();
        }
        int id = Integer.parseInt(inputSplit[2]);
        switch (inputSplit[1]) {
            case ConsoleOperationEntities.LEAD -> lookUpLead(id);
            case ConsoleOperationEntities.OPPORTUNITY -> lookUpOpportunity(id);
            default -> throw new Exception();
        }
    }

    private void lookUpOpportunity(int id) {
        try {
            JOptionPane.showMessageDialog(null, opportunityService.lookUpOpportunity(id));
        } catch (DataNotFoundException e) {
            JOptionPane.showMessageDialog(null, "❌ - Opportunity with ID " + id + " was not found in the Database!");
        } catch (EmptyException e) {
            JOptionPane.showMessageDialog(null, "❌ - No Opportunities in the database!");
        }
    }

    private void lookUpLead(int id) {
        try {
            JOptionPane.showMessageDialog(null, leadService.lookUpLead(id));
        } catch (EmptyException e) {
            JOptionPane.showMessageDialog(null, "❌ - No leads in Database!");
        } catch (DataNotFoundException e) {
            JOptionPane.showMessageDialog(null, "❌ - The Lead with ID " + id + " was not found in the database!");
        }
    }

    private void showMenu(String[] inputSplit) throws Exception {
        if (inputSplit.length <= 1) {
            throw new Exception();
        }
        switch (inputSplit[1]) {
            case ConsoleOperationEntities.LEADS -> showLeads();
            case ConsoleOperationEntities.OPPORTUNITIES -> showOpportunities();
            default -> JOptionPane.showMessageDialog(null, "🤔 Command not recognized, please try again");
        }
    }

    private void showLeads() {
        try {
            var leads = leadService.getAllLeads();
            String output = "Following Leads where found in the database:\n";
            output += "************************************************      \n";
            for (var lead : leads) {
                output += lead + "\n";
            }
            JOptionPane.showMessageDialog(null, output);
        } catch (EmptyException e) {
            JOptionPane.showMessageDialog(null, "❌ - No Leads in Database!");
        }
    }

    private void showOpportunities() {
        try {
            var opps = opportunityService.getAllOpportunities();
            String output = "Following Opportunities where found in the Database: \n";
            output += "********************************************************    \n";
            for (var opp : opps) {
                output += opp.toString() + "\n";
            }
            JOptionPane.showMessageDialog(null, output);
        } catch (EmptyException e) {
            JOptionPane.showMessageDialog(null, "❌ - No Opportunities in the Database!");
        }
    }

    private void convertMenu(String[] inputSplit) throws Exception {
        if (inputSplit.length <= 1) {
            throw new Exception();
        }
        int id = Integer.parseInt(inputSplit[1]);
        try {
            var leadFound = leadService.lookUpLead(id);

            var product = getProduct();
            int productQty = Integer.parseInt((String) getValues("Quantity?").get(0));
            var industry = getIndustry();
            int employees = Integer.parseInt((String) getValues("Number of employees?").get(0));
            String city = (String) getValues("City?").get(0);
            String country = (String) getValues("Country?").get(0);
            leadService.convert(id, product, productQty, industry, employees, city, country);

            JOptionPane.showMessageDialog(null, "Lead Succesfully converted");

        } catch (EmptyException e) {
            JOptionPane.showMessageDialog(null, "❌ - No leads in Database!");
        } catch (DataNotFoundException e) {
            JOptionPane.showMessageDialog(null, "❌ - The Lead with ID " + id + " was not found in the database!");
        }

    }

    private void newMenu(String[] inputSplit) throws Exception {
        if (inputSplit.length <= 1) {
            throw new Exception();
        }
        switch (inputSplit[1]) {
            case ConsoleOperationEntities.LEAD -> {
                List<Object> values = getValues("Name :\n", "Phone number : \n", "Email : \n", "Company : ");
                Lead lead = leadService.newLead((String) values.get(0), (String) values.get(1), (String) values.get(2), (String) values.get(3));
                JOptionPane.showMessageDialog(null, "Lead Successfully added: \n" + lead);
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
//        GraphicsController controller = new GraphicsController();
//        ProductsGraphics pro = new ProductsGraphics();
//
//        pro.setContro(controller);
//        controller.setProd(pro);
//
//        String product = controller.getValue();

        System.out.println(product);
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
