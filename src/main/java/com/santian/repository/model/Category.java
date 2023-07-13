package com.santian.repository.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Category {
    @Id
    public String id;
    public String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }


    public Category(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Category updateCategory(String id, String name){
        return new Category(id, name);
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
}
