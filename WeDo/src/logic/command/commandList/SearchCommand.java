/**
 * 
 */
package logic.command.commandList;

import java.util.ArrayList;

import logic.SearchEngine;
import logic.utility.Task;
import definedEnumeration.TaskFeedBack;



/**
 * @author TienLong This class makes use of the Command interface to implement
 *         execute function for searchTask
 */
public class SearchCommand extends Command {
    private ArrayList<Task> displayedTask;

    public TaskFeedBack execute() {

        System.out.println("searching");

        SearchEngine searchEngine = new SearchEngine();
        ArrayList<Task> searchList = searchEngine.searchCaseInsensitive(
                dataHandler.getMainList(), task.getDescription());
        if (searchList.isEmpty()) {
            return TaskFeedBack.FEEDBACK_NOT_FOUND;
        } else {
            displayedTask = dataHandler.getDisplayedTasks(task.getStarDate(),
                    task.getEndDate());
            return TaskFeedBack.FEEDBACK_VALID;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.Command#undo() This function undo by displaying the previous
     * list.
     */
    @Override
    public void undo() {
        dataHandler.setDisplayedTasks(displayedTask);
    }

}
