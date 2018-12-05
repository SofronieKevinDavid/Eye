package com.kevin.DTO;

import com.kevin.domain.History;
import com.kevin.domain.RunnedGame;

import java.util.ArrayList;
import java.util.List;

public class PerformanceDTO {
    private long ID;
    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    private List<History> resultList =new ArrayList<>();

    public List<History> getResultList() {
        return resultList;
    }

    public void setResultList(List<History> resultList) {
        this.resultList = resultList;
    }

    public void printMap(List<History> list, RunnedGame runnedGame){
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i).getResult(runnedGame));
        }
    }
    public boolean deleteResult(int index){
        if(resultList.get(index)!=null) {
            resultList.remove(index);
            return true;
        }
        return false;
    }
    public boolean addPerformance(History history){

        if(resultList.contains(history)==true){
            resultList.add(history);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String toString() {
        return "PerformanceDTO{" +
                "ID=" + ID +
                ", resultList=" + resultList +
                '}';
    }
}
