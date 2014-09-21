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
     * @param dataHandler the handler which contains of all the data
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
    public TaskFeedBack executeCommand(String userInput) 
    {
        TaskParser taskParser = new TaskParser();
        Task task = taskParser.buildTask(userInput);
        
        if(!task.isValid())
        {
            return TaskFeedBack.FEEDBACK_INVALID;
        }
        
        Command command = getCommand(userInput);        
        TaskFeedBack taskFeedBack = command.execute(dataHandler, task);

        if(task.isValid())
        {
            dataHandler.addUndoCommandStack(command);
        }
        return taskFeedBack;
    }
    
    /**
     * @param userInput
     * @return
     */
    private Command getCommand(String userInput) 
    {
        final String ADD_COMMAND = "add";
        final String CLEAR_COMMAND = "clear";
        final String EXIT_COMMAND = "exit";
        final String DELETE_COMMAND = "delete";
        final String SEARCH_COMMAND = "search";

        switch (userInput.toLowerCase()) {
        case ADD_COMMAND:
            return new AddCommand();
        case CLEAR_COMMAND:
            return new ClearCommand();
        case DELETE_COMMAND:
            return new DeleteCommand();
        case SEARCH_COMMAND:
            return new SearchCommand();
        case EXIT_COMMAND:
            return new ExitCommand();
        default:
            return new InvalidCommand();
        }

    }
}