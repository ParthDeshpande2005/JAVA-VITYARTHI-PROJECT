package edu.ccrm.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Student extends Person {
    private final String regNo;
    private boolean active;
    private final LocalDateTime createdAt;

    public Student(String regNo, String fullName, String email) {
        super(UUID.randomUUID(), fullName, email);
        this.regNo = regNo;
        this.active = true;
        this.createdAt = LocalDateTime.now();
    }

    public String getRegNo() { return regNo; }
    public boolean isActive() { return active; }
    public void deactivate() { this.active = false; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    @Override
    public String profile() {
        return "Student " + fullName + " (" + regNo + ")";
    }
}
