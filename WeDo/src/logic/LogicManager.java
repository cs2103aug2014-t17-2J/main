/**
 * 
 */
package logic;

import logic.command.CommandExecutor;
import logic.command.commandList.Command;
import logic.parser.ParseResult;
import logic.parser.ParserManager;
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
    public ParseResult processCommand(String userInput) throws InvalidParseException {

        ParserManager parserManager = new ParserManager();
        return parserManager.interpret(userInput);
        
       
    }
    
    public void executeCommand(ParseResult parseResult) throws InvalidCommandException
    {
        commandExecutor.execute(parseResult.getCommand(), parseResult.getTask());
    }
}
