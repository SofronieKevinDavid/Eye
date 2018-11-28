
package com.kevin.service;

import com.kevin.domain.RunnedGame;
import com.kevin.persistance.RunnedGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

