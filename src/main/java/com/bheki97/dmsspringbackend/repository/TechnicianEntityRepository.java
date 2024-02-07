package com.bheki97.dmsspringbackend.repository;

import com.bheki97.dmsspringbackend.entity.TechnicianEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicianEntityRepository extends JpaRepository<TechnicianEntity , Long> {
    boolean existsByUserEmail(String email);
    boolean existsByTechnicianId(long id);
    TechnicianEntity findByTechnicianId(long id);

}
