package com.bheki97.dmsspringbackend.repository;

import com.bheki97.dmsspringbackend.entity.SpecialityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecialityEntityRepository extends JpaRepository<SpecialityEntity,Long> {

    List<SpecialityEntity> findAllByActive(boolean active);
    boolean existsBySpecName(String name);
}
