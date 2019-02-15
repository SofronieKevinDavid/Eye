package com.kevin.web;



import com.kevin.dto.RanGameDTO;
import com.kevin.service.RanGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RanGameController {

    @Autowired
    private RanGameService ranGameService;


    @RequestMapping(path = "/rangame/{id}", method = RequestMethod.GET)
    public RanGameDTO getRanGame(@PathVariable("id") long id){
        return ranGameService.getRanGameById(id);
    }

    @ResponseBody
    @RequestMapping(path="/rangame", method=RequestMethod.POST)
    public long saveRanGame(@RequestBody RanGameDTO ranGameDTO){
        return ranGameService.saveRanGame(ranGameDTO);
    }


    @RequestMapping(path="/rangame/{id}", method=RequestMethod.PUT)
    public RanGameDTO updateRanGame(@PathVariable long id, @RequestBody RanGameDTO dto){
        return ranGameService.updateRanGame(id, dto);
    }

    @RequestMapping(path = "/rangame/{id}", method =RequestMethod.DELETE)
    public void deleteRanGame(@PathVariable("id") long id){
        ranGameService.deleteRanGameById(id);
    }
}