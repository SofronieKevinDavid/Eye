
package com.kevin.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private double result;
    @Column(name="date")
    private LocalDateTime date;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="runnedGameId")
    private RunnedGame runnedGame;


    private LocalDateTime getDate(){
       return date=LocalDateTime.now();
    }

    public History() {
        //this.date=getDate();
    }

    public double getResult() {
        //eroare cu nullpointerexception:
        return runnedGame.medium();
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

