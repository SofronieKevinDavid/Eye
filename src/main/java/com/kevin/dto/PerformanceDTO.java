package com.kevin.dto;

import com.kevin.domain.History;
import com.kevin.domain.RunnedGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PerformanceDTO {
    private long ID;
    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    private List<HistoryDTO> resultList =new ArrayList<>();

    public List<HistoryDTO> getResultList() {
        return resultList;
    }



    public void setResultList(List<HistoryDTO> resultList) {
        this.resultList = resultList;
    }

    public void printList(List<History> list){
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i).getResult());
        }
    }
    public boolean deleteResult(int index){
        if(resultList.get(index)!=null) {
            resultList.remove(index);
            return true;
        }
        return false;
    }
    public boolean addPerformance(HistoryDTO historyDTO){

        if(resultList.contains(historyDTO)==true){
            resultList.add(historyDTO);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PerformanceDTO that = (PerformanceDTO) o;
        return ID == that.ID &&
                Objects.equals(resultList, that.resultList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, resultList);
    }
}
