package com.kevin.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gameDefinitions")
public class GameDefinition {

    @Id
    private long ID;
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
