package com.kevin.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

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

    public long getRunnedGameId(){
        return runnedGameDTO.getId();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoryDTO that = (HistoryDTO) o;
        return ID == that.ID &&
                Double.compare(that.result, result) == 0 &&
                Objects.equals(date, that.date) &&
                Objects.equals(runnedGameDTO, that.runnedGameDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, result, date, runnedGameDTO);
    }
}
