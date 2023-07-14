package com.santian.repository.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Person {
    @Id
    public String id;
    public String name;
    public String lastName;
    public Long document;
    public  String typePerson;

    public Person() {
    }

    public Person (String name, String lastName, Long document, String typePerson) {
        this.name = name;
        this.lastName = lastName;
        this.document = document;
        this.typePerson = typePerson;
    }
    public Person(String id, String name, String lastName, Long document, String typePerson) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.document = document;
        this.typePerson = typePerson;
    }

    public static Person updatePerson(String id, String name, String lastName, Long document, String typePerson){
        return new Person(id, name, lastName, document, typePerson);
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getDocument() {
        return document;
    }

    public void setDocument(Long document) {
        this.document = document;
    }

    public String getTypePerson() {
        return typePerson;
    }

    public void setTypePerson(String typePerson) {
        this.typePerson = typePerson;
    }
}
