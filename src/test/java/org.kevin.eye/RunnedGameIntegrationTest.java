
package org.kevin.eye;

import com.kevin.EyeApplication;
import com.kevin.domain.RunnedGame;
import com.kevin.service.RunnedGameService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EyeApplication.class, loader = SpringApplicationContextLoader.class, initializers = ConfigFileApplicationContextInitializer.class)
public class RunnedGameIntegrationTest {

    @Autowired
    private RunnedGameService runnedGameService;

    @Test
    public void testFind() {

        RunnedGame runnedGame=new RunnedGame();
        runnedGame.setLevel(11534);
        runnedGameService.saveRunnedGame(runnedGame);

    }
}

