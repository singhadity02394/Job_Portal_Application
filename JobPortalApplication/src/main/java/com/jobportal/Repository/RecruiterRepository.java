package com.jobportal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobportal.entities.Recruiter;

import java.util.Optional;

public interface RecruiterRepository extends JpaRepository<Recruiter, Long> {
    Optional<Recruiter> findByEmail(String email);
}
