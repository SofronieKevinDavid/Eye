package com.kevin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class HistoryDTO {
    private long ID;

    private double result;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
    private Date date;

    private RunnedGameDTO runnedGameDTO;


    public void setDate(Date date){this.date=date;
    }

    public Date getDatePublic(){
        Date dateTime=new Date();
        return dateTime;
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

    private UserDTO historyUserDTO;

    public UserDTO getHistoryUserDTO() {
        return historyUserDTO;
    }

    public void setHistoryUserDTO(UserDTO historyUserDTO) {
        this.historyUserDTO = historyUserDTO;
    }

    public long getHistoryUserDTOId(){
        return historyUserDTO.getID();
    }

    public HistoryDTO() {

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoryDTO that = (HistoryDTO) o;
        return ID == that.ID &&
                Double.compare(that.result, result) == 0 &&
                Objects.equals(date, that.date) &&
                Objects.equals(runnedGameDTO, that.runnedGameDTO) &&
                Objects.equals(historyUserDTO, that.historyUserDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, result, date, runnedGameDTO, historyUserDTO);
    }

    @Override
    public String toString() {
        return "HistoryDTO{" +
                "ID=" + ID +
                ", result=" + result +
                ", date=" + date +
                ", runnedGameDTO=" + runnedGameDTO +
                ", historyUserDTO=" + historyUserDTO +
                '}';
    }
}
