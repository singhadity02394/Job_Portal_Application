package com.jobportal.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.jobportal.entities.Candidate;
import com.jobportal.security.JwtUtil;
import com.jobportal.service.CandidateService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    private final CandidateService candidateService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public CandidateController(CandidateService candidateService,
                               PasswordEncoder passwordEncoder,
                               JwtUtil jwtUtil) {
        this.candidateService = candidateService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // ✅ Login API
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");

        return candidateService.getCandidateByEmail(email)
                .filter(c -> passwordEncoder.matches(password, c.getPassword()))
                .map(c -> {
                    String token = jwtUtil.generateToken(email);
                    return ResponseEntity.ok(Map.of("token", token));
                })
                .orElse(ResponseEntity.status(401).body(Map.of("error", "Invalid credentials")));
    }

    // ✅ Register Candidate
    @PostMapping("/register")
    public ResponseEntity<Candidate> registerCandidate(@RequestBody Candidate candidate) {
        Candidate savedCandidate = candidateService.registerCandidate(candidate);
        return ResponseEntity.ok(savedCandidate);
    }

    // ✅ Get Candidate by ID
    @GetMapping("/{id}")
    public ResponseEntity<Candidate> getCandidateById(@PathVariable Long id) {
        return candidateService.getCandidateById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Get All Candidates
    @GetMapping("/all")
    public ResponseEntity<List<Candidate>> getAllCandidates() {
        return ResponseEntity.ok(candidateService.getAllCandidates());
    }
}
