package com.kevin.service;

import com.kevin.domain.GameDefinition;
import com.kevin.domain.User;
import com.kevin.dto.GameDefinitionDTO;
import com.kevin.dto.RunnedGameDTO;
import com.kevin.domain.RunnedGame;
import com.kevin.dto.UserDTO;
import com.kevin.persistance.GameDefinitionRepository;
import com.kevin.persistance.RunnedGameRepository;
import com.kevin.persistance.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class RunnedGameService {

    @Autowired
    private RunnedGameRepository runnedGameRepository;

    @Autowired
    private GameDefinitionRepository gameDefinitionRepository;

    @Autowired
    private UserRepository userRepository;

    public void saveRunnedGame(RunnedGameDTO dto) {
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
        RunnedGame runnedGame = convert(dto, user, gameDefinition);
        try {
            runnedGameRepository.save(runnedGame);
        } catch (Exception e) {
            System.out.println("Error in saving user " + e);
        }
    }
    private RunnedGame convert(RunnedGameDTO runnedGameDTO, User user, GameDefinition game) {
        RunnedGame runnedGame = new RunnedGame();
        runnedGame.setLevel(runnedGameDTO.getLevel());
        runnedGame.setId(runnedGameDTO.getId());
        runnedGame.setGameDefinition(game);
        runnedGame.setUser(user);
        return runnedGame;
    }
    public RunnedGameDTO getRunnedGameById(long id) {
        RunnedGame runnedGame = runnedGameRepository.findOne(id);
        if (runnedGame == null) {
            throw new IllegalArgumentException("The id is not valid.");
        }
        return convertToDto(runnedGame);
    }

    public RunnedGameDTO updateRunnedGame(long id, RunnedGameDTO dto) {
        RunnedGame runnedGame = runnedGameRepository.findOne(id);
        runnedGame.setLevel(dto.getLevel());

        RunnedGame savedObject = runnedGameRepository.save(runnedGame);

        return convertToDto(savedObject);
    }

    public boolean deleteRunnedGameById(long id) {
        if (runnedGameRepository.findOne(id) != null) {
            runnedGameRepository.delete(id);
            return true;
        }
        return false;
    }
    @Transactional
    public List<RunnedGameDTO> getRunnedGames() {
        Iterator<RunnedGame> iterator =
                runnedGameRepository.findAll().iterator();


        List<RunnedGameDTO> list = new ArrayList<>();

        while (iterator.hasNext()) {
            RunnedGame runnedGame = iterator.next();

            RunnedGameDTO runnedGameDTO = convertToDto(runnedGame);

            list.add(runnedGameDTO);
        }

        return list;
    }

    private RunnedGameDTO convertToDto(RunnedGame runnedGame) {
        RunnedGameDTO runnedGameDTO = new RunnedGameDTO();
        runnedGameDTO.setLevel(runnedGame.getLevel());
        runnedGameDTO.setId(runnedGame.getId());
        runnedGameDTO.setGameDefinitionId(runnedGame.getGameDefinition().getId());
        runnedGameDTO.setUserId(runnedGame.getUser().getId());
        runnedGameDTO.setUsername(runnedGame.getUser().getName());
        runnedGameDTO.setGameName(runnedGame.getGameDefinition().getName());
        return runnedGameDTO;
    }
}

