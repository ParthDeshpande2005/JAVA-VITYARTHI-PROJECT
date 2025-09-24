package edu.ccrm.domain;

import java.util.UUID;

public abstract class Person {
    protected final UUID id;
    protected String fullName;
    protected String email;

    public Person(UUID id, String fullName, String email) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }

    public UUID getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }

    public abstract String profile();

    @Override
    public String toString() {
        return fullName + " <" + email + ">";
    }
}
