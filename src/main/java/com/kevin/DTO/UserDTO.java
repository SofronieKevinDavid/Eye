package com.kevin.DTO;

public class UserDTO {
    private long ID;
    private String name;

    public UserDTO(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "UserDTO{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                '}';
    }
}
