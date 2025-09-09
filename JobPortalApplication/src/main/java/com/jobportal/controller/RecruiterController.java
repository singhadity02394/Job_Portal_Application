package com.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.jobportal.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jobportal.entities.Job;
import com.jobportal.entities.Recruiter;
import com.jobportal.service.JobService;
import com.jobportal.service.RecruiterService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/recruiters")
public class RecruiterController {

    @Autowired
    private RecruiterService recruiterService;

    @Autowired
    private JobService jobService;

    @Autowired
    private JwtUtil jwtUtil;  // ✅ inject JwtUtil

    @PostMapping("/register")
    public ResponseEntity<Recruiter> registerRecruiter(@RequestBody Recruiter recruiter) {
        Recruiter savedRecruiter = recruiterService.registerRecruiter(recruiter);
        return ResponseEntity.ok(savedRecruiter);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginRecruiter(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");

        return recruiterService.login(email, password)
                .map(r -> {
                    String token = jwtUtil.generateToken(email); // ✅ now works
                    return ResponseEntity.ok(Map.of("token", token));
                })
                .orElse(ResponseEntity.status(401)
                        .body(Map.of("error", "Invalid credentials")));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recruiter> getRecruiterById(@PathVariable Long id) {
        return recruiterService.getRecruiterById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Recruiter>> getAllRecruiters() {
        return ResponseEntity.ok(recruiterService.getAllRecruiters());
    }

    // Post a job
    @PostMapping("/{recruiterId}/jobs")
    public ResponseEntity<Job> postJob(@PathVariable Long recruiterId, @RequestBody Job job) {
        recruiterService.getRecruiterById(recruiterId).ifPresent(job::setRecruiter);
        Job savedJob = jobService.postJob(job);
        return ResponseEntity.ok(savedJob);
    }
}
