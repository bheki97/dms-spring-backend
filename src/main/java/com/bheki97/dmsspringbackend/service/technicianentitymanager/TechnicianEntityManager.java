package com.bheki97.dmsspringbackend.service.technicianentitymanager;

import com.bheki97.dmsspringbackend.dto.TechnicianDto;
import com.bheki97.dmsspringbackend.entity.TechnicianEntity;


public interface TechnicianEntityManager {
    TechnicianDto[] newTechnician(TechnicianDto technician);
    TechnicianDto[] getAllTechnicians();
}
