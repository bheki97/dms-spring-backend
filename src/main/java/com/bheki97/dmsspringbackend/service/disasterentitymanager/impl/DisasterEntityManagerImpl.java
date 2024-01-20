package com.bheki97.dmsspringbackend.service.disasterentitymanager.impl;

import com.bheki97.dmsspringbackend.dto.DisasterEntityDto;
import com.bheki97.dmsspringbackend.entity.DisasterEntity;
import com.bheki97.dmsspringbackend.entity.DisasterReportEntity;
import com.bheki97.dmsspringbackend.entity.UserEntity;
import com.bheki97.dmsspringbackend.exception.DMSException;
import com.bheki97.dmsspringbackend.service.disasterentitymanager.DisasterEntityManager;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

@Service
public class DisasterEntityManagerImpl implements DisasterEntityManager {
    @Override
    public DisasterEntityDto reportNewDisaster(DisasterEntityDto dto) {

        validateNewDisaster(dto);

        DisasterEntity entity = translateNewDtoToEntity(dto);

        return translateEntityToDto(entity);
    }

    @Override
    public List<DisasterEntityDto> getAllDisasters() {
        return null;
    }

    private void validateNewDisaster(DisasterEntityDto dto) {

        if(
                dto.getReporterId()<1
                ||dto.getTechnicianId()<1
                ||dto.getType()==null
                ||dto.getDisasterDesc()==null||dto.getDisasterDesc().isEmpty()
                ||dto.getLatitude()==null||dto.getLatitude().isEmpty()
                ||dto.getLongitude()==null||dto.getLongitude().isEmpty()

        ){
            throw new DMSException("Cannot report disaster with empty or invalid Fields");
        }

    }

    private DisasterEntity translateNewDtoToEntity(DisasterEntityDto dto) {
        DisasterEntity entity = new DisasterEntity();

        entity.setDisasterId(dto.getDisasterId());
        entity.setDisasterDesc(dto.getDisasterDesc());
        entity.setType(dto.getType());
        entity.setLatitude(dto.getLatitude());
        entity.setLongitude(dto.getLongitude());

        DisasterReportEntity reportEntity = new DisasterReportEntity();
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(dto.getTechnicianId());
        reportEntity.setTechnician(userEntity);
        entity.setReportEntity(reportEntity);


        userEntity = new UserEntity();
        userEntity.setUserId(dto.getReporterId());
        entity.setReporter(userEntity);


        String imgPath = saveImageToFolder(dto.getImgFile());




        return entity;
    }

    private String saveImageToFolder(File imgFile) {
        Path path = Path.of("resources","static","disaster-images",imgFile.getName());
        

        return null;
    }

    private DisasterEntityDto translateEntityToDto(DisasterEntity entity) {



        return null;
    }





}
