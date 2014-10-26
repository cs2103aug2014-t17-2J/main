/**
 * 
 */
package logic.command.commandList;

import java.util.EnumSet;

import logic.exception.InvalidCommandException;
import logic.parser.ParserFlags;
import logic.utility.StringHandler;
import logic.utility.Task;

/**
 * @author TienLong This class makes use of the Command interface to implement
 *         execute function for edit
 */
public class EditCommand extends Command {

    private static final int NOT_SET = -1;

    private int index = NOT_SET;
    private Task source;

    public void execute() throws InvalidCommandException {

        System.out.println("Editing");

        if (index == NOT_SET) {
            String indexString = StringHandler.getIntegerFromFirstSlot(task
                    .getDescription());

            if (indexString == null) {
            }

            index = getIndex(indexString);

            if (!dataHandler.indexValid(index)) {
                throw new InvalidCommandException("Edit failed, invalid index");
            }

            task.setDescription(StringHandler.removeFirstMatched(
                    task.getDescription(), indexString));

            source = dataHandler.getTask(index);

        }

        if (!dataHandler.indexValid(index)) {
            throw new InvalidCommandException("Edit failed, invalid index");
        }
            dataHandler.editTask(source, task);
            undoHandler.addUndo(this);
        
    }

    private int getIndex(String indexString) {
        final int ARRAY_OFFSET = -1;
        return StringHandler.parseStringToInteger(indexString) + ARRAY_OFFSET;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.Command#undo()
     */
    @Override
    public void undo() {
        dataHandler.editTask(task, source);
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
        return parseFlags.size() > MIN_VALID_FLAGS;
    }
    
    /* (non-Javadoc)
     * @see logic.command.commandList.Command#toString()
     */
    @Override
    public String toString() {
            return "Edit";
    }
}
