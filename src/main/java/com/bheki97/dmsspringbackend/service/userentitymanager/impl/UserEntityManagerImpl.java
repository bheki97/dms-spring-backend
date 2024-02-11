package com.bheki97.dmsspringbackend.service.userentitymanager.impl;

import com.bheki97.dmsspringbackend.dto.UserEntityDto;
import com.bheki97.dmsspringbackend.entity.UserEntity;
import com.bheki97.dmsspringbackend.exception.DMSException;
import com.bheki97.dmsspringbackend.repository.UserEntityRepository;
import com.bheki97.dmsspringbackend.service.technicianentitymanager.impl.TechnicianEntityManagerImpl;
import com.bheki97.dmsspringbackend.service.userentitymanager.UserEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserEntityManagerImpl implements UserEntityManager {

    @Autowired
    private UserEntityRepository userRepository;

    @Override
    public UserEntity addNewUser(UserEntity entity) {
        checkForNullAndEmptyField(entity);

        if(userRepository.existsByEmail(entity.getEmail())){
            throw new DMSException("Account Already exists");
        }

        return userRepository.save(entity);
    }

    @Override
    public UserEntityDto addNewAdmin(UserEntity entity) {


        entity.setPassword(TechnicianEntityManagerImpl.generatePassword());
        System.out.println(entity.getPassword());

        checkForNullAndEmptyField(entity);

        if(userRepository.existsByEmail(entity.getEmail())){
            throw new DMSException("Account Already exists");
        }
        return toUserEntityDto(userRepository.save(entity));
    }

    @Override
    public UserEntityDto[] getAllAdmins() {
        List<UserEntityDto> list  = userRepository.findAllByUserRole("admin")
                .stream().map(this::toUserEntityDto).toList();
        UserEntityDto[] arr = new UserEntityDto[list.size()];

        return list.toArray(arr);
    }

    private UserEntityDto toUserEntityDto(UserEntity userEntity) {
        UserEntityDto dto = new UserEntityDto();

        dto.setUserId(userEntity.getUserId());
        dto.setFirstname(userEntity.getFirstname());
        dto.setLastname(userEntity.getLastname());
        dto.setCellNo(userEntity.getCellNo());
        dto.setEmail(userEntity.getEmail());

        return dto;
    }

    private void checkForNullAndEmptyField(UserEntity entity) {
        if(
                entity.getEmail()==null
                ||entity.getEmail().isEmpty()
                ||entity.getPassword()==null
                ||entity.getPassword().isEmpty()
                ||entity.getUserRole() ==null
                ||entity.getUserRole().isEmpty()
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

        return userRepository.findByEmail(email);
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
