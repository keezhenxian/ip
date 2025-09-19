package bestbot;

import bestbot.command.Command;
import bestbot.task.TaskList;
import bestbot.exception.BestbotException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Entry point of the Bestbot application.
 * Handles setup, main loop, and delegates commands.
 */
public class Bestbot {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Creates a Bestbot instance with storage file.
     *
     * @param filePath Path to storage file.
     */
    public Bestbot(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);

        TaskList loadedTasks;
        try {
            loadedTasks = new TaskList(storage.load());
        } catch (BestbotException e) {
            ui.showLoadingError();
            loadedTasks = new TaskList();
        }

        this.tasks = loadedTasks;
    }

    /**
     * Starts the interactive loop to process user commands.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();

            } catch (BestbotException e) {
                ui.showError(e.getMessage()); // centralized error handling

            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Program entry point.
     */
    public static void main(String[] args) {
        String filePath = args.length > 0 ? args[0] : "bestbot.txt";
        new Bestbot(filePath).run();
    }

    /**
     * Generates a response string for a given input, without printing to console.
     *
     * @param input user input command
     * @return Bestbot's response as a string
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            Ui tempUi = new Ui(ps);                 // Use temporary Ui that writes to ByteArrayOutputStream
            c.execute(tasks, tempUi, storage);
            return baos.toString().trim();

        } catch (BestbotException e) {
            return e.getMessage();
        }
    }
}
