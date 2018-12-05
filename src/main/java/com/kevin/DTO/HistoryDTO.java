package com.kevin.DTO;

import com.kevin.domain.RunnedGame;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HistoryDTO {
    private long ID;

    private double result;
    private String date;

    private RunnedGame runnedGame;


    private String getDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.now();
        String formattedDateTime = dateTime.format(formatter);
        return formattedDateTime;
    }

    public HistoryDTO() {
        this.date=getDate();
    }

    public double getResult(RunnedGame runnedGame) {
        return runnedGame.medium();
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

    @Override
    public String toString() {
        return "HistoryDTO{" +
                "ID=" + ID +
                ", result=" + result +
                ", date='" + date + '\'' +
                ", runnedGame=" + runnedGame +
                '}';
    }
}
