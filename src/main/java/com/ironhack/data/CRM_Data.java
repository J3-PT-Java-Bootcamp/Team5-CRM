package com.ironhack.data;

import com.ironhack.domain.Account;
import com.ironhack.domain.Contact;
import com.ironhack.domain.Lead;
import com.ironhack.domain.Opportunity;

import java.util.ArrayList;

public abstract class CRM_Data {
    static ArrayList<Lead> leadsList = new ArrayList<>();
    static ArrayList<Contact> contactsList = new ArrayList<>();
    static ArrayList<Opportunity> oppsList = new ArrayList<>();
    static ArrayList<Account> accountsList = new ArrayList<>();

    static Lead selectedLead;
    static Opportunity selectedOpp;
    static Contact selectedContact;
    static Account selectedAccount;

    /******************* GETTERS *********************/

    public static ArrayList<Lead> getLeadsList() {
        return leadsList;
    }

    public static ArrayList<Contact> getContactsList() {
        return contactsList;
    }

    public static ArrayList<Opportunity> getOppsList() {
        return oppsList;
    }

    public static ArrayList<Account> getAccountsList() {
        return accountsList;
    }

    public static Lead getSelectedLead() {
        return selectedLead;
    }

    public static Opportunity getSelectedOpp() {
        return selectedOpp;
    }

    public static Contact getSelectedContact() {
        return selectedContact;
    }

    public static Account getSelectedAccount() {
        return selectedAccount;
    }

    /******************* ADDERS *********************/

    public static void addLead(Lead lead) {
        boolean exists = false;
        for (var l: leadsList) {
            if (l.getId()==lead.getId()){
                System.out.println("- Lead "+l.getName()+" was not added because");
                System.out.println("  another Lead with the ID ("+ l.getId()+") already exists.");
                exists = true;
            }
        }
        if (exists==false) {
            CRM_Data.leadsList.add(lead);
            System.out.println("+ "+lead.getName()+" was added to the Leads List.");
        };
    }

    public static void addContact (Contact contact) {
        boolean exists = false;
        for (Contact c: contactsList) {
            if (c.getId()==contact.getId()){
                System.out.println("- Contact "+c.getName()+" was not added because");
                System.out.println("  a contact with the ID ("+ c.getId()+") already exists.");

                exists = true;
            }
        }
        if (exists==false) {
            CRM_Data.contactsList.add(contact);
            System.out.println("+ "+contact.getName()+" was added to the Contacts List.");
        };
    }

    public static void addOpp(Opportunity opp) {
        boolean exists = false;
        for (Opportunity o: oppsList) {
            if (o.getId()==opp.getId()){
                System.out.println("- Opportunity with ID ("+o.getId()+") was not added because");
                System.out.println("  another opportunity with that ID already exists.");
                exists = true;
            }
        }
        if (exists==false) {
            CRM_Data.oppsList.add(opp);
            System.out.println("+ Opportunity with ID ("+opp.getId()+") was added to the Opportunities List.");
        };
    }

    public static void addAccount (Account account) {
        boolean exists = false;
        for (Account a: accountsList) {
            if (a.getId()==account.getId()){
                System.out.println("- Account with ID ("+a.getId()+") was not added because");
                System.out.println("  another account with that ID already exists.");
                exists = true;
            }
        }
        if (exists==false) {
            CRM_Data.accountsList.add(account);
            System.out.println("+ Account with ID ("+account.getId()+") was added to the Opportunities List.");
        };        }

    public static void setSelectedLead(Lead selectedLead) {
        CRM_Data.selectedLead = selectedLead;
    }

    public static void setSelectedOpp(Opportunity selectedOpp) {
        CRM_Data.selectedOpp = selectedOpp;
    }

    public static void setSelectedContact(Contact selectedContact) {
        CRM_Data.selectedContact = selectedContact;
    }

    public static void setSelectedAccount(Account selectedAccount) {
        CRM_Data.selectedAccount = selectedAccount;
    }
}

