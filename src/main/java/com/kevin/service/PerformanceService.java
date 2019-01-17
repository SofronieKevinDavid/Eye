package com.kevin.service;


import com.kevin.domain.User;
import com.kevin.dto.*;
import com.kevin.domain.History;
import com.kevin.domain.Performance;
import com.kevin.domain.RunnedGame;
import com.kevin.persistance.PerformanceRepository;
import com.kevin.persistance.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class PerformanceService {

    @Autowired
    private PerformanceRepository performanceRepository;

    @Autowired
    private UserRepository userRepository;

    public void savePerformance(PerformanceDTO performanceDTO){
        long userId=performanceDTO.getPerformanceUserDTOId();

        User user=userRepository.findOne(userId);
        Performance performanceObject=convert(performanceDTO);
        performanceObject.setPerformanceUser(user);
        try {
            performanceRepository.save(performanceObject);
        }catch (Exception e){
            System.out.println("Error in saving user "+e);
        }
    }

//    public void saveHistory(HistoryDTO historyDTO){
//
//        if(historyDTO.getResult()==-1){
//            throw new IllegalArgumentException("Result not valid.");
//        }
//        long runnedGameId=historyDTO.getRunnedGameId();
//        RunnedGame runnedGame=runnedGameRepository.findOne(runnedGameId);
//
//        History historyObject=convert(historyDTO);
//        historyObject.setRunnedGame(runnedGame);
//
//        try {
//            historyRepository.save(historyObject);
//        }catch (Exception e){
//            System.out.println("Error in saving user "+e);
//        }
//    }

    public PerformanceDTO getPerformanceForUserID(long userID){
        PerformanceDTO performanceDTO;
        performanceDTO=convertToDto(performanceRepository.findByPerformanceUserId(userID)) ;
        return  performanceDTO;
    }
//
//    public PerformanceDTO getPerformanceForUserForGame(long userID, long gameDefinitionID){
//        return getPerformanceById(userID);
//    }



    @Transactional
    public List<PerformanceDTO> getPerformances() {
        Iterator<Performance> iterator =
                performanceRepository.findAll().iterator();


        List<PerformanceDTO> list = new ArrayList<>();

        while (iterator.hasNext()) {
            Performance performance = iterator.next();

            PerformanceDTO performanceDTO = new PerformanceDTO();
            performanceDTO.setID(performance.getId());

            List<History> histories = performance.getResultList();
            int nrOfProd = 0;

            while(nrOfProd<histories.size()){
                History history = histories.get(nrOfProd);
                HistoryDTO historyDTO = new HistoryDTO();

                historyDTO.setID(history.getId());
                RunnedGame runnedGame=new RunnedGame();
                historyDTO.setResult(history.getResult());

                performanceDTO.getResultList().add(historyDTO);
                nrOfProd++;
            }
            list.add(performanceDTO);
        }

        return list;
    }

    private PerformanceDTO convertToDto(Performance performance) {
        PerformanceDTO performanceDTO = new PerformanceDTO();
        performanceDTO.setResultList(performance.getResultListInDto());
        performanceDTO.setID(performance.getId());
        performanceDTO.setPerformanceUserDTO(convertUserToDto(performance.getPerformanceUser()));
        return performanceDTO;
    }



    private UserDTO convertUserToDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setID(user.getId());
        return userDTO;
    }
/////////////////////
    private Performance convert(PerformanceDTO performanceDTO) {
        Performance performance = new Performance();

        performance.setResultList(getResultListAsNotDTO(performanceDTO));
        performance.setId(performanceDTO.getID());
        performance.setPerformanceUser(convertUser(performanceDTO.getPerformanceUserDTO()));
        return performance;
    }

    private User convertUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setId(userDTO.getID());
        return user;
    }
////////////////////
    public PerformanceDTO getPerformanceById(long id) {
        Performance performance=performanceRepository.findOne(id);
        if(performance==null){
            throw new IllegalArgumentException("The id is not valid.");
        }
        return convertToDto(performance);
    }

    public PerformanceDTO updatePerformance(long id,PerformanceDTO dto) {
        Performance performance=performanceRepository.findOne(id);
//
        performance.setResultList(getResultListAsNotDTO(dto));


        Performance savedObject= performanceRepository.save(performance);

        return convertToDto(savedObject);
    }

    public boolean deletePerformanceById(long id){
        if(performanceRepository.findOne(id)!=null) {
            performanceRepository.delete(id);
            return true;
        }
        return false;
    }
//
    public List<History> getResultListAsNotDTO(PerformanceDTO performanceDTO) {
        List<HistoryDTO> resultList=performanceDTO.getResultList();
        List<History> list=new ArrayList<>();
        for(int i=0;i<resultList.size();i++) {
            HistoryDTO historyDTO=resultList.get(i);
            History history = new History();

            history.setDate(historyDTO.getDatePublic());
            history.setResult(historyDTO.getResult());
            history.setId(historyDTO.getID());
            list.add(history);
        }
        return list;
    }
}
