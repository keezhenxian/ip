package bestbot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Simple file storage using a line-based format compatible with Task.encode/decode.
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /** Loads tasks from file; returns empty list if file doesn't exist. */
    public List<Task> load() {
        List<Task> tasks = new ArrayList<>();
        File f = new File(filePath);
        if (!f.exists()) {
            return tasks;
        }
        try (Scanner sc = new Scanner(f)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Task t = Task.decode(line);
                if (t != null) {
                    tasks.add(t);
                }
            }
        } catch (IOException ioe) {
            System.out.println("Error loading tasks: " + ioe.getMessage());
        }
        return tasks;
    }

    /** Saves tasks to file (overwrites file). Ensures parent directory exists. */
    public void save(List<Task> tasks) {
        try {
            File f = new File(filePath);
            File parent = f.getParentFile();
            if (parent != null) {
                parent.mkdirs();
            }
            try (FileWriter fw = new FileWriter(f, false)) {
                for (Task t : tasks) {
                    fw.write(t.encode());
                    fw.write(System.lineSeparator());
                }
            }
        } catch (IOException ioe) {
            System.out.println("Error saving tasks: " + ioe.getMessage());
        }
    }
}
