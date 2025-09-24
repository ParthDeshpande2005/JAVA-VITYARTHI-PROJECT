package edu.ccrm.service;

import edu.ccrm.domain.*;
import java.util.*;

public class InMemoryEnrollmentService implements EnrollmentService {
    private final List<Enrollment> enrollments = new ArrayList<>();
    private static final int MAX_CREDITS = 24; // max credits per student

    @Override
    public void enroll(Student s, Course c)
            throws DuplicateEnrollmentException, MaxCreditLimitExceededException {

        // 1. Prevent duplicate enrollment
        for (Enrollment e : enrollments) {
            if (e.getStudent().equals(s) && e.getCourse().equals(c)) {
                throw new DuplicateEnrollmentException(
                        "Student already enrolled in " + c.getCode());
            }
        }

        // 2. Check credit limit
        int currentCredits = enrollments.stream()
                .filter(e -> e.getStudent().equals(s))
                .mapToInt(e -> e.getCourse().getCredits())
                .sum();

        if (currentCredits + c.getCredits() > MAX_CREDITS) {
            throw new MaxCreditLimitExceededException(
                    "Credit limit exceeded (max " + MAX_CREDITS + ")");
        }

        // 3. Add enrollment
        enrollments.add(new Enrollment(s, c));
    }

    @Override
    public void unenroll(Student s, Course c) {
        enrollments.removeIf(e -> e.getStudent().equals(s) && e.getCourse().equals(c));
    }

    @Override
    public void recordGrade(Student s, Course c, Grade g) {
        for (Enrollment e : enrollments) {
            if (e.getStudent().equals(s) && e.getCourse().equals(c)) {
                e.setGrade(g);
                return;
            }
        }
    }

    @Override
    public List<Enrollment> listEnrollments() {
        return new ArrayList<>(enrollments);
    }
}
