package com.bheki97.dmsspringbackend.repository;

import com.bheki97.dmsspringbackend.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserEntityRepository extends JpaRepository<UserEntity,Long> {


    boolean existsByEmail(String email);
    boolean existsByUserIdAndEmail(long userId,String email);
    UserEntity findByEmail(String email);

    List<UserEntity> findAllByUserRole(String role);
}
