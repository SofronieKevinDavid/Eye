package com.kevin.domain;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "histories")
public class History {
    @Id
    @Column(name="historyId")
    @GeneratedValue(generator = "history_generator")
    @SequenceGenerator(
            name = "history_generator",
            sequenceName = "history_sequence",
            initialValue = 1
    )
    private long ID;
    @Column(name="result")
    private int result;
    @Column(name="date")
    private String date;
    private RunnedGame runnedGame;


    private String getDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public History() {
        this.date=getDate();
    }

    public String getResult() {
        return runnedGame.stringMedium();
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "History{" +
                "result=" + result +
                ", date='" + date + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof History)) return false;
        History history = (History) o;
        return getResult() == history.getResult() &&
                Objects.equals(getDate(), history.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getResult(), getDate());
    }
}
