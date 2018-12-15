package com.kevin.web;



import com.kevin.dto.HistoryDTO;
import com.kevin.service.HistoryService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController

public class HistoryController {


    @Autowired
    private HistoryService historyService;

    @RequestMapping(path = "/history/{id}", method = RequestMethod.GET)
    public HistoryDTO getHistory(@PathVariable("id") long id){
        return historyService.getHistoryById(id);
    }

    @RequestMapping(path="/history", method=RequestMethod.POST)
    public void saveUser(@RequestBody HistoryDTO historyDTO){
        historyService.saveHistory(historyDTO);
    }

    @RequestMapping(path="/history/{id}", method=RequestMethod.PUT)
    public HistoryDTO updateUser(@PathVariable long id, @RequestBody HistoryDTO dto){
        return historyService.updateHistory(id, dto);
    }

    @RequestMapping(path = "/history/{id}", method =RequestMethod.DELETE)
    public void deleteHistory(@PathVariable("id") long id){
        historyService.deleteHistoryById(id);
    }
}
