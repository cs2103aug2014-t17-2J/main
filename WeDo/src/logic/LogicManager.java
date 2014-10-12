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

    public void processUserInput(String userInput) throws InvalidCommandException {

        StringBuilder userInputBuilder = new StringBuilder(userInput);
        
        TaskParserPlus taskParser = new TaskParserPlus();
        Task task = taskParser.buildTask(userInputBuilder);
        
        CommandParser commandParser = new CommandParser();
        Command command = commandParser.getCommand(userInputBuilder.toString());
        
        
        if (command == null) 
        {
            throw new InvalidCommandException("Command given was invalid");
        }
        
        
        CommandExecutor commandExecutor = new CommandExecutor(dataHandler);
        commandExecutor.execute(command, task);
    }   
}
