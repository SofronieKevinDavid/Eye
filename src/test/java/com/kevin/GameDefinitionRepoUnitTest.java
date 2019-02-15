package com.kevin;

import com.kevin.domain.GameDefinition;
import com.kevin.dto.GameDefinitionDTO;
import com.kevin.persistance.GameDefinitionRepository;
import com.kevin.service.GameDefinitionService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GameDefinitionRepoUnitTest {

    @Mock
    private GameDefinitionRepository repository;


    @InjectMocks
    private GameDefinitionService service;

    @Test
    public void testGetById(){
        GameDefinition gameDefinitionToBeReturned=new GameDefinition();
        gameDefinitionToBeReturned.setId(5L);
        gameDefinitionToBeReturned.setName("a");
        gameDefinitionToBeReturned.setDescription("a");

        Mockito.doReturn(gameDefinitionToBeReturned).when(repository).findOne(5L);

        GameDefinitionDTO gameDefinitionDTO=service.getGameDefinitionById(5L);

        Assert.assertNotNull(gameDefinitionDTO);
        Assert.assertEquals("a",gameDefinitionDTO.getName());
        Assert.assertEquals(5L, gameDefinitionDTO.getID());
    }
}
