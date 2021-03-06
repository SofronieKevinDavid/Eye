package com.kevin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.Objects;


public class HistoryDTO {
    private long ID;

    private double result;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date date;

    private int ranGameLevel;
    private String gameName;


    private long userId;

    private long ranGameId;

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

    public String getGameName() {
        return gameName;
    }

    public int getRanGameLevel() {
        return ranGameLevel;
    }

    public void setRanGameLevel(int ranGameLevel) {
        this.ranGameLevel = ranGameLevel;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getRanGameId() {
        return ranGameId;
    }

    public void setRanGameId(long ranGameId) {
        this.ranGameId = ranGameId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoryDTO that = (HistoryDTO) o;
        return ID == that.ID &&
                Double.compare(that.result, result) == 0 &&
                date.equals(that.date) &&
                userId == that.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, result, date,
                userId);
    }
}