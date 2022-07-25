package com.ironhack.domain;

import com.ironhack.domain.enums.Industry;

import java.util.ArrayList;
import java.util.Objects;

public class Account {
    private int id;
    private Industry industry;
    private int employeesCount;
    private String city;
    private String country;
    private ArrayList<Contact> contactList;
    private ArrayList<Opportunity> opportunityList;


    public Account(int id, Industry industry, int employeesCount, String city, String country, ArrayList<Contact> contactList, ArrayList<Opportunity> opportunityList) {
        this.id = id;
        this.industry = industry;
        this.employeesCount = employeesCount;
        this.city = city;
        this.country = country;
        this.contactList = contactList;
        this.opportunityList = opportunityList;
    }

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

    public ArrayList<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(ArrayList<Contact> contactList) {
        this.contactList = contactList;
    }

    public ArrayList<Opportunity> getOpportunityList() {
        return opportunityList;
    }

    public void setOpportunityList(ArrayList<Opportunity> opportunityList) {
        this.opportunityList = opportunityList;
    }

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

    //*** only for tester


    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", industry=" + industry +
                ", employeesCount=" + employeesCount +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", contactList=" + contactList +
                ", opportunityList=" + opportunityList +
                '}';
    }
}


