
package com.kevin.service;


import com.kevin.domain.GameDefinition;
import com.kevin.domain.RunnedGame;
import com.kevin.domain.User;
import com.kevin.dto.GameDefinitionDTO;
import com.kevin.dto.HistoryDTO;
import com.kevin.domain.History;


import com.kevin.dto.RunnedGameDTO;
import com.kevin.dto.UserDTO;
import com.kevin.persistance.GameDefinitionRepository;
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

    @Autowired
    private GameDefinitionRepository gameDefinitionRepository;

    public void saveHistory(HistoryDTO historyDTO){

        if(historyDTO.getResult()==-1){
            throw new IllegalArgumentException("Result not valid.");
        }

        long runnedGameId=historyDTO.getRunnedGameId();
        RunnedGame runnedGame=runnedGameRepository.findOne(runnedGameId);

        //TODO see comments HISTORYDTO in class
//la tine historyDTO.getHistoryUserDTOId() nu returneaza user id ci history id . nu e ok .
        //scimba historyDto sa nu mai aiba user dti numa user id , nu iti trebuie tot userul in dto
        //numa in model trebuie tot userul
        User userById = userRepository.findOne(historyDTO.getHistoryUserDTOId());
        if (userById == null) {
            throw new IllegalArgumentException("Invalid userId " + historyDTO.getHistoryUserDTOId());
        }

        if (runnedGame != null && runnedGame.getUser().getId() != userById.getId()) {
            throw new IllegalArgumentException("Yu are trying to save a history object for a runned game that is created for other user.");
        }
        History historyObject = convert(historyDTO);
        historyObject.setHistoryUser(userById);

        if (runnedGame != null) {
            historyObject.setRunnedGame(runnedGame);
        }
        try {
            historyRepository.save(historyObject);
        } catch (Exception e) {
            System.out.println("Error in saving user " + e);
        }
    }
    @Transactional
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
        if (history.getHistoryUser() == null) {
            throw new IllegalArgumentException("Invalid history object" +
                    " that is not linked to a user directly");
        }
       // historyDTO.setUserId(history.getHistoryUser().getId());
        historyDTO.setDate(history.getDate());
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
        history.setDate(historyDTO.getDate());
        history.setId(historyDTO.getID());
        if (historyDTO.getRunnedGameDTO() == null) {
            throw new IllegalArgumentException("runned game dto can not be null");
        }

        history.setHistoryUser(convertUser(historyDTO.getHistoryUserDTO()));
        history.setRunnedGame(convertRunnedGame(historyDTO.getRunnedGameDTO()));
        return history;
    }
    private RunnedGame convertRunnedGame(RunnedGameDTO runnedGameDTO) {
        RunnedGame runnedGame = new RunnedGame();
        runnedGame.setLevel(runnedGameDTO.getLevel());
        runnedGame.setId(runnedGameDTO.getId());
        if (runnedGameDTO.getGameDefinitionDTO() != null) {
            if (runnedGameDTO.getGameDefinitionDTO().getID() > 0) {
                //game already exists in db we avoid updating it by cascading
                GameDefinition gameDef = gameDefinitionRepository.findOne(runnedGameDTO.getGameDefinitionDTO().getID());
                runnedGame.setGameDefinition(gameDef);
            } else {
                //TODO KEVIN
                //this is a new game it will be saved in CASCADE - do we want this? normally
                // I would throw an exception here because teh request was invalid - the game should already be in the DB
                runnedGame.setGameDefinition(convertGameDefinitionForHistory(runnedGameDTO.getGameDefinitionDTO()));
            }
        }
        if (runnedGameDTO.getUserDTO() != null) {
            if (runnedGameDTO.getUserDTO().getID() > 0) {
                //the user already exists, we avoid reupdating it in cascade
                User user = userRepository.findOne(runnedGameDTO.getUserDTO().getID());
                runnedGame.setUser(user);
            } else {
                //TOTO KEVIN - similar to prev
                runnedGame.setUser(convertUser(runnedGameDTO.getUserDTO()));
            }
        }
        return runnedGame;
    }

    @Transactional
    public HistoryDTO getHistoryById(long id) {
        History history = historyRepository.findOne(id);
        if (history == null) {
            throw new IllegalArgumentException("The id is not valid.");
        }
        return convertToDto(history);
    }

    @Transactional
    public HistoryDTO updateHistory(long id, HistoryDTO dto) {
        History history = historyRepository.findOne(id);
        history.setResult(dto.getResult());
        history.setDate(dto.getDate());
        history.setHistoryUser(convertUser(dto.getHistoryUserDTO()));

        History savedObject= historyRepository.save(history);

        return convertToDto(savedObject);
    }

    @Transactional
    public boolean deleteHistoryById(long id) {
        if (historyRepository.findOne(id) != null) {
            historyRepository.delete(id);
            return true;
        }
        return false;
    }
}

