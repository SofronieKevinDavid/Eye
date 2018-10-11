package com.kevin;

import java.util.*;

public class Performance {
    private Map<String, List<History>> resultList =new HashMap();
    private RunnedGame runnedGame;
    public void printMap(Map<String,List<History>> map){
        for(Map.Entry<String, List<History>> entry:map.entrySet()){
            String key=entry.getKey();
            Object value=entry.getValue();
            System.out.println(key+" ---> "+value);
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
        if(resultList.get(string)==null){
            resultList.put(string, new ArrayList<>());
        }
        resultList.put(string, (List<History>) history);
    }
}
