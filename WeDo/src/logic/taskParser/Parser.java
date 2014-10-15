/**
 * 
 */
package logic.taskParser;

import logic.command.commandList.Command;
import logic.utility.Task;

/**
 * @author Kuan Tien Long
 *
 */
public class Parser
{
    private Task task;
    private Command command;
    
    public boolean interpret(String userInput)
    {
        
        return true;
    }  

    
    public Task buildTask(String userInput) 
    {
        
        System.out.println("to date parser " + userInput);
        DateParser dateParser = new DateParser();
        if(dateParser.parseDate(userInput))
        {
            userInput = dateParser.getWordRemaining();
        }
        
        System.out.println("to priority parser is " + userInput);

        PriorityParser priorityParser = new PriorityParser();
        if(priorityParser.parsePriority(userInput))
        {
            userInput = priorityParser.getWordRemaining();
        }
        
        System.out.println("to description parser is " + userInput);
        
        DescriptionParser descriptionParser = new DescriptionParser();
        if(descriptionParser.parseDescription(userInput))
        {
            userInput = descriptionParser.getWordRemaining();
        }
        
        System.out.println("to command parser is " + userInput);

        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @return the task
     */
    public Task getTask() {
        return task;
    }

    /**
     * @return the command
     */
    public Command getCommand() {
        return command;
    }



}
