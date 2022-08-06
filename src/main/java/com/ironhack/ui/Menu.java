package com.ironhack.ui;

import com.ironhack.services.LeadService;
import com.ironhack.services.OpportunityService;
import com.ironhack.services.exceptions.EmptyException;
import com.ironhack.data.exceptions.DataNotFoundException;
import com.ironhack.domain.Lead;
import com.ironhack.domain.enums.Industry;
import com.ironhack.domain.enums.Product;
import com.ironhack.domain.enums.Status;
import com.ironhack.ui.exceptions.AbortedException;
import com.ironhack.ui.exceptions.WrongInputException;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Menu implements ConsoleOperations {

    private final LeadService leadService;
    private final OpportunityService opportunityService;
    static ImageIcon teamIcon = new ImageIcon("Icons/team5logo.png");

    public Menu(LeadService leadService, OpportunityService opportunityService) {
        this.leadService = leadService;
        this.opportunityService = opportunityService;
    }

    public void main() throws WrongInputException, AbortedException {
        String input;

        do {
            var mainMenu = """
                    🤖💬 Welcome to    
                    <html>  <h1> <b> Team 5 CRM </b> </h1> 

                    Available Operations:
                    =====================

                    <html> <b> [ new lead ] </b> -> create a new Lead

                    <html> <b> [ show leads ] </b> -> show all leads

                    <html> <b> [ lookup lead id ] </b> -> look up a lead by ID

                    <html> <b> [ convert id ] </b> -> convert a selected lead into a new Opportunity

                    <html> <b> [ show opportunities ] </b> -> show all available opportunities

                    <html> <b> [ lookup opportunity id ] </b> -> look up an opportunity by it's ID

                    <html> <b> [ open id ]  </b> -> sets the opportunity status to open

                    <html> <b> [ close-lost id ] </b> -> sets the opportunity status to CLOSE / LOST

                    <html> <b> [ close-won id ] </b> -> sets the opportunity status to CLOSE / WON

                    <html> <b> [ exit ] </b>  - to Exit CRM
                    ====================

                    When the command has 'id', replace it with the id of the lead or opportunity you want to work with

                    ====================

                    Write your COMMAND:
                    """;
            input = (String) JOptionPane.showInputDialog(null, mainMenu, "Team 5 - CRM", 3, teamIcon, null, null);

            var inputSplit = input.toLowerCase().split(" ");

            try {
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
            } catch (WrongInputException e) {
                JOptionPane.showMessageDialog(null, "Command not recognized, please try again. 🤔 ", "Not Found", 2);
            } catch (DataNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Data not found", "Not Found", 2);
            } catch (AbortedException a) {
                JOptionPane.showMessageDialog(null, "Convert aborted", "Aborted", 2);
        }


        } while (!input.equals("exit"));
    }

    // STATUS UPDATERS
    // **********************************************************

    /**
     * This menu method is for setting the status of an opportunity to OPEN
     */
    private void openMenu(String[] inputSplit) throws WrongInputException, DataNotFoundException {
        if (inputSplit.length <= 1) {
            throw new WrongInputException();
        }
        int id = Integer.parseInt(inputSplit[1]);

        var opportunity = opportunityService.updateOpportunityStatus(id, Status.OPEN);
        JOptionPane.showMessageDialog(null, "✏️ Opportunity Status is now 'OPEN': \n" + opportunity, "Status Update",
                1);
    }

    /**
     * This menu method is for setting the status of an opportunity to CLOSE_LOST
     */
    private void closeLostMenu(String[] inputSplit) throws WrongInputException, DataNotFoundException {
        if (inputSplit.length <= 1) {
            throw new WrongInputException();
        }
        int id = Integer.parseInt(inputSplit[1]);

        var opportunity = opportunityService.updateOpportunityStatus(id, Status.CLOSED_LOST);
        JOptionPane.showMessageDialog(null, "🆑 Opportunity Status is now 'CLOSE_LOST': \n" + opportunity,
                "Status Update", 1);
    }

    /**
     * This menu method is for setting the status of an opportunity to CLOSE_WON
     */
    private void closeWonMenu(String[] inputSplit) throws WrongInputException, DataNotFoundException {
        if (inputSplit.length <= 1) {
            throw new WrongInputException();
        }
        int id = Integer.parseInt(inputSplit[1]);

        var opportunity = opportunityService.updateOpportunityStatus(id, Status.CLOSED_WON);
        JOptionPane.showMessageDialog(null, "✅ Opportunity Status is now 'CLOSE_WON': \n" + opportunity,
                "Status Update", 1);
    }

    // 'LOOK UP' MENUS
    // **********************************************************

    /**
     * This method is for selecting the "lookup menu" desired by the user according
     * to user's input
     */
    private void lookupMenu(String[] inputSplit) throws WrongInputException {
        if (inputSplit.length <= 2) {
            throw new WrongInputException();
        }

        int id = Integer.parseInt(inputSplit[2]);
        switch (inputSplit[1]) {
            case ConsoleOperationEntities.LEAD -> lookUpLead(id);
            case ConsoleOperationEntities.OPPORTUNITY -> lookUpOpportunity(id);
            default -> throw new WrongInputException();
        }
    }

    /**
     * This method handles the 'lookup opportunity' menu
     */
    private void lookUpOpportunity(int id) {
        try {
            JOptionPane.showMessageDialog(null, opportunityService.lookUpOpportunity(id), "Opportunities " + id, 1);
        } catch (DataNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Opportunity with ID " + id + " was not found in the Database!",
                    "Not Found", 2);
        } catch (EmptyException e) {
            JOptionPane.showMessageDialog(null, "No Opportunities in the database!", "Not Found", 2);
        }
    }

    /**
     * This method handles the 'lookup leads' menu
     */
    private void lookUpLead(int id) {
        try {
            JOptionPane.showMessageDialog(null, leadService.lookUpLead(id), "Lead " + id, 1);
        } catch (EmptyException e) {
            JOptionPane.showMessageDialog(null, "No leads in Database!", "Not Found", 2);
        } catch (DataNotFoundException e) {
            JOptionPane.showMessageDialog(null, "The Lead with ID " + id + " was not found in the database!",
                    "Not Found", 2);
        }
    }

    // 'SHOW' MENUS
    // **********************************************************

    /**
     * This method is for selecting the "show menu" desired by the user according to
     * user's input
     */
    private void showMenu(String[] inputSplit) throws WrongInputException {
        if (inputSplit.length <= 1) {
            throw new WrongInputException();
        }
        switch (inputSplit[1]) {
            case ConsoleOperationEntities.LEADS -> showLeads();
            case ConsoleOperationEntities.OPPORTUNITIES -> showOpportunities();
            default -> throw new WrongInputException();
        }
    }

    /**
     * This method handles the 'show leads' menu
     */
    private void showLeads() {
        try {
            StringBuffer output = new StringBuffer();
            output.append("Following Leads where found in the database: \n" );
            output.append("************************************************\n\n" );

            var leads = leadService.getAllLeads();
            for (var lead : leads) {
                output.append(lead).append("\n");
            }
            JTextArea textArea = new JTextArea(String.valueOf(output));

            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize( new Dimension( 500, 500 ) );

            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);

            JOptionPane.showMessageDialog(null, scrollPane, "Leads in Database", 1,teamIcon);
        } catch (EmptyException e) {
            JOptionPane.showMessageDialog(null, "No Leads in Database!", "Not Found", 2);
        }
    }

    private void showOpportunities() {
        try {
            StringBuffer output = new StringBuffer();
            output.append("Following Opportunities where found in the database: \n" );
            output.append("************************************************\n\n" );

            var opps = opportunityService.getAllOpportunities();
            for (var opp : opps) {
                output.append(opp.toString()).append("\n");
            }
            JTextArea textArea = new JTextArea(String.valueOf(output));

            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize( new Dimension( 500, 500 ) );

            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);

            JOptionPane.showMessageDialog(null, scrollPane, "Opportunities in Database", 1,teamIcon);
        } catch (EmptyException e) {
            JOptionPane.showMessageDialog(null, "No Opportunities in the Database!", "Not Found", 2);
        }
    }

    // CONVERT MENUS
    // **********************************************************
    private void convertMenu(String[] inputSplit) throws WrongInputException, AbortedException {
        if (inputSplit.length <= 1) {
            throw new WrongInputException();
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
            leadService.convert(leadFound.getId(), product, productQty, industry, employees, city, country);

            JOptionPane.showMessageDialog(null, "Lead Succesfully converted");

        } catch (EmptyException e) {
            JOptionPane.showMessageDialog(null, "No leads in Database!", "Not Found", 2);
        } catch (DataNotFoundException e) {
            JOptionPane.showMessageDialog(null, "The Lead with ID " + id + " was not found in the database!",
                    "Not Found", 2);
        }
    }

    // 'NEW' MENUS
    // **********************************************************
    private void newMenu(String[] inputSplit) throws WrongInputException {
        if (inputSplit.length <= 1) {
            throw new WrongInputException();
        }
        switch (inputSplit[1]) {
            case ConsoleOperationEntities.LEAD -> {
                List<Object> values = getValues("Name :\n", "Phone number : \n", "Email : \n", "Company : ");
                Lead lead = leadService.newLead((String) values.get(0), (String) values.get(1), (String) values.get(2),
                        (String) values.get(3));
                JOptionPane.showMessageDialog(null, "Lead Successfully added: \n" + lead,"Lead Added", 1);
            }
            default -> throw new WrongInputException();
        }
    }

    // OTHER MENUS METHODS
    // **********************************************************

    // ******************* USING VARARGS FOR REUSING METHODS
    public static List<Object> getValues(Object... values) throws WrongInputException {
        List<Object> value = new ArrayList<>();
        for (var i : values) {
            try {
                value.add(JOptionPane.showInputDialog(null, i, "Input", JOptionPane.QUESTION_MESSAGE, teamIcon, null,
                        null));
            } catch (Exception e) {
                throw new WrongInputException("1");
            }
        }
        return value;
    }

    /**
     * Opens a dropsdown menu that gives the user the options to select a status
     * This method returns a status accordingly to users selection
     */
    public Status getStatus() throws AbortedException {

        String status;
        // These are the options for the dropdown menu
        String[] options = {OPEN, CLOSE_WON, CLOSE_LOST};
        // this is the message displayed on the window with the dropdown menu
        String message = "Please select status to set new status";

        // opens a dropdown menu
        status = (String) JOptionPane.showInputDialog(
                null,
                message,
                "Status Update",
                JOptionPane.QUESTION_MESSAGE,
                teamIcon,
                options,
                "---");

        // logic
        switch (status) {
            case CLOSE_LOST -> {
                return Status.CLOSED_LOST;
            }
            case OPEN -> {
                return Status.OPEN;
            }
            case CLOSE_WON -> {
                return Status.CLOSED_WON;
            }
            default -> throw new AbortedException();
        }
    }

    /**
     * Opens a dropsdown menu that gives the user the options to select a product
     * This method returns a product accordingly to users selection
     */
    public Product getProduct() throws AbortedException {

        String product;
        // these are the options for the dropdown menu
        String[] options = {HYBRID, FLATBED, BOX};

        // this is the message displayed on the window with the dropdown menu
        String message = "Please select a product";

        // opens a dropdown menu
        product = (String) JOptionPane.showInputDialog(
                null,
                message,
                "Status Update",
                JOptionPane.QUESTION_MESSAGE,
                teamIcon,
                options,
                "Select");

        // Logic:
        try {
            switch (product) {
                case HYBRID -> {
                    return Product.HYBRID;
                }
                case FLATBED -> {
                    return Product.FLATBED;
                }
                case BOX -> {
                    return Product.BOX;
                }
                default -> throw new AbortedException();
            }
        } catch (Exception e) {
            throw new AbortedException();
        }
    }

    /**
     * Opens a dropsdown menu that gives the user the options to select an industry
     * This method returns an industry accordingly to users selection
     */
    public Industry getIndustry() throws AbortedException {
        String industry;

        // these are the options for the dropdown menu
        String[] options = {PRODUCE, ECOMMERCE, MANUFACTURING, MEDICAL, OTHER};
        // this is the message displayed on the window with the dropdown menu
        String message = "Please select a product";

        // opens a dropdown menu
        industry = (String) JOptionPane.showInputDialog(
                null,
                message,
                "Status Update",
                JOptionPane.QUESTION_MESSAGE,
                teamIcon,
                options,
                "---");

        // Logic:
        switch (industry) {
            case PRODUCE -> {
                return Industry.PRODUCE;
            }
            case ECOMMERCE -> {
                return Industry.ECOMMERCE;
            }
            case MANUFACTURING -> {
                return Industry.MANUFACTURING;
            }
            case MEDICAL -> {
                return Industry.MEDICAL;
            }
            case OTHER -> {
                return Industry.OTHER;
            }
            default -> throw new AbortedException();
        }
    }
}
