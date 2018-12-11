package com.kevin.service;


import com.kevin.dto.GameDefinitionDTO;
import com.kevin.domain.GameDefinition;
import com.kevin.persistance.GameDefinitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class GameDefinitionService {

    @Autowired
    private GameDefinitionRepository gameDefinitionRepository;


    public void saveGameDefinition(GameDefinition gameDefinition){
        if(gameDefinition.getName()==null){
            throw new IllegalArgumentException("Name can not be null.");
        }
        //if(gameDefinition.getId()==0){
            //throw new IllegalArgumentException("ID can not be 0.");
        //}

        try {
            gameDefinitionRepository.save(gameDefinition);
        }catch (Exception e){
            System.out.println("Error in saving user "+e);
        }
    }

    @Transactional
    public List<GameDefinitionDTO> getGameDefinitions() {
        Iterator<GameDefinition> iterator =
                gameDefinitionRepository.findAll().iterator();


        List<GameDefinitionDTO> list = new ArrayList<>();

        while (iterator.hasNext()) {
            GameDefinition gameDefinition = iterator.next();

            GameDefinitionDTO gameDefinitionDTO = new GameDefinitionDTO("GameDefinitionDTO");
            gameDefinitionDTO.setName(gameDefinition.getName());
            gameDefinitionDTO.setID(gameDefinition.getId());


            list.add(gameDefinitionDTO);
        }

        return list;


    }
}
