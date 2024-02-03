package com.bheki97.dmsspringbackend.service.disasterentitymanager;


import com.bheki97.dmsspringbackend.dto.AssignTechnicianDto;
import com.bheki97.dmsspringbackend.dto.DisasterEntityDto;

import java.util.List;

public interface DisasterEntityManager {

    DisasterEntityDto reportNewDisaster(DisasterEntityDto entity);
    DisasterEntityDto[] getAllDisasters();
    DisasterEntityDto[] getDisastersAssignToTechnician(long technicianId);
    boolean AssignTechnicianToDisaster(AssignTechnicianDto dto);
}
