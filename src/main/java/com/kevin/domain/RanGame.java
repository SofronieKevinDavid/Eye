package com.kevin.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "rangames")
public class RanGame {
    @Id
    @Column(name="id")
    @GeneratedValue(generator = "ranGame_generator")
    @SequenceGenerator(
            name = "ranGame_generator",
            sequenceName = "ranGame_sequence",
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

    public RanGame() {

    }

    public int getLevel() {
        return level;
    }

    public long getGameDefinitionId(){
        return gameDefinition.getId();

    }

    public long getUserId(){
        return user.getId();
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "RanGame{" +
                "id=" + id +
                ", level=" + level +
                ", gameDefinition=" + gameDefinition +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RanGame that = (RanGame) o;
        return id == that.id &&
                level == that.level &&
                Objects.equals(gameDefinition, that.gameDefinition) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, level, gameDefinition, user);
    }
}
