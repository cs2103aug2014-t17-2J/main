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

//@author A0112887X
/**
 * This class makes use of the Command interface to implement execute function
 * for searchTask
 */
public class SearchCommand extends Command {
    private ArrayList<Task> displayedTask;

    public void execute() throws InvalidCommandException {

        SearchEngine searchEngine = new SearchEngine(
                (BasicDataHandler) dataHandler);
        ArrayList<Task> searchList = searchEngine.search(task);
        if (searchList.isEmpty()) {
            throw new InvalidCommandException("Search failed unable to find...");
        } else {
            displayedTask = dataHandler.getDisplayedTasks(task.getStartDate(),
                    task.getEndDate());
            undoHandler.addUndo(this);

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

    /*
     * (non-Javadoc)
     * 
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
     * @param parseFlags
     *            the set of ParserFlag to be tested
     * @return if it contains more than MIN_VALID_FLAGS flags
     */
    public static boolean isCommandValid(EnumSet<ParserFlags> parseFlags) {
        final EnumSet<ParserFlags> VALID_SEARCH_CATEGORY_PARSE = EnumSet.of(
                ParserFlags.DESCRIPTION_FLAG, ParserFlags.COMMAND_FLAG);
        final EnumSet<ParserFlags> VALID_SEARCH_DATE_PARSE = EnumSet.of(
                ParserFlags.DATE_FLAG, ParserFlags.COMMAND_FLAG);
        final EnumSet<ParserFlags> VALID_SEARCH_PRIORITY_PARSE = EnumSet.of(
                ParserFlags.PRIORITY_FLAG, ParserFlags.COMMAND_FLAG);

        return (ParserFlags.containsOnly(parseFlags,
                VALID_SEARCH_CATEGORY_PARSE)
                || ParserFlags
                        .containsOnly(parseFlags, VALID_SEARCH_DATE_PARSE) || ParserFlags
                    .containsOnly(parseFlags, VALID_SEARCH_PRIORITY_PARSE));
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.command.commandList.Command#toString()
     */
    @Override
    public String toString() {
        final String COMMAND_NAME = "Search";
        return COMMAND_NAME;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.command.commandList.Command#getValidateErrorMessage()
     */
    @Override
    public String getValidateErrorMessage() {
        final String ERROR_MESSAGE = "Format of Search should only be <Search> <Description> or <Search> <Date> or <Search> <Priority>";
        return ERROR_MESSAGE;
    }

}
