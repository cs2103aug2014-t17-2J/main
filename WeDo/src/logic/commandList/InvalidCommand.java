/**
 * 
 */
package logic.commandList;

import definedEnumeration.TaskFeedBack;


/**
 * @author TienLong This class makes use of the Command interface to implement
 *         execute function for InvalidTask
 */
public class InvalidCommand extends Command {

    public TaskFeedBack execute() {
        System.out.println("Invalid");

        return TaskFeedBack.FEEDBACK_INVALID;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.Command#undo()
     */
    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }
}
