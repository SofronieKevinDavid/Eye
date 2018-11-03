package com.kevin;


public class User {
    private static int ID = 0;
    private String name;
    private int age;

    public User(String nume, int age) {
        this.name = nume;
        this.age = age;
        this.ID++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
