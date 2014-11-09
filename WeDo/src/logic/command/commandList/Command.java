package logic.command.commandList;

import java.util.EnumSet;

import logic.command.UndoHandler;
import logic.exception.InvalidCommandException;
import logic.parser.ParserFlags;
import logic.utility.Task;
import dataStorage.DataHandler;

//@author A0112887X
/**
 * Abstract class that all commands must extend
 *
 */
public abstract class Command {

    protected Task task;
    protected DataHandler dataHandler;
    protected UndoHandler undoHandler = UndoHandler.getInstance();

    public void setTask(Task task) {
        this.task = task;
    }

    /**
     * Set the dataHandler for the command
     * @param dataHandler the dataHandler to set
     */
    public void setDataHandler(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }

    /**
     * Execute the command such as add, view etc.
     * @throws InvalidCommandException when command is invalid
     */
    public abstract void execute() throws InvalidCommandException;

    /**
     * undo the command executed
     */
    public abstract void undo();

    /**
     * <p>
     * Determine whether the parse occurred was valid
     * <p>
     * 
     * @param parseFlags
     *            the set of ParserFlag to be tested
     * @return if what is parsed is valid for the command
     */
    public abstract boolean validate(EnumSet<ParserFlags> parseFlags);

    /**
     * Get validation error message from the command
     * 
     * @return Error Message
     */
    public abstract String getValidateErrorMessage();

    /*
     * (non-Javadoc)
     * 
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

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public abstract String toString();

}
