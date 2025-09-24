package edu.ccrm.domain;

import java.util.UUID;

public class Instructor extends Person {
    private final String department;

    public Instructor(String fullName, String email, String department) {
        super(UUID.randomUUID(), fullName, email);
        this.department = department;
    }

    public String getDepartment() { return department; }

    @Override
    public String profile() {
        return "Instructor " + fullName + " (" + department + ")";
    }
}
