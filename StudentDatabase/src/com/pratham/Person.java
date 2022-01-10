package com.pratham;

public class Person {

    String name = "";

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String print() {
        String retValue = "Person - Name: " + name;
        return retValue;
    }

}
