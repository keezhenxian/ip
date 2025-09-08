package bestbot;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles saving/loading tasks to/from disk.
 * Level-7 version: Deadline/Event times are stored as Strings.
 */
public final class Storage {
    private static final Path DATA_DIR  = Paths.get("data");
    private static final Path DATA_FILE = DATA_DIR.resolve("bestbot.txt");

    private Storage() { }

    /** Loads tasks from data file. If missing/corrupted, returns an empty list. */
    public static List<Task> load() {
        List<Task> list = new ArrayList<>();
        if (!Files.exists(DATA_FILE)) {
            return list; // first run: no file yet
        }
        try {
            for (String line : Files.readAllLines(DATA_FILE)) {
                if (line == null || line.isBlank()) {
                    continue;
                }
                // Format: T | 1 | description
                //         D | 0 | description | by
                //         E | 1 | description | from | to
                String[] t = line.split("\\s*\\|\\s*");
                String type = t[0];
                boolean done = "1".equals(t[1]);

                switch (type) {
                    case "T": {
                        Task todo = new Todo(t[2]);
                        if (done) todo.markAsDone();
                        list.add(todo);
                        break;
                    }
                    case "D": {
                        Task dl = new Deadline(t[2], t[3]);
                        if (done) dl.markAsDone();
                        list.add(dl);
                        break;
                    }
                    case "E": {
                        Task ev = new Event(t[2], t[3], t[4]);
                        if (done) ev.markAsDone();
                        list.add(ev);
                        break;
                    }
                    default:
                        // Ignore unknown lines (treat as corrupted)
                }
            }
        } catch (IOException | ArrayIndexOutOfBoundsException ex) {
            System.err.println("Warning: data file is missing or corrupted; starting with an empty list.");
            list.clear();
        }
        return list;
    }

    /** Saves the whole task list to disk, creating folders/files as needed. */
    public static void save(List<Task> tasks) {
        try {
            if (!Files.exists(DATA_DIR)) {
                Files.createDirectories(DATA_DIR);
            }
            try (BufferedWriter bw = Files.newBufferedWriter(DATA_FILE)) {
                for (Task task : tasks) {
                    bw.write(encode(task));
                    bw.newLine();
                }
            }
        } catch (IOException ex) {
            System.err.println("Warning: failed to save tasks: " + ex.getMessage());
        }
    }

    private static String encode(Task task) {
        String done = task.isDone ? "1" : "0"; // same package → allowed
        if (task instanceof Todo) {
            return String.join(" | ", "T", done, task.description);
        } else if (task instanceof Deadline) {
            Deadline d = (Deadline) task;
            return String.join(" | ", "D", done, d.description, d.getBy());
        } else if (task instanceof Event) {
            Event e = (Event) task;
            return String.join(" | ", "E", done, e.description, e.getFrom(), e.getTo());
        } else {
            return String.join(" | ", "?", done, task.description);
        }
    }
}

