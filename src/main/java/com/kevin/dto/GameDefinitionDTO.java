package com.kevin.dto;

import java.util.Objects;

public class GameDefinitionDTO {
    private long ID;
    private String name;

    public GameDefinitionDTO() {

    }
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
    public boolean validateAnswer(String answer){
        return true;
    }

    public GameDefinitionDTO(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GameDefinitionDTO{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameDefinitionDTO that = (GameDefinitionDTO) o;
        return ID == that.ID &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, description);
    }
}
