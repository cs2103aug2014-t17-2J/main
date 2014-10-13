package logic.command.commandList;

import logic.InvalidCommandException;
import logic.command.UndoHandler;
import logic.taskParser.TaskParserBasic;
import logic.utility.Task;
import dataStorage.DataHandler;

public abstract class Command {

    protected Task task;
    protected DataHandler dataHandler;
    protected UndoHandler undoHandler;

    protected void buildTask(StringBuilder userInput) {
        TaskParserBasic taskParser = new TaskParserBasic();
        this.task = taskParser.buildTask(userInput);
    }

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

}
