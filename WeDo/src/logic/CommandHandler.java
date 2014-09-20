package logic;

import java.util.ArrayList;

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
            return TaskFeedBack.TASK_INVALID;
        }
        
        Command command = getCommand(userInput);        
        TaskFeedBack taskFeedBack = command.execute(task);

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
    
    /**
     * @author TienLong This class makes use of the Command interface to implement
     *         execute function for AddTask
     */
    class AddCommand implements Command {
        public TaskFeedBack execute(Task task) 
        {
            if(dataHandler.addTask(task))
            {
                dataHandler.addUndoCommandStack(this);
                return TaskFeedBack.TASK_VALID;
            }
            else
            {
                return TaskFeedBack.TASK_INVALID;
            }
        }
    }

    /**
     * @author TienLong This class makes use of the Command interface to implement
     *         execute function for ClearTask
     */
    class ClearCommand implements Command 
    {
        public TaskFeedBack execute(Task task) 
        {
            if(dataHandler.clearTask(task.getStarDatet(), task.getEndDate()))
            {
                return TaskFeedBack.TASK_VALID;
            }
            else
            {
                return TaskFeedBack.TASK_INVALID;
            }
        }
    }


    /**
     * @author TienLong This class makes use of the Command interface to implement
     *         execute function for DeleteTask
     */
    class DeleteCommand implements Command {
        
        final int INVALID_INDEX = -1;
        
        public TaskFeedBack execute(Task task) {
            final int ARRAY_OFFSET = -1;
            int lineToDelete = getLineIndex(task.getDescription()) + ARRAY_OFFSET;
            if (dataHandler.remove(lineToDelete)) 
            {
              return TaskFeedBack.TASK_VALID;
                
            } else 
            {
                return TaskFeedBack.TASK_INVALID;
            }
        }


        /**
         * @param userArguments the arguments passed in by the user
         * @return Integer which specified the line to be deleted
         */
        private int getLineIndex(String userArguments) {
            
      
            if(isValidString(userArguments))
            {
                userArguments = userArguments.trim();
                if(isInteger(userArguments))
                {
                    return Integer.parseInt(userArguments);
                }
            }
            return INVALID_INDEX;
        }
        
        /**
         * @param userInput the user input
         * @return whether the string could be parsed
         */
        private boolean isInteger(String userInput) {
            
            try
            {
                Integer.parseInt(userInput);
                return true;
            }
            catch(Exception e)
            {
                return false;
            }
        }

        /**
         * @param addedInput the input to be added
         * @return whether it is a valid string
         */
        private boolean isValidString(String addedInput) {
            return addedInput.length() > 0;
        }
    }

    /**
     * @author TienLong This class makes use of the Command interface to implement
     *         execute function for ExitTask
     */
    class ExitCommand implements Command {
        public TaskFeedBack execute(Task task) {
            return TaskFeedBack.EXIT;
        }
    }


    /**
     * @author TienLong This class makes use of the Command interface to implement
     *         execute function for searchTask
     */
    class SearchCommand implements Command {
        public TaskFeedBack execute(Task task) {
            SearchEngine searchEngine = new SearchEngine();
            ArrayList<Task> searchList  = searchEngine.searchCaseInsensitive(dataHandler, task.getDescription());
            if (searchList.isEmpty()) 
            {
                return TaskFeedBack.TASK_NOTFOUND;
            } 
            else 
            {
                return TaskFeedBack.TASK_VALID;
            }
        }

    }

    /**
     * @author TienLong This class makes use of the Command interface to implement
     *         execute function for InvalidTask
     */
    class InvalidCommand implements Command {
        public TaskFeedBack execute(Task task) 
        {
            return TaskFeedBack.TASK_INVALID;
        }
    }

}