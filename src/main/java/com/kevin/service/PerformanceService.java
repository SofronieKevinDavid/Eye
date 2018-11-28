/*
package com.kevin.service;

import com.kevin.domain.Performance;
import com.kevin.persistance.PerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerformanceService {

    @Autowired
    private PerformanceRepository performanceRepository;

    public void savePerformance(Performance performance){
        if(performance.getID()==0){
            throw new IllegalArgumentException("Name can not be null.");
        }
        try {
            performanceRepository.save(performance);
        }catch (Exception e){
            System.out.println("Error in saving user "+e);
        }
    }
}
*/
