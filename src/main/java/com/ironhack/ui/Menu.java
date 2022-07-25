package com.ironhack.ui;
import com.ironhack.Exceptions.Exceptions;
import com.ironhack.data.CRM_Data;
import com.ironhack.domain.*;

import javax.swing.*;
import java.util.*;

public class Menu implements ConsoleOperations{

        private static final Scanner scanner = new Scanner(System.in);
        private ControllerMenu menu = new ControllerMenu();
       /* String convert = String.format("convert ", scanner.nextInt());
        String reg = ".*[0-9].*";
        String x = "convert %o".formatted(reg);*/
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
                        //input = JOptionPane.showInputDialog(mainMenu).trim().toLowerCase();
                        var inpuSplit = input.split(" ");
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

        private void newLead() throws Exceptions {
              menu.newLead();
        }

        private void convert() throws Exceptions {
              menu.convert();
        }
        private void lookUpOpportunities() {// add exceptions
              menu.lookUpOpportunities();
        }

        private void showOpportunities() {// add exceptions
              menu.showOpportunities();
        }

        private void lookUpLead() {// add exceptions
              menu.lookUpLead();
        }

        private void showLeads() {
             menu.showLeads();
        }

        private void open() {
        }

        private void closeLost() {

        }
        private void closeWon() {

        }

}
