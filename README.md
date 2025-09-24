# 📘 Campus Course & Records Manager (CCRM)

A Java SE console application for managing students, courses, grades, transcripts, and academic records. Built using object-oriented principles and modern Java features.

---

## 🚀 Project Overview & How to Run

This project simulates a university record system using Java SE. It includes features like student enrollment, course management, grade recording, and transcript generation.

### Requirements

- **JDK Version**: Java SE 17 or higher
- **IDE**: Eclipse (recommended) or any Java-compatible IDE

### How to Run

1. Open the project in Eclipse.
2. Locate `Main.java` in the `edu.ccrm.cli` package.
3. Run the application using:
   ```bash
   java edu.ccrm.cli.Main
   ```

---

## 🕰️ Evolution of Java (Highlights)

- 1995: Java 1.0 released by Sun Microsystems
- 2004: Java 5 introduced generics and annotations
- 2014: Java 8 added lambdas and Streams API
- 2017: Java 9 introduced the module system
- 2021: Java 17 became the latest LTS release

---

## 📦 Java Editions Comparison

| Edition   | Description         | Use Case                        |
|-----------|---------------------|---------------------------------|
| Java SE   | Standard Edition    | Desktop apps, CLI tools         |
| Java EE   | Enterprise Edition  | Web apps, distributed systems   |
| Java ME   | Micro Edition       | Embedded systems, mobile apps   |

---

## ⚙️ JDK vs JRE vs JVM

- **JDK (Java Development Kit)**: Includes compiler (`javac`), debugger, and JRE. Used for developing Java applications.
- **JRE (Java Runtime Environment)**: Contains JVM and libraries to run Java programs.
- **JVM (Java Virtual Machine)**: Executes Java bytecode on any platform.

---

## 🪟 Windows Installation Steps

1. Download JDK from [Oracle](https://www.oracle.com/java/technologies/javase-downloads.html)
2. Install and verify installation:
   ```bash
   java -version
   javac -version
   ```
3. Set environment variables:
   - `JAVA_HOME` → path to JDK
   - Add `%JAVA_HOME%\bin` to `PATH`
4. Download and install Eclipse IDE from [eclipse.org](https://www.eclipse.org)

---

## 🧑‍💻 Eclipse Setup Steps

1. Open Eclipse → File → New → Java Project
2. Import source files into the `src/` folder
3. Right-click `Main.java` → Run As → Java Application

---

## 🧾 Mapping: Syllabus Topic → Code Reference

| Syllabus Topic         | File/Class/Method                     |
|------------------------|---------------------------------------|
| Encapsulation          | `Student.java`, `Course.java`         |
| Inheritance            | `Person.java` → `Student`, `Instructor` |
| Polymorphism           | `TranscriptService.java`              |
| Streams API            | `ReportService.java`                  |
| Singleton              | `AppConfig.java`                      |
| Builder Pattern        | `Course.Builder.java`                 |
| Custom Exceptions      | `DuplicateEnrollmentException.java`   |
| Recursion              | `BackupUtil.java`                     |

---

## ✅ Enabling Assertions

Assertions are used to validate assumptions during runtime.

### How to Enable

```bash
java -ea edu.ccrm.cli.Main
```

### Use Cases

- Validating student ID format
- Checking credit limits
- Ensuring non-null values

---

## 📂 Sample Data Files

Place these files in the `data/` folder:

- `students.csv`
- `courses.csv`
- `enrollments.csv`

These files are used for import/export operations within the application.

---

## 🙌 Credits

- Java Documentation: [docs.oracle.com](https://docs.oracle.com)
- Eclipse IDE: [eclipse.org](https://www.eclipse.org)

---