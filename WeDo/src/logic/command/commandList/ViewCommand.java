/**
 * 
 */
package logic.command.commandList;

import java.util.ArrayList;
import java.util.EnumSet;

import logic.exception.InvalidCommandException;
import logic.parser.ParserFlags;
import logic.utility.Task;

//@author A0112887X
/**
 * This class makes use of the Command interface to implement execute function
 * for InvalidTask
 */
public class ViewCommand extends Command {

    ArrayList<Task> previousView;

    public void execute() throws InvalidCommandException {
        assert(dataHandler != null);
        
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
        assert(dataHandler != null);
       
        dataHandler.setDisplayedTasks(previousView);
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

        final EnumSet<ParserFlags> VALID_VIEW_CATEGORY_PARSE = EnumSet.of(
                ParserFlags.DESCRIPTION_FLAG, ParserFlags.COMMAND_FLAG);
        final EnumSet<ParserFlags> VALID_VIEW_DATE_PARSE = EnumSet.of(
                ParserFlags.DATE_FLAG, ParserFlags.COMMAND_FLAG);
        final EnumSet<ParserFlags> VALID_VIEW_PRIORITY_PARSE = EnumSet.of(
                ParserFlags.PRIORITY_FLAG, ParserFlags.COMMAND_FLAG);

        return (ParserFlags.containsOnly(parseFlags, VALID_VIEW_CATEGORY_PARSE)
                || ParserFlags.containsOnly(parseFlags, VALID_VIEW_DATE_PARSE) || ParserFlags
                    .containsOnly(parseFlags, VALID_VIEW_PRIORITY_PARSE));

    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.command.commandList.Command#toString()
     */
    @Override
    public String toString() {
        final String COMMAND_NAME = "View";
        return COMMAND_NAME;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.command.commandList.Command#getValidateErrorMessage()
     */
    @Override
    public String getValidateErrorMessage() {
        final String ERROR_MESSAGE = "Format of view should only be <View> <Date> or <View> <Category> or <View> <Priority>";
        return ERROR_MESSAGE;
    }

}
