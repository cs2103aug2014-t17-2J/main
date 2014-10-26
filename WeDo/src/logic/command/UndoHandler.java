/**
 * 
 */
package logic.command;

import java.util.Stack;

import logic.command.commandList.Command;
import logic.command.commandList.RedoCommand;
import logic.command.commandList.UndoCommand;
import logic.exception.InvalidCommandException;

/**
 * @author Kuan Tien Long
 *
 */
public class UndoHandler {
    private enum State {
        STATE_UNDO, STATE_ADD_UNDO, STATE_ADD_REDO, STATE_UNDEFINED
    }
    
    private static UndoHandler undoHandler = new UndoHandler();
    private static Stack<Command> undoStack = new Stack<Command>();
    private static Stack<Command> redoStack = new Stack<Command>();
    private static State currentState = State.STATE_UNDEFINED;

    
    public static UndoHandler getInstance()
    {
        return undoHandler;
    }

    private UndoHandler()
    {
        
    }


    private boolean canUndo() {
        return undoStack.size() > 0;
    }

    private boolean canRedo() {
        return redoStack.size() > 0;
    }

    /**
     * Precondition : command must not be null, RedoCommand, UndoCommand
     * Add command that can be undo to the undo stack
     * @param command the command that could be undo
     * @return  Stack<Command> which contains the available commands to undo
     * @throws InvalidCommandException
     */
    public Stack<Command> addUndo(Command command){
        
        assert (command != null);
        assert (!(command instanceof RedoCommand));
        assert (!(command instanceof UndoCommand));
        
        
        if (!redoStack.isEmpty() && currentState == State.STATE_UNDO) {
            redoStack.clear();
        }
    
        currentState = State.STATE_ADD_UNDO;
        undoStack.add(command);
        return undoStack;

    }

    /**
     * Undo a command stored at the top of undo stack.
     * @return Command that was undo
     * @throws InvalidCommandException if there are no undo command left
     */
    public Command undo() throws InvalidCommandException {
        
        final String NO_COMMAND_TO_UNDO = "No command to undo";

        if (canUndo()) {
            currentState = State.STATE_UNDO;
            Command command = undoStack.pop();
            redoStack.push(command);
            command.undo();
            return command;

        } else {
            throw new InvalidCommandException(NO_COMMAND_TO_UNDO);
        }

    }

    /**
     * Redo a command at the top of the redo stack.
     * @return Command that was redo
     * @throws InvalidCommandException if there are no redo command left
     */
    public Command redo() throws InvalidCommandException {
        
        final String NO_COMMAND_TO_REDO = "No command to redo";

        if (canRedo()) {
            currentState = State.STATE_ADD_REDO;
            Command command = redoStack.pop();
            command.execute();
            return command;
        } else {
            throw new InvalidCommandException(NO_COMMAND_TO_REDO);
        }
    }
}
