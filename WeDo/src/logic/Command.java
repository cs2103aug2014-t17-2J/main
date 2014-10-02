/**
 * 
 */
package logic;

import java.time.LocalDate;
import java.util.ArrayList;

import brain.Processor;
import definedEnumeration.TaskFeedBack;

public abstract class Command {
    
    protected Task task;
    protected Processor processor;
    
    protected void buildTask(StringBuilder userInput) 
    {
        TaskParserBasic taskParser = new TaskParserBasic();
        this.task = taskParser.buildTask(userInput);
    }
    
    protected void setTask(Task task) 
    {
        this.task = task;
    }
    
    public void setProcessor(Processor processor)
    {
        this.processor = processor;
    }
    
    /**
     * This method execute the commands such as add, display, clear etc.
     * @return TaskFeedBack to display if the command is valid
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
     * @param processor
     */

    public TaskFeedBack execute() 
    {
        System.out.println("adding");
        if(processor.addTask(task))
        {
            processor.addUndoCommand(this);
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
        processor.removeTask(task);
        
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
     * @param processor
     */


    public TaskFeedBack execute() 
    {
        System.out.println("clear");
        
        storedList = processor.getDisplayedTasks(task.getStarDate(), task.getEndDate());
        
        if(processor.clearTask(task.getStarDate(), task.getEndDate()))
        {
            processor.addUndoCommand(this);
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
                processor.addTask(task);
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
        if( processor.removeTask(lineToDelete))
        {
            processor.addUndoCommand(this);
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
        processor.addTask(task);
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
        ArrayList<Task> searchList  = searchEngine.searchCaseInsensitive(processor.getMainList(), task.getDescription());
        if (searchList.isEmpty()) 
        {
            return TaskFeedBack.FEEDBACK_NOT_FOUND;
        } 
        else 
        {
            displayedTask = processor.getDisplayedTasks(task.getStarDate(), task.getEndDate());
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
        processor.setDisplayedTasks(displayedTask);
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