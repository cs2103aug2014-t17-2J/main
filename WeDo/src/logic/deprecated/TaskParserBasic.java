/**
 * 
 */
package logic.deprecated;

import logic.deprecated.taskFieldSetter.TaskDescriptionFieldSetter;
import logic.deprecated.taskFieldSetter.TaskFieldSetter;
import logic.utility.MultiMapMatcher;
import logic.utility.StringHandler;
import logic.utility.Task;

//@author A0112887X - unused
/**
 * @author Kuan Tien Long
 *
 */
public class TaskParserBasic implements TaskParser {
    
    private final String actionsDelimiter = "-";
  
    
    public Task buildTask(StringBuilder userInputBuilder)
    {
        String userInput = userInputBuilder.toString();
        String[] actionsTokens = StringHandler.splitString(userInput,
                actionsDelimiter);
        Task task = createTask(actionsTokens);
        

        return task;

    }
    
   
    /**
     * @param commandTokens
     * @return
     * @throws InvalidCommandException 
     */
    private Task createTask(String[] actionTokens) 
    {
        Task task = new Task();
        boolean initial = true;
        
        for(String token : actionTokens )
        {
            if (initial == true)
            {
                setDescription(token, task);
                initial = false;
            }
            else
            {
                setTaskAttribute(token, task);
            }
        }
        
        return task;
    }

    /**
     * @param token
     * @param task
     */
    private void setDescription(String token, Task task) 
    {
        String operation = StringHandler.getFirstWord(token);
        String arguments = StringHandler.removeFirstMatched(token, operation);
        TaskFieldSetter taskAttribute = new TaskDescriptionFieldSetter();
        taskAttribute.set(task, arguments);
    }

    /**
     * @param tokens
     */
    private void setTaskAttribute(String token, Task task) 
    {
        String operation = StringHandler.getFirstWord(token);
        String arguments = StringHandler.removeFirstMatched(token, operation);
        TaskFieldSetter taskAttribute = determineAttribute(operation);
        if(taskAttribute == null)
        {
            return;
        }
        taskAttribute.set(task, arguments);
        
        System.out.println("Opeartion :" + operation + "  arguments : " + arguments);
    }

    /**
     * @param operation
     * @return 
     */
    private TaskFieldSetter determineAttribute(String operation)
    {
        return MultiMapMatcher.getMatchedKey(createFakeMultiMapForPriority(), operation);   

    }
    

}