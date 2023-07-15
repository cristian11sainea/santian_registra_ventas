package com.santian.repository.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {
    @Id
    public String id;
    public String name;
    public String typeUser;

    public User() {
    }

    public User(String name, String typeUser) {
        this.name = name;
        this.typeUser = typeUser;
    }

    public User(String id, String name, String typeUser) {

        this.id = id;
        this.name = name;
        this.typeUser = typeUser;
    }

    public static User updateUser(String id, String name, String typeUser){

        return new User(id, name, typeUser);
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

    public String getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
    }
}
