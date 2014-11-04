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
public class RedoCommand extends Command {

    public void execute() throws InvalidCommandException {
        System.out.println("redo");
        
        undoHandler.redo();
  
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
        final EnumSet<ParserFlags> VALID_REDO_PARSE = EnumSet.of(
                ParserFlags.COMMAND_FLAG);
        
        return ParserFlags.containsOnly(parseFlags, VALID_REDO_PARSE);
    }
    
    /* (non-Javadoc)
     * @see logic.command.commandList.Command#toString()
     */
    @Override
    public String toString() {
            return "Redo";
    }

    /* (non-Javadoc)
     * @see logic.command.commandList.Command#getValidateErrorMessage()
     */
    @Override
    public String getValidateErrorMessage() {
        // TODO Auto-generated method stub
        return null;
    }
}
