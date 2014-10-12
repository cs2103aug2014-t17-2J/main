/**
 * 
 */
package logic.command.commandList;

import logic.InvalidCommandException;


/**
 * @author TienLong This class makes use of the Command interface to
 *         implement execute function for undoCommand
 */
public class RedoCommand extends Command {

    public void execute() throws InvalidCommandException {
        System.out.println("redo");
        
        final boolean FAILED = false;

        if(undoHandler.redo() == FAILED)
        {
            throw new InvalidCommandException("Nothing to redo");
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
