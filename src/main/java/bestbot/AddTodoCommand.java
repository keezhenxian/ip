package bestbot;

/**
 * Command to add a new Todo task to the task list.
 */
public class AddTodoCommand extends Command {
    private final String description;

    /**
     * Creates an AddTodoCommand.
     *
     * @param description Description of the todo task.
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BestbotException {
        if (description.isBlank()) {
            throw new BestbotException("The description of a todo cannot be empty.");
        }

        Task todo = new Todo(description);
        tasks.add(todo);

        ui.showTaskAdded(todo, tasks.size());
        storage.save(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
