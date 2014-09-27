/**
 * 
 */
package logic;

import java.util.ArrayList;


import dataStorage.DataHandler;
import definedEnumeration.TaskFeedBack;

public abstract class Command {
    
    protected Task task;
    protected DataHandler dataHandler;
    
    protected void buildTask(String userInput, DataHandler dataHandler) throws InvalidCommandException
    {
        TaskParser taskParser = new TaskParser();
        this.task = taskParser.buildTask(userInput);
        this.dataHandler = dataHandler;
    }
    
    /**
     * This method execute the commands such as add, display, clear etc.
     * @return TaskFeedBack to continue or exit
     */
    
    abstract TaskFeedBack execute();
    abstract void undo();
    
}

/**
 * @author TienLong This class makes use of the Command interface to implement
 *         execute function for AddTask
 */
class AddCommand extends Command {

    /**
     * @param task
     * @param dataHandler
     */

    public TaskFeedBack execute() 
    {
        System.out.println("adding");
        if(dataHandler.addTask(task))
        {
            dataHandler.addUndo(this);
            return TaskFeedBack.FEEDBACK_VALID;
        }
        else
        {
            return TaskFeedBack.FEEDBACK_INVALID;
        }
    }

    /* (non-Javadoc)
     * @see logic.Command#undo()
     */
    @Override
    void undo() 
    {
        dataHandler.remove(task);
        
    }
}

/**
 * @author TienLong This class makes use of the Command interface to implement
 *         execute function for ClearTask
 */
class ClearCommand extends Command 
{
    ArrayList<Task> storedList = new ArrayList<Task>();
    


    /**
     * @param task
     * @param dataHandler
     */


    public TaskFeedBack execute() 
    {
        System.out.println("clear");
        
        storedList = dataHandler.getList(task.getStarDate(), task.getEndDate());
        
        if(dataHandler.clearTask(task.getStarDate(), task.getEndDate()))
        {
            dataHandler.addUndo(this);
            return TaskFeedBack.FEEDBACK_VALID;
        }
        else
        {
            return TaskFeedBack.FEEDBACK_INVALID;
        }
    }

    /* (non-Javadoc)
     * @see logic.Command#undo()
     */
    @Override
    void undo() 
    {
        if(!storedList.isEmpty())
        {
            for(Task task : storedList)
            {
                dataHandler.addTask(task);
            }
                
        }
    }
}


/**
 * @author TienLong This class makes use of the Command interface to implement
 *         execute function for DeleteTask
 */
class DeleteCommand extends Command {
    
  



    final int INVALID_INDEX = -1;
    
    public TaskFeedBack execute() {
        
        System.out.println("delete");

        final int ARRAY_OFFSET = -1;
        int lineToDelete = getLineIndex(task.getDescription()) + ARRAY_OFFSET;
        if (dataHandler.canRemove(lineToDelete)) 
        {
            task = dataHandler.getIndexedTask(lineToDelete);
            dataHandler.remove(lineToDelete);
            dataHandler.addUndo(this);
          return TaskFeedBack.FEEDBACK_VALID;
            
        } else 
        {
            return TaskFeedBack.FEEDBACK_INVALID;
        }
    }
    
    /* (non-Javadoc)
     * @see logic.Command#undo()
     */
    @Override
    void undo() 
    {
        dataHandler.addTask(task);
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
class ExitCommand extends Command {



    public TaskFeedBack execute() {
        System.out.println("exit");
        return TaskFeedBack.FEEDBACK_EXIT;
    }

    /* (non-Javadoc)
     * @see logic.Command#undo()
     * No undo for exit.
     */
    @Override
    void undo() 
    {
        
    }
}


/**
 * @author TienLong This class makes use of the Command interface to implement
 *         execute function for searchTask
 */
class SearchCommand extends Command 
{
    private ArrayList<Task> displayedTask;


    public TaskFeedBack execute() {
        
        System.out.println("searching");

        SearchEngine searchEngine = new SearchEngine();
        ArrayList<Task> searchList  = searchEngine.searchCaseInsensitive(dataHandler, task.getDescription());
        if (searchList.isEmpty()) 
        {
            return TaskFeedBack.FEEDBACK_NOT_FOUND;
        } 
        else 
        {
            displayedTask = dataHandler.getDisplayedTask();
            return TaskFeedBack.FEEDBACK_VALID;
        }
    }

    /* (non-Javadoc)
     * @see logic.Command#undo()
     * This function undo by displaying the previous list.
     */
    @Override
    void undo() 
    {
        dataHandler.replaceList(displayedTask);
    }

}

/**
 * @author TienLong This class makes use of the Command interface to implement
 *         execute function for InvalidTask
 */
class InvalidCommand extends Command {



    public TaskFeedBack execute() 
    {
        System.out.println("Invalid");

        return TaskFeedBack.FEEDBACK_INVALID;
    }

    /* (non-Javadoc)
     * @see logic.Command#undo()
     */
    @Override
    void undo() {
        // TODO Auto-generated method stub
        
    }
}