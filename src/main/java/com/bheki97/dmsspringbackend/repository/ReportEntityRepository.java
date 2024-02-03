package com.bheki97.dmsspringbackend.repository;

import com.bheki97.dmsspringbackend.entity.DisasterReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportEntityRepository extends JpaRepository<DisasterReportEntity,Long> {

}
