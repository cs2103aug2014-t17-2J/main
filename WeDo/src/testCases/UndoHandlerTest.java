/**
 * 
 */
package testCases;

import static org.junit.Assert.*;

import java.util.Stack;

import logic.command.UndoHandler;
import logic.command.commandList.AddCommand;
import logic.command.commandList.ClearCommand;
import logic.command.commandList.Command;
import logic.command.commandList.DeleteCommand;
import logic.command.commandList.EditCommand;
import logic.command.commandList.ExitCommand;
import logic.command.commandList.SearchCommand;
import logic.command.commandList.ViewCommand;

import org.junit.Test;

//@formatter:off
/**
 * @author Kuan Tien Long
 *  UndoHandler contains 3 methods
 *  1. addUndo
 *  2. undo
 *  3. redo
 *  
 *  This test case will cover the above mentioned methods.
 *  
 *  Below are the valid and invalid parameters for addUndo method
 *  +---------------+-------------+
 *  | Valid         | Invalid     |
 *  +---------------+-------------+
 *  | AddCommand    | null        |
 *  +---------------+-------------+
 *  | DeleteCommand | RedoCommand |
 *  +---------------+-------------+
 *  | ViewCommand   | UndoCommand |
 *  +---------------+-------------+
 *  | ClearCommand  |             |
 *  +---------------+-------------+
 *  | EditCommand   |             |
 *  +---------------+-------------+
 *  | SearchCommand |             |
 *  +---------------+-------------+
 *  | ExitCommand   |             |
 *  +---------------+-------------+
 */
//@formatter:on

public class UndoHandlerTest {

    @Test
    public void test() {
        UndoHandler undoHandler = UndoHandler.getInstance();
        Stack<Command> expectedStack = new Stack<Command>();

        addUndoValidWithAddCommand(undoHandler, expectedStack);
        addUndoValidWithDeleteCommand(undoHandler, expectedStack);
        addUndoValidWithViewCommand(undoHandler, expectedStack);
        addUndoValidWithClearCommand(undoHandler, expectedStack);
        addUndoValidWithEditCommand(undoHandler, expectedStack);
        addUndoValidWithSearchCommand(undoHandler, expectedStack);
        addUndoValidWithExitCommand(undoHandler, expectedStack);

    }

    private void addUndoValidWithDeleteCommand(UndoHandler undoHandler,
            Stack<Command> expectedStack) {
        addUndoValidWithCommand(new DeleteCommand(), undoHandler, expectedStack);
    }
    private void addUndoValidWithClearCommand(UndoHandler undoHandler,
            Stack<Command> expectedStack) {
        addUndoValidWithCommand(new ClearCommand(), undoHandler, expectedStack);
    }
    private void addUndoValidWithEditCommand(UndoHandler undoHandler,
            Stack<Command> expectedStack) {
        addUndoValidWithCommand(new EditCommand(), undoHandler, expectedStack);
    }
    
    private void addUndoValidWithSearchCommand(UndoHandler undoHandler,
            Stack<Command> expectedStack) {
        addUndoValidWithCommand(new SearchCommand(), undoHandler, expectedStack);
    }
    
    private void addUndoValidWithExitCommand(UndoHandler undoHandler,
            Stack<Command> expectedStack) {
        addUndoValidWithCommand(new ExitCommand(), undoHandler, expectedStack);
    }
    
    private void addUndoValidWithViewCommand(UndoHandler undoHandler,
            Stack<Command> expectedStack) {
        addUndoValidWithCommand(new ViewCommand(), undoHandler, expectedStack);
    }
    
    private void addUndoValidWithAddCommand(UndoHandler undoHandler,
            Stack<Command> expectedStack) {
        addUndoValidWithCommand(new AddCommand(), undoHandler, expectedStack);
    }

    private void addUndoValidWithCommand(Command command,
            UndoHandler undoHandler, Stack<Command> expectedStack) {
        expectedStack.add(command);
        assertEquals(expectedStack, undoHandler.addUndo(command));
    }

}
