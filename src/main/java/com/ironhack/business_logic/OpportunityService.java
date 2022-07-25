package com.ironhack.business_logic;

import com.ironhack.data.CRM_Data;
import com.ironhack.domain.Contact;
import com.ironhack.domain.Opportunity;
import com.ironhack.domain.Product;
import com.ironhack.domain.Status;

public abstract class OpportunityService {


    public static void main(String[] args) {
        Contact testContact = new Contact(12,"Miguel","+555 55-555-555", "mail@mail.com");
        CRM_Data.addOpp(new Opportunity(100 ,testContact, Status.CLOSED_LOST, Product.HYBRID,30 ));

        testContact = new Contact(12,"Ana Lopez","+555 55-555-555", "mail@mail.com");
        CRM_Data.addOpp(new Opportunity(100,testContact, Status.CLOSED_LOST, Product.HYBRID,30 ));

        testContact = new Contact(13,"Mauricio","+555 55-555-555", "mail@mail.com");
        CRM_Data.addOpp(new Opportunity(102,testContact, Status.CLOSED_LOST, Product.HYBRID,30 ));

        OpportunityService.showOpportunities();

        OpportunityService.lookUpOpportunity(100);

    }



    public static void showOpportunities (){
        System.out.println("+ Following Opportunities are available: ");
        if (CRM_Data.getOppsList().isEmpty()){
            System.out.println("-> None.");
        } else {
            for (Opportunity opp: CRM_Data.getOppsList()){
                System.out.println("-> " + opp.toString());
            }
        }

    }

    public static void lookUpOpportunity(int id){
        for (Opportunity opp: CRM_Data.getOppsList()) {
            if(opp.getId()==id){
                CRM_Data.setSelectedOpp(opp);
                System.out.println("Opportunity with ID ("+opp.getId()+") was selected:");
                System.out.println(opp.toString());

            }

        }
    }



}
