/**
 * 
 */
package logic;

import logic.command.CommandExecutor;
import logic.command.CommandParser;
import logic.command.UndoHandler;
import logic.command.commandList.Command;
import logic.taskParser.TaskParserPlus;
import logic.utility.Task;
import dataStorage.DataHandler;
import definedEnumeration.TaskFeedBack;
import edu.emory.mathcs.backport.java.util.concurrent.Executor;

/**
 * @author Kuan Tien Long
 *
 */
public class LogicManager 
{
    
    private DataHandler dataHandler;
    private UndoHandler undoHandler;
    

    /**
     * Constructor for CommandHandler
     * 
     * @param dataHandler
     *            the handler which contains of all the data
     */
    public LogicManager(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
        undoHandler = new UndoHandler();
    } 

    public TaskFeedBack processUserInput(String userInput) {

        StringBuilder userInputBuilder = new StringBuilder(userInput);
        
        TaskParserPlus taskParser = new TaskParserPlus();
        Task task = taskParser.buildTask(userInputBuilder);
        
        CommandParser commandParser = new CommandParser();
        Command command = commandParser.getCommand(userInputBuilder.toString());
        
        
        if (command == null) 
        {
            return TaskFeedBack.FEEDBACK_INVALID;
        }
        
        
        CommandExecutor commandExecutor = new CommandExecutor(dataHandler);
        return commandExecutor.execute(command, task);
    }   
}
