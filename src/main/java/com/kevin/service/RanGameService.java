package com.kevin.service;

import com.kevin.domain.GameDefinition;
import com.kevin.domain.User;
import com.kevin.dto.RanGameDTO;
import com.kevin.domain.RanGame;
import com.kevin.persistance.GameDefinitionRepository;
import com.kevin.persistance.RanGameRepository;
import com.kevin.persistance.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RanGameService {

    @Autowired
    private RanGameRepository ranGameRepository;

    @Autowired
    private GameDefinitionRepository gameDefinitionRepository;

    @Autowired
    private UserRepository userRepository;

    public long saveRanGame(RanGameDTO dto) {
        if (dto.getLevel() == 0) {
            throw new IllegalArgumentException("Level can not be 0.");
        }
        long gameId = dto.getGameDefinitionId();
        long userId = dto.getUserId();
        GameDefinition gameDefinition = gameDefinitionRepository.findOne(gameId);
        User user = userRepository.findOne(userId);
        if (gameDefinition == null) {
            throw new IllegalArgumentException("Game definition id invalid");
        }
        if (user == null) {
            throw new IllegalArgumentException("userId invalid");

        }
        RanGame ranGame = convert(dto, user, gameDefinition);
        try {
            ranGameRepository.save(ranGame);
            return ranGame.getId();
        } catch (Exception e) {
            System.out.println("Error in saving user " + e);
            return -1;
        }
    }
    private RanGame convert(RanGameDTO ranGameDTO, User user, GameDefinition game) {
        RanGame ranGame = new RanGame();
        ranGame.setLevel(ranGameDTO.getLevel());
        ranGame.setId(ranGameDTO.getId());
        ranGame.setGameDefinition(game);
        ranGame.setUser(user);
        return ranGame;
    }
    public RanGameDTO getRanGameById(long id) {
        RanGame ranGame = ranGameRepository.findOne(id);
        if (ranGame == null) {
            throw new IllegalArgumentException("The id is not valid.");
        }
        return convertToDto(ranGame);
    }

    public RanGameDTO updateRanGame(long id, RanGameDTO dto) {
        RanGame ranGame = ranGameRepository.findOne(id);
        ranGame.setLevel(dto.getLevel());

        RanGame savedObject = ranGameRepository.save(ranGame);

        return convertToDto(savedObject);
    }

    public boolean deleteRanGameById(long id) {
        if (ranGameRepository.findOne(id) != null) {
            ranGameRepository.delete(id);
            return true;
        }
        return false;
    }

    private RanGameDTO convertToDto(RanGame ranGame) {
        RanGameDTO ranGameDTO = new RanGameDTO();
        ranGameDTO.setLevel(ranGame.getLevel());
        ranGameDTO.setId(ranGame.getId());
        ranGameDTO.setGameDefinitionId(ranGame.getGameDefinition().getId());
        ranGameDTO.setUserId(ranGame.getUser().getId());
        return ranGameDTO;
    }
}
