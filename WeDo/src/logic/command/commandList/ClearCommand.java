/**
 * 
 */
package logic.command.commandList;

import java.util.ArrayList;

import logic.utility.Task;
import definedEnumeration.TaskFeedBack;

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

    public TaskFeedBack execute() {
        System.out.println("clear");

        storedList = dataHandler.getDisplayedTasks(task.getStarDate(),
                task.getEndDate());

        if (dataHandler.clearTask(task.getStarDate(), task.getEndDate())) {
            undoHandler.add(this);
            return TaskFeedBack.FEEDBACK_VALID;
        } else {
            return TaskFeedBack.FEEDBACK_INVALID;
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
