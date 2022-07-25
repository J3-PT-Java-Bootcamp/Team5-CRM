package com.ironhack.ui;

import com.ironhack.Exceptions.Exceptions;
import com.ironhack.data.CRM_Data;
import com.ironhack.domain.*;
import com.ironhack.domain.Industry;
import com.ironhack.domain.Status;
import com.ironhack.domain.Product;
import javax.swing.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static com.ironhack.ui.ConsoleOperations.*;

public class ControllerMenu {

    private static final Scanner scanner = new Scanner(System.in);
    private Lead lead = new Lead();
    private Opportunity opportunity = new Opportunity();
    //call to methof of lead class for a new Lead


    public ControllerMenu() {
    }

    //**********  CHECK ALL VIEWS, ARE VERY POOR
    public Lead newLead() throws Exceptions {//DEBE DE ACOMODARSE A RETURN, SOLO A MODO DE TEST
        List< Object > values = getValues("ID : \n", "Name :\n", "Phonenumbers : \n", "Email : \n", "Company : ");
        lead = new Lead(Integer.parseInt(values.get(0).toString()), (String) values.get(1), (String) values.get(2), (String) values.get(3), (String) values.get(4));
        JOptionPane.showMessageDialog(null, "Lead Succesfully added \n\nLead Register : \n\nID : " + lead.getId()  +"\nName : " + lead.getName() + "\nPhonenumber : " + lead.getPhoneNumber() + "\nEmail :" + lead.getEmail() + "\nCompany :" + lead.getCompanyName());
        CRM_Data.addLead(lead);

        return lead;
    }

    public Opportunity convert() throws Exceptions {
        List <Object> values = getValues("ID?\n", "Quantity :");
        opportunity = new Opportunity(Integer.parseInt(values.get(0).toString()), getContact(), getStats(), getProduct(), Integer.parseInt(values.get(1).toString()));

        if(opportunity.getStatus() == Status.OPEN) {// --->> check, only add in that case?
            getAccount();
            CRM_Data.addOpp(opportunity);
        }

        CRM_Data.addOpp(opportunity);

        return opportunity;
    }

    public Contact getContact(){
        return  new Contact(lead.getId(), lead.getName(), lead.getPhoneNumber(), lead.getCompanyName());
    }

    public void showLeads(){
        JOptionPane.showMessageDialog(null, CRM_Data.getLeadsList());// check the print on the pane in the CRM Method
    }

    public Lead lookUpLead(){
        String ide = JOptionPane.showInputDialog("Id Lead? ");
        int id = Integer.parseInt(ide);
        CRM_Data.setSelectedLead((Lead) getIdObject(id, lead));


        JOptionPane.showMessageDialog(null, CRM_Data.getSelectedLead());
        //JOptionPane.showMessageDialog(CRM_Data.getSelectedLead()); --->JOPTION DESDE EL MÉTODO ORIGINAL

        return CRM_Data.getSelectedLead();// check the print on the pane in the CRM Method
    }

    public void showOpportunities(){
        JOptionPane.showMessageDialog(null, CRM_Data.getOppsList());// check the print on the pane in the CRM Method
    }

    public Opportunity lookUpOpportunities(){
        String ide = JOptionPane.showInputDialog("Id Opportunity? ");
        int id = Integer.parseInt(ide);
        CRM_Data.setSelectedOpp((Opportunity) getIdObject(id, opportunity));
        System.out.println(CRM_Data.getSelectedOpp());
        JOptionPane.showMessageDialog(null, CRM_Data.getSelectedOpp()); // check the print on the pane in the CRM Method

        return CRM_Data.getSelectedOpp();
    }

    public void open(){

    }

    public void closeLost(){

    }

    public void closeWon(){

    }

    public Account getAccount() throws Exceptions {
        List <Object> values = getValues("ID?\n","Employees?\n", "City?\n", "Country? \n");
        Account account = new Account(Integer.parseInt(values.get(0).toString()), getIndustry(), Integer.parseInt(values.get(1).toString()), (String) values.get(2), (String) values.get(3), CRM_Data.getContactsList(), CRM_Data.getOppsList());
        CRM_Data.addAccount(account);
        System.out.println(account.toString());
        JOptionPane.showMessageDialog(null, account.toString());

        return account;
    }
    //******************* USING VARARGS FOR REUSING METHODS
    public static List < Object > getValues(Object ... values) throws Exceptions {
        List < Object > value = new ArrayList<>();
        for(var i : values) {
            try {  // --> dont work, check
                value.add(JOptionPane.showInputDialog(i).trim().toLowerCase());
            } catch (InputMismatchException e){
                throw new Exceptions("1");
            }
        }
        return value;
    }

    // ***************************   ENUMS
    public Status getStats() {
        String stats;

        String message = "Status client commands  -> \n" + "'open' for 'OPEN'\n" + "'close-lost' for 'CLOSE/LOSE'\n" +  "'close-won' for 'CLOSE/WON'\n" + "'exit' for abort";
        stats = JOptionPane.showInputDialog(message).trim().toLowerCase();

        switch (stats){
            case CLOSE_LOST -> {
                return Status.CLOSED_LOST;
            }
            case OPEN -> {
                return Status.OPEN;
            }
            case CLOSE_WON -> {
                return  Status.CLOSED_WON;
            }
            default -> JOptionPane.showMessageDialog(null,"Only a real status");
        }
        return null;
    }

    public Product getProduct(){
        String product;
        String message =  "Stock Products : \n\n 'Hybrid' \n 'Flatbed' \n 'Box' \n";

        product = JOptionPane.showInputDialog(message).trim().toLowerCase();
        switch (product){
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

    public Industry getIndustry(){
        String industry;
        String message = "Type of Industry : \n\n 'Produce'\n 'Ecommerce'\n 'Manufacturing'\n 'Medical'\n 'Other'\n";
        industry = JOptionPane.showInputDialog(message).trim().toLowerCase();

        switch (industry){
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

    public static Object getIdObject(int id, Object variable){
        if(variable instanceof Lead)
            for (var i : CRM_Data.getLeadsList()) {
                if (i.getId() == id)
                    return i;
            }
        for (var i : CRM_Data.getOppsList()) {
            if (i.getId() == id)
                return i;
        }
        return null;
    }

}
