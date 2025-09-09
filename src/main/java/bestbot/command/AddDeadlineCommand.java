package bestbot.command;

import bestbot.exception.BestbotException;
import bestbot.Ui;
import bestbot.Storage;
import bestbot.task.Task;
import bestbot.task.TaskList;
import bestbot.task.Deadline;

/**
 * Command to add a new Deadline task.
 */
public class AddDeadlineCommand extends Command {
    private final String description;
    private final String by;

    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BestbotException {
        if (description.isBlank() || by.isBlank()) {
            throw new BestbotException("Deadline needs a description and /by <date>.");
        }

        Task deadline = new Deadline(description, by);
        tasks.add(deadline);

        ui.showTaskAdded(deadline, tasks.size());
        storage.save(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
