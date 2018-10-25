package com.kevin;

public class RunnedGame {
    private int level;
    private GameDefinition gameDefinition;
    private User user;

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
