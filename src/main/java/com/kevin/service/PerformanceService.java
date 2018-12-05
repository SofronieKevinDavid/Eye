package com.kevin.service;

import com.kevin.DTO.HistoryDTO;
import com.kevin.DTO.PerformanceDTO;
import com.kevin.DTO.UserDTO;
import com.kevin.domain.History;
import com.kevin.domain.Performance;
import com.kevin.domain.RunnedGame;
import com.kevin.domain.User;
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
        //if(performance.getID()==0){
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
            performanceDTO.setID(performance.getID());

            List<History> histories = performance.getResultList();
            int nrOfProd = 0;
            while (nrOfProd < 5 && histories.size() >= 5) {

                History history = histories.get(nrOfProd);
                HistoryDTO historyDTO = new HistoryDTO();

                historyDTO.setID(history.getID());
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
