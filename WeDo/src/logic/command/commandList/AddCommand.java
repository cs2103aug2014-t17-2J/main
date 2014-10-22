/**
 * 
 */
package logic.command.commandList;

import logic.exception.InvalidCommandException;

/**
 * @author TienLong This class makes use of the Command interface to implement
 *         execute function for AddTask
 */
public class AddCommand extends Command {

    /**
     * @param task
     * @param processor
     * @throws InvalidCommandException 
     */

    public void execute() throws InvalidCommandException {
        System.out.println("adding");
        if (dataHandler.addTask(task)) {
            undoHandler.addUndo(this);
        } else {
            throw new InvalidCommandException("Adding failed");
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.Command#undo()
     */
    @Override
    public void undo() {
        dataHandler.removeTask(task);

    }
}
