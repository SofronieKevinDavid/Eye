package com.kevin.dto;

import com.kevin.domain.GameDefinition;
import com.kevin.domain.User;

public class RunnedGameDTO {
    private long ID;
    private int level;

    private GameDefinition gameDefinition;
    private User user;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public RunnedGameDTO() {

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

    private int numberOfCorrectAnswers =0;

    private int totalNumberOfResults=1;

    @Override
    public String toString() {
        return "RunnedGameDTO{" +
                "ID=" + ID +
                ", level=" + level +
                ", gameDefinition=" + gameDefinition +
                ", user=" + user +
                ", numberOfCorrectAnswers=" + numberOfCorrectAnswers +
                ", totalNumberOfResults=" + totalNumberOfResults +
                '}';
    }
}
