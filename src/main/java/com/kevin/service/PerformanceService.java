package com.kevin.service;


import com.kevin.domain.User;
import com.kevin.dto.*;
import com.kevin.domain.History;
import com.kevin.domain.Performance;
import com.kevin.persistance.HistoryRepository;
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
    private HistoryRepository historyRepository;

    @Autowired
    private UserRepository userRepository;

    public void savePerformance(PerformanceDTO performanceDTO){
        long userId=performanceDTO.getPerformanceUserDTOId();
        User user=userRepository.findOne(userId);



        List<Long> idList=new ArrayList<>();

        for(int i = 0; i<performanceDTO.getResultListDTO().size(); i++) {
            idList.add(performanceDTO.getResultListDTO().get(i).getID());
        }

        List<History> list=new ArrayList<>();
        for(int i=0;i<idList.size();i++){
            list.add(historyRepository.findOne(idList.get(i)));
        }


        Performance performanceObject=convert(performanceDTO);
        performanceObject.setPerformanceUser(user);
        performanceObject.setResultList(list);
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
                //historyDTO.setRunnedGameDTO();
                //RunnedGame runnedGame=new RunnedGame();
                historyDTO.setResult(history.getResult());

                performanceDTO.getResultListDTO().add(historyDTO);
                nrOfProd++;
            }
            list.add(performanceDTO);
        }

        return list;
    }

    private PerformanceDTO convertToDto(Performance performance) {
        PerformanceDTO performanceDTO = new PerformanceDTO();

        performanceDTO.setID(performance.getId());
        performanceDTO.setPerformanceUserDTO(convertUserToDto(performance.getPerformanceUser()));
        performanceDTO.setResultListDTO(convertResultListToDTO(performance.getResultList()));
        return performanceDTO;
    }

    public List<HistoryDTO> convertResultListToDTO(List<History> resultList) {
        List<HistoryDTO> list=new ArrayList<>();
        for(int i=0;i<resultList.size();i++) {
            History history=resultList.get(i);
            HistoryDTO historyDTO = new HistoryDTO();
            historyDTO.setDate(history.getDatePublic());
            historyDTO.setResult(history.getResult());
            historyDTO.setID(history.getId());
            list.add(historyDTO);
        }
        return list;
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
        performance.setResultList(convertResultList(performanceDTO.getResultListDTO()));
        return performance;
    }

    private List<History> convertResultList(List<HistoryDTO> resultList){
        List<History> list=new ArrayList<>();
        for(int i=0;i<resultList.size();i++){
            list.add(convert(resultList.get(i)));
        }
        return list;
    }

    private History convert(HistoryDTO historyDTO) {
        History history = new History();
        history.setResult(historyDTO.getResult());
        history.setDate(historyDTO.getDatePublic());
        history.setId(historyDTO.getID());
        //history.setRunnedGame(convertRunnedGame(historyDTO.getRunnedGameDTO()));
        return history;
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
        List<HistoryDTO> resultList=performanceDTO.getResultListDTO();
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
