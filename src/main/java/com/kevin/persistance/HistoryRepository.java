
package com.kevin.persistance;

import com.kevin.domain.History;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface HistoryRepository extends
        PagingAndSortingRepository<History, Long> {

    List<History> findByHistoryUserId(long id);
}
