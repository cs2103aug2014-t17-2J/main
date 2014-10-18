/**
 * 
 */
package logic.command.commandList;

import java.util.ArrayList;

import logic.InvalidCommandException;
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

}
