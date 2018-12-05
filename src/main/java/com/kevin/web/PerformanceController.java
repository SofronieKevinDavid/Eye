package com.kevin.web;


import com.kevin.domain.Performance;
import com.kevin.service.PerformanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/performance")
public class PerformanceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PerformanceService.class);

    @Autowired
    private PerformanceService performanceService;

    @RequestMapping(value = {"/{performance}"}, method = RequestMethod.POST)
    public void savePerformance(@PathVariable("performance") Performance performance) {
        LOGGER.info("performance >> {}", performance);

        performanceService.savePerformance(performance);
    }
}
