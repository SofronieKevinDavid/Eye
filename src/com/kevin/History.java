package com.kevin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class History {
    private int result;
    private int level;
    private String date;
    private RunedGame runedGame;


    private String getDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public History() {
        this.date=getDate();
        this.level=20;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "History{" +
                "result=" + result +
                ", level=" + level +
                ", date='" + date + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof History)) return false;
        History history = (History) o;
        return getResult() == history.getResult() &&
                getLevel() == history.getLevel() &&
                Objects.equals(getDate(), history.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getResult(), getLevel(), getDate());
    }
}
