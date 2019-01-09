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

    public void savePerformance(PerformanceDTO performanceDTO){
        Performance performanceObject=convert(performanceDTO);
        try {
            performanceRepository.save(performanceObject);
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

            while(nrOfProd<histories.size()){
                History history = histories.get(nrOfProd);
                HistoryDTO historyDTO = new HistoryDTO();

                historyDTO.setID(history.getId());
                RunnedGame runnedGame=new RunnedGame();
                historyDTO.setResult(history.getResult());

                performanceDTO.getResultList().add(historyDTO);
                nrOfProd++;
            }
            list.add(performanceDTO);
        }

        return list;
    }

    private PerformanceDTO convertToDto(Performance performance) {
        PerformanceDTO performanceDTO = new PerformanceDTO();
        performanceDTO.setResultList(performance.getResultListInDto());
        performanceDTO.setID(performance.getId());
        return performanceDTO;
    }

    private Performance convert(PerformanceDTO performanceDTO) {
        Performance performance = new Performance();
//
        performance.setResultList(getResultListAsNotDTO(performanceDTO));
        performance.setId(performanceDTO.getID());
        return performance;
    }

    public PerformanceDTO getPerformanceById(long id) {
        Performance performance=performanceRepository.findOne(id);
        if(performance==null){
            throw new IllegalArgumentException("The id is not valid.");
        }
        return convertToDto(performance);
    }

    public PerformanceDTO updatePerformance(long id,PerformanceDTO dto) {
        Performance performance=performanceRepository.findOne(id);
//
        performance.setResultList(getResultListAsNotDTO(dto));


        Performance savedObject= performanceRepository.save(performance);

        return convertToDto(savedObject);
    }

    public boolean deletePerformanceById(long id){
        if(performanceRepository.findOne(id)!=null) {
            performanceRepository.delete(id);
            return true;
        }
        return false;
    }
//
    public List<History> getResultListAsNotDTO(PerformanceDTO performanceDTO) {
        List<HistoryDTO> resultList=performanceDTO.getResultList();
        List<History> list=new ArrayList<>();
        for(int i=0;i<resultList.size();i++) {
            HistoryDTO historyDTO=resultList.get(i);
            History history = new History();

            history.setDate(historyDTO.getDatePublic());
            history.setResult(historyDTO.getResult());
            history.setId(historyDTO.getID());
            list.add(history);
        }
        return list;
    }
}
