/**
 * 
 */
package logic;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Multimap;

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
        TaskAttribute taskAttribute = new TaskDescriptionAttribute();
        taskAttribute.set(task, arguments);
    }

    /**
     * @param tokens
     */
    private void setTaskAttribute(String token, Task task) 
    {
        String operation = StringHandler.getFirstWord(token);
        String arguments = StringHandler.removeFirstMatched(token, operation);
        TaskAttribute taskAttribute = determineAttribute(operation);
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
    private TaskAttribute determineAttribute(String operation)
    {
        return KeyMatcher.matchKey(createFakeMultiMapNow(), operation);   

    }
    

}