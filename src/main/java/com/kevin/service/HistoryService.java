
package com.kevin.service;

import com.kevin.dto.HistoryDTO;
import com.kevin.domain.History;
import com.kevin.domain.RunnedGame;
import com.kevin.persistance.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    public void saveHistory(History history, RunnedGame runnedGame){
        if(history.getResult(runnedGame)==-1){
            throw new IllegalArgumentException("Result not valid.");
        }
        //if(history.getId()==0){
            //throw new IllegalArgumentException("Name can not be null.");
        //}

        try {
            historyRepository.save(history);
        }catch (Exception e){
            System.out.println("Error in saving user "+e);
        }
    }


    @Transactional
    public List<HistoryDTO> getHistories() {
        Iterator<History> iterator =
                historyRepository.findAll().iterator();


        List<HistoryDTO> list = new ArrayList<>();

        while (iterator.hasNext()) {
            History history = iterator.next();

            HistoryDTO historyDTO = new HistoryDTO();
            RunnedGame runnedGame= new RunnedGame();
            historyDTO.setResult(history.getResult(runnedGame));
            historyDTO.setID(history.getId());


            list.add(historyDTO);
        }

        return list;
    }
}

