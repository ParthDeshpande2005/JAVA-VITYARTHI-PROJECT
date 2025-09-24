package edu.ccrm.util;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BackupUtil {

    private static final Path BACKUP_ROOT = Paths.get("backups");

    // Create a backup folder with timestamp and copy CSV files
    public static void backupData(Path... files) throws IOException {
        // timestamp format: yyyyMMdd_HHmmss
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        Path backupFolder = BACKUP_ROOT.resolve(timestamp);

        Files.createDirectories(backupFolder);

        for (Path file : files) {
            if (Files.exists(file)) {
                Path target = backupFolder.resolve(file.getFileName());
                Files.copy(file, target, StandardCopyOption.REPLACE_EXISTING);
            }
        }

        System.out.println("Backup created at: " + backupFolder.toAbsolutePath());
    }

    // Recursively calculate folder size
    public static long folderSize(Path dir) throws IOException {
        if (!Files.exists(dir)) return 0;
        if (Files.isRegularFile(dir)) return Files.size(dir);

        long total = 0;
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path p : stream) {
                total += folderSize(p); // recursion
            }
        }
        return total;
    }
}
