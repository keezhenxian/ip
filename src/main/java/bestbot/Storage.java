package bestbot;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import bestbot.task.Task;
import bestbot.exception.BestbotException;


/**
 * Handles saving and loading tasks from a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object bound to the specified file.
     *
     * @param filePath The path to the storage file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return List of tasks.
     * @throws BestbotException If file cannot be read.
     */
    public List<Task> load() throws BestbotException {
        List<Task> tasks = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                tasks.add(Task.fromSaveFormat(line));
            }
        } catch (IOException e) {
            throw new BestbotException("Error loading tasks from file.");
        }
        return tasks;
    }

    /**
     * Saves tasks to the storage file.
     *
     * @param tasks List of tasks to save.
     */
    public void save(List<Task> tasks) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                bw.write(task.toSaveFormat());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
