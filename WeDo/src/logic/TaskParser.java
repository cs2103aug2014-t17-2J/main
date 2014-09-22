/**
 * 
 */
package logic;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @author Kuan Tien Long
 *
 */
public class TaskParser {
    private final String operationsDelimiter = "-";

    public Task buildTask(String userInput) {
        String[] operationTokens = StringHandler.splitString(userInput,
                operationsDelimiter);
        
        Task task = getTask(operationTokens);
        System.out.println(Arrays.toString(operationTokens));

        return task;

    }

    /**
     * @param commandTokens
     * @return
     */
    private Task getTask(String[] operationTokens) 
    {
        Task task = new Task();

        enableSearching();
        
        for(String token : operationTokens )
        {
            setTaskAttribute(token, task);
        }
        
        return task;
    }

    /**
     * @param tokens
     */
    private void setTaskAttribute(String token, Task task) 
    {
        String operation = StringHandler.getFirstWord(token);
        String arguments = StringHandler.removeFirstMatched(token, operation);
        TaskAttribute taskAttribute = determineAttribute(operation);
        taskAttribute.set(task, arguments);
        
        System.out.println("Opeartion :" + operation + "  arguments : " + arguments); 
    }

    /**
     * @param operation
     * @return 
     */
    private TaskAttribute determineAttribute(String operation) 
    {
        return null;
        // TODO Auto-generated method stub
        
    }
    
    
    private boolean canSearchCommand;
    private boolean canSearchDate;
    private boolean canSearchPriority;
    
    
    private void enableSearching()
    {
        canSearchCommand = true;
        canSearchDate = true;
        canSearchPriority = true;
    }
    
    private boolean isCommandOpeartion()
    {
        if(!canSearchCommand)
        {
            return false;
        }
        else
        {
            disableRepeatedSearch(canSearchCommand);
            return true;
        }
    }

    private void disableRepeatedSearch(boolean searchOpeartion) 
    {
        searchOpeartion = false;
    }
    
    private boolean isDateOpeartion()
    {
        if(!canSearchDate)
        {
            return false;
        }
        else
        {
            disableRepeatedSearch(canSearchDate);
            return true;
        }
    }

    private boolean isPriorityOperation()
    {
        if(!canSearchPriority)
        {
            return false;
        }
        else
        {
            disableRepeatedSearch(canSearchPriority);
            return true;
        }
    }
    
    
    private boolean isInvalidOpeartion()
    {
        return true;
    }
}