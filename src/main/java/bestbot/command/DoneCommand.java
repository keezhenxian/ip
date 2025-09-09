package bestbot.command;

import bestbot.Ui;
import bestbot.Storage;
import bestbot.task.Task;
import bestbot.task.TaskList;
import bestbot.exception.BestbotException;

/**
 * Command to mark a task as done.
 */
public class DoneCommand extends Command {
    private final int index;

    /**
     * Constructs a DoneCommand with the task's 1-based index.
     *
     * @param index The index of the task to mark as done.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BestbotException {
        if (index < 1 || index > tasks.size()) {
            throw new BestbotException("Task number is invalid.");
        }

        Task task = tasks.getTask(index - 1);
        task.markAsDone();
        ui.showTaskDone(task);
        storage.save(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
