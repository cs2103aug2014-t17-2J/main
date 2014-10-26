/**
 * 
 */
package logic.command.commandList;

import java.util.EnumSet;

import logic.exception.InvalidCommandException;
import logic.parser.ParserFlags;
import logic.utility.StringHandler;

/**
 * @author TienLong This class makes use of the Command interface to implement
 *         execute function for DeleteTask
 */
public class DeleteCommand extends Command {

    private static final int NOT_SET = -1;
    private int index = NOT_SET;


    public void execute() throws InvalidCommandException {

        System.out.println("delete");

        final int ARRAY_OFFSET = -1;

        if (index == NOT_SET) {
            int lineToDelete = StringHandler.parseStringToInteger(task
                    .getDescription()) + ARRAY_OFFSET;
            if (dataHandler.indexValid(lineToDelete)) {
                task = dataHandler.getTask(lineToDelete);
        }
        

            dataHandler.removeTask(task);
            undoHandler.addUndo(this);

        } else {
            throw new InvalidCommandException("Deleting failed");
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.Command#undo()
     */
    @Override
    public void undo() {
        dataHandler.addTask(task);
    }
    
    /* (non-Javadoc)
     * @see logic.command.commandList.Command#validate(java.util.EnumSet)
     */
    @Override
    public boolean validate(EnumSet<ParserFlags> parseFlags) {
        final int MAX_VALID_FLAG = 1;
        return parseFlags.size() > MAX_VALID_FLAG; 
    }
    
    /* (non-Javadoc)
     * @see logic.command.commandList.Command#toString()
     */
    @Override
    public String toString() {
            return "Delete";
    }
    
}
