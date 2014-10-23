/**
 * 
 */
package logic.command.commandList;

import java.util.ArrayList;
import java.util.EnumSet;

import dataStorage.BasicDataHandler;
import logic.exception.InvalidCommandException;
import logic.parser.ParserFlags;
import logic.utility.SearchEngine;
import logic.utility.Task;



/**
 * @author TienLong This class makes use of the Command interface to implement
 *         execute function for searchTask
 */
public class SearchCommand extends Command {
    private ArrayList<Task> displayedTask;

    public void execute() throws InvalidCommandException {

        System.out.println("searching");

        SearchEngine searchEngine = new SearchEngine((BasicDataHandler) dataHandler);
        ArrayList<Task> searchList = searchEngine.searchWagnerList( task.getDescription());
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
    /* (non-Javadoc)
     * @see logic.command.commandList.Command#validate(java.util.EnumSet)
     */
    @Override
    public boolean validate(EnumSet<ParserFlags> parseFlags) {
        return isCommandValid(parseFlags);
    }
    
    /**
     * <p>
     * Determine whether the command is valid
     * <p>
     * 
     * @param parseFlags the set of ParserFlag to be tested
     * @return if it contains more than MIN_VALID_FLAGS flags
     */
    public static boolean isCommandValid(EnumSet<ParserFlags> parseFlags) {
        final int MIN_VALID_FLAGS = 1;
        return parseFlags.size()>MIN_VALID_FLAGS;
    }

}
