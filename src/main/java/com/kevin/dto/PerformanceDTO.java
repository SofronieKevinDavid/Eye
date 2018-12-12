package com.kevin.dto;

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

    private List<HistoryDTO> resultList =new ArrayList<>();

    public List<HistoryDTO> getResultList() {
        return resultList;
    }

    public List<History> getResultListAsNotDTO() {
        List<History> list=new ArrayList<>();
        for(int i=0;i<resultList.size();i++) {
            HistoryDTO historyDTO=resultList.get(i);
            History history = new History();
            RunnedGameDTO runnedGameDTO=new RunnedGameDTO();
            history.setDate(historyDTO.getDatePublic());
            history.setResult(historyDTO.getResult(runnedGameDTO));
            history.setId(historyDTO.getID());
            list.add(history);
        }
        return list;
    }

    public void setResultList(List<HistoryDTO> resultList) {
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
}
