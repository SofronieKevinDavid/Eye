
package com.kevin.persistance;

import com.kevin.domain.RanGame;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RanGameRepository extends
        PagingAndSortingRepository<RanGame, Long> {
}
