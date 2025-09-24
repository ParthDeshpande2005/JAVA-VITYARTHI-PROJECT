package edu.ccrm.io;

import edu.ccrm.domain.Student;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class StudentCSV {
    private static final Path FILE = Paths.get("students.csv");

    // Export students to CSV
    public static void export(List<Student> students) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("regNo,fullName,email,status"); // header
        for (Student s : students) {
            lines.add(s.getRegNo() + "," +
                      s.getFullName() + "," +
                      s.getEmail() + "," +
                      (s.isActive() ? "ACTIVE" : "INACTIVE"));
        }
        Files.write(FILE, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    // Import students from CSV
    public static List<Student> importFile() throws IOException {
        if (!Files.exists(FILE)) return new ArrayList<>();

        return Files.lines(FILE)
                .skip(1) // skip header
                .map(line -> line.split(","))
                .map(fields -> new Student(fields[0], fields[1], fields[2]))
                .collect(Collectors.toList());
    }
}
