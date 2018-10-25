package com.kevin;

import java.util.*;

public class Performance {
    private Map<String, List<History>> resultList =new HashMap();
    private RunnedGame runnedGame;
    public void printMap(Map<String,List<History>> map){
        for(Map.Entry<String, List<History>> entry:map.entrySet()){
            String key=entry.getKey();
            List<History> list=entry.getValue();
            System.out.print(key+" ---> ");
            for(int i=0;i<list.size();i++){
                System.out.println(list.get(i).getResult()+", ");
            }
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

        if(resultList.containsKey(string)){
            List<History> list = resultList.get(string);
            list.add(history);
        }else {
            List<History> performance=new ArrayList<>();
            resultList.put(string, performance);
            performance.add(history);
        }

    }
}
