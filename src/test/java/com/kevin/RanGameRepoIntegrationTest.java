package com.kevin;

import com.kevin.domain.GameDefinition;
import com.kevin.domain.RanGame;
import com.kevin.domain.User;
import com.kevin.persistance.RanGameRepository;
import com.kevin.service.RanGameService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EyeApplication.class,
        loader = SpringApplicationContextLoader.class,
        initializers = ConfigFileApplicationContextInitializer.class)
public class RanGameRepoIntegrationTest {

    @Autowired
    private RanGameRepository repository;

    @Autowired
    private RanGameService service;

    @Test
    public void testSave(){
        RanGame ranGame= getRanGame(1,getGameDefinition(),getUser());
        repository.save(ranGame);

        RanGame ranGame2= getRanGame(2,getGameDefinition(),getUser());
        repository.save(ranGame2);
    }

    @Test
    public void deleteRanGameById(){
        service.deleteRanGameById(18);
    }

    private RanGame getRanGame(int level, GameDefinition gameDefinition, User user){
        RanGame ranGame=new RanGame();
        ranGame.setLevel(level);
        ranGame.setGameDefinition(gameDefinition);
        ranGame.setUser(user);
        return ranGame;
    }

    private User getUser(){
        User user=new User();
        user.setId(12);
        user.setName("Andrei");
        user.setPassword("2");
        return user;
    }

    private GameDefinition getGameDefinition(){
        GameDefinition gameDefinition=new GameDefinition();
        gameDefinition.setId(4);
        gameDefinition.setName("Play");
        gameDefinition.setDescription("desc");
        return gameDefinition;
    }
}
