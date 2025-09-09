package com.jobportal.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.Repository.ApplicationRepository;
import com.jobportal.entities.Application;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    // Apply for a job
    public Application applyForJob(Application application) {
        application.setStatus("PENDING");
        return applicationRepository.save(application);
    }

    // Get applications by candidate
    public List<Application> getApplicationsByCandidate(Long candidateId) {
        return applicationRepository.findByCandidateId(candidateId);
    }

    // Get applications by job
    public List<Application> getApplicationsByJob(Long jobId) {
        return applicationRepository.findByJobId(jobId);
    }

    // Get application by ID
    public Optional<Application> getApplicationById(Long id) {
        return applicationRepository.findById(id);
    }

    // Update application status
    public Application updateApplicationStatus(Application application, String status) {
        application.setStatus(status);
        return applicationRepository.save(application);
    }
}

