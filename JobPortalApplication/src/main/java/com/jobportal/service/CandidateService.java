package com.jobportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jobportal.Repository.CandidateRepository;
import com.jobportal.entities.Candidate;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    public Candidate registerCandidate(Candidate candidate) {
        candidate.setPassword(passwordEncoder.encode(candidate.getPassword()));
        Candidate savedCandidate = candidateRepository.save(candidate);

        emailService.sendEmail(
                savedCandidate.getEmail(),
                "Welcome to Job Portal",
                "Hi " + savedCandidate.getFullName() + ", your account has been created successfully!"
        );

        return savedCandidate;
    }

   

    // Fetch candidate by ID
    public Optional<Candidate> getCandidateById(Long id) {
        return candidateRepository.findById(id);
    }

    // Fetch candidate by Email
    public Optional<Candidate> getCandidateByEmail(String email) {
        return candidateRepository.findByEmail(email);
    }

    // Fetch all candidates
    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }
}

