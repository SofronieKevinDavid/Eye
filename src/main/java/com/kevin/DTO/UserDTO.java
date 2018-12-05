package com.kevin.DTO;

public class UserDTO {
    private long ID;
    private String name;

    public UserDTO(String nume) {
        this.name = nume;
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
}
