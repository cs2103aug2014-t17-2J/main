/**
 * 
 */
package logic.command.commandList;

import java.util.ArrayList;
import java.util.EnumSet;

import logic.exception.InvalidCommandException;
import logic.parser.ParserFlags;
import logic.utility.Task;

/**
 * @author TienLong This class makes use of the Command interface to implement
 *         execute function for ClearTask
 */
public class ClearCommand extends Command {
    ArrayList<Task> storedList = new ArrayList<Task>();

    /**
     * @param task
     * @param processor
     */

    public void execute() throws InvalidCommandException {
        System.out.println("clear");

        storedList = dataHandler.getDisplayedTasks(task.getStartDate(),
                task.getEndDate());

        if (dataHandler.clearTask(task.getStartDate(), task.getEndDate())) {
            undoHandler.addUndo(this);
        } else {
            throw new InvalidCommandException("Clear failed");
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.Command#undo()
     */
    @Override
    public void undo() {
        if (!storedList.isEmpty()) {
            for (Task task : storedList) {
                dataHandler.addTask(task);
            }

        }
    }

    /* (non-Javadoc)
     * @see logic.command.commandList.Command#validate(java.util.EnumSet)
     */
    @Override
    public boolean validate(EnumSet<ParserFlags> parseFlags) {
        return isCommandValid(parseFlags);
    }
    
    /**
     * <p>
     * Determine whether the command is valid
     * <p>
     * 
     * @param parseFlags the set of ParserFlag to be tested
     * @return if it contains more than MIN_VALID_FLAGS flags
     */
    public static boolean isCommandValid(EnumSet<ParserFlags> parseFlags) {
        final int MIN_VALID_FLAGS = 1;
        return parseFlags.size() > MIN_VALID_FLAGS;
    }
    
    /* (non-Javadoc)
     * @see logic.command.commandList.Command#toString()
     */
    @Override
    public String toString() {
            return "Clear";
    }

}
