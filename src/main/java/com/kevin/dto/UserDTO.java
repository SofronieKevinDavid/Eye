package com.kevin.dto;

import java.util.Objects;

public class UserDTO {
    private long ID;
    private String name;

    public UserDTO() {
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return ID == userDTO.ID &&
                Objects.equals(name, userDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name);
    }
}
