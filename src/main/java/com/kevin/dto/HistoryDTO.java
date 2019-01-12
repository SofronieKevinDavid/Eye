package com.kevin.dto;

import com.kevin.domain.RunnedGame;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HistoryDTO {
    private long ID;

    private double result;
    private String date;

    private RunnedGameDTO runnedGameDTO;


    private String getDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.now();
        String formattedDateTime = dateTime.format(formatter);
        return formattedDateTime;
    }

    public RunnedGameDTO getRunnedGameDTO() {
        return runnedGameDTO;
    }

    public void setRunnedGameDTO(RunnedGameDTO runnedGameDTO) {
        this.runnedGameDTO = runnedGameDTO;
    }

    public HistoryDTO() {
        this.date=getDate();
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

    public void setDate(String date) {
        this.date = date;
    }

    public String getDatePublic(){
        return date;
    }

    @Override
   public String toString() {
        return "HistoryDTO{" +
                "ID=" + ID +
                ", result=" + result +
                ", date='" + date + '\'' +
                ", runnedGameDTO=" + runnedGameDTO +
                '}';
    }
}
