package com.bheki97.dmsspringbackend.service.userentitymanager.impl;

import com.bheki97.dmsspringbackend.entity.UserEntity;
import com.bheki97.dmsspringbackend.exception.DMSException;
import com.bheki97.dmsspringbackend.repository.UserEntityRepository;
import com.bheki97.dmsspringbackend.service.userentitymanager.UserEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserEntityManagerImpl implements UserEntityManager {

    @Autowired
    private UserEntityRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserEntity addNewUser(UserEntity entity) {
        checkForNullAndEmptyField(entity);
        entity.setUserRole("user");
        if(userRepository.existsByEmail(entity.getEmail())){
            throw new DMSException("Account Already exists");
        }

        entity.setPassword(passwordEncoder.encode(entity.getPassword()));

        return userRepository.save(entity);
    }

    private void checkForNullAndEmptyField(UserEntity entity) {
        if(
                entity.getEmail()==null
                ||entity.getEmail().isEmpty()
                ||entity.getPassword()==null
                ||entity.getPassword().isEmpty()
                ||entity.getCellNo()==null
                ||entity.getCellNo().isEmpty()
                ||entity.getFirstname()==null
                ||entity.getFirstname().isEmpty()
                ||entity.getLastname()==null
                ||entity.getLastname().isEmpty()
        ){
            throw new DMSException("Cannot have any of the user field Empty");
        }
    }

    @Override
    public UserEntity getUserByEmail(String email) {

        if(email==null || email.isEmpty()||  !userRepository.existsByEmail(email)){
            throw new DMSException("User does not exist");
        }

        return userRepository.findByEmail(email).get();
    }

    @Override
    public UserEntity updateUser(UserEntity entity) {

        checkForNullAndEmptyField(entity);

        String email = entity.getEmail();
        if(!userRepository.existsByUserIdAndEmail(entity.getUserId(),email)){
            throw new DMSException("User does not exist");
        }


        return userRepository.save(entity);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
}
