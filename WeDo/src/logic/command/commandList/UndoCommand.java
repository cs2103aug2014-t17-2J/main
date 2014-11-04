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

        undoHandler.undo();
        
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
        final EnumSet<ParserFlags> VALID_UNDO_PARSE = EnumSet.of(
                ParserFlags.COMMAND_FLAG);
        
        return ParserFlags.containsOnly(parseFlags, VALID_UNDO_PARSE);
    }
    
    /* (non-Javadoc)
     * @see logic.command.commandList.Command#toString()
     */
    @Override
    public String toString() {
            return "Undo";
    }

    /* (non-Javadoc)
     * @see logic.command.commandList.Command#getValidateErrorMessage()
     */
    @Override
    public String getValidateErrorMessage() {
        final String ERROR_MESSAGE = "Format of undo should only be <Undo>";
        return ERROR_MESSAGE;
    }
}
