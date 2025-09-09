package com.jobportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jobportal.Repository.RecruiterRepository;
import com.jobportal.entities.Recruiter;

import java.util.List;
import java.util.Optional;

@Service
public class RecruiterService {

    @Autowired
    private RecruiterRepository recruiterRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ✅ Register new recruiter with password encoding
    public Recruiter registerRecruiter(Recruiter recruiter) {
        recruiter.setPassword(passwordEncoder.encode(recruiter.getPassword()));
        return recruiterRepository.save(recruiter);
    }

    // Fetch recruiter by ID
    public Optional<Recruiter> getRecruiterById(Long id) {
        return recruiterRepository.findById(id);
    }

    // Fetch recruiter by email
    public Optional<Recruiter> getRecruiterByEmail(String email) {
        return recruiterRepository.findByEmail(email);
    }

    // Fetch all recruiters
    public List<Recruiter> getAllRecruiters() {
        return recruiterRepository.findAll();
    }

    // ✅ Login method (optional if you want service-layer login logic)
    public Optional<Recruiter> login(String email, String rawPassword) {
        return recruiterRepository.findByEmail(email)
                .filter(r -> passwordEncoder.matches(rawPassword, r.getPassword()));
    }
}
