/**
 * 
 */
package logic.parser;

import logic.command.commandList.Command;
import logic.utility.Task;

/**
 * @author Kuan Tien Long
 *
 */

public class ParseResult 
{
    private Task task;
    private Command command;
    private boolean isSuccessful;
    private String failedMessage;
    
    /**
     * 
     */
    public ParseResult() {
    }
    /**
     * @param task
     * @param command
     */
    public ParseResult( Command command, Task task) {
        this.command = command;
        this.task = task;
    }

    
    /**
     * @return the task
     */
    public Task getTask() {
        return task;
    }
    /**
     * @param task the task to set
     */
    public void setTask(Task task) {
        this.task = task;
    }
    /**
     * @return the command
     */
    public Command getCommand() {
        return command;
    }
    /**
     * @param command the command to set
     */
    public void setCommand(Command command) {
        this.command = command;
    }
    
    
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ParseResult other = (ParseResult) obj;
        if (command == null) {
            if (other.command != null)
                return false;
        } else if (!command.equals(other.command))
            return false;
        if (task == null) {
            if (other.task != null)
                return false;
        } else if (!task.equals(other.task))
            return false;
        return true;
    }
    /**
     * @return the isSuccessful
     */
    public boolean isSuccessful() {
        return isSuccessful;
    }
    /**
     * @param isSuccessful the isSuccessful to set
     */
    public void setSuccessful(boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }
    /**
     * @return the failedMessage
     */
    public String getFailedMessage() {
        return failedMessage;
    }
    /**
     * @param failedMessage the failedMessage to set
     */
    public void setFailedMessage(String failedMessage) {
        this.failedMessage = failedMessage;
    }
    
   
}
