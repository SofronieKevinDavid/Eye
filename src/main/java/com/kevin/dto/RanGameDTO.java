package com.kevin.dto;

import java.util.Objects;

public class RanGameDTO {
    private long id;
    private int level;

    private long gameDefinitionId;
    private long userId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RanGameDTO() {

    }

    public long getGameDefinitionId() {
        return gameDefinitionId;
    }

    public void setGameDefinitionId(long gameDefinitionId) {
        this.gameDefinitionId = gameDefinitionId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RanGameDTO{");
        sb.append("id=").append(id);
        sb.append(", level=").append(level);
        sb.append(", gameDefinitionId=").append(gameDefinitionId);
        sb.append(", userId=").append(userId);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RanGameDTO that = (RanGameDTO) o;
        return id == that.id &&
                level == that.level &&
                gameDefinitionId == that.gameDefinitionId &&
                userId == that.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, level, gameDefinitionId, userId);
    }
}