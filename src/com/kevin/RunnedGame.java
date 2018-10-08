package com.kevin;

public class RunnedGame extends GameDefinition {
    private int level;

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

    private boolean ifCorrect(boolean raspuns){
        if(raspuns){
            numberOfCorrectAnswers++;
            return true;
        }else{
            return false;
        }
    }





}
