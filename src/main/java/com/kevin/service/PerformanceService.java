package com.kevin.service;

import com.kevin.dto.HistoryDTO;
import com.kevin.dto.PerformanceDTO;
import com.kevin.domain.History;
import com.kevin.domain.Performance;
import com.kevin.domain.RunnedGame;
import com.kevin.persistance.PerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class PerformanceService {

    @Autowired
    private PerformanceRepository performanceRepository;

    public void savePerformance(Performance performance){
        //if(performance.getId()==0){
            //throw new IllegalArgumentException("ID can not be null.");
        //}
        try {
            performanceRepository.save(performance);
        }catch (Exception e){
            System.out.println("Error in saving user "+e);
        }
    }


    @Transactional
    public List<PerformanceDTO> getPerformances() {
        Iterator<Performance> iterator =
                performanceRepository.findAll().iterator();


        List<PerformanceDTO> list = new ArrayList<>();

        while (iterator.hasNext()) {
            Performance performance = iterator.next();

            PerformanceDTO performanceDTO = new PerformanceDTO();
            performanceDTO.setID(performance.getId());

            List<History> histories = performance.getResultList();
            int nrOfProd = 0;
            //while (nrOfProd < 5 && histories.size() >= 5) {
            while(nrOfProd<histories.size()){
                History history = histories.get(nrOfProd);
                HistoryDTO historyDTO = new HistoryDTO();

                historyDTO.setID(history.getId());
                RunnedGame runnedGame=new RunnedGame();
                historyDTO.setResult(history.getResult(runnedGame));

                performanceDTO.getResultList().add(historyDTO);
                nrOfProd++;
            }
            list.add(performanceDTO);
        }

        return list;
    }
}
