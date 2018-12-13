package com.kevin.web;



import com.kevin.dto.PerformanceDTO;
import com.kevin.service.PerformanceService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController

public class PerformanceController {


    @Autowired
    private PerformanceService performanceService;

    @RequestMapping(path = "/performance/{id}", method = RequestMethod.GET)
    public PerformanceDTO getPerformance(@PathVariable("id") long id){
        return performanceService.getPerformanceById(id);
    }

    @RequestMapping(path="/performance", method=RequestMethod.POST)
    public void savePerformance(@RequestBody PerformanceDTO performanceDTO){
        performanceService.savePerformance(performanceDTO);
    }


    @RequestMapping(path="/performance/{id}", method=RequestMethod.PUT)
    public PerformanceDTO updatePerformance(@PathVariable long id, @RequestBody PerformanceDTO dto){
        return performanceService.updatePerformance(id, dto);
    }
}
