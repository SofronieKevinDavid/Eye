package com.kevin.web;



import com.kevin.domain.RunnedGame;
import com.kevin.dto.RunnedGameDTO;
import com.kevin.service.RunnedGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController

public class RunnedGameController {

    @Autowired
    private RunnedGameService runnedGameService;


    @RequestMapping(path = "/runnedgame/{id}", method = RequestMethod.GET)
    public RunnedGameDTO getRunnedGame(@PathVariable("id") long id){
        return runnedGameService.getRunnedGameById(id);
    }


    @RequestMapping(path="/runnedgame", method=RequestMethod.POST)
    public RunnedGame saveRunnedGame(@RequestBody RunnedGameDTO runnedGameDTO){
        RunnedGame runnedGame=runnedGameService.saveRunnedGame(runnedGameDTO);
        return runnedGame;
    }


    @RequestMapping(path="/runnedgame/{id}", method=RequestMethod.PUT)
    public RunnedGameDTO updateRunnedGame(@PathVariable long id, @RequestBody RunnedGameDTO dto){
        return runnedGameService.updateRunnedGame(id, dto);
    }

    @RequestMapping(path = "/runnedgame/{id}", method =RequestMethod.DELETE)
    public void deleteRunnedGame(@PathVariable("id") long id){
        runnedGameService.deleteRunnedGameById(id);
    }
}
