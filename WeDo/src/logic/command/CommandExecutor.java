package logic.command;

import logic.command.commandList.Command;
import logic.taskParser.TaskParserPlus;
import logic.utility.Task;
import dataStorage.DataHandler;
import definedEnumeration.TaskFeedBack;

/**
 * @author Kuan Tien Long This class handle all the commands passed in by the
 *         user
 * 
 */
public class CommandExecutor {

    private DataHandler dataHandler;
    private UndoHandler undoHandler;

    /**
     * Constructor for CommandHandler
     * 
     * @param dataHandler
     *            the handler which contains of all the data
     */
    public CommandExecutor(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
        undoHandler = new UndoHandler();
    }


    /**
     * @param command the command to execute
     * @param task the task to execute
     * @return 
     */
    public TaskFeedBack execute(Command command, Task task) {

        if (command == null) {
            return TaskFeedBack.FEEDBACK_INVALID;
        }

        command.setDataHandler(dataHandler);
        command.setTask(task);
        command.setUndoHandler(undoHandler);
        System.out.println(task);

        return command.execute();
    }

}
