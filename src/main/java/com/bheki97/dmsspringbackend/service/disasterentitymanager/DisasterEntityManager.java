package com.bheki97.dmsspringbackend.service.disasterentitymanager;


import com.bheki97.dmsspringbackend.dto.AssignTechnicianDto;
import com.bheki97.dmsspringbackend.dto.CompleteDisasterDto;
import com.bheki97.dmsspringbackend.dto.DisasterEntityDto;

public interface DisasterEntityManager {

    DisasterEntityDto reportNewDisaster(DisasterEntityDto entity);
    DisasterEntityDto[] getAllDisasters();
    DisasterEntityDto[] getAllMyReportedDisasters(long reporterId);
    DisasterEntityDto[] getAllTechnicianActiveDisasters(long technicianId);
    CompleteDisasterDto[] getAllTechnicianCompletedDisasters(long technicianId);


    DisasterEntityDto[] getDisastersAssignToTechnician(long technicianId);
    boolean assignTechnicianToDisaster(AssignTechnicianDto dto);
    boolean attendDisaster(long reportId);
    boolean notifyCompletion(long reportId);
}
