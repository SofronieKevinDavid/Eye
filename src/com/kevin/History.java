package com.kevin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class History {
    private int result;
    private int level;
    private String date;


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
}
