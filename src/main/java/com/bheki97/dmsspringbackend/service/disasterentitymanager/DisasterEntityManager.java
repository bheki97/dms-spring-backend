package com.bheki97.dmsspringbackend.service.disasterentitymanager;


import com.bheki97.dmsspringbackend.dto.DisasterEntityDto;

import java.util.List;

public interface DisasterEntityManager {

    DisasterEntityDto reportNewDisaster(DisasterEntityDto entity);
    List<DisasterEntityDto> getAllDisasters();
}
