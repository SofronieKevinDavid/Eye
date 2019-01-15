package com.kevin.dto;

import com.kevin.domain.GameDefinition;
import com.kevin.domain.User;

import java.util.Objects;

public class RunnedGameDTO {
    private long ID;
    private int level;

    private GameDefinitionDTO gameDefinitionDTO;
    private UserDTO userDTO;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public RunnedGameDTO() {

    }

    public GameDefinitionDTO getGameDefinitionDTO() {
        return gameDefinitionDTO;
    }

    public void setGameDefinitionDTO(GameDefinitionDTO gameDefinitionDTO) {
        this.gameDefinitionDTO = gameDefinitionDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public long getGameDefinitionId() {
        if (gameDefinitionDTO != null) {
            return gameDefinitionDTO.getID();
        } else {
            return 0;
        }

    }

    public long getUserId() {
        if (userDTO != null) {
            return gameDefinitionDTO.getID();
        } else {
            return 0;
        }
    }

    public RunnedGameDTO(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    @Override
    public String toString() {
        return "RunnedGameDTO{" +
                "ID=" + ID +
                ", level=" + level +
                ", gameDefinitionDTO=" + gameDefinitionDTO +
                ", userDTO=" + userDTO +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RunnedGameDTO that = (RunnedGameDTO) o;
        return ID == that.ID &&
                level == that.level &&
                Objects.equals(gameDefinitionDTO, that.gameDefinitionDTO) &&
                Objects.equals(userDTO, that.userDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, level, gameDefinitionDTO, userDTO);
    }
}
