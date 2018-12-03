/*
package com.kevin.domain;


import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "performances")
public class Performance {
    @Id
    @Column(name="Id")
    @GeneratedValue(generator = "performance_generator")
    @SequenceGenerator(
            name = "performance_generator",
            sequenceName = "performance_sequence",
            initialValue = 1
    )
    private long ID;
    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="runned_Game_Id")
    private Map<String, List<History>> resultList =new HashMap();
    public void printMap(Map<String,List<History>> map, RunnedGame runnedGame){
        for(Map.Entry<String, List<History>> entry:map.entrySet()){
            String key=entry.getKey();
            List<History> list=entry.getValue();
            System.out.print(key+" ---> ");
            for(int i=0;i<list.size();i++){
                System.out.println(list.get(i).getResult(runnedGame)+", ");
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



    public Map<String, List<History>> getResultList() {
        return resultList;
    }

    public void setResultList(Map<String, List<History>> resultList) {
        this.resultList = resultList;
    }
}
*/
