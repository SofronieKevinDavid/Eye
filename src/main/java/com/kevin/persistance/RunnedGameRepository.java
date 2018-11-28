package com.kevin.persistance;

import com.kevin.domain.RunnedGame;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RunnedGameRepository extends
        PagingAndSortingRepository<RunnedGame, Long> {
}
