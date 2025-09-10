package bestbot.command;

import bestbot.Ui;
import bestbot.Storage;
import bestbot.task.Task;
import bestbot.task.TaskList;
import bestbot.exception.BestbotException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Command to find tasks by keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BestbotException {
        if (keyword.isBlank()) {
            throw new BestbotException("The keyword for find cannot be empty.");
        }

        List<Task> matchingTasks = tasks.getTasks().stream()
                .filter(task -> task.toString().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());

        ui.showFoundTasks(matchingTasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
