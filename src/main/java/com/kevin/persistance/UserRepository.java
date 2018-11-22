package com.kevin.persistance;


import com.kevin.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;
public interface UserRepository extends
        PagingAndSortingRepository<User, Long>{

}
