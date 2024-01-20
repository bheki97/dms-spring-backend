package com.bheki97.dmsspringbackend.repository;

import com.bheki97.dmsspringbackend.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentEntityRepository extends JpaRepository<DepartmentEntity,Long> {

    List<DepartmentEntity> findAllByActive(boolean active);
    boolean existsByDeptName(String name);
}
