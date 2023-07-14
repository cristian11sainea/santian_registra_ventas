package com.santian.repository.model;

import java.math.BigInteger;
import java.util.Date;

public class Report {

    private Person client;
    private Product product;
    private String billingDate;
    private Integer quantity;
    private String type;
    private BigInteger total;

    public Report(Person client, Product product, String billingDate, Integer quantity, String type, BigInteger total) {
        this.client = client;
        this.billingDate = billingDate;
        this.quantity = quantity;
        this.type = type;
        this.total = total;
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Person getClient() {
        return client;
    }

    public void setClient(Person client) {
        this.client = client;
    }

    public String getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(String billingDate) {
        this.billingDate = billingDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigInteger getTotal() {
        return total;
    }

    public void setTotal(BigInteger total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Report{" +
                "client='" + client + '\'' +
                ", billingDate=" + billingDate +
                ", quantity=" + quantity +
                ", type='" + type + '\'' +
                ", total=" + total +
                '}';
    }
}
