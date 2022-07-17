package com.ironhack.domain;

import java.util.Objects;

public class Opportunity {
    private int id;
    private Contact decisionMaker;
    private Status status;
    private Product product;
    private int quantity;

    public Opportunity(int id, Contact decisionMaker, Status status, Product product, int quantity) {
        this.id = id;
        this.decisionMaker = decisionMaker;
        this.status = status;
        this.product = product;
        this.quantity = quantity;
    }

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
    public String toString() {
        final StringBuffer sb = new StringBuffer("Opportunity{");
        sb.append("id=").append(id);
        sb.append(", decisionMaker=").append(decisionMaker.getName());
        sb.append(", status=").append(status);
        sb.append(", product=").append(product);
        sb.append(", quantity=").append(quantity);
        sb.append('}');
        return sb.toString();
    }
}
