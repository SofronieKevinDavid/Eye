
package com.kevin.service;

import com.kevin.dto.RunnedGameDTO;
import com.kevin.domain.RunnedGame;
import com.kevin.persistance.RunnedGameRepository;
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

    public void saveRunnedGame(RunnedGameDTO runnedGameDTO){
        if(runnedGameDTO.getLevel()==0){
            throw new IllegalArgumentException("Level can not be 0.");
        }
        RunnedGame runnedGameObject=convert(runnedGameDTO);
        try {
            runnedGameRepository.save(runnedGameObject);
        }catch (Exception e){
            System.out.println("Error in saving user "+e);
        }
    }

    /*public void saveUser(UserDTO userDTO){
        if(userDTO.getName()==null){
            throw new IllegalArgumentException("Name can not be null.");
        }


        User userObject=convert(userDTO);
        try {
            userRepository.save(userObject);
        }catch (Exception e){
            System.out.println("Error in saving user "+e);
        }
    }*/

    @Transactional
    public List<RunnedGameDTO> getRunnedGames() {
        Iterator<RunnedGame> iterator =
                runnedGameRepository.findAll().iterator();


        List<RunnedGameDTO> list = new ArrayList<>();

        while (iterator.hasNext()) {
            RunnedGame runnedGame = iterator.next();

            RunnedGameDTO runnedGameDTO = new RunnedGameDTO();
            runnedGameDTO.setLevel(runnedGame.getLevel());
            runnedGame.setId(runnedGame.getId());


            list.add(runnedGameDTO);
        }

        return list;


    }

    private RunnedGameDTO convertToDto(RunnedGame runnedGame) {
        RunnedGameDTO runnedGameDTO = new RunnedGameDTO();
        runnedGameDTO.setLevel(runnedGame.getLevel());
        runnedGameDTO.setID(runnedGame.getId());
        return runnedGameDTO;
    }

    private RunnedGame convert(RunnedGameDTO runnedGameDTO) {
        RunnedGame runnedGame = new RunnedGame();
        runnedGame.setLevel(runnedGame.getLevel());
        runnedGame.setId(runnedGameDTO.getID());
        return runnedGame;
    }

    public RunnedGameDTO getRunnedGameById(long id) {
        RunnedGame runnedGame=runnedGameRepository.findOne(id);
        if(runnedGame==null){
            throw new IllegalArgumentException("The id is not valid.");
        }
        return convertToDto(runnedGame);
    }

    public RunnedGameDTO updateRunnedGame(long id,RunnedGameDTO dto) {
        RunnedGame runnedGame=runnedGameRepository.findOne(id);
        runnedGame.setLevel(dto.getLevel());

        RunnedGame savedObject= runnedGameRepository.save(runnedGame);

        return convertToDto(savedObject);
    }
}

