package bestbot.command;

import bestbot.Ui;
import bestbot.Storage;
import bestbot.task.TaskList;

/**
 * Command to list all tasks.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTasks(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

