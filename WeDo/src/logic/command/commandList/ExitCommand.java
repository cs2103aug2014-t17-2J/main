/**
 * 
 */
package logic.command.commandList;

import java.util.EnumSet;

import logic.exception.InvalidCommandException;
import logic.parser.ParserFlags;

/**
 * @author TienLong This class makes use of the Command interface to implement
 *         execute function for ExitTask
 */
public class ExitCommand extends Command {

    public void execute() throws InvalidCommandException {
        System.out.println("exit");
        System.exit(0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.Command#undo() No undo for exit.
     */
    @Override
    public void undo() {

    }

    /* (non-Javadoc)
     * @see logic.command.commandList.Command#validate(java.util.EnumSet)
     */
    @Override
    public boolean validate(EnumSet<ParserFlags> parseFlags) {
        final int MIN_VALID_FLAGS = 1;
        return parseFlags.size() == MIN_VALID_FLAGS;
    }
    
    /* (non-Javadoc)
     * @see logic.command.commandList.Command#toString()
     */
    @Override
    public String toString() {
            return "Exit";
    }
}
