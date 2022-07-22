package com.ironhack.ui;
import com.ironhack.Exceptions.Exceptions;
import com.ironhack.data.CRM_Data;
import com.ironhack.domain.*;

import javax.swing.*;
import java.util.*;

public class Menu implements ConsoleOperations{

        private static final Scanner scanner = new Scanner(System.in);
        private Lead lead = new Lead();
        private Opportunity opportunity = new Opportunity();

        public void main() throws Exceptions {
                String input;
                do {
                        var mainMenu = """
                    Welcome to CRM Manager
                    Available Operations
                 
                    ===============
                    command -> 'new lead' for add a new Lead
                    command -> 'convert' plus the id for convert to new object
                    command -> 'show leads' for show all leads
                    command -> 'lookup lead' for see a lookup lead
                    command -> 'show opportunities' for show all oportunities
                    command -> 'lookup opportunity' for see a lookup opportunity
                    command -> 'open' for changes the sales states to open
                    command -> 'close-lost' for changes the sales states to CLOSE / LOST
                    command -> 'close-won' for changes the sales states to CLOSE / WON

                    [EXIT] - Exit CRM Manager
                    ===============
                    Write your COMMAND:
                    """;
                        input = JOptionPane.showInputDialog(mainMenu).trim().toLowerCase();
                        switch (input) {
                                case NEW_LEAD -> newLead();
                                case CONVERT -> convert();
                                case SHOW_LEADS -> showLeads();
                                case LOOKUP_LEAD -> lookUpLead();
                                case SHOW_OPPORTUNITIES -> showOpportunities();
                                case LOOKUP_OPPORTUNITY -> lookUpOpportunities();
                                case OPEN -> open();
                                case CLOSE_LOST -> closeLost();
                                case CLOSE_WON -> closeWon();
                                case "EXIT" -> {
                                        System.out.println("Adeu");
                                        System.exit(1);
                                }
                                default -> System.out.println("Command not recognized!");
                        }
                } while (!input.equals("exit"));
        }

        /*FLUX OF REQUIREMENTS
        *
        *CRM FLUX


                Every person in the webinars  --> Lead;

                - after webinar

                a Lead(Interested) covert to an --> Opportunity (conserv ID) --> Command 'convert ...id' ---> NEED REVIEW


                - CRM begins

                    Create a new Contact, with Lead info

                Lead  --> Contact

                    Asking for the product

                Choice with switch --> Products (ENUM)


                    Asking for quantity


                int quantity = choice in the scanner.


                    them, create the Opportunity

                attributes  --> 	int id, Contact decisionMaker, Status status, Product product, int quantity   --> Status default 'Open'


                - CRM Asking

                Switch --> Industry (ENUM)

                    Ask for Contact Account

                int employees  -- > Account
                city   -- > Account
                country --> Account

                them create the Account

                int id, String industry, int employeesCount, String city, String country, ArrayList<Contact> contactList, ArrayList<Opportunity> opportunityList


                    them add the Lead Object to ContactList of the Account and Opportunity to opportunityList of the Account

                    - Them, only the the Account and past steps are aproved, delete the Lead.


                    - Lookup Opportunity by ID

                    - If the Contact deny, with command 'close-lost + OpportunityID' close that order.

        * */

        //call to methof of lead class for a new Lead
        public void newLead() throws Exceptions {//DEBE DE ACOMODARSE A RETURN, SOLO A MODO DE TEST
            List < Object > values = getValues("ID : \n", "Name :\n", "Phonenumbers : \n", "Email : \n", "Company : ");
            //---> repair the int values !!!!
            lead = new Lead(Integer.parseInt(values.get(0).toString()), (String) values.get(1), (String) values.get(2), (String) values.get(3), (String) values.get(4));
            JOptionPane.showMessageDialog(null, "Lead Succesfully added \n  Lead : \n " + lead.toString());
            CRM_Data.addLead(lead);
        }

        public void convert() throws Exceptions {
             Contact contact = new Contact(lead.getId(), lead.getName(), lead.getPhoneNumber(), lead.getCompanyName());
             String ide = JOptionPane.showInputDialog("Id? ");
             int id = Integer.parseInt(ide);
             opportunity = new Opportunity(id, contact, getStats(), getProduct(), 3);

             if(opportunity.getStatus() == Status.OPEN) // --->> check, only add in that case?
                 getAccount();
                 CRM_Data.addOpp(opportunity);
        }

        public void showLeads(){
            JOptionPane.showMessageDialog(null, CRM_Data.getLeadsList());
        }

        public void lookUpLead(){
            String ide = JOptionPane.showInputDialog("Id Lead? ");
            int id = Integer.parseInt(ide);
            CRM_Data.setSelectedLead((Lead) getIdObject(id, lead));


           System.out.println(CRM_Data.getSelectedLead());
            //JOptionPane.showMessageDialog(CRM_Data.getSelectedLead()); --->JOPTION DESDE EL MÉTODO ORIGINAL
        }

        public void showOpportunities(){
            System.out.println(CRM_Data.getOppsList());
        }

        public void lookUpOpportunities(){
            String ide = JOptionPane.showInputDialog("Id Opportunity? ");
            int id = Integer.parseInt(ide);
            CRM_Data.setSelectedOpp((Opportunity) getIdObject(id, opportunity));
            System.out.println(CRM_Data.getSelectedOpp());
        }

        public void open(){

        }

        public void closeLost(){

        }

        public void closeWon(){

        }

        public void getAccount() throws Exceptions {
            List <Object> values = getValues("ID?\n","Employees?\n", "City?\n", "Country? \n");
            Account account = new Account(Integer.parseInt(values.get(0).toString()), getIndustry(), Integer.parseInt(values.get(1).toString()), (String) values.get(2), (String) values.get(3), CRM_Data.getContactsList(), CRM_Data.getOppsList());
            CRM_Data.addAccount(account);
            System.out.println(CRM_Data.getAccountsList());
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
                  default -> System.out.println("Only a real status");
             }
             return null;
        }

        public Product getProduct(){
            String product;
            String message =  "'Hybrid' \n" + "'Flatbed' \n" +  "'Box' \n";

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
                  default -> System.out.println("Only existents products");
             }
             return null;
        }

        public Industry getIndustry(){
            String industry;
            String message = "'PRODUCE'\n 'ECOMMERCE'\n 'MANUFACTURING'\n 'MEDICAL'\n 'OTHER'\n";
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
