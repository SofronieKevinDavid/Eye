package com.kevin.dto;

import com.kevin.domain.GameDefinition;
import com.kevin.domain.User;

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

    public long getGameDefinitionId(){
        return gameDefinitionDTO.getID();
    }

    public long getUserId(){
        return gameDefinitionDTO.getID();
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
}
