package com.kevin.web;



import com.kevin.domain.History;
import com.kevin.dto.HistoryDTO;
import com.kevin.service.HistoryService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class HistoryController {


    @Autowired
    private HistoryService historyService;

    @RequestMapping(path = "/history/{id}", method = RequestMethod.GET)
    public HistoryDTO getHistory(@PathVariable("id") long id){
        return historyService.getHistoryById(id);
    }

    @RequestMapping(path = "/history", method = RequestMethod.GET)
    public List<HistoryDTO> getHistoryForUser(@RequestParam long userID){
        return historyService.getHistoryForUserID(userID);
    }

    @RequestMapping(path="/history", method=RequestMethod.POST)
    public History saveHistory(@RequestBody HistoryDTO historyDTO){
        History history=historyService.saveHistory(historyDTO);
        return history;
    }

    @RequestMapping(path="/history/{id}", method=RequestMethod.PUT)
    public HistoryDTO updateHistory(@PathVariable long id, @RequestBody HistoryDTO dto){
        return historyService.updateHistory(id, dto);
    }

    @RequestMapping(path = "/history/{id}", method =RequestMethod.DELETE)
    public void deleteHistory(@PathVariable("id") long id){
        historyService.deleteHistoryById(id);
    }
}
