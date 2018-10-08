package com.kevin;

public class RunedGame extends GameDefinition {
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
