package bestbot.command;

import bestbot.exception.BestbotException;
import bestbot.Ui;
import bestbot.Storage;
import bestbot.task.Task;
import bestbot.task.TaskList;
import bestbot.task.Event;

/**
 * Command to add a new Event task.
 */
public class AddEventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BestbotException {
        if (description.isBlank() || from.isBlank() || to.isBlank()) {
            throw new BestbotException("Event needs a description, /from <time>, and /to <time>.");
        }

        Task event = new Event(description, from, to);
        tasks.add(event);

        ui.showTaskAdded(event, tasks.size());
        storage.save(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

