/**
 * 
 */
package logic.command.commandList;

import java.util.ArrayList;

import logic.InvalidCommandException;
import logic.utility.Task;

/**
 * @author TienLong This class makes use of the Command interface to implement
 *         execute function for InvalidTask
 */
public class ViewCommand extends Command {

    ArrayList<Task> previousView;

    public void execute() throws InvalidCommandException {
        System.out.println("view");

        previousView = dataHandler.getObservableList().getList();
        dataHandler.view(task);
        undoHandler.addUndo(this);
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
