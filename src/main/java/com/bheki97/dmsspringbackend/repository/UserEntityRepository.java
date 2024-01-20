package com.bheki97.dmsspringbackend.repository;

import com.bheki97.dmsspringbackend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserEntityRepository extends JpaRepository<UserEntity,Long> {


    boolean existsByEmail(String email);
    boolean existsByUserIdAndEmail(long userId,String email);
    UserEntity findByEmail(String email);
}
