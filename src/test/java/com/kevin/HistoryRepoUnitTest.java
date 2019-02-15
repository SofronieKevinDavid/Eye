package com.kevin;

import com.kevin.domain.GameDefinition;
import com.kevin.domain.History;
import com.kevin.domain.RanGame;
import com.kevin.domain.User;
import com.kevin.dto.HistoryDTO;
import com.kevin.persistance.HistoryRepository;
import com.kevin.service.HistoryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class HistoryRepoUnitTest {

    @Mock
    private HistoryRepository repository;

    @InjectMocks
    private HistoryService service;


    @Test
    public void testGetById(){
        History historyToBeReturned=new History();
        historyToBeReturned.setId(5L);
        double result=12.00;
        historyToBeReturned.setResult(result);
        Date date=new Date();
        historyToBeReturned.setDate(date);
        historyToBeReturned.setRanGame(getRanGame(23,getGameDefinition(),getUser()));
        historyToBeReturned.setHistoryUser(getUser());

        Mockito.doReturn(historyToBeReturned).when(repository).findOne(5L);

        HistoryDTO historyDTO=service.getHistoryById(5L);

        Assert.assertNotNull(historyDTO);
        Assert.assertEquals(getRanGame(23,getGameDefinition(),getUser()), getRanGame(23,getGameDefinition(),getUser()));
        Assert.assertEquals(getUser(), getUser());
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
