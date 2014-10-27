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
import logic.command.commandList.RedoCommand;
import logic.command.commandList.SearchCommand;
import logic.command.commandList.UndoCommand;
import logic.command.commandList.ViewCommand;
import logic.exception.InvalidCommandException;

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
        addUndoInvalidWithNull(undoHandler);
        addUndoInvalidWithRedoCommand(undoHandler);
        addUndoInvalidWithUndoCommand(undoHandler);
//        undoValidWithExitCommand(undoHandler, expectedStack);
//        undoValidWithSearchCommand(undoHandler, expectedStack);
//        undoValidWithEditCommand(undoHandler, expectedStack);
//        undoValidWithClearCommand(undoHandler, expectedStack);
//        undoValidWithViewCommand(undoHandler, expectedStack);
//        undoValidWithDeleteCommand(undoHandler, expectedStack);
//        undoValidWithAddCommand(undoHandler, expectedStack);
      
    }

    private void undoValidWithAddCommand(UndoHandler undoHandler,
            Stack<Command> expectedStack) 
    {
        final String FAIL_MSG = "Exception should not happen";
        Command undoCommand;
        
        try {
            assertEquals(expectedStack.pop(), (undoCommand = undoHandler.undo()));
            assertTrue(undoCommand instanceof AddCommand);
        } catch (InvalidCommandException e) {
            fail(FAIL_MSG);
        }
    }
    
    
    private void undoValidWithSearchCommand(UndoHandler undoHandler,
            Stack<Command> expectedStack) 
    {
        final String FAIL_MSG = "Exception should not happen";
        Command undoCommand;
        
        try {
            assertEquals(expectedStack.pop(), (undoCommand = undoHandler.undo()));
            assertTrue(undoCommand instanceof SearchCommand);
        } catch (InvalidCommandException e) {
            fail(FAIL_MSG);
        }
    }

    private void undoValidWithEditCommand(UndoHandler undoHandler,
            Stack<Command> expectedStack) 
    {
        final String FAIL_MSG = "Exception should not happen";
        Command undoCommand;
        
        try {
            assertEquals(expectedStack.pop(), (undoCommand = undoHandler.undo()));
            assertTrue(undoCommand instanceof EditCommand);
        } catch (InvalidCommandException e) {
            fail(FAIL_MSG);
        }
    }

    private void undoValidWithClearCommand(UndoHandler undoHandler,
            Stack<Command> expectedStack) 
    {
        final String FAIL_MSG = "Exception should not happen";
        Command undoCommand;
        
        try {
            assertEquals(expectedStack.pop(), (undoCommand = undoHandler.undo()));
            assertTrue(undoCommand instanceof ClearCommand);
        } catch (InvalidCommandException e) {
            fail(FAIL_MSG);
        }
    }

    private void undoValidWithViewCommand(UndoHandler undoHandler,
            Stack<Command> expectedStack) 
    {
        final String FAIL_MSG = "Exception should not happen";
        Command undoCommand;
        
        try {
            assertEquals(expectedStack.pop(), (undoCommand = undoHandler.undo()));
            assertTrue(undoCommand instanceof ViewCommand);
        } catch (InvalidCommandException e) {
            fail(FAIL_MSG);
        }
    }

    private void undoValidWithDeleteCommand(UndoHandler undoHandler,
            Stack<Command> expectedStack) 
    {
        final String FAIL_MSG = "Exception should not happen";
        Command undoCommand;
        
        try {
            assertEquals(expectedStack.pop(), (undoCommand = undoHandler.undo()));
            assertTrue(undoCommand instanceof DeleteCommand);
        } catch (InvalidCommandException e) {
            fail(FAIL_MSG);
        }
    }

    private void undoValidWithExitCommand(UndoHandler undoHandler,
            Stack<Command> expectedStack) 
    {
        final String FAIL_MSG = "Exception should not happen";
        Command undoCommand;
        
        try {
            assertEquals(expectedStack.pop(), (undoCommand = undoHandler.undo()));
            assertTrue(undoCommand instanceof ExitCommand);
        } catch (InvalidCommandException e) {
            fail(FAIL_MSG);
        }
    }

    private void addUndoInvalidWithRedoCommand(UndoHandler undoHandler)
    {

        final String EXPECTED_ERROR_MSG = "command must not be RedoCommand for addUndo";
         addUndoInvalidWithCommand(new RedoCommand(), EXPECTED_ERROR_MSG, undoHandler);
    }

    private void addUndoInvalidWithUndoCommand(UndoHandler undoHandler)
    {
        final String EXPECTED_ERROR_MSG = "command must not be UndoCommand for addUndo";
         addUndoInvalidWithCommand(new UndoCommand(), EXPECTED_ERROR_MSG, undoHandler);
    }

    private void addUndoInvalidWithNull(UndoHandler undoHandler)
    {
         final String EXPECTED_ERROR_MSG = "command must not be null for addUndo";
         addUndoInvalidWithCommand(null, EXPECTED_ERROR_MSG, undoHandler);
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
        
        final String FAIL_MSG = "Exception should not happen";
        expectedStack.add(command);
        try {
            assertEquals(expectedStack, undoHandler.addUndo(command));
        } catch (InvalidCommandException exception) 
        {
            fail(FAIL_MSG);
        }
    }

    
    public void addUndoInvalidWithCommand(Command command, final String EXPECTED_ERROR_MSG, UndoHandler undoHandler)
    {
        final String FAIL_MSG = "Exception should happen";

        try
        {
            undoHandler.addUndo(command);
            fail(FAIL_MSG);
        }
        catch(InvalidCommandException exception)
        {
             assertEquals(EXPECTED_ERROR_MSG, exception.getMessage());
        }
    }
    

}
