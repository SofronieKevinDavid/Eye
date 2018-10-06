package com.kevin;

public class Joc {
    private int numartDeRaspunsuriCorecte=0;

    private int numarTotalRaspunsuri;

    public double medie(){
        return numartDeRaspunsuriCorecte/numarTotalRaspunsuri;
    }
    public String medieString(){
        return numartDeRaspunsuriCorecte+"/"+numarTotalRaspunsuri;
    }

    private boolean esteCorect(boolean raspuns){
        if(raspuns){
            numartDeRaspunsuriCorecte++;
            return true;
        }else{
            return false;
        }
    }





}
