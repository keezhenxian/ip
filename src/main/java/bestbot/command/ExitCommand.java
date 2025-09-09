package bestbot.command;

import bestbot.Ui;
import bestbot.Storage;
import bestbot.task.TaskList;

/**
 * Command to exit the program.
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
