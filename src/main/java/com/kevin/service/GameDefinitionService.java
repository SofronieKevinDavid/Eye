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


    public void saveGameDefinition(GameDefinitionDTO gameDefinitionDTO){
        if(gameDefinitionDTO.getName()==null){
            throw new IllegalArgumentException("Name can not be null.");
        }
        GameDefinition gameDefinitionObject=convert(gameDefinitionDTO);

        try {
            gameDefinitionRepository.save(gameDefinitionObject);
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

            GameDefinitionDTO gameDefinitionDTO = convertToDto(gameDefinition);


            list.add(gameDefinitionDTO);
        }

        return list;


    }

    private GameDefinitionDTO convertToDto(GameDefinition gameDefinition) {
        GameDefinitionDTO gameDefinitionDTO = new GameDefinitionDTO();
        gameDefinitionDTO.setName(gameDefinition.getName());
        gameDefinitionDTO.setID(gameDefinition.getId());
        gameDefinitionDTO.setDescription(gameDefinition.getDescription());
        return gameDefinitionDTO;
    }

    private GameDefinition convert(GameDefinitionDTO gameDefinitionDTO) {
        GameDefinition gameDefinition = new GameDefinition();
        gameDefinition.setName(gameDefinitionDTO.getName());
        gameDefinition.setId(gameDefinitionDTO.getID());
        gameDefinition.setDescription(gameDefinitionDTO.getDescription());
        return gameDefinition;
    }

    public GameDefinitionDTO getGameDefinitionById(long id) {
        GameDefinition gameDefinition=gameDefinitionRepository.findOne(id);
        if(gameDefinition==null){
            throw new IllegalArgumentException("The id is not valid.");
        }
        return convertToDto(gameDefinition);
    }

    public boolean deleteGameDefinitionById(long id){
        if(gameDefinitionRepository.findOne(id)!=null) {
            gameDefinitionRepository.delete(id);
            return true;
        }
        return false;
    }

    public GameDefinitionDTO updateGameDefinition(long id,GameDefinitionDTO dto) {
        GameDefinition gameDefinition=gameDefinitionRepository.findOne(id);
        gameDefinition.setDescription(dto.getDescription());
        gameDefinition.setName(dto.getName());

        GameDefinition savedObject= gameDefinitionRepository.save(gameDefinition);

        return convertToDto(savedObject);
    }
}
