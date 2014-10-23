/**
 * 
 */
package logic.command.commandList;

import java.util.EnumSet;

import logic.exception.InvalidCommandException;
import logic.parser.ParserFlags;

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
    
    /* (non-Javadoc)
     * @see logic.command.commandList.Command#validate(java.util.EnumSet)
     */
    @Override
    public boolean validate(EnumSet<ParserFlags> parseFlags) {
        final int MAX_VALID_FLAG = 1;
        return parseFlags.size() == MAX_VALID_FLAG; 
    }
}
