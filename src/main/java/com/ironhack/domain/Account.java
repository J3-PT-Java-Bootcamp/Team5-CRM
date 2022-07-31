package com.ironhack.domain;

import com.ironhack.domain.enums.Industry;

import java.util.List;
import java.util.List;
import java.util.Objects;

public class Account {
    private int id;
    private Industry industry;
    private int employeesCount;
    private String city;
    private String country;
    private List<Contact> contactList;
    private List<Opportunity> opportunityList;

    //* CONSTRUCTOR
    //**********************************************
    public Account(int id, Industry industry, int employeesCount, String city, String country, List<Contact> contactList, List<Opportunity> opportunityList) {
        this.id = id;
        this.industry = industry;
        this.employeesCount = employeesCount;
        this.city = city;
        this.country = country;
        this.contactList = contactList;
        this.opportunityList = opportunityList;
    }


    //* GETTERS AND SETTERS
    //**********************************************
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Industry getIndustry() {
        return industry;
    }
    public void setIndustry(Industry industry) {
        this.industry = industry;
    }
    public int getEmployeesCount() {
        return employeesCount;
    }
    public void setEmployeesCount(int employeesCount) {
        this.employeesCount = employeesCount;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public List<Contact> getContactList() {
        return contactList;
    }
    public void setContactList() {
        this.contactList = contactList;
    }
    public List<Opportunity> getOpportunityList() {
        return opportunityList;
    }
    public void setOpportunityList(List<Opportunity> opportunityList) {
        this.opportunityList = opportunityList;
    }


    //* Equals, hashcode and toString
    //**********************************************
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Account account = (Account) object;
        return id == account.id;
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public String toString() {

        //Creating String with all contacts
        String contactsString = "";
        for (Contact contactInList: contactList){
            contactsString = contactsString + contactInList.toString() + "\n";
        }

        //Creating String with all Opportunities
        String opportunitesString = "";
        for (Opportunity oppInList: opportunityList){
            opportunitesString = opportunitesString + oppInList.toString() +"\n";
        }

        //adding the two strings creaated above (contacts and opportunities) and
        //addinf this to the toString final string.
        return "📁 Account with ID "+ id + ": \n" +
                "***************************\n" +
                "Industry: " + industry +" | Number of Employees: " + employeesCount + "\n" +
                "City / Country: " + city +" - "+ country + "\n\n" +
                "👥 Contacts in this account \n" +
                "---------------------------\n" +
                contactsString +
                "📖 Opportunites in this account \n" +
                "---------------------------\n" +
                opportunitesString + "\n";
    }
}


