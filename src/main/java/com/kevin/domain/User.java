package com.kevin.domain;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(generator = "user_generator")
    @SequenceGenerator(
            name = "user_generator",
            sequenceName = "user_games_sequence",
            initialValue = 1
    )
    @Column(name="id")
    private long ID;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;

    public User(){
        this.name="defaultConstructor";
        this.age=0;
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
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
