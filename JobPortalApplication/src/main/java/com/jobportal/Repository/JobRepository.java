package com.jobportal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobportal.entities.Job;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByLocation(String location);
    List<Job> findBySkillsRequiredContaining(String skill);
    List<Job> findByLocationContainingIgnoreCaseAndSkillsRequiredContainingIgnoreCase(
            String location, String skills
    );
	
}
