/**
 * 
 */
package logic.command.commandList;

import definedEnumeration.TaskFeedBack;

/**
 * @author TienLong This class makes use of the Command interface to implement
 *         execute function for AddTask
 */
public class AddCommand extends Command {

    /**
     * @param task
     * @param processor
     */

    public TaskFeedBack execute() {
        System.out.println("adding");
        if (dataHandler.addTask(task)) {
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
        dataHandler.removeTask(task);

    }
}
