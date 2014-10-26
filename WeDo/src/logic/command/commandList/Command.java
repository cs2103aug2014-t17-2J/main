package logic.command.commandList;

import java.util.EnumSet;

import logic.command.UndoHandler;
import logic.exception.InvalidCommandException;
import logic.parser.ParserFlags;
import logic.utility.Task;
import dataStorage.DataHandler;

/**
 * @author Kuan Tien Long
 *
 */
public abstract class Command {

    protected Task task;
    protected DataHandler dataHandler;
    protected UndoHandler undoHandler;



    public void setTask(Task task) {
        this.task = task;
    }

    public void setDataHandler(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }
    
    public void setUndoHandler(UndoHandler undoHandler) {
        this.undoHandler = undoHandler;
    }

    /**
     * This method execute the commands such as add, display, clear etc.
     * 
     * @return TaskFeedBack to display if the command is valid
     * @throws InvalidCommandException TODO
     */

    public abstract void execute() throws InvalidCommandException;

    public abstract void undo();
    
    /**
     * <p>
     * Determine whether the parse occurred was valid 
     * <p>
     * 
     * @param parseFlags the set of ParserFlag to be tested
     * @return if what is parsed is valid for the command
     */
    public abstract boolean validate(EnumSet<ParserFlags> parseFlags);


    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Command other = (Command) obj;
        if (dataHandler == null) {
            if (other.dataHandler != null)
                return false;
        } else if (!dataHandler.equals(other.dataHandler))
            return false;
        if (task == null) {
            if (other.task != null)
                return false;
        } else if (!task.equals(other.task))
            return false;
        if (undoHandler == null) {
            if (other.undoHandler != null)
                return false;
        } else if (!undoHandler.equals(other.undoHandler))
            return false;
        return true;
    }

    public abstract String toString();

}
