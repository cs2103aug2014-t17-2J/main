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
 * for AddTask
 */

public class AddCommand extends Command {

    /* (non-Javadoc)
     * @see logic.command.commandList.Command#execute()
     */
    public void execute() throws InvalidCommandException {
        final String ERROR_MESSAGE = "Adding failed";
        assert(dataHandler != null);
        if (dataHandler.addTask(task)) {
            undoHandler.addUndo(this);
        } else {
            throw new InvalidCommandException(ERROR_MESSAGE);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.Command#undo()
     */
    @Override
    public void undo() {
        assert(dataHandler != null);
        dataHandler.removeTask(task);

    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.command.commandList.Command#validate(logic.parser.ParserFlags)
     */
    @Override
    public boolean validate(EnumSet<ParserFlags> parseFlags) {
        return isParseValidForAdd(parseFlags);

    }

    /**
     * <p>
     * Determine whether the parse occurred was valid by matching it with
     * VALID_PARSE which contains compulsory parse result(s) for add command
     * required
     * <p>
     * 
     * @param parseFlags
     *            the set of ParserFlag to be tested
     * @return if it contains all of the VALID_ADD_SET flag
     */
    public boolean isParseValidForAdd(EnumSet<ParserFlags> parseFlags) {

        final EnumSet<ParserFlags> VALID_PARSE = EnumSet.of(
                ParserFlags.DESCRIPTION_FLAG, ParserFlags.COMMAND_FLAG);

        if (parseFlags.containsAll(VALID_PARSE)) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.command.commandList.Command#toString()
     */
    @Override
    public String toString() {
        final String COMMAND_NAME = "Add";
        return COMMAND_NAME;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.command.commandList.Command#getValidateErrorMessage()
     */
    @Override
    public String getValidateErrorMessage() {
        final String ERROR_MESSAGE = "Format of add should contains description field";
        return ERROR_MESSAGE;
    }

}
