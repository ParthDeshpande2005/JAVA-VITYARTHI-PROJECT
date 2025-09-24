package edu.ccrm.service;

import edu.ccrm.domain.*;

import java.util.List;

public class TranscriptPrinter {

    public static void printTranscript(Student s, List<Enrollment> enrollments) {
        System.out.println("\n===== Transcript for " + s.getFullName() + " (" + s.getRegNo() + ") =====");

        for (Enrollment e : enrollments) {
            if (e.getStudent().equals(s)) {
                String gradeText = (e.getGrade() != null) ? e.getGrade().toString() : "Not graded";
                System.out.println(e.getCourse().getCode() + " - " +
                                   e.getCourse().getTitle() + " (" +
                                   e.getCourse().getCredits() + " credits) -> " + gradeText);
            }
        }

        double gpa = GPAService.computeGPA(
            enrollments.stream()
                       .filter(e -> e.getStudent().equals(s))
                       .toList()
        );

        System.out.printf("GPA: %.2f%n", gpa);
        System.out.println("=============================================");
    }
}
