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
    private long ID;
    @Column(name = "name")
    private String name;

    public boolean validateAnswer(String answer){
        return true;
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

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }
}
