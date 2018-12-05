
package com.kevin.service;

import com.kevin.DTO.RunnedGameDTO;
import com.kevin.DTO.UserDTO;
import com.kevin.domain.RunnedGame;
import com.kevin.domain.User;
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

    public void saveRunnedGame(RunnedGame runnedGame){
        if(runnedGame.getLevel()==0){
            throw new IllegalArgumentException("Level can not be 0.");
        }
//        if(runnedGame.getID()==0){
//            throw new IllegalArgumentException("ID can not be 0.");
//        }
        try {
            runnedGameRepository.save(runnedGame);
        }catch (Exception e){
            System.out.println("Error in saving user "+e);
        }
    }

    @Transactional
    public List<RunnedGameDTO> getRunnedGames() {
        Iterator<RunnedGame> iterator =
                runnedGameRepository.findAll().iterator();


        List<RunnedGameDTO> list = new ArrayList<>();

        while (iterator.hasNext()) {
            RunnedGame runnedGame = iterator.next();

            RunnedGameDTO runnedGameDTO = new RunnedGameDTO();
            runnedGameDTO.setLevel(runnedGame.getLevel());
            runnedGame.setID(runnedGame.getID());


            list.add(runnedGameDTO);
        }

        return list;


    }
}

