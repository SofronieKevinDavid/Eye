package com.kevin.web;



import com.kevin.dto.RunnedGameDTO;
import com.kevin.service.RunnedGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/runnedGame")
public class RunnedGameController {

    @Autowired
    private RunnedGameService runnedGameService;

    @RequestMapping(path = "/runnedgame/{id}", method = RequestMethod.GET)
    public RunnedGameDTO getRunnedGame(@PathVariable("id") long id){
        return runnedGameService.getRunnedGameById(id);
    }

    @RequestMapping(path="/runnedgame", method=RequestMethod.POST)
    public void saveRunnedGame(@RequestBody RunnedGameDTO runnedGameDTO){
        runnedGameService.saveRunnedGame(runnedGameDTO);
    }

    @RequestMapping(path="/runnedgame/{id}", method=RequestMethod.PUT)
    public RunnedGameDTO updateRunnedGame(@PathVariable long id, @RequestBody RunnedGameDTO dto){
        return runnedGameService.updateRunnedGame(id, dto);
    }
}
