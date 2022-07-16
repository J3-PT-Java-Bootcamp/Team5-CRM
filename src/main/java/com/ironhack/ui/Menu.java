package com.ironhack.ui;
import java.util.Scanner;

public class Menu implements ConsoleOperations{

        private final Scanner scanner = new Scanner(System.in);

        public void main() {
                String input;
                do {
                        var mainMenu = """
                    Welcome to CRM Manager
                    Available Operations
                 
                    ===============
                    command -> 'new lead' for add a new Lead
                    command -> 'convert' for convert to new object
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
                        System.out.println(mainMenu);
                        input = scanner.nextLine().trim().toUpperCase();
                        switch (input) {
                                case NEW_LEAD -> newLead();
                                case CONVERT -> newLead();
                                case SHOW_LEADS -> newLead();
                                case LOOKUP_LEAD -> newLead();
                                case SHOW_OPPORTUNITIES -> newLead();
                                case LOOKUP_OPPORTUNITY -> newLead();
                                case OPEN -> newLead();
                                case CLOSE_LOST -> newLead();
                                case CLOSE_WON -> newLead();
                                case "EXIT" -> {
                                        System.out.println("Adeu");
                                        System.exit(1);
                                }
                                default -> System.out.println("Command not recognized!");
                        }
                } while (!input.equals("exit"));
        }

        public void newLead() {

        }
}
