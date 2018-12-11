
package org.kevin.eye;

import com.kevin.dto.HistoryDTO;
import com.kevin.EyeApplication;
import com.kevin.service.HistoryService;
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
public class HistoryIntegrationTest {

    @Autowired
    private HistoryService historyService;

    @Test
    public void testFind() {

        /*History history=new History();
        RunnedGame runnedGame=new RunnedGame();
        history.setResult(history.getResult(runnedGame));
        historyService.saveHistory(history, runnedGame);*/

        List<HistoryDTO> list=historyService.getHistories();
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }

    }
}

