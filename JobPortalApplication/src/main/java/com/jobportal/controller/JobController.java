package com.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jobportal.entities.Job;
import com.jobportal.service.JobService;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Job>> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    @GetMapping("/search/location")
    public ResponseEntity<List<Job>> searchJobsByLocation(@RequestParam String location) {
        return ResponseEntity.ok(jobService.searchJobsByLocation(location));
    }

    @GetMapping("/search/skill")
    public ResponseEntity<List<Job>> searchJobsBySkill(@RequestParam String skill) {
        return ResponseEntity.ok(jobService.searchJobsBySkill(skill));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        return jobService.getJobById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Job>> searchJobs(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String skill
    ) {
        List<Job> jobs = jobService.searchJobs(location, skill);
        return ResponseEntity.ok(jobs);
    }
}
