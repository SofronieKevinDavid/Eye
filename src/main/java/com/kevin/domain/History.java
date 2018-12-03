
package com.kevin.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

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
    @JoinColumn(name="runned_Game_Id")
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

    public double getResult(RunnedGame runnedGame) {
        //eroare cu nullpointerexception:
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        History history = (History) o;
        return ID == history.ID &&
                Double.compare(history.result, result) == 0 &&
                Objects.equals(date, history.date) &&
                Objects.equals(runnedGame, history.runnedGame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, result, date, runnedGame);
    }

    @Override
    public String toString() {
        return "History{" +
                "ID=" + ID +
                ", result=" + result +
                ", date='" + date + '\'' +
                ", runnedGame=" + runnedGame +
                '}';
    }
}

