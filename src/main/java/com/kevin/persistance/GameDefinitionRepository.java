package com.kevin.persistance;

import com.kevin.domain.GameDefinition;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GameDefinitionRepository extends
        PagingAndSortingRepository<GameDefinition, Long> {
}