package edu.ccrm.cli;

import edu.ccrm.domain.*;
import edu.ccrm.service.*;
import edu.ccrm.io.*;
import edu.ccrm.util.BackupUtil;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentService studentService = new InMemoryStudentService();
        CourseService courseService = new InMemoryCourseService();
        EnrollmentService enrollmentService = new InMemoryEnrollmentService();

        Scanner sc = new Scanner(System.in);
        boolean run = true;

        while (run) {
            System.out.println("\n===== CCRM Menu =====");
            System.out.println("1. Add Student");
            System.out.println("2. List Students");
            System.out.println("3. Add Course");
            System.out.println("4. List Courses");
            System.out.println("5. Enroll Student");
            System.out.println("6. List Enrollments");
            System.out.println("7. Record Grade");
            System.out.println("8. Export Students & Courses");
            System.out.println("9. Import Students & Courses");
            System.out.println("10. Backup Data");
            System.out.println("11. Show Backup Folder Size");
            System.out.println("12. Print Transcript");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> {
                    System.out.print("Reg No: ");
                    String reg = sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    Student s = new Student(reg, name, email);
                    studentService.addStudent(s);
                    System.out.println("Student added!");
                }
                case 2 -> {
                    for (Student s : studentService.listStudents()) {
                        System.out.println(s.profile());
                    }
                }
                case 3 -> {
                    System.out.print("Course Code: ");
                    String code = sc.nextLine();
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Credits: ");
                    int credits = Integer.parseInt(sc.nextLine());
                    System.out.print("Department: ");
                    String dept = sc.nextLine();
                    System.out.print("Instructor Name: ");
                    String instName = sc.nextLine();
                    System.out.print("Instructor Email: ");
                    String instEmail = sc.nextLine();
                    Instructor i = new Instructor(instName, instEmail, dept);

                    Course c = new Course.Builder()
                                    .code(code)
                                    .title(title)
                                    .credits(credits)
                                    .department(dept)
                                    .instructor(i)
                                    .semester(Semester.FALL) // for now fixed
                                    .build();
                    courseService.addCourse(c);
                    System.out.println("Course added!");
                }
                case 4 -> {
                    for (Course c : courseService.listCourses()) {
                        System.out.println(c);
                    }
                }
                case 5 -> {
                    System.out.print("Enter Student Reg No: ");
                    String reg = sc.nextLine();
                    Student s = studentService.findByRegNo(reg);
                    if (s == null) {
                        System.out.println("Student not found!");
                        break;
                    }

                    System.out.print("Enter Course Code: ");
                    String code = sc.nextLine();
                    Course c = courseService.findByCode(code);
                    if (c == null) {
                        System.out.println("Course not found!");
                        break;
                    }

                    try {
                        enrollmentService.enroll(s, c);
                        System.out.println("Enrollment done!");
                    } catch (DuplicateEnrollmentException | MaxCreditLimitExceededException ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }
                }
                case 6 -> {
                    for (Enrollment e : enrollmentService.listEnrollments()) {
                        System.out.println(e);
                    }
                }
                case 7 -> {
                    System.out.print("Enter Student Reg No: ");
                    String reg = sc.nextLine();
                    Student s = studentService.findByRegNo(reg);
                    if (s == null) {
                        System.out.println("Student not found!");
                        break;
                    }

                    System.out.print("Enter Course Code: ");
                    String code = sc.nextLine();
                    Course c = courseService.findByCode(code);
                    if (c == null) {
                        System.out.println("Course not found!");
                        break;
                    }

                    System.out.print("Enter Grade (S/A/B/C/D/E/F): ");
                    String gradeInput = sc.nextLine().toUpperCase();
                    try {
                        Grade g = Grade.valueOf(gradeInput);
                        enrollmentService.recordGrade(s, c, g);
                        System.out.println("Grade recorded!");
                    } catch (IllegalArgumentException ex) {
                        System.out.println("Invalid grade!");
                    }
                }
                case 8 -> {
                    try {
                        StudentCSV.export(studentService.listStudents());
                        CourseCSV.export(courseService.listCourses());
                        System.out.println("Exported to students.csv and courses.csv");
                    } catch (Exception e) {
                        System.out.println("Error exporting: " + e.getMessage());
                    }
                }
                case 9 -> {
                    try {
                        var students = StudentCSV.importFile();
                        for (Student s : students) {
                            studentService.addStudent(s);
                        }

                        var courses = CourseCSV.importFile();
                        for (Course c : courses) {
                            courseService.addCourse(c);
                        }

                        System.out.println("Imported students and courses!");
                    } catch (Exception e) {
                        System.out.println("Error importing: " + e.getMessage());
                    }
                }
                case 10 -> {
                    try {
                        java.nio.file.Path studentsFile = java.nio.file.Paths.get("students.csv");
                        java.nio.file.Path coursesFile = java.nio.file.Paths.get("courses.csv");
                        BackupUtil.backupData(studentsFile, coursesFile);
                    } catch (Exception e) {
                        System.out.println("Backup failed: " + e.getMessage());
                    }
                }
                case 11 -> {
                    try {
                        long size = BackupUtil.folderSize(java.nio.file.Paths.get("backups"));
                        System.out.println("Backup folder size: " + size + " bytes");
                    } catch (Exception e) {
                        System.out.println("Error calculating backup size: " + e.getMessage());
                    }
                }
                case 12 -> {
                    System.out.print("Enter Student Reg No: ");
                    String reg = sc.nextLine();
                    Student s = studentService.findByRegNo(reg);
                    if (s == null) {
                        System.out.println("Student not found!");
                        break;
                    }

                    TranscriptPrinter.printTranscript(s, enrollmentService.listEnrollments());
                }
                case 0 -> {
                    run = false;
                    System.out.println("Goodbye!");
                }
                default -> System.out.println("Invalid choice");
            }
        }
        sc.close();
    }
}
