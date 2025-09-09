package bestbot;

import bestbot.command.Command;
import bestbot.task.TaskList;
import bestbot.exception.BestbotException;

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
}
