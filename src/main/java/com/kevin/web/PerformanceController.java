//package com.kevin.web;
//
//import com.kevin.dto.GameDefinitionDTO;
//import com.kevin.dto.PerformanceDTO;
//import com.kevin.dto.UserDTO;
//import com.kevin.service.PerformanceService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//
//public class PerformanceController {
//
//
//    @Autowired
//    private PerformanceService performanceService;
//
//    @RequestMapping(path = "/performance/{id}", method = RequestMethod.GET)
//    public PerformanceDTO getPerformance(@PathVariable("id") long id){
//        return performanceService.getPerformanceById(id);
//    }
//
//    @RequestMapping(path = "/performance", method = RequestMethod.GET)
//    public PerformanceDTO getPerformanceForUser(@RequestParam long userID){
//        return performanceService.getPerformanceForUserID(userID);
//    }
//
////    @RequestMapping(path = "/performance", method = RequestMethod.GET)
////    public PerformanceDTO getPerformanceForUserForGame(@RequestBody long userID,@RequestBody long gameDefinitionID){
////        return performanceService.getPerformanceForUserForGame(userID,gameDefinitionID);
////    }
//
//    @RequestMapping(path="/performance", method=RequestMethod.POST)
//    public void savePerformance(@RequestBody PerformanceDTO performanceDTO){
//        performanceService.savePerformance(performanceDTO);
//    }
//
//
//    @RequestMapping(path="/performance/{id}", method=RequestMethod.PUT)
//    public PerformanceDTO updatePerformance(@PathVariable long id, @RequestBody PerformanceDTO dto){
//        return performanceService.updatePerformance(id, dto);
//    }
//
//    @RequestMapping(path = "/performance/{id}", method =RequestMethod.DELETE)
//    public void deletePerformance(@PathVariable("id") long id){
//        performanceService.deletePerformanceById(id);
//    }
//}
