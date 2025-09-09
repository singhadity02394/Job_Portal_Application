package com.jobportal.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.Repository.JobRepository;
import com.jobportal.entities.Job;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    // Post a new job
    public Job postJob(Job job) {
        return jobRepository.save(job);
    }

    // Fetch job by ID
    public Optional<Job> getJobById(Long id) {
        return jobRepository.findById(id);
    }

    // Fetch all jobs
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    // Search jobs by location
    public List<Job> searchJobsByLocation(String location) {
        return jobRepository.findByLocation(location);
    }

    // Search jobs by skill
    public List<Job> searchJobsBySkill(String skill) {
        return jobRepository.findBySkillsRequiredContaining(skill);
    }
    
    // ✅ Search jobs by location and skill
    public List<Job> searchJobs(String location, String skill) {
        return jobRepository.findByLocationContainingIgnoreCaseAndSkillsRequiredContainingIgnoreCase(
                location != null ? location : "",
                skill != null ? skill : ""
        );
    }
    
   
    

}

