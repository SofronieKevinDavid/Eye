package com.kevin.web;


import com.kevin.domain.History;
import com.kevin.domain.RunnedGame;
import com.kevin.service.HistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/history")
public class HistoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HistoryService.class);

    @Autowired
    private HistoryService historyService;

    @RequestMapping(value = {"/{history}"}, method = RequestMethod.POST)
    public void saveHistory(@PathVariable("history") History history, RunnedGame runnedGame) {
        LOGGER.info("history >> {}", history);

        historyService.saveHistory(history, runnedGame);
    }
}
