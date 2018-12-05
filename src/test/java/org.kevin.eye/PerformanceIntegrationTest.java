package org.kevin.eye;

import com.kevin.DTO.PerformanceDTO;
import com.kevin.EyeApplication;
import com.kevin.domain.Performance;
import com.kevin.service.PerformanceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EyeApplication.class, loader = SpringApplicationContextLoader.class, initializers = ConfigFileApplicationContextInitializer.class)
public class PerformanceIntegrationTest {

    @Autowired
    private PerformanceService performanceService;

    @Test
    public void testFind() {

        /*Performance performance=new Performance();
        performanceService.savePerformance(performance);*/

        List<PerformanceDTO> list=performanceService.getPerformances();
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }

    }
}
