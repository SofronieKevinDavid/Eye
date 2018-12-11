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
    private long id;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
