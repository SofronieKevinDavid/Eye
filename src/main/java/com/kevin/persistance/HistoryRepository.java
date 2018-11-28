
package com.kevin.persistance;

import com.kevin.domain.History;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface HistoryRepository extends
        PagingAndSortingRepository<History, Long> {
}

