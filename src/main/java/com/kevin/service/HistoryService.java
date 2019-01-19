
package com.kevin.service;


import com.kevin.domain.GameDefinition;
import com.kevin.domain.RunnedGame;
import com.kevin.domain.User;
import com.kevin.dto.GameDefinitionDTO;
import com.kevin.dto.HistoryDTO;
import com.kevin.domain.History;


import com.kevin.dto.RunnedGameDTO;
import com.kevin.dto.UserDTO;
import com.kevin.persistance.HistoryRepository;
import com.kevin.persistance.RunnedGameRepository;
import com.kevin.persistance.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private RunnedGameRepository runnedGameRepository;

    @Autowired
    private UserRepository userRepository;


    public void saveHistory(HistoryDTO historyDTO){

        if(historyDTO.getResult()==-1){
            throw new IllegalArgumentException("Result not valid.");
        }

        long userId=historyDTO.getHistoryUserDTOId();
        User user=userRepository.findOne(userId);


        long runnedGameId=historyDTO.getRunnedGameId();
        RunnedGame runnedGame=runnedGameRepository.findOne(runnedGameId);

        History historyObject=convert(historyDTO);
        historyObject.setRunnedGame(runnedGame);
        historyObject.setHistoryUser(user);
        try {
            historyRepository.save(historyObject);
        }catch (Exception e){
            System.out.println("Error in saving user "+e);
        }
    }

    public List<HistoryDTO> getHistoryForUserID(long userID){
        List<HistoryDTO> listDTO=new ArrayList<>();
        List<History> list=historyRepository.findByHistoryUserId(userID);
        for(int i=0;i<list.size();i++){
            listDTO.add(convertToDto(list.get(i)));
        }
        return listDTO;
    }


    @Transactional
    public List<HistoryDTO> getHistories() {
        Iterator<History> iterator =
                historyRepository.findAll().iterator();


        List<HistoryDTO> list = new ArrayList<>();

        while (iterator.hasNext()) {
            History history = iterator.next();

            HistoryDTO historyDTO = new HistoryDTO();

            historyDTO.setResult(history.getResult());
            historyDTO.setID(history.getId());


            list.add(historyDTO);
        }

        return list;
    }

    private HistoryDTO convertToDto(History history) {
        HistoryDTO historyDTO = new HistoryDTO();
        historyDTO.setResult(history.getResult());
        historyDTO.setDate(history.getDatePublic());
        historyDTO.setID(history.getId());
        historyDTO.setHistoryUserDTO(convertUserToDto(history.getHistoryUser()));
        historyDTO.setRunnedGameDTO(convertRunnedGameToDTO(history.getRunnedGame()));
        return historyDTO;
    }

    private RunnedGameDTO convertRunnedGameToDTO(RunnedGame runnedGame) {
        RunnedGameDTO runnedGameDTO = new RunnedGameDTO();
        runnedGameDTO.setLevel(runnedGame.getLevel());
        runnedGameDTO.setId(runnedGame.getId());
        runnedGameDTO.setUserDTO(convertUserToDto(runnedGame.getUser()));
        runnedGameDTO.setGameDefinitionDTO(convertGameDefinitionToDto(runnedGame.getGameDefinition()));
        return runnedGameDTO;
    }

    private GameDefinitionDTO convertGameDefinitionToDto(GameDefinition gameDefinition) {
        GameDefinitionDTO gameDefinitionDTO = new GameDefinitionDTO();
        gameDefinitionDTO.setName(gameDefinition.getName());
        gameDefinitionDTO.setID(gameDefinition.getId());
        gameDefinitionDTO.setDescription(gameDefinition.getDescription());
        return gameDefinitionDTO;
    }

    private GameDefinition convertGameDefinitionForHistory(GameDefinitionDTO gameDefinitionDTO) {
        GameDefinition gameDefinition = new GameDefinition();
        gameDefinition.setName(gameDefinitionDTO.getName());
        gameDefinition.setId(gameDefinitionDTO.getID());
        gameDefinition.setDescription(gameDefinitionDTO.getDescription());
        return gameDefinition;
    }

    private User convertUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setId(userDTO.getID());
        return user;
    }

    private UserDTO convertUserToDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setID(user.getId());
        return userDTO;
    }


    private History convert(HistoryDTO historyDTO) {
        History history = new History();
        history.setResult(historyDTO.getResult());
        history.setDate(historyDTO.getDatePublic());
        history.setId(historyDTO.getID());
        history.setHistoryUser(convertUser(historyDTO.getHistoryUserDTO()));
        history.setRunnedGame(convertRunnedGame(historyDTO.getRunnedGameDTO()));
        return history;
    }
    private RunnedGame convertRunnedGame(RunnedGameDTO runnedGameDTO) {
        RunnedGame runnedGame = new RunnedGame();
        runnedGame.setLevel(runnedGameDTO.getLevel());
        runnedGame.setId(runnedGameDTO.getId());
        if(runnedGame.getGameDefinition()!=null) {
            runnedGame.setGameDefinition(convertGameDefinitionForHistory(runnedGameDTO.getGameDefinitionDTO()));
        }
        if(runnedGame.getUser()!=null) {
            runnedGame.setUser(convertUser(runnedGameDTO.getUserDTO()));
        }
        return runnedGame;
    }

    public HistoryDTO getHistoryById(long id) {
        History history=historyRepository.findOne(id);
        if(history==null){
            throw new IllegalArgumentException("The id is not valid.");
        }
        return convertToDto(history);
    }

    public HistoryDTO updateHistory(long id,HistoryDTO dto) {
        History history=historyRepository.findOne(id);
        history.setResult(dto.getResult());
        history.setDate(dto.getDatePublic());
        history.setHistoryUser(convertUser(dto.getHistoryUserDTO()));

        History savedObject= historyRepository.save(history);

        return convertToDto(savedObject);
    }

    public boolean deleteHistoryById(long id){
        if(historyRepository.findOne(id)!=null) {
            historyRepository.delete(id);
            return true;
        }
        return false;
    }
}

