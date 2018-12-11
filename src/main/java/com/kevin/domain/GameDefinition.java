package com.kevin.domain;

import javax.persistence.*;

@Entity
@Table(name = "gamedefinitions")
public class GameDefinition {

    @Id
    @Column(name="id")
    @GeneratedValue(generator = "gameDefinition_generator")
    @SequenceGenerator(
            name = "gameDefinition_generator",
            sequenceName = "gameDefinition_sequence",
            initialValue = 1
    )
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name="description")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean validateAnswer(String answer){
        return true;
    }

    public GameDefinition() {
        this.name="defaultConstructor";
    }

    public GameDefinition(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
