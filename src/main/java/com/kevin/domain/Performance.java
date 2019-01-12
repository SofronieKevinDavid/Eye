package com.kevin.domain;


import com.kevin.dto.HistoryDTO;

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
    private long id;

    public Performance() {
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "performances_history_Id_fk", nullable = true)
    private List<History> resultList = new ArrayList<>();

    public List<History> getResultList() {
        return resultList;
    }
    public List<HistoryDTO> getResultListInDto() {
        List<HistoryDTO> list=new ArrayList<>();
        for(int i=0;i<resultList.size();i++) {
            History history=resultList.get(i);
            HistoryDTO historyDTO = new HistoryDTO();
            historyDTO.setDate(history.getDatePublic());
            historyDTO.setResult(history.getResult());
            historyDTO.setID(history.getId());
            list.add(historyDTO);
        }
        return list;
    }

    public void setResultList(List<History> resultList) {
        this.resultList = resultList;
    }

    public void printMap(List<History> list, RunnedGame runnedGame) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getResult());
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

    @Override
    public String toString() {
        return "Performance{" +
                "id=" + id +
                ", resultList=" + resultList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Performance that = (Performance) o;
        return id == that.id &&
                Objects.equals(resultList, that.resultList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, resultList);
    }
}
