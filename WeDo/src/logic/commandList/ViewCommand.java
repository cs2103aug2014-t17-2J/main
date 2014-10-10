/**
 * 
 */
package logic.commandList;

import java.util.ArrayList;

import logic.Task;
import definedEnumeration.TaskFeedBack;

/**
 * @author TienLong This class makes use of the Command interface to implement
 *         execute function for InvalidTask
 */
public class ViewCommand extends Command {

    ArrayList<Task> previousView;

    public TaskFeedBack execute() {
        System.out.println("view");

        previousView = dataHandler.getObservableList().getList();
        dataHandler.view(task);
        undoHandler.add(this);

        return TaskFeedBack.FEEDBACK_VALID;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.Command#undo()
     */
    @Override
    public void undo() {
        dataHandler.setDisplayedTasks(previousView);
    }

}
