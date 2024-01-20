package com.bheki97.dmsspringbackend.service.disasterentitymanager.impl;

import com.bheki97.dmsspringbackend.dto.DisasterEntityDto;
import com.bheki97.dmsspringbackend.dto.DisasterReportDto;
import com.bheki97.dmsspringbackend.dto.ReporterDto;
import com.bheki97.dmsspringbackend.dto.TechnicianDto;
import com.bheki97.dmsspringbackend.entity.DisasterEntity;
import com.bheki97.dmsspringbackend.entity.DisasterReportEntity;
import com.bheki97.dmsspringbackend.entity.TechnicianEntity;
import com.bheki97.dmsspringbackend.entity.UserEntity;
import com.bheki97.dmsspringbackend.exception.DMSException;
import com.bheki97.dmsspringbackend.service.disasterentitymanager.DisasterEntityManager;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.util.Base64;
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
                dto.getReporter().getReporterId()<1
                ||dto.getReportDto().getTechnicianDto().getTechnicianId()<1
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

        entity.setDisasterDesc(dto.getDisasterDesc());
        entity.setType(dto.getType());
        entity.setLatitude(dto.getLatitude());
        entity.setLongitude(dto.getLongitude());

        DisasterReportEntity reportEntity = new DisasterReportEntity();
        reportEntity.setReportDate(new Timestamp(System.currentTimeMillis()));
        if(dto.getReportDto()!=null && dto.getReportDto().getTechnicianDto()!=null){
            TechnicianEntity technicianEntity = new TechnicianEntity();
            technicianEntity.setUserId(dto.getReportDto().getTechnicianDto().getUserId());
            technicianEntity.setUserId(dto.getReportDto().getTechnicianDto().getTechnicianId());
            reportEntity.setTechnician(technicianEntity);
        }
        entity.setReportEntity(reportEntity);


        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(dto.getReporter().getReporterId());
        entity.setReporter(userEntity);


        String imgPath = saveImageToFolder(dto.getImgFileName(),dto.getImgFileContent());
        entity.setImgPath(imgPath);



        return entity;
    }

    private String saveImageToFolder(String imgFileName,String imgFileContent) {


        Path path = Path.of("resources","static","disaster-images",+System.currentTimeMillis() +imgFileName);

        try {
            Files.write(path,Base64.getDecoder().decode(imgFileContent));
            return path.getFileName().toString();

        } catch (IOException e) {
            throw new DMSException("image content invalid");
        }



    }

    private DisasterEntityDto translateEntityToDto(DisasterEntity entity) {
        DisasterEntityDto dto = new DisasterEntityDto();
        dto.setDisasterId(entity.getDisasterId());
        dto.setDisasterDesc(entity.getDisasterDesc());
        dto.setLatitude(entity.getLatitude());
        dto.setLongitude(entity.getLongitude());
        dto.setType(entity.getType());


        //initialize reporter
        UserEntity userEntity = entity.getReporter();
        ReporterDto reporterDto = new ReporterDto();
        reporterDto.setReporterId(userEntity.getUserId());
        reporterDto.setLastname(userEntity.getLastname());
        reporterDto.setFirstname(userEntity.getFirstname());
        reporterDto.setCellNo(userEntity.getCellNo());
        reporterDto.setEmail(userEntity.getEmail());
        dto.setReporter(reporterDto);

        //initialize Report
        DisasterReportEntity reportEntity = entity.getReportEntity();
        DisasterReportDto reportDto = new DisasterReportDto();
        reportDto.setDisasterReportId(reportEntity.getReportId());
        reportDto.setReportDate(reportEntity.getReportDate());
        reportDto.setTechnicianAttendDate(reportEntity.getTechnicianAttendDate());
        reportDto.setCompleteDate(reportEntity.getCompleteDate());

        //initialize Technician
        if(reportEntity.getTechnician()!=null){
            TechnicianDto technicianDto = new TechnicianDto();
            TechnicianEntity technician = reportEntity.getTechnician();
            technicianDto.setUserId(technician.getUserId());
            technicianDto.setTechnicianId(technician.getTechnicianId());
            technicianDto.setFirstname(technician.getFirstname());
            technicianDto.setLastname(technician.getLastname());
            technicianDto.setEmail(technician.getEmail());
            technicianDto.setCellNo(technician.getCellNo());

            technicianDto.setDeptID(technician.getDepartment().getDeptId());
            technicianDto.setDeptName(technician.getDepartment().getDeptName());
            technicianDto.setSpecId(technician.getSpeciality().getSpecId());
            technicianDto.setSpecName(technician.getSpeciality().getSpecName());

            reportDto.setTechnicianDto(technicianDto);
        }
        dto.setReportDto(reportDto);


//      initialize Image
        dto.setImgFileName(entity.getImgPath());
        String content =  getBase64ImgContent(entity.getImgPath());
        dto.setImgFileContent(content);


        return dto;
    }

    private String getBase64ImgContent(String imgPath) {

        Path path = Path.of("resources","static","disaster-images",imgPath);
        try {
            byte[] bytes = Files.readAllBytes(path);
            return Base64.getEncoder().encodeToString(bytes);
        } catch (IOException e) {
            throw new DMSException("Could not get Images");
        }


    }


}
