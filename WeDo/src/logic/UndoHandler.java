/**
 * 
 */
package logic;

import java.util.Stack;

/**
 * @author Kuan Tien Long
 *
 */
public class UndoHandler<T> 
{
    private Stack<Command> undoStack;
    private Stack<Command> redoStack;
    
    public UndoHandler() 
    {
        undoStack = new Stack<Command>();
        redoStack = new Stack<Command>();
    }
    
    private boolean canUndo()
    {
        return undoStack.size() > 0;
    }
    
    private boolean canRedo()
    {
        return redoStack.size() > 0;
    }
    
    public void add(Command command)
    {
        undoStack.add(command);
        redoStack.clear();
    }

    public boolean undo()
    {
        if(canUndo())
        {
            Command command = undoStack.pop();
            redoStack.push(command);
            command.undo();
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean redo()
    {
        if(canRedo())
        {
            Command command = redoStack.pop();
            command.execute();
            
            return true;
        }
        else
        {
            return false;
        }
    }
}
