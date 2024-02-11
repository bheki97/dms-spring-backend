package com.bheki97.dmsspringbackend.service.userentitymanager;

import com.bheki97.dmsspringbackend.dto.UserEntityDto;
import com.bheki97.dmsspringbackend.entity.UserEntity;

import java.util.List;

public interface UserEntityManager {


    UserEntity addNewUser(UserEntity entity);
    UserEntityDto addNewAdmin(UserEntity entity);
    UserEntityDto[] getAllAdmins();

    UserEntity getUserByEmail(String email);
    UserEntity updateUser(UserEntity entity);
    List<UserEntity> getAllUsers();
}
