/**
 * 
 */
package logic.commandList;

import definedEnumeration.TaskFeedBack;

/**
 * @author TienLong This class makes use of the Command interface to implement
 *         execute function for InvalidTask
 */
public class ViewCommand extends Command {

    public TaskFeedBack execute() {
        System.out.println("view");

        dataHandler.getObservableList();
        dataHandler.view(task);

        return TaskFeedBack.FEEDBACK_VALID;
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
