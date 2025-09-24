package edu.ccrm.domain;

import java.time.LocalDateTime;

public class Enrollment {
    private final Student student;
    private final Course course;
    private Grade grade;
    private final LocalDateTime enrolledAt;

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.enrolledAt = LocalDateTime.now();
    }

    public Student getStudent() { return student; }
    public Course getCourse() { return course; }
    public Grade getGrade() { return grade; }
    public void setGrade(Grade g) { this.grade = g; }
    public LocalDateTime getEnrolledAt() { return enrolledAt; }

    @Override
    public String toString() {
        return student.getFullName() + " -> " + course.getCode() + " [" +
               (grade != null ? grade : "Not graded") + "]";
    }
}
