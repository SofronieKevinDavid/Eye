package com.kevin.web;



import com.kevin.domain.RunnedGame;
import com.kevin.service.RunnedGameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/runnedGame")
public class RunnedGameController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RunnedGameService.class);

    @Autowired
    private RunnedGameService runnedGameService;

    @RequestMapping(value = {"/{runnedGame}"}, method = RequestMethod.POST)
    public void saveRunnedGame(@PathVariable("runnedGame") RunnedGame runnedGame) {
        LOGGER.info("runnedGame >> {}", runnedGame);

        runnedGameService.saveRunnedGame(runnedGame);
    }
}
