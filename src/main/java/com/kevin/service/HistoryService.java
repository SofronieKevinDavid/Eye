
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

    public void saveHistory(HistoryDTO historyDTO) {

        if (historyDTO.getResult() == -1) {
            throw new IllegalArgumentException("Result not valid.");
        }

        long runnedGameId = historyDTO.getRunnedGameId();
        RunnedGame runnedGame = runnedGameRepository.findOne(runnedGameId);

        User userById = userRepository.findOne(historyDTO.getUserId());
        if (userById == null) {
            throw new IllegalArgumentException("Invalid userId " + historyDTO.getUserId());
        }

        if (runnedGame != null && runnedGame.getUser().getId() != userById.getId()) {
            throw new IllegalArgumentException("Yu are trying to save a history object for a runned game that is created for other user.");
        }
        History historyObject = convert(historyDTO, runnedGame, userById);
        try {
            historyRepository.save(historyObject);
        } catch (Exception e) {
            System.out.println("Error in saving user " + e);
        }
    }

    @Transactional
    public List<HistoryDTO> getHistoryForUserID(long userID) {
        List<HistoryDTO> listDTO = new ArrayList<>();
        List<History> list = historyRepository.findByHistoryUserId(userID);
        for (int i = 0; i < list.size(); i++) {
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
        historyDTO.setUserId(history.getHistoryUser().getId());
        historyDTO.setUsername(history.getHistoryUser().getName());
        historyDTO.setRunnedGameId(history.getRunnedGame().getId());
        return historyDTO;
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


    private History convert(HistoryDTO historyDTO, RunnedGame game, User user) {
        History history = new History();
        history.setResult(historyDTO.getResult());
        history.setDate(historyDTO.getDate());
        history.setId(historyDTO.getID());

        history.setHistoryUser(user);
        history.setRunnedGame(game);
        return history;
    }

    private RunnedGame convertRunnedGame(RunnedGameDTO runnedGameDTO) {
        RunnedGame runnedGame = new RunnedGame();
        runnedGame.setLevel(runnedGameDTO.getLevel());
        runnedGame.setId(runnedGameDTO.getId());
        if (runnedGameDTO.getGameDefinitionId() > 0) {
            //game already exists in db we avoid updating it by cascading
            GameDefinition gameDef = gameDefinitionRepository.findOne(runnedGameDTO.getGameDefinitionId());
            runnedGame.setGameDefinition(gameDef);
        }
        if (runnedGameDTO.getUserId() > 0) {
            //the user already exists, we avoid reupdating it in cascade
            User user = userRepository.findOne(runnedGameDTO.getUserId());
            runnedGame.setUser(user);
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

        History savedObject = historyRepository.save(history);

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

