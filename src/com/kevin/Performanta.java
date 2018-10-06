package com.kevin;

import java.util.ArrayList;
import java.util.List;

public class Performanta {
    private List<Istoric> listaRezultate =new ArrayList<>();

    public void afisareRezultate(){
        for(int i=0;i<listaRezultate.size();i++){
            Istoric istoric=listaRezultate.get(i);
            System.out.println("La nivelul "+istoric.getLevel()+" s-a avut un rezultat egal cu "+istoric.getRezultat());
        }
    }

    public boolean stergereRezultat(int index){
        if(listaRezultate.get(index)!=null) {
            listaRezultate.remove(index);
            return true;
        }
        return false;
    }
}
