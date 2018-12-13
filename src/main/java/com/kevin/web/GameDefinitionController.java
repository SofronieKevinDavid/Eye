package com.kevin.web;


import com.kevin.dto.GameDefinitionDTO;
import com.kevin.service.GameDefinitionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameDefinitionController {

    @Autowired
    private GameDefinitionService gameDefinitionService;


    @RequestMapping(path = "/gamedefinition/{id}", method =RequestMethod.GET)
    public GameDefinitionDTO getGameDefinition(@PathVariable("id") long id){
        return gameDefinitionService.getGameDefinitionById(id);
    }

    @RequestMapping(path="/gamedefinition", method=RequestMethod.POST)
    public void saveGameDefinition(@RequestBody GameDefinitionDTO gameDefinitionDTO){
        gameDefinitionService.saveGameDefinition(gameDefinitionDTO);
    }

    @RequestMapping(path="/gamedefinition/{id}", method=RequestMethod.PUT)
    public GameDefinitionDTO updateGameDefinition(@PathVariable long id, @RequestBody GameDefinitionDTO dto){
        return gameDefinitionService.updateGameDefinition(id, dto);
    }



}
