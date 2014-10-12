/**
 * 
 */
package logic.command.commandList;

import logic.InvalidCommandException;

/**
 * @author TienLong This class makes use of the Command interface to
 *         implement execute function for undoCommand
 */
public class UndoCommand extends Command {

    public void execute() throws InvalidCommandException {
        System.out.println("undo");
        final boolean FAILED = false;

        if(undoHandler.undo() == FAILED)

        {
            throw new InvalidCommandException("Nothing to undo");
        }
        
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.Command#undo()
     */
    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }
}
