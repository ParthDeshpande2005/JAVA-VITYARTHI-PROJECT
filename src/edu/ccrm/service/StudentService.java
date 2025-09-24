package edu.ccrm.service;

import edu.ccrm.domain.Student;
import java.util.List;

public interface StudentService {
    void addStudent(Student s);
    List<Student> listStudents();
    Student findByRegNo(String regNo);
    void deactivateStudent(String regNo);
}
