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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
    private Date date;

    private RunnedGameDTO runnedGameDTO;


    public Date getDate() {
        Date dateTime = new Date();
        return dateTime;
    }

    public void setDate(Date date){this.date=date;
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

    //TODO this is not ok .. nu returneaza userid ci history id - nu o sa poti salva 2 history pt un user asa
    //normal in history object nu ar trebui sa ai nici user dto nici gameDefinition dto
    // ar trebui sa ai user id , gameDefinition id, si eventual game name  si cam atata
    public long getHistoryUserDTOId(){
        return historyUserDTO.getID();
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
