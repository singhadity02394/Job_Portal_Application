package com.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jobportal.entities.Application;
import com.jobportal.service.ApplicationService;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    // Apply for a job
    @PostMapping("/apply")
    public ResponseEntity<Application> applyForJob(@RequestBody Application application) {
        Application savedApp = applicationService.applyForJob(application);
        return ResponseEntity.ok(savedApp);
    }

    // Get applications by candidate
    @GetMapping("/candidate/{candidateId}")
    public ResponseEntity<List<Application>> getApplicationsByCandidate(@PathVariable Long candidateId) {
        return ResponseEntity.ok(applicationService.getApplicationsByCandidate(candidateId));
    }

    // Get applications by job
    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<Application>> getApplicationsByJob(@PathVariable Long jobId) {
        return ResponseEntity.ok(applicationService.getApplicationsByJob(jobId));
    }

    // Update application status
    @PutMapping("/{id}/status")
    public ResponseEntity<Application> updateStatus(@PathVariable Long id, @RequestParam String status) {
        return applicationService.getApplicationById(id)
                .map(app -> ResponseEntity.ok(applicationService.updateApplicationStatus(app, status)))
                .orElse(ResponseEntity.notFound().build());
    }
}
