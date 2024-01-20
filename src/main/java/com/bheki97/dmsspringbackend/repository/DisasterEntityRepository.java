package com.bheki97.dmsspringbackend.repository;

import com.bheki97.dmsspringbackend.entity.DisasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisasterEntityRepository extends JpaRepository<DisasterEntity,Long> {
}
