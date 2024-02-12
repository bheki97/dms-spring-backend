package com.bheki97.dmsspringbackend.service.disasterentitymanager.impl;

import com.bheki97.dmsspringbackend.dto.*;

import com.bheki97.dmsspringbackend.entity.DisasterEntity;
import com.bheki97.dmsspringbackend.entity.DisasterReportEntity;
import com.bheki97.dmsspringbackend.entity.TechnicianEntity;
import com.bheki97.dmsspringbackend.entity.UserEntity;
import com.bheki97.dmsspringbackend.exception.DMSException;
import com.bheki97.dmsspringbackend.repository.DisasterEntityRepository;
import com.bheki97.dmsspringbackend.repository.DisasterReportEntityRepository;
import com.bheki97.dmsspringbackend.repository.TechnicianEntityRepository;
import com.bheki97.dmsspringbackend.repository.UserEntityRepository;
import com.bheki97.dmsspringbackend.service.disasterentitymanager.DisasterEntityManager;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.List;

@Service
public class DisasterEntityManagerImpl implements DisasterEntityManager {

    @Autowired
    private DisasterEntityRepository entityRepository;

    @Autowired
    private UserEntityRepository userEntityRepository;
    @Autowired
    private DisasterReportEntityRepository reportEntityRepository;
    @Autowired
    private TechnicianEntityRepository technicianEntityRepository;


    @Override
    public DisasterEntityDto reportNewDisaster(DisasterEntityDto dto) {

        validateNewDisaster(dto);

        DisasterEntity entity = translateNewDtoToEntity(dto);

        return translateEntityToDto(entityRepository.save(entity));
    }

    @Override
    public DisasterEntityDto[] getAllDisasters() {

        List<DisasterEntityDto> list = entityRepository.findAll()
                .stream().map(this::translateEntityToDto).toList();
        DisasterEntityDto[] dto = new DisasterEntityDto[list.size()];
        return list.toArray(dto);
    }

    @Override
    public DisasterEntityDto getDisasterByDisasterId(long disasterId) {

        return translateEntityToDto(entityRepository.findById(disasterId)
                .orElseThrow(()->new DMSException("Disaster does not Exists")));
    }

    @Override
    public DisasterEntityDto[] getDisastersAssignToTechnician(long technicianId) {

        if(!technicianEntityRepository.existsByTechnicianId(technicianId)){
            throw new DMSException("Technician Does not Exist");
        }

        List<DisasterEntityDto> list = entityRepository
                .findAllByReportEntityTechnicianTechnicianId(technicianId).stream()
                .map(this::translateEntityToDto).toList();
        DisasterEntityDto[] arr = new DisasterEntityDto[list.size()];

        return list.toArray(arr);
    }
    @Override
    public DisasterEntityDto[] getAllMyReportedDisasters(long reporterId) {

        if(!userEntityRepository.existsById(reporterId)){
            throw new DMSException("User Does not Exist");
        }

        List<DisasterEntityDto> list = entityRepository
                .findAllByReporterUserId(reporterId).stream()
                .map(this::translateEntityToDto).toList();
        DisasterEntityDto[] arr = new DisasterEntityDto[list.size()];

        return list.toArray(arr);
    }

    @Override
    public DisasterEntityDto[] getAllTechnicianActiveDisasters(long technicianId) {

        if(!technicianEntityRepository.existsByTechnicianId(technicianId)){
            throw new DMSException("Technician Does not Exist");
        }

        List<DisasterEntityDto> list = entityRepository
                .findAllByReportEntityTechnicianTechnicianIdAndReportEntityCompleteDateIsNull(technicianId).stream()
                .map(this::translateEntityToDto).toList();
        DisasterEntityDto[] arr = new DisasterEntityDto[list.size()];

        return list.toArray(arr);
    }

    @Override
    public CompleteDisasterDto[] getAllTechnicianCompletedDisasters(long technicianId) {
        if(!technicianEntityRepository.existsByTechnicianId(technicianId)){
            throw new DMSException("Technician Does not Exist");
        }

        List<CompleteDisasterDto> list = entityRepository
                .findAllByReportEntityTechnicianTechnicianIdAndReportEntityCompleteDateIsNotNull(technicianId).stream()
                .map(this::disasterEntityToCompleteDisasterDto).toList();
        CompleteDisasterDto[] arr = new CompleteDisasterDto[list.size()];

        return list.toArray(arr);
    }


    private CompleteDisasterDto disasterEntityToCompleteDisasterDto(DisasterEntity entity){
        CompleteDisasterDto dto = new CompleteDisasterDto();
        DisasterReportEntity reportEntity  = entity.getReportEntity();


        dto.setImgContent(getBase64ImgContent(entity.getImgPath()));
        dto.setCompleteDate(reportEntity.getCompleteDate());
        dto.setReportDate(reportEntity.getReportDate());
        dto.setType(entity.getType());


        return dto;
    }


    @Override
    public boolean assignTechnicianToDisaster(AssignTechnicianDto dto) {

        if(!reportEntityRepository.existsById(dto.getReportId())){
            throw new DMSException("Disaster Report Does not exists!!");
        }

        if(!technicianEntityRepository.existsByTechnicianId(dto.getTechnicianId())){
            throw new DMSException("You have choose non existing Technician");
        }

        TechnicianEntity technician = technicianEntityRepository.findByTechnicianId(dto.getTechnicianId());
        DisasterReportEntity report = reportEntityRepository
                .findById(dto.getReportId()).orElseThrow( ()-> new DMSException("Disaster Report Does not exists!!"));

        report.setTechnician(technician);
        report.setDelegationDate( new Timestamp(System.currentTimeMillis()));

        reportEntityRepository.save(report);

        return true;
    }

    @Override
    public boolean attendDisaster(long reportId) {

        if(!reportEntityRepository.existsById(reportId)){
            throw new DMSException("Disasters does not exist");
        }
        DisasterReportEntity reportEntity = reportEntityRepository
                .findById(reportId)
                .orElseThrow(() -> new DMSException("Disaster Does not exist"));

        if(reportEntity.getTechnician()==null || reportEntity.getDelegationDate()==null){
            throw new DMSException("You have not been delegated to attend this Disaster");
        }
        if(reportEntity.getCompleteDate()!=null){
            throw new DMSException("Disaster have been resolved!!!");
        }

        reportEntity.setTechnicianAttendDate(new Timestamp(System.currentTimeMillis()));

        reportEntityRepository.save(reportEntity);

        return true;
    }

    @Override
    public boolean notifyCompletion(long reportId) {

        if(!reportEntityRepository.existsById(reportId)){
            throw new DMSException("Disasters does not exist");
        }

        DisasterReportEntity reportEntity = reportEntityRepository
                .findById(reportId)
                .orElseThrow(() -> new DMSException("Disaster Does not exist"));

        if(reportEntity.getDelegationDate()==null){
            throw new DMSException("You have not been delegated to attend this Disaster");
        }

        if(reportEntity.getTechnicianAttendDate()==null){
            throw new DMSException("You have not attended this Disaster");
        }

        if(reportEntity.getCompleteDate()!=null){
            throw new DMSException("Disaster have been resolved!!!");
        }
        reportEntity.setCompleteDate(new Timestamp(System.currentTimeMillis()));
        reportEntityRepository.save(reportEntity);




        return true;
    }

    private void validateNewDisaster(DisasterEntityDto dto) {

        if(
                dto.getReporter().getReporterId()<1
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
        entity.setLocation(dto.getLocation());

        DisasterReportEntity reportEntity = new DisasterReportEntity();
        reportEntity.setReportDate(new Timestamp(System.currentTimeMillis()));

        entity.setReportEntity(reportEntity);


        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(dto.getReporter().getReporterId());
        entity.setReporter(userEntity);

        if(dto.getImgFileContent()!=null && !dto.getImgFileContent().isEmpty()){
            String imgPath = saveImageToFolder(dto.getImgFileName(),dto.getImgFileContent());
            entity.setImgPath(imgPath);
        }




        return entity;
    }

    private String saveImageToFolder(String imgFileName,String imgFileContent) {


        Path path = Path.of("src","main","resources","static","disaster-images",+System.currentTimeMillis() +"-"+imgFileName);
        System.out.println(path.toAbsolutePath());
        try {
            path = path.toAbsolutePath();
            Files.createFile(path);


            Files.write(path,Base64.getDecoder().decode(imgFileContent));
            return path.getFileName().toString();

        } catch (IOException e) {
            e.printStackTrace();
            throw new DMSException("image content invalid: "+e.getMessage());
        }



    }

    private DisasterEntityDto translateEntityToDto(DisasterEntity entity) {
        DisasterEntityDto dto = new DisasterEntityDto();
        dto.setDisasterId(entity.getDisasterId());
        dto.setDisasterDesc(entity.getDisasterDesc());
        dto.setLatitude(entity.getLatitude());
        dto.setLongitude(entity.getLongitude());
        dto.setType(entity.getType());
        dto.setLocation(entity.getLocation());


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
        reportDto.setDelegationDate(reportEntity.getDelegationDate());
        reportDto.setTechnicianAttendDate(reportEntity.getTechnicianAttendDate());
        reportDto.setCompleteDate(reportEntity.getCompleteDate());

        //initialize Technician
        if(reportEntity.getTechnician()!=null){
            TechnicianDto technicianDto = new TechnicianDto();
            TechnicianEntity technician = reportEntity.getTechnician();
            technicianDto.setUserId(technician.getUser().getUserId());
            technicianDto.setTechnicianId(technician.getTechnicianId());
            technicianDto.setFirstname(technician.getUser().getFirstname());
            technicianDto.setLastname(technician.getUser().getLastname());
            technicianDto.setEmail(technician.getUser().getEmail());
            technicianDto.setCellNo(technician.getUser().getCellNo());

            technicianDto.setDeptId(technician.getDepartment().getDeptId());
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

        Path path = Path.of("src","main","resources","static","disaster-images",imgPath);
        try {
            byte[] bytes = Files.readAllBytes(path);
            return Base64.getEncoder().encodeToString(bytes);
        } catch (IOException e) {
            throw new DMSException("Could not get Images");
        }


    }


}
