package com.kevin.service;


import com.kevin.domain.RanGame;
import com.kevin.domain.User;
import com.kevin.dto.HistoryDTO;
import com.kevin.domain.History;
import com.kevin.persistance.GameDefinitionRepository;
import com.kevin.persistance.HistoryRepository;
import com.kevin.persistance.RanGameRepository;
import com.kevin.persistance.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private RanGameRepository ranGameRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameDefinitionRepository gameDefinitionRepository;

    public long saveHistory(HistoryDTO historyDTO) {

        if (historyDTO.getResult() == -1) {
            throw new IllegalArgumentException("Result not valid.");
        }

        long ranGameId = historyDTO.getRanGameId();
        RanGame ranGame = ranGameRepository.findOne(ranGameId);

        User userById = userRepository.findOne(historyDTO.getUserId());
        if (userById == null) {
            throw new IllegalArgumentException("Invalid userId " + historyDTO.getUserId());
        }

        if (ranGame != null && ranGame.getUser().getId() != userById.getId()) {
            throw new IllegalArgumentException("Yu are trying to save a history object for a ran game that is created for other user.");
        }
        History historyObject = convert(historyDTO, ranGame, userById);
        try {
            historyRepository.save(historyObject);
            return historyObject.getId();
        } catch (Exception e) {
            System.out.println("Error in saving user " + e);
            return -1;
        }
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

    @Transactional
    public List<HistoryDTO> getHistoryForUserID(long userID) {
        List<HistoryDTO> listDTO = new ArrayList<>();
        List<History> list = historyRepository.findByHistoryUserId(userID);
        for (int i = 0; i < list.size(); i++) {
            listDTO.add(convertToDto(list.get(i)));
        }
        return listDTO;
    }


    private History convert(HistoryDTO historyDTO, RanGame game, User user) {
        History history = new History();
        history.setResult(historyDTO.getResult());
        history.setDate(historyDTO.getDate());
        history.setId(historyDTO.getID());
        history.setHistoryUser(user);
        history.setRanGame(game);
        return history;
    }

    private HistoryDTO convertToDto(History history) {
        HistoryDTO historyDTO = new HistoryDTO();
        historyDTO.setResult(history.getResult());
        if (history.getHistoryUser() == null) {
            throw new IllegalArgumentException("Invalid history object" +
                    " that is not linked to a user directly");
        }
        historyDTO.setRanGameLevel(history.getRanGame().getLevel());
        historyDTO.setDate(history.getDate());
        historyDTO.setID(history.getId());
        historyDTO.setGameName(history.getRanGame().getGameDefinition().getName());
        historyDTO.setUserId(history.getHistoryUser().getId());
        historyDTO.setRanGameId(history.getRanGame().getId());
        return historyDTO;
    }
}
