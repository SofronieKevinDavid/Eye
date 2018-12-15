
package com.kevin.service;


import com.kevin.dto.HistoryDTO;
import com.kevin.domain.History;
import com.kevin.domain.RunnedGame;
import com.kevin.dto.RunnedGameDTO;


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


    public void saveHistory(HistoryDTO historyDTO){
        RunnedGameDTO runnedGameDTO=new RunnedGameDTO();
        if(historyDTO.getResult(runnedGameDTO)==-1){
            throw new IllegalArgumentException("Result not valid.");
        }

        History historyObject=convert(historyDTO);

        try {
            historyRepository.save(historyObject);
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

    private HistoryDTO convertToDto(History history) {
        HistoryDTO historyDTO = new HistoryDTO();
        //RunnedGame runnedGame=new RunnedGame();-pt a se putea da data din postman
        historyDTO.setResult(history.getResult());
        historyDTO.setDate(history.getDatePublic());
        historyDTO.setID(history.getId());
        return historyDTO;
    }

    private History convert(HistoryDTO historyDTO) {
        History history = new History();
        //RunnedGameDTO runnedGameDTO=new RunnedGameDTO();-pt a se putea da data din postman
        history.setResult(historyDTO.getResult());
        history.setDate(historyDTO.getDatePublic());
        history.setId(historyDTO.getID());
        return history;
    }

    public HistoryDTO getHistoryById(long id) {
        History history=historyRepository.findOne(id);
        if(history==null){
            throw new IllegalArgumentException("The id is not valid.");
        }
        return convertToDto(history);
    }

    public HistoryDTO updateHistory(long id,HistoryDTO dto) {
        History history=historyRepository.findOne(id);
        //RunnedGameDTO runnedGameDTO=new RunnedGameDTO();-pt a se putea da data din postman
        history.setResult(dto.getResult());
        history.setDate(dto.getDatePublic());

        History savedObject= historyRepository.save(history);

        return convertToDto(savedObject);
    }
}

