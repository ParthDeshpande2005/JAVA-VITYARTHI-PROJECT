package edu.ccrm.io;

import edu.ccrm.domain.*;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class CourseCSV {
    private static final Path FILE = Paths.get("courses.csv");

    // Export courses to CSV
    public static void export(List<Course> courses) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("code,title,credits,department,instructorName,semester"); // header
        for (Course c : courses) {
            lines.add(c.getCode() + "," +
                      c.getTitle() + "," +
                      c.getCredits() + "," +
                      c.getDepartment() + "," +
                      c.getInstructor().getFullName() + "," +
                      c.getSemester());
        }
        Files.write(FILE, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    // Import courses from CSV
    public static List<Course> importFile() throws IOException {
        if (!Files.exists(FILE)) return new ArrayList<>();

        return Files.lines(FILE)
                .skip(1)
                .map(line -> line.split(","))
                .map(fields -> {
                    Instructor i = new Instructor(fields[4], fields[4].toLowerCase() + "@uni.edu", fields[3]);
                    return new Course.Builder()
                            .code(fields[0])
                            .title(fields[1])
                            .credits(Integer.parseInt(fields[2]))
                            .department(fields[3])
                            .instructor(i)
                            .semester(Semester.valueOf(fields[5]))
                            .build();
                })
                .collect(Collectors.toList());
    }
}
