package com.kevin.dto;

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
}
