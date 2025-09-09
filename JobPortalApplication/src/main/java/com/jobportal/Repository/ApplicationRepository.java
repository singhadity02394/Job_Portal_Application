package com.jobportal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobportal.entities.Application;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByCandidateId(Long candidateId);
    List<Application> findByJobId(Long jobId);
}
