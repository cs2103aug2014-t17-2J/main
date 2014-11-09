package logic.command;

import junit.framework.Assert;
import logic.command.commandList.Command;
import logic.exception.InvalidCommandException;
import logic.utility.Task;
import dataStorage.DataHandler;

//@author A0112887X
/**
 * This class handle all the commands passed in by the user
 * 
 */
public class CommandExecutor {

    private DataHandler dataHandler;

    /**
     * Constructor for CommandHandler
     * 
     * @param dataHandler
     *            the handler which contains of all the data
     */
    public CommandExecutor(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }

    /**
     * Execute the command such as add, view etc
     * @param command
     *            the command to execute
     * @param task
     *            the task to execute
     * @return
     * @throws InvalidCommandException
     */
    public void execute(Command command, Task task)
            throws InvalidCommandException {

        Assert.assertNotNull(command);
        Assert.assertNotNull(task);

        command.setDataHandler(dataHandler);
        command.setTask(task);
        System.out.println(task);

        command.execute();
    }

}
