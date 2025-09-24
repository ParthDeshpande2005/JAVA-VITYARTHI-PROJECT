package edu.ccrm.domain;

public final class Course {
    private final String code;
    private final String title;
    private final int credits;
    private final String department;
    private final Instructor instructor;
    private final Semester semester;

    private Course(Builder b) {
        this.code = b.code;
        this.title = b.title;
        this.credits = b.credits;
        this.department = b.department;
        this.instructor = b.instructor;
        this.semester = b.semester;
    }

    public String getCode() { return code; }
    public String getTitle() { return title; }
    public int getCredits() { return credits; }
    public String getDepartment() { return department; }
    public Instructor getInstructor() { return instructor; }
    public Semester getSemester() { return semester; }

    @Override
    public String toString() {
        return code + " - " + title + " (" + credits + " credits, " + semester + ")";
    }

    public static class Builder {
        private String code;
        private String title;
        private int credits;
        private String department;
        private Instructor instructor;
        private Semester semester;

        public Builder code(String c) { this.code = c; return this; }
        public Builder title(String t) { this.title = t; return this; }
        public Builder credits(int c) { this.credits = c; return this; }
        public Builder department(String d) { this.department = d; return this; }
        public Builder instructor(Instructor i) { this.instructor = i; return this; }
        public Builder semester(Semester s) { this.semester = s; return this; }
        public Course build() { return new Course(this); }
    }
}
