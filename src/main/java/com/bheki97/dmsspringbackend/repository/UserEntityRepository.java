package com.bheki97.dmsspringbackend.repository;

import com.bheki97.dmsspringbackend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserEntityRepository extends JpaRepository<UserEntity,Long> {


    boolean existsByEmail(String email);
    boolean existsByUserIdAndEmail(long userId,String email);
    Optional<UserEntity> findByEmail(String email);
}
