package com.kevin.domain;

import javax.persistence.*;

@Entity
@Table(name = "runnedgames")
public class RunnedGame {
    @Id
    @Column(name="id")
    @GeneratedValue(generator = "runnedGame_generator")
    @SequenceGenerator(
            name = "runnedGame_generator",
            sequenceName = "runnedGame_sequence",
            initialValue = 1
    )
    private long id;
    @Column(name="level")

    private int level;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="game_Definition_Id")
    private GameDefinition gameDefinition;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_Id")
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public GameDefinition getGameDefinition() {
        return gameDefinition;
    }

    public void setGameDefinition(GameDefinition gameDefinition) {
        this.gameDefinition = gameDefinition;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RunnedGame() {

    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


}

