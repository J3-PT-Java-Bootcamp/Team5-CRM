package com.ironhack.domain;

import java.util.Objects;

public class Contact {
    private int id;
    private String name;
    private String phone;
    private String email;


    //* CONSTRUCTOR
    //**********************************************
    public Contact(int id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }


    //* GETTERS AND SETTERS
    //**********************************************
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


    //* Equals, hashcode and toString
    //**********************************************
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Contact contact = (Contact) object;
        return id == contact.id;
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public String toString() {
        return "👤 Contact with ID " + id + ": \n" +
                "Name: " + name + "\n" +
                "Phone: " + phone +  "\n" +
                "e-mail: " + email + "\n";
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
