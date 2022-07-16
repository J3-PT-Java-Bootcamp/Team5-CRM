package com.ironhack.domain;

public class Contact {
    private int iD;
    private String name;
    private String phone;
    private String email;

    public Contact(int iD, String name, String phone, String email) {
        this.iD = iD;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Contact contact = (Contact) object;
        return iD == contact.iD;
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), iD);
    }

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
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
}
