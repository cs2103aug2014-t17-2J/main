/**
 * 
 */
package logic.commandList;

import logic.Task;
import logic.utility.StringHandler;
import definedEnumeration.TaskFeedBack;



/**
 * @author TienLong This class makes use of the Command interface to implement
 *         execute function for DeleteTask
 */
public class DeleteCommand extends Command {

    final int INVALID_INDEX = -1;

    public TaskFeedBack execute() {

        System.out.println("delete");

        final int ARRAY_OFFSET = -1;
        int lineToDelete = StringHandler.parseStringToInteger(task
                .getDescription()) + ARRAY_OFFSET;
        if (dataHandler.canRemove(lineToDelete)) {
            Task deletedTask = dataHandler.getTask(lineToDelete);
            dataHandler.removeTask(lineToDelete);
            this.task = deletedTask;
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
        dataHandler.addTask(task);
    }

}
