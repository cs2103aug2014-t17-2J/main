/**
 * 
 */
package logic.parser;

import java.util.EnumSet;

import logic.command.commandList.Command;
import logic.utility.Task;

/**
 * @author A0112887X
 *
 */
public class DynamicParseResult 
{
    private Command command;
    private Task task;
    private EnumSet<ParserFlags> parseFlags;
    
    private String dateWordUsed;
    private String priorityWordUsed;
    private String commandWordUsed;
    private String descriptionWordUsed;
    


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
     * @return the dateWordUsed
     */
    public String getDateWordUsed() {
        return dateWordUsed;
    }


    /**
     * @param dateWordUsed the dateWordUsed to set
     */
    public void setDateWordUsed(String dateWordUsed) {
        this.dateWordUsed = dateWordUsed;
    }


    /**
     * @return the priorityWordUsed
     */
    public String getPriorityWordUsed() {
        return priorityWordUsed;
    }


    /**
     * @param priorityWordUsed the priorityWordUsed to set
     */
    public void setPriorityWordUsed(String priorityWordUsed) {
        this.priorityWordUsed = priorityWordUsed;
    }


    /**
     * @return the commandWordUsed
     */
    public String getCommandWordUsed() {
        return commandWordUsed;
    }


    /**
     * @param commandWordUsed the commandWordUsed to set
     */
    public void setCommandWordUsed(String commandWordUsed) {
        this.commandWordUsed = commandWordUsed;
    }


    /**
     * @return the descriptionWordUsed
     */
    public String getDescriptionWordUsed() {
        return descriptionWordUsed;
    }


    /**
     * @param descriptionWordUsed the descriptionWordUsed to set
     */
    public void setDescriptionWordUsed(String descriptionWordUsed) {
        this.descriptionWordUsed = descriptionWordUsed;
    }


    /**
     * @return the parseFlags
     */
    public EnumSet<ParserFlags> getParseFlags() {
        return parseFlags;
    }


    /**
     * @param parseFlags the parseFlags to set
     */
    public void setParseFlags(EnumSet<ParserFlags> parseFlags) {
        this.parseFlags = parseFlags;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "DynamicParseResult [command=" + command + ", task=" + task
                + ", parseFlags=" + parseFlags + ", dateWordUsed="
                + dateWordUsed + ", priorityWordUsed=" + priorityWordUsed
                + ", commandWordUsed=" + commandWordUsed
                + ", descriptionWordUsed=" + descriptionWordUsed + "]";
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
        DynamicParseResult other = (DynamicParseResult) obj;
        if (command == null) {
            if (other.command != null)
                return false;
        } else if (!command.equals(other.command))
            return false;
        if (commandWordUsed == null) {
            if (other.commandWordUsed != null)
                return false;
        } else if (!commandWordUsed.equals(other.commandWordUsed))
            return false;
        if (dateWordUsed == null) {
            if (other.dateWordUsed != null)
                return false;
        } else if (!dateWordUsed.equals(other.dateWordUsed))
            return false;
        if (descriptionWordUsed == null) {
            if (other.descriptionWordUsed != null)
                return false;
        } else if (!descriptionWordUsed.equals(other.descriptionWordUsed))
            return false;
        if (parseFlags == null) {
            if (other.parseFlags != null)
                return false;
        } else if (!parseFlags.equals(other.parseFlags))
            return false;
        if (priorityWordUsed == null) {
            if (other.priorityWordUsed != null)
                return false;
        } else if (!priorityWordUsed.equals(other.priorityWordUsed))
            return false;
        if (task == null) {
            if (other.task != null)
                return false;
        } else if (!task.equals(other.task))
            return false;
        return true;
    }

    

    
}
