package edu.ccrm.service;

import edu.ccrm.domain.Course;
import java.util.*;

public class InMemoryCourseService implements CourseService {
    private final Map<String, Course> courses = new HashMap<>();

    @Override
    public void addCourse(Course c) {
        courses.put(c.getCode(), c);
    }

    @Override
    public List<Course> listCourses() {
        return new ArrayList<>(courses.values());
    }

    @Override
    public Course findByCode(String code) {
        return courses.get(code);
    }
}
