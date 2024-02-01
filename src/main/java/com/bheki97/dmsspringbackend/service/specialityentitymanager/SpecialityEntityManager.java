package com.bheki97.dmsspringbackend.service.specialityentitymanager;


import com.bheki97.dmsspringbackend.entity.SpecialityEntity;

import java.util.List;

public interface SpecialityEntityManager {

    SpecialityEntity[] addNewSpeciality(SpecialityEntity entity);
    SpecialityEntity[] getAllSpeciality();
    SpecialityEntity[] getAllActiveSpeciality();
}
