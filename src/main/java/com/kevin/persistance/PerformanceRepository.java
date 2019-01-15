
package com.kevin.persistance;

import com.kevin.domain.Performance;
import com.kevin.dto.PerformanceDTO;
import com.kevin.dto.UserDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PerformanceRepository extends
        PagingAndSortingRepository<Performance, Long> {

    //@Query("select p from Performance p where p.resultList.runnedGame.user.id=?1")
    //Performance findPerformanceByResultListRunnedGameUserId(long id);
}
