package com.kevin.domain;

import javax.persistence.*;

@Entity
@Table(name = "runnedgames")
public class RunnedGame {
    @Id
    @Column(name="runnedgameid")
    @GeneratedValue(generator = "runnedGame_generator")
    @SequenceGenerator(
            name = "runnedGame_generator",
            sequenceName = "runnedGame_sequence",
            initialValue = 1
    )
    private long ID;
    @Column(name="level")
    private int level;
    private GameDefinition gameDefinition;
    private User user;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public RunnedGame() {
        this.level=20;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    private int numberOfCorrectAnswers =0;

    private int totalNumberOfResults;

    public double medium(){
        return numberOfCorrectAnswers / totalNumberOfResults;
    }
    public String stringMedium(){
        return numberOfCorrectAnswers +"/"+ totalNumberOfResults;
    }
}
