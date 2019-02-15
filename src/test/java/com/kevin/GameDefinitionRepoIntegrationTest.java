package com.kevin;

import com.kevin.domain.GameDefinition;
import com.kevin.persistance.GameDefinitionRepository;
import com.kevin.service.GameDefinitionService;
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
public class GameDefinitionRepoIntegrationTest {

    @Autowired
    private GameDefinitionRepository repository;

    @Autowired
    private GameDefinitionService service;

    @Test
    public void testSave(){
        GameDefinition gameDefinition=getGameDefinition("andrei44","description");
        repository.save(gameDefinition);

        GameDefinition gameDefinition2= getGameDefinition("andrei44","description2");
        repository.save(gameDefinition2);
    }

    @Test
    public void testGetById(){
        System.out.print(service.getGameDefinitionById(1));
    }

    @Test
    public void deleteUserById(){
        service.deleteGameDefinitionById(8);
    }

    private GameDefinition getGameDefinition(String name, String description){
        GameDefinition gameDefinition=new GameDefinition();
        gameDefinition.setName(name);
        gameDefinition.setDescription(description);
        return gameDefinition;
    }
}
