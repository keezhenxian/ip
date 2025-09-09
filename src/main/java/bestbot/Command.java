package bestbot;

/**
 * Abstract representation of a user command.
 * Each concrete command must implement execute() and isExit().
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks   TaskList to operate on.
     * @param ui      UI for user interaction.
     * @param storage Storage handler for persistence.
     * @throws BestbotException If execution fails due to invalid input or logic errors.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BestbotException;

    /**
     * Indicates whether this command should exit the program.
     */
    public abstract boolean isExit();
}
