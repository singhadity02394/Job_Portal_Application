package com.jobportal.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.jobportal.entities.Candidate;

import java.util.Optional;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    Optional<Candidate> findByEmail(String email);
}

