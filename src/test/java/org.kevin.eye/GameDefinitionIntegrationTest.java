package org.kevin.eye;

import com.kevin.DTO.GameDefinitionDTO;
import com.kevin.EyeApplication;
import com.kevin.domain.GameDefinition;
import com.kevin.service.GameDefinitionService;
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
public class GameDefinitionIntegrationTest {

    @Autowired
    private GameDefinitionService gameDefinitionService;

    @Test
    public void testFind() {

        /*GameDefinition gameDefinition=new GameDefinition("asbury17");
        gameDefinition.setName("asbury17");
        gameDefinitionService.saveGameDefinition(gameDefinition);*/

        List<GameDefinitionDTO> list=gameDefinitionService.getGameDefinitions();
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }

    }
}
