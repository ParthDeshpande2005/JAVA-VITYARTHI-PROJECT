package edu.ccrm.service;

import edu.ccrm.domain.*;
import java.util.List;

public interface EnrollmentService {
    void enroll(Student s, Course c) throws DuplicateEnrollmentException, MaxCreditLimitExceededException;
    void unenroll(Student s, Course c);
    void recordGrade(Student s, Course c, Grade g);
    List<Enrollment> listEnrollments();
}
