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

    private static final int NOT_SET = -1;
    private int index = NOT_SET;


    public TaskFeedBack execute() {

        System.out.println("delete");

        final int ARRAY_OFFSET = -1;

        if (index == NOT_SET) {
            int lineToDelete = StringHandler.parseStringToInteger(task
                    .getDescription()) + ARRAY_OFFSET;
            if (dataHandler.indexValid(lineToDelete)) {
                task = dataHandler.getTask(lineToDelete);
        }
        

            dataHandler.removeTask(task);
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
