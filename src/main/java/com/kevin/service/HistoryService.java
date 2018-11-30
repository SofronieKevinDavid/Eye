
package com.kevin.service;

import com.kevin.domain.History;
import com.kevin.persistance.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    public void saveHistory(History history){
        if(history.getResult()==-1){
            throw new IllegalArgumentException("Result not valid.");
        }
        //if(history.getID()==0){
            //throw new IllegalArgumentException("Name can not be null.");
        //}

        try {
            historyRepository.save(history);
        }catch (Exception e){
            System.out.println("Error in saving user "+e);
        }
    }
}

