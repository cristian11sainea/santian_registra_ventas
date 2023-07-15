package com.santian.repository.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Product {

    @Id
    public String id;
    public String name;
    public Category category;
    public Double unitValue;
    public Double saleValue;
    public Integer amount;
    public User user;

    public Product( String name, Category category, Double unitValue, Double saleValue, Integer amount, User user) {

        this.name = name;
        this.category = category;
        this.unitValue = unitValue;
        this.saleValue = saleValue;
        this.amount = amount;
        this.user = user;
    }

    public Product() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Double getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(Double unitValue) {
        this.unitValue = unitValue;
    }

    public Double getSaleValue() {
        return saleValue;
    }

    public void setSaleValue(Double saleValue) {
        this.saleValue = saleValue;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
