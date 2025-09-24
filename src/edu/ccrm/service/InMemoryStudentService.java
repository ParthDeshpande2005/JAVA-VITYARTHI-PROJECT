package edu.ccrm.service;

import edu.ccrm.domain.Student;
import java.util.*;

public class InMemoryStudentService implements StudentService {
    private final Map<String, Student> students = new HashMap<>();

    @Override
    public void addStudent(Student s) {
        students.put(s.getRegNo(), s);
    }

    @Override
    public List<Student> listStudents() {
        return new ArrayList<>(students.values());
    }

    @Override
    public Student findByRegNo(String regNo) {
        return students.get(regNo);
    }

    @Override
    public void deactivateStudent(String regNo) {
        Student s = students.get(regNo);
        if (s != null) {
            s.deactivate();
        }
    }
}
