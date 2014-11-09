/**
 * 
 */
package testCases;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Stack;

import logic.command.UndoHandler;
import logic.command.commandList.AddCommand;
import logic.command.commandList.CompleteCommand;
import logic.command.commandList.Command;
import logic.command.commandList.DeleteCommand;
import logic.command.commandList.EditCommand;
import logic.command.commandList.ExitCommand;
import logic.command.commandList.RedoCommand;
import logic.command.commandList.SearchCommand;
import logic.command.commandList.UndoCommand;
import logic.command.commandList.ViewCommand;
import logic.exception.InvalidCommandException;
import logic.utility.Task;

import org.junit.Test;

import dataStorage.BasicDataHandler;
import dataStorage.DataHandler;
import definedEnumeration.Priority;

//@author A0112887X
//@formatter:off
/**
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
        DataHandler dataHandler = new BasicDataHandler();

        addUndoValidWithAddCommand(dataHandler, undoHandler, expectedStack);
        addUndoValidWithDeleteCommand(dataHandler, undoHandler, expectedStack);
        addUndoValidWithViewCommand(dataHandler, undoHandler, expectedStack);
        addUndoValidWithCompleteCommand(dataHandler, undoHandler, expectedStack);
        addUndoValidWithEditCommand(dataHandler, undoHandler, expectedStack);
        addUndoValidWithSearchCommand(dataHandler, undoHandler, expectedStack);
        addUndoValidWithExitCommand(undoHandler, expectedStack);
        addUndoInvalidWithNull(undoHandler);
        addUndoInvalidWithRedoCommand(undoHandler);
        addUndoInvalidWithUndoCommand(undoHandler);
        undoValidWithExitCommand(undoHandler, expectedStack);
        undoValidWithSearchCommand(undoHandler, expectedStack);
        undoValidWithEditCommand(undoHandler, expectedStack);
        undoValidWithCompleteCommand(undoHandler, expectedStack);
        undoValidWithViewCommand(undoHandler, expectedStack);
        undoValidWithDeleteCommand(undoHandler, expectedStack);
        undoValidWithAddCommand(undoHandler, expectedStack);

    }

    private void undoValidWithAddCommand(UndoHandler undoHandler,
            Stack<Command> expectedStack) {
        final String FAIL_MSG = "Exception should not happen";
        Command undoCommand;

        try {
            assertEquals(expectedStack.pop(),
                    (undoCommand = undoHandler.undo()));
            assertTrue(undoCommand instanceof AddCommand);
        } catch (InvalidCommandException e) {
            fail(FAIL_MSG);
        }
    }

    private void undoValidWithSearchCommand(UndoHandler undoHandler,
            Stack<Command> expectedStack) {
        final String FAIL_MSG = "Exception should not happen";
        Command undoCommand;

        try {
            assertEquals(expectedStack.pop(),
                    (undoCommand = undoHandler.undo()));
            assertTrue(undoCommand instanceof SearchCommand);
        } catch (InvalidCommandException e) {
            fail(FAIL_MSG);
        }
    }

    private void undoValidWithEditCommand(UndoHandler undoHandler,
            Stack<Command> expectedStack) {
        final String FAIL_MSG = "Exception should not happen";
        Command undoCommand;

        try {
            assertEquals(expectedStack.pop(),
                    (undoCommand = undoHandler.undo()));
            assertTrue(undoCommand instanceof EditCommand);
        } catch (InvalidCommandException e) {
            fail(FAIL_MSG);
        }
    }

    private void undoValidWithCompleteCommand(UndoHandler undoHandler,
            Stack<Command> expectedStack) {
        final String FAIL_MSG = "Exception should not happen";
        Command undoCommand;

        try {
            assertEquals(expectedStack.pop(),
                    (undoCommand = undoHandler.undo()));
            assertTrue(undoCommand instanceof CompleteCommand);
        } catch (InvalidCommandException e) {
            fail(FAIL_MSG);
        }
    }

    private void undoValidWithViewCommand(UndoHandler undoHandler,
            Stack<Command> expectedStack) {
        final String FAIL_MSG = "Exception should not happen";
        Command undoCommand;

        try {
            assertEquals(expectedStack.pop(),
                    (undoCommand = undoHandler.undo()));
            assertTrue(undoCommand instanceof ViewCommand);
        } catch (InvalidCommandException e) {
            fail(FAIL_MSG);
        }
    }

    private void undoValidWithDeleteCommand(UndoHandler undoHandler,
            Stack<Command> expectedStack) {
        final String FAIL_MSG = "Exception should not happen";
        Command undoCommand;

        try {
            assertEquals(expectedStack.pop(),
                    (undoCommand = undoHandler.undo()));
            assertTrue(undoCommand instanceof DeleteCommand);
        } catch (InvalidCommandException e) {
            fail(FAIL_MSG);
        }
    }

    private void undoValidWithExitCommand(UndoHandler undoHandler,
            Stack<Command> expectedStack) {
        final String FAIL_MSG = "Exception should not happen";
        Command undoCommand;

        try {
            assertEquals(expectedStack.pop(),
                    (undoCommand = undoHandler.undo()));
            assertTrue(undoCommand instanceof ExitCommand);
        } catch (InvalidCommandException e) {
            fail(FAIL_MSG);
        }
    }

    private void addUndoInvalidWithRedoCommand(UndoHandler undoHandler) {

        final String EXPECTED_ERROR_MSG = "command must not be RedoCommand for addUndo";
        addUndoInvalidWithCommand(new RedoCommand(), EXPECTED_ERROR_MSG,
                undoHandler);
    }

    private void addUndoInvalidWithUndoCommand(UndoHandler undoHandler) {
        final String EXPECTED_ERROR_MSG = "command must not be UndoCommand for addUndo";
        addUndoInvalidWithCommand(new UndoCommand(), EXPECTED_ERROR_MSG,
                undoHandler);
    }

    private void addUndoInvalidWithNull(UndoHandler undoHandler) {
        final String EXPECTED_ERROR_MSG = "command must not be null for addUndo";
        addUndoInvalidWithCommand(null, EXPECTED_ERROR_MSG, undoHandler);
    }

    private void addUndoValidWithDeleteCommand(DataHandler dataHandler,
            UndoHandler undoHandler, Stack<Command> expectedStack) {
        Command command = new DeleteCommand();
        command.setDataHandler(dataHandler);
        addUndoValidWithCommand(command, undoHandler, expectedStack);
    }

    private void addUndoValidWithCompleteCommand(DataHandler dataHandler,
            UndoHandler undoHandler, Stack<Command> expectedStack) {
        Command command = new CompleteCommand();
        command.setDataHandler(dataHandler);
        addUndoValidWithCommand(command, undoHandler, expectedStack);
    }

    private void addUndoValidWithEditCommand(DataHandler dataHandler,
            UndoHandler undoHandler, Stack<Command> expectedStack) {
        EditCommand command = new EditCommand();
        Task task = new Task("edit 1 Random Task", Priority.PRIORITY_MEDIUM,
                LocalDate.of(2014, 9, 18), LocalTime.of(14, 0), LocalDate.of(
                        2014, 9, 22), LocalTime.of(2, 0));
        
        command.setTask(task);
        command.setSource(task);
        command.setDataHandler(dataHandler);
        
        addUndoValidWithCommand(command, undoHandler, expectedStack);
    }

    private void addUndoValidWithSearchCommand(DataHandler dataHandler,
            UndoHandler undoHandler, Stack<Command> expectedStack) {
        SearchCommand searchCommand = new SearchCommand();
        searchCommand.setDataHandler(dataHandler);
        addUndoValidWithCommand(searchCommand, undoHandler, expectedStack);
    }

    private void addUndoValidWithExitCommand(UndoHandler undoHandler,
            Stack<Command> expectedStack) {
        addUndoValidWithCommand(new ExitCommand(), undoHandler, expectedStack);
    }

    private void addUndoValidWithViewCommand(DataHandler dataHandler,
            UndoHandler undoHandler, Stack<Command> expectedStack) {
        Command command = new ViewCommand();
        command.setDataHandler(dataHandler);
        addUndoValidWithCommand(command, undoHandler, expectedStack);
    }

    private void addUndoValidWithAddCommand(DataHandler dataHandler,
            UndoHandler undoHandler, Stack<Command> expectedStack) {
        Command command = new AddCommand();
        Task task = new Task("Random Task", Priority.PRIORITY_MEDIUM,
                LocalDate.of(2014, 9, 18), LocalTime.of(14, 0), LocalDate.of(
                        2014, 9, 22), LocalTime.of(2, 0));
        
        command.setDataHandler(dataHandler);
        command.setTask(task);
        addUndoValidWithCommand(command, undoHandler, expectedStack);
    }

    private void addUndoValidWithCommand(Command command,
            UndoHandler undoHandler, Stack<Command> expectedStack) {

        final String FAIL_MSG = "Exception should not happen";
        expectedStack.add(command);
        try {
            assertEquals(expectedStack, undoHandler.addUndo(command));
        } catch (InvalidCommandException exception) {
            fail(FAIL_MSG);
        }
    }

    public void addUndoInvalidWithCommand(Command command,
            final String EXPECTED_ERROR_MSG, UndoHandler undoHandler) {
        final String FAIL_MSG = "Exception should happen";

        try {
            undoHandler.addUndo(command);
            fail(FAIL_MSG);
        } catch (InvalidCommandException exception) {
            assertEquals(EXPECTED_ERROR_MSG, exception.getMessage());
        }
    }

}
