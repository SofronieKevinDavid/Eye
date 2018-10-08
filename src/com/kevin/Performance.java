package com.kevin;

import java.util.HashMap;

import java.util.Iterator;
import java.util.Map;

public class Performance {
    private Map<String, History> resultList =new HashMap();

    public void printMap(Map map){
        Iterator it=map.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pair=(Map.Entry) it.next();
            System.out.println(pair.getKey()+" = "+pair.getValue());
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
