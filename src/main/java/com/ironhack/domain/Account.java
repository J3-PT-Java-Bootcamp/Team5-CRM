package com.ironhack.domain;

public class Account {
    private int id;
    private String industry;
    private int employeesCount;
    private String city;
    private String country;
    private ArrayList<Contact> contactList;
    private ArrayList<Opportunity> opportinityList;


    public Account(int id, String industry, int employeesCount, String city, String country, ArrayList<Contact> contactList, ArrayList<Opportunity> opportinityList) {
        this.id = id;
        this.industry = industry;
        this.employeesCount = employeesCount;
        this.city = city;
        this.country = country;
        this.contactList = contactList;
        this.opportinityList = opportinityList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
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

    public ArrayList<Opportunity> getOpportinityList() {
        return opportinityList;
    }

    public void setOpportinityList(ArrayList<Opportunity> opportinityList) {
        this.opportinityList = opportinityList;
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
}


