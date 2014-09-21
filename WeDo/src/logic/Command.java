/**
 * 
 */
package logic;

import java.util.ArrayList;

import dataStorage.DataHandler;
import definedEnumeration.TaskFeedBack;

public abstract class Command {
    /**
     * This method execute the commands such as add, display, clear etc.
     * @param dataHandler TODO
     * @param task TODO
     * @return TaskFeedBack to continue or exit
     */
    abstract TaskFeedBack execute(DataHandler dataHandler, Task task);
    abstract void undo();
    
}

/**
 * @author TienLong This class makes use of the Command interface to implement
 *         execute function for AddTask
 */
class AddCommand extends Command {
    public TaskFeedBack execute(DataHandler dataHandler, Task task) 
    {
        if(dataHandler.addTask(task))
        {
            dataHandler.addUndoCommandStack(this);
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
    void undo() {
        // TODO Auto-generated method stub
        
    }
}

/**
 * @author TienLong This class makes use of the Command interface to implement
 *         execute function for ClearTask
 */
class ClearCommand extends Command 
{
    public TaskFeedBack execute(DataHandler dataHandler, Task task) 
    {
        if(dataHandler.clearTask(task.getStarDate(), task.getEndDate()))
        {
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
    void undo() {
        // TODO Auto-generated method stub
        
    }
}


/**
 * @author TienLong This class makes use of the Command interface to implement
 *         execute function for DeleteTask
 */
class DeleteCommand extends Command {
    
    final int INVALID_INDEX = -1;
    
    public TaskFeedBack execute(DataHandler dataHandler, Task task) {
        final int ARRAY_OFFSET = -1;
        int lineToDelete = getLineIndex(task.getDescription()) + ARRAY_OFFSET;
        if (dataHandler.remove(lineToDelete)) 
        {
          return TaskFeedBack.FEEDBACK_VALID;
            
        } else 
        {
            return TaskFeedBack.FEEDBACK_INVALID;
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


    /* (non-Javadoc)
     * @see logic.Command#undo()
     */
    @Override
    void undo() {
        // TODO Auto-generated method stub
        
    }
}

/**
 * @author TienLong This class makes use of the Command interface to implement
 *         execute function for ExitTask
 */
class ExitCommand extends Command {
    public TaskFeedBack execute(DataHandler dataHandler, Task task) {
        return TaskFeedBack.FEEDBACK_EXIT;
    }

    /* (non-Javadoc)
     * @see logic.Command#undo()
     */
    @Override
    void undo() {
        // TODO Auto-generated method stub
        
    }
}


/**
 * @author TienLong This class makes use of the Command interface to implement
 *         execute function for searchTask
 */
class SearchCommand extends Command {
    public TaskFeedBack execute(DataHandler dataHandler, Task task) {
        SearchEngine searchEngine = new SearchEngine();
        ArrayList<Task> searchList  = searchEngine.searchCaseInsensitive(dataHandler, task.getDescription());
        if (searchList.isEmpty()) 
        {
            return TaskFeedBack.FEEDBACK_NOT_FOUND;
        } 
        else 
        {
            return TaskFeedBack.FEEDBACK_VALID;
        }
    }

    /* (non-Javadoc)
     * @see logic.Command#undo()
     */
    @Override
    void undo() {
        // TODO Auto-generated method stub
        
    }

}

/**
 * @author TienLong This class makes use of the Command interface to implement
 *         execute function for InvalidTask
 */
class InvalidCommand extends Command {
    public TaskFeedBack execute(DataHandler dataHandler, Task task) 
    {
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