/**
 * 
 */
package logic;

import java.util.Stack;

import logic.commandList.Command;

/**
 * @author Kuan Tien Long
 *
 */
public class UndoHandler {
    private enum State {
        STATE_UNDO, STATE_ADD_UNDO, STATE_ADD_REDO, STATE_UNDEFINED
    }

    private Stack<Command> undoStack;
    private Stack<Command> redoStack;
    private State currentState = State.STATE_UNDEFINED;

    public UndoHandler() {
        undoStack = new Stack<Command>();
        redoStack = new Stack<Command>();
    }

    private boolean canUndo() {
        return undoStack.size() > 0;
    }

    private boolean canRedo() {
        return redoStack.size() > 0;
    }

    public void add(Command command) {
        if (!redoStack.isEmpty() && currentState == State.STATE_UNDO) {
            redoStack.clear();
        }
    
        currentState = State.STATE_ADD_UNDO;
        undoStack.add(command);

    }

    public boolean undo() {

        if (canUndo()) {
            currentState = State.STATE_UNDO;
            Command command = undoStack.pop();
            redoStack.push(command);
            command.undo();
            return true;

        } else {
            return false;
        }

    }

    public boolean redo() {
        if (canRedo()) {
            currentState = State.STATE_ADD_REDO;
            Command command = redoStack.pop();
            command.execute();
            return true;
        } else {
            return false;
        }
    }
}
