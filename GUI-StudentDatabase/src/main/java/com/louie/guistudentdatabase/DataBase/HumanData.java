package com.louie.guistudentdatabase.DataBase;

public class HumanData {
    protected String name;
    protected String email;
    protected int age;

    public HumanData(String name, int age, String email) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public int getAge() {
        return this.age;
    }

    public String toString() {
        return this.name;
    }
}
