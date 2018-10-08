package com.kevin;

import java.util.HashMap;

import java.util.Map;

public class Performance {
    private Map<String, History> resultList =new HashMap();

    public void showResults(){

        for(int i = 0; i< resultList.size(); i++){
            History history = resultList.get(i);
            System.out.println("At the level "+ history.getLevel()+" there was a result equal to "+ history.getResult());
        }
    }

    public boolean deleteResult(int index){
        if(resultList.get(index)!=null) {
            resultList.remove(index);
            return true;
        }
        return false;
    }

    public void addPerformance(String string, History history){
        resultList.put(string, history);
    }
}
