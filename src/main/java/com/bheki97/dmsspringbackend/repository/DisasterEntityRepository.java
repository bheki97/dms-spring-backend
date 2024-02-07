package com.bheki97.dmsspringbackend.repository;

import com.bheki97.dmsspringbackend.entity.DisasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DisasterEntityRepository extends JpaRepository<DisasterEntity,Long> {

    List<DisasterEntity> findAllByReportEntityTechnicianTechnicianId(long id);
    List<DisasterEntity> findAllByReporterUserId(long reporterId);
}
