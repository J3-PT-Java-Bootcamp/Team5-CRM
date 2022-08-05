package com.ironhack.domain;

import com.ironhack.domain.enums.Product;
import com.ironhack.domain.enums.Status;

import java.util.Objects;

public class Opportunity {
    private int id;
    private Contact decisionMaker;
    private Status status;
    private Product product;
    private int quantity;

    //* CONSTRUCTORS
    //**********************************************
    public Opportunity() {
    }

    public Opportunity(int id, Contact decisionMaker, Status status, Product product, int quantity) {
        this.id = id;
        this.decisionMaker = decisionMaker;
        this.status = status;
        this.product = product;
        this.quantity = quantity;
    }


    //* GETTERS AND SETTERS
    //**********************************************
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Contact getDecisionMaker() {
        return decisionMaker;
    }
    public void setDecisionMaker(Contact decisionMaker) {
        this.decisionMaker = decisionMaker;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    //* Equals, hashcode and toString
    //**********************************************
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Opportunity that = (Opportunity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString(){
        return "📘 Opportunity with ID "+ id + ":\n" +
                "-------------------------------:\n" +
                "🗣 Decision Maker is " + decisionMaker.getName() +" (Contact ID: "+decisionMaker.getId() + ")\n" +
                "📞: "+decisionMaker.getPhone()+ "  |  📧: " +decisionMaker.getEmail()+ "\n" +
                "PRODUCT: "+ product +" | QUANTITY: " + quantity + " | STATUS: " + status + "\n";
    }
}
