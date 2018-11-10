package com.kevin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class History {
    private int result;

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

    public int getResult() {
        return result;
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