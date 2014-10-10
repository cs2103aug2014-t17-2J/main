/**
 * 
 */
package logic.commandList;

import definedEnumeration.TaskFeedBack;

/**
 * @author TienLong This class makes use of the Command interface to implement
 *         execute function for ExitTask
 */
public class ExitCommand extends Command {

    public TaskFeedBack execute() {
        System.out.println("exit");
        return TaskFeedBack.FEEDBACK_EXIT;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.Command#undo() No undo for exit.
     */
    @Override
    public void undo() {

    }
}
