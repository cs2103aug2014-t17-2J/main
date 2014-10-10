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

    private static final int INVALID_INDEX = -1;
    private Task taskBeforeDelete;

    public TaskFeedBack execute() {

        System.out.println("delete");

        final int ARRAY_OFFSET = -1;
        int lineToDelete = StringHandler.parseStringToInteger(task
                .getDescription()) + ARRAY_OFFSET;
        if (dataHandler.indexValid(lineToDelete)) {
            taskBeforeDelete = dataHandler.getTask(lineToDelete);
            dataHandler.removeTask(lineToDelete);
            undoHandler.add(this);
            return TaskFeedBack.FEEDBACK_VALID;

        } else {
            return TaskFeedBack.FEEDBACK_INVALID;
        }
    }

    private void setTaskForUndo(Task taskBeforeDelete) {
        this.task = taskBeforeDelete;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.Command#undo()
     */
    @Override
    public void undo() {
        dataHandler.addTask(taskBeforeDelete);
    }

}
