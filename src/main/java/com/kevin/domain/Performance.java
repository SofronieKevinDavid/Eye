package com.kevin.domain;


import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "performances")
public class Performance {
    @Id
    @Column(name = "Id")
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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "performances_history_Id_fk", nullable = true)
    private List<History> resultList = new ArrayList<>();

    public List<History> getResultList() {
        return resultList;
    }

    public void setResultList(List<History> resultList) {
        this.resultList = resultList;
    }

    public void printMap(List<History> list, RunnedGame runnedGame) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getResult(runnedGame));
        }
    }

    public boolean deleteResult(int index) {
        if (resultList.get(index) != null) {
            resultList.remove(index);
            return true;
        }
        return false;
    }

    public boolean addPerformance(History history) {

        if (resultList.contains(history) == true) {
            resultList.add(history);
            return true;
        } else {
            return false;
        }
    }
}
