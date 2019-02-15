package com.kevin;

import com.kevin.domain.GameDefinition;
import com.kevin.domain.RanGame;
import com.kevin.domain.User;
import com.kevin.dto.RanGameDTO;
import com.kevin.persistance.RanGameRepository;
import com.kevin.service.RanGameService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RanGameRepoUnitTest {

    @Mock
    private RanGameRepository repository;

    @InjectMocks
    private RanGameService service;

    @Test
    public void testGetById(){
        RanGame ranGameToBeReturned=new RanGame();
        ranGameToBeReturned.setId(5L);
        ranGameToBeReturned.setLevel(12);
        ranGameToBeReturned.setGameDefinition(getGameDefinition());
        ranGameToBeReturned.setUser(getUser());

        Mockito.doReturn(ranGameToBeReturned).when(repository).findOne(5L);

        RanGameDTO ranGameDTO=service.getRanGameById(5L);

        Assert.assertNotNull(ranGameDTO);
        Assert.assertEquals(12,ranGameDTO.getLevel());
        Assert.assertEquals(getGameDefinition(), getGameDefinition());
        Assert.assertEquals(getUser(), getUser());
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
