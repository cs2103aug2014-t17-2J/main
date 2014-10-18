/**
 * 
 */
package logic;

import logic.command.CommandExecutor;
import logic.command.CommandParser;
import logic.command.UndoHandler;
import logic.command.commandList.Command;
import logic.taskParser.ParserManager;
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
    private CommandExecutor commandExecutor;
    

    /**
     * Constructor for CommandHandler
     * 
     * @param dataHandler
     *            the handler which contains of all the data
     */
    public LogicManager(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
        this.commandExecutor = new CommandExecutor(this.dataHandler);
    } 

    /**
     * @param userInput the input that the user entered which will be decipher into task and command
     * @throws InvalidCommandException      
     */
    public void processCommand(String userInput) throws InvalidCommandException {

        final String INVALID_COMMAND = "The command given was invalid";
        StringBuilder userInputBuilder = new StringBuilder(userInput);

        ParserManager parserManager = new ParserManager();
        if(parserManager.interpret(userInput))
        {
            Task task = parserManager.getTask();
            Command command = parserManager.getCommand();
            commandExecutor.execute(command, task);
        }
        
//        TaskParserPlus taskParser = new TaskParserPlus();
//        CommandParser commandParser = new CommandParser();
//
//        Task task = taskParser.buildTask(userInputBuilder);
//
//        Command command = commandParser.getCommand(userInputBuilder.toString());
//
//        if (command == null) {
//            throw new InvalidCommandException(INVALID_COMMAND);
//        }
//
//        commandExecutor.execute(command, task);
    }
}
