/**
 * 
 */
package logic.command.commandList;

import logic.InvalidCommandException;
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

}
