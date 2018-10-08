package com.kevin;

import java.util.HashMap;

import java.util.Iterator;
import java.util.Map;

public class Performance {
    private Map<String, History> resultList =new HashMap();

    public void printMap(Map<String,History> map){
        for(Map.Entry<String, History> entry:map.entrySet()){
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
        resultList.put(string, history);
    }
}
