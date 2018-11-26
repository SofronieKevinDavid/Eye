package com.kevin.service;


import com.kevin.domain.GameDefinition;
import com.kevin.persistance.GameDefinitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameDefinitionService {

    @Autowired
    private GameDefinitionRepository gameDefinitionRepository;


    public void saveGameDefinition(GameDefinition gameDefinition){
        if(gameDefinition.getName()==null){
            throw new IllegalArgumentException("Name can not be null.");
        }

        try {
            gameDefinitionRepository.save(gameDefinition);
        }catch (Exception e){
            System.out.println("Error in saving user "+e);
        }
    }
}
