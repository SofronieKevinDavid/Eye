package com.kevin.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "histories")
public class History {
    @Id
    @Column(name = "Id")
    @GeneratedValue(generator = "history_generator")
    @SequenceGenerator(
            name = "history_generator",
            sequenceName = "history_sequence",
            initialValue = 1
    )
    private long id;

    @Column(name = "result")
    private double result;

    @Column(name = "date")
    private Date date;

    ////////////////////////
    @JoinColumn(name = "ran_Game_Id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private RanGame ranGame;

    public RanGame getRanGame() {
        return ranGame;
    }

    public void setRanGame(RanGame ranGame) {
        this.ranGame = ranGame;
    }

    public double getResult() {
        return this.result;
    }

    public void setDate(Date date){this.date=date;
    }

    public Date getDate() {
        return date;
    }

    public Date getDatePublic(){
        return date;
    }

    public History() {
        this.date = getDate();
    }

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "histories_user_Id_fk")
    private User historyUser;

    public User getHistoryUser() {
        return historyUser;
    }

    public void setHistoryUser(User historyUser) {
        this.historyUser = historyUser;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setResult(double result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        History history = (History) o;
        return id == history.id &&
                Double.compare(history.result, result) == 0 &&
                Objects.equals(date, history.date) &&
                Objects.equals(ranGame, history.ranGame) &&
                Objects.equals(historyUser, history.historyUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, result, date, ranGame, historyUser);
    }

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", result=" + result +
                ", date=" + date +
                ", ranGame=" + ranGame +
                ", historyUser=" + historyUser +
                '}';
    }
}
////////////////////////////////////////////////////////////////////////////////////////////////
