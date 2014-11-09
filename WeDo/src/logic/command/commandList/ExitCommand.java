/**
 * 
 */
package logic.command.commandList;

import java.util.EnumSet;

import logic.exception.InvalidCommandException;
import logic.parser.ParserFlags;

//@author A0112887X
/**
 * This class makes use of the Command interface to implement execute function
 * for ExitTask
 */
public class ExitCommand extends Command {

    /* (non-Javadoc)
     * @see logic.command.commandList.Command#execute()
     */
    public void execute() throws InvalidCommandException {
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

    /*
     * (non-Javadoc)
     * 
     * @see logic.command.commandList.Command#validate(java.util.EnumSet)
     */
    @Override
    public boolean validate(EnumSet<ParserFlags> parseFlags) {
        final EnumSet<ParserFlags> VALID_EXIT_PARSE = EnumSet
                .of(ParserFlags.COMMAND_FLAG);

        return ParserFlags.containsOnly(parseFlags, VALID_EXIT_PARSE);
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.command.commandList.Command#toString()
     */
    @Override
    public String toString() {
        final String COMMAND_NAME = "Exit";
        return COMMAND_NAME;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.command.commandList.Command#getValidateErrorMessage()
     */
    @Override
    public String getValidateErrorMessage() {
        final String ERROR_MESSAGE = "Format of exit should only be <Exit>";
        return ERROR_MESSAGE;
    }
}
