/**
 * 
 */
package logic.command.commandList;

import java.util.ArrayList;

import logic.InvalidCommandException;
import logic.SearchEngine;
import logic.utility.Task;



/**
 * @author TienLong This class makes use of the Command interface to implement
 *         execute function for searchTask
 */
public class SearchCommand extends Command {
    private ArrayList<Task> displayedTask;

    public void execute() throws InvalidCommandException {

        System.out.println("searching");

        SearchEngine searchEngine = new SearchEngine();
        ArrayList<Task> searchList = searchEngine.searchCaseInsensitive(
                dataHandler.getMainList(), task.getDescription());
        if (searchList.isEmpty()) {
            throw new InvalidCommandException("Search failed unable to find...");
        } else {
            displayedTask = dataHandler.getDisplayedTasks(task.getStartDate(),
                    task.getEndDate());
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
