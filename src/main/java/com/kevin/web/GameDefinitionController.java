package com.kevin.web;


import com.kevin.domain.GameDefinition;
import com.kevin.service.GameDefinitionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gameDefinition")
public class GameDefinitionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameDefinitionService.class);

    @Autowired
    private GameDefinitionService gameDefinitionService;

    @RequestMapping(value = {"/{gameDefinition}"}, method = RequestMethod.POST)
    public void saveUser(@PathVariable("gameDefinition")GameDefinition gameDefinition) {
        LOGGER.info("gameDefinition >> {}", gameDefinition);

        gameDefinitionService.saveGameDefinition(gameDefinition);
    }
}
