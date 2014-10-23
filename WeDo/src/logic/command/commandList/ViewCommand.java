/**
 * 
 */
package logic.command.commandList;

import java.util.ArrayList;
import java.util.EnumSet;

import logic.exception.InvalidCommandException;
import logic.parser.ParserFlags;
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
