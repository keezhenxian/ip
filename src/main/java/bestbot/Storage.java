package bestbot;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles saving and loading tasks from a file on disk.
 * Each task is saved in a machine-friendly format that can be parsed later.
 */
public class Storage {
    /** Path to the save file. */
    private final Path filePath;

    /**
     * Creates a Storage object for the given file path.
     *
     * @param filePath Path to the save file.
     */
    public Storage(String filePath) {
        this.filePath = Path.of(filePath);
    }

    /**
     * Loads all tasks from the save file.
     *
     * @return List of tasks.
     */
    public List<Task> load() {
        List<Task> tasks = new ArrayList<>();
        if (!Files.exists(filePath)) {
            return tasks;
        }

        try {
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                String[] parts = line.split(" \\| ");
                switch (parts[0]) {
                    case "T":
                        tasks.add(Todo.fromSaveFormat(parts));
                        break;
                    case "D":
                        tasks.add(Deadline.fromSaveFormat(parts));
                        break;
                    case "E":
                        tasks.add(Event.fromSaveFormat(parts));
                        break;
                    default:
                        System.out.println("Unknown task type: " + parts[0]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Saves all tasks to the save file in machine-friendly format.
     *
     * @param tasks List of tasks to save.
     */
    public void save(List<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()))) {
            for (Task task : tasks) {
                writer.write(task.toSaveFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
