
package com.kevin.domain;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity
@Table(name = "histories")
public class History {
    @Id
    @Column(name="Id")
    @GeneratedValue(generator = "history_generator")
    @SequenceGenerator(
            name = "history_generator",
            sequenceName = "history_sequence",
            initialValue = 1
    )
    private long ID;
    @Column(name="result")
    private double result;
    @Column(name="date")
    private String date;
    @JoinColumn(name="runnedGameId")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private RunnedGame runnedGame;


    private String getDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.now();
        String formattedDateTime = dateTime.format(formatter);
        return formattedDateTime;
    }

    public History() {
        this.date=getDate();
    }

    public double getResult() {
        //eroare cu nullpointerexception:
        //return runnedGame.medium();
        return 178;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    //public void setResult(int result) {
        //this.result = result;
    //}

    /*@Override
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
    }*/
}

