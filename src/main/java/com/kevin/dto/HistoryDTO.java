package com.kevin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.Objects;


public class HistoryDTO {
    private long ID;

    private double result;

    //DTO urile se convertesc in json cu Jackson nu modelul!
    //DTOurile sunt expuse pe api, nu modelul
    //annotarea de jackson trebuie sa fie aici in dto ca sa stie jackson cum sa converteasca acest date cand creaza jsonul
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date date;

    private long gameId;


    private long userId;
    private String username;

    private long runnedGameId;

    public Date getDate() {
        Date dateTime = new Date();
        return dateTime;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public HistoryDTO() {
        this.date = getDate();
    }

    public double getResult() {
        return this.result;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getRunnedGameId() {
        return runnedGameId;
    }

    public void setRunnedGameId(long runnedGameId) {
        this.runnedGameId = runnedGameId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoryDTO that = (HistoryDTO) o;
        return ID == that.ID &&
                Double.compare(that.result, result) == 0 &&
                gameId == that.gameId &&
                date.equals(that.date) &&
                userId == that.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, result, date, gameId, userId);
    }
}
