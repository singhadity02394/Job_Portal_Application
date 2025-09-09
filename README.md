Job Portal Application

A  Job Portal Application that connects candidates with employers. Candidates can register, create profiles, and apply for jobs,
while employers can post job openings and manage applications. The platform includes secure authentication and role-based authorization for candidates, employers, and admins.


Features

Candidate Module

Register and login

Search and apply for jobs

Employer Module

Register and login

Post new job openings

Manage job applications

Admin Module

Manage users (candidates & employers)

Monitor job postings

Ensure platform security

Security

JWT Authentication & Authorization




🛠️ Tech Stack

Backend: Java, Spring Boot, Spring Security, Spring Data JPA

Database: MySQL

Build Tool: Maven

Version Control: Git, GitHub

Testing & API Tools: JUnit, Postman

Job-Portal-Application/
│── src/
│   ├── main/
│   │   ├── java/com/jobportal/
│   │   │   ├── controller/      # REST Controllers
│   │   │   ├── service/         # Business Logic
│   │   │   ├── repository/      # Data Access Layer
│   │   │   ├── entity/          # Database Models
│   │   │   ├── security/        # JWT & Security Config
│   │   └── resources/
│   │       ├── application.properties
│   │       └── templates/   
│   │
│   └── test/                  
│
│── pom.xml
│── README.md
Configure Database (MySQL)
spring.application.name=JobPortal

# ===============================
# DATABASE CONFIGURATION
# ===============================
spring.datasource.url=jdbc:mysql://localhost:3306/jobPortal
spring.datasource.username=root
spring.datasource.password=Sunny123@

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# ===============================
# SERVER CONFIG
# ===============================
server.port=8081

# ===============================
# JWT CONFIG
# ===============================
jwt.secret=MyJwtSecretKey12345
jwt.expiration=86400000   # 1 day in milliseconds

# ===============================
# EMAIL CONFIG (Gmail SMTP)
# ===============================
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=singhaditya5343@gmail.com
spring.mail.password=gocd hdcn xitr fsev 
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true



📂 API Controllers Overview
1️⃣ ApplicationController (/api/applications)

Handles job application-related APIs.

POST /apply → Apply for a job

GET /candidate/{candidateId} → Get all applications by a candidate

GET /job/{jobId} → Get all applications for a job

PUT /{id}/status?status=... → Update application status (e.g., Pending → Accepted/Rejected)

2️⃣ CandidateController (/api/candidates)

Manages candidate authentication and profiles.

POST /login → Candidate login (returns JWT token)

POST /register → Register a new candidate

GET /{id} → Get candidate details by ID

GET /all → Get list of all candidates

3️⃣ JobController (/api/jobs)

Handles job searching and retrieval.

GET /getAll → Get all jobs

GET /search/location?location=Delhi → Search jobs by location

GET /search/skill?skill=Java → Search jobs by skill

GET /{id} → Get job details by ID

GET /search?location=Delhi&skill=Java → Search jobs by location and/or skill (combined filter)

4️⃣ RecruiterController (/api/recruiters)

Handles recruiter authentication and job posting.

POST /register → Register a recruiter

POST /login → Recruiter login (returns JWT token)

GET /{id} → Get recruiter details by ID

GET /getAll → Get all recruiters

POST /{recruiterId}/jobs → Recruiter posts a job

✨ Contributor: Aditya Singh




Role-based access control

Password encryption with BCrypt
