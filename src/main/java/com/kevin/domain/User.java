package com.kevin.domain;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(generator = "user_generator")
    @SequenceGenerator(
            name = "user_generator",
            sequenceName = "user_sequence",
            initialValue = 1
    )
    @Column(name="userid")
    private long ID;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;

    public User(String nume, int age) {
        this.name = nume;
        this.age = age;
    }

    public long getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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
