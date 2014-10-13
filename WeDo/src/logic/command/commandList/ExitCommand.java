/**
 * 
 */
package logic.command.commandList;

import logic.InvalidCommandException;

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
}
