package logic.command.commandList;

import logic.InvalidCommandException;
import logic.command.UndoHandler;
import logic.utility.Task;
import dataStorage.DataHandler;

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

}
