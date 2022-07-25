package com.ironhack.ui;

import com.ironhack.business_logic.AccountService;
import com.ironhack.business_logic.LeadService;
import com.ironhack.business_logic.OpportunityService;
import com.ironhack.domain.enums.Industry;
import com.ironhack.domain.enums.Status;
import com.ironhack.domain.enums.Product;

import javax.swing.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static com.ironhack.ui.ConsoleOperations.*;

public class MenuController {

    private final AccountService accountService;
    private final LeadService leadService;
    private final OpportunityService opportunityService;

    private static final Scanner scanner = new Scanner(System.in);


    public MenuController(AccountService accountService, LeadService leadService, OpportunityService opportunityService) {
        this.accountService = accountService;
        this.leadService = leadService;
        this.opportunityService = opportunityService;
    }

    //**********  CHECK ALL VIEWS, ARE VERY POOR
    public void newLead() throws Exception {//DEBE DE ACOMODARSE A RETURN, SOLO A MODO DE TEST
        List<Object> values = getValues("Name :\n", "Phone number : \n", "Email : \n", "Company : ");
        var lead = leadService.newLead((String) values.get(0), (String) values.get(1), (String) values.get(2), (String) values.get(3));
        JOptionPane.showMessageDialog(null, "Lead Succesfully added \n\nLead Register : \n\nID : " + lead.getId() + "\nName : " + lead.getName() + "\nPhonenumber : " + lead.getPhoneNumber() + "\nEmail :" + lead.getEmail() + "\nCompany :" + lead.getCompanyName());
    }

    public void convert(int id) throws Exception {
        leadService.convert(id);
//        List <Object> values = getValues("ID?\n", "Quantity :");
//        opportunity = new Opportunity(Integer.parseInt(values.get(0).toString()), getContact(), getStats(), getProduct(), Integer.parseInt(values.get(1).toString()));
//
//        if(opportunity.getStatus() == Status.OPEN) {// --->> check, only add in that case?
//            getAccount();
//            CRM_Data.addOpp(opportunity);
//        }
//
//        CRM_Data.addOpp(opportunity);
//
//        return opportunity;
    }

    public void showLeads() {
        leadService.showLeads();
    }

    public void lookUpLead(int id) {
        leadService.lookUpLead(id);
    }

    public void showOpportunities() {
        opportunityService.showOpportunities();
    }

    public void lookUpOpportunity(int id) {
        opportunityService.lookUpOpportunity(id);
    }

    public void open(int id) {

    }

    public void closeLost(int id) {

    }

    public void closeWon(int id) {

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
