package logic;

import dataStorage.DataHandler;
import definedEnumeration.TaskFeedBack;

/**
 * @author TienLong This class handle all the commands passed in by the user
 * 
 */
public class CommandHandler {

    private DataHandler dataHandler;

    /**
     * Constructor for CommandHandler
     * 
     * @param dataHandler
     *            the handler which contains of all the data
     */
    public CommandHandler(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }

    /**
     * Execute Command based on userInput
     * 
     * @param userInput
     *            the input to execute command
     * @return TaskFeedBack to continue or exit textBuddy
     */
    public TaskFeedBack executeCommand(String userInput) {

        Command command = getCommand(userInput);
        
        if (command.isValid()) {
            return TaskFeedBack.FEEDBACK_INVALID;
        }
        
        return command.execute();
    }

    /**
     * @param userInput
     * @return
     */
    private Command getCommand(String userInput) {
        final String ADD_COMMAND = "add";
        final String CLEAR_COMMAND = "clear";
        final String EXIT_COMMAND = "exit";
        final String DELETE_COMMAND = "delete";
        final String SEARCH_COMMAND = "search";

        switch (userInput.toLowerCase()) {
        case ADD_COMMAND:
            return new AddCommand(userInput, dataHandler);
        case CLEAR_COMMAND:
            return new ClearCommand(userInput, dataHandler);
        case DELETE_COMMAND:
            return new DeleteCommand(userInput, dataHandler);
        case SEARCH_COMMAND:
            return new SearchCommand(userInput, dataHandler);
        case EXIT_COMMAND:
            return new ExitCommand(userInput, dataHandler);
        default:
            return new InvalidCommand(userInput, dataHandler);
        }

    }
}