/**
 * 
 */
package logic.utility;

import java.time.LocalDate;
import java.time.LocalTime;

import definedEnumeration.Priority;

/**
 * @author Kuan Tien Long
 *
 */
public class AbstractTask {
    
    protected static final LocalTime TIME_NOT_SET = LocalTime.MAX;
    protected static final LocalDate DATE_NOT_SET = LocalDate.MAX;
    protected static final boolean DEFAULT_COMPLETE_STATUS = false;
    
    private static int uniqueID = 1;
    
    private Priority priority;
    private String description;
    private boolean isCompleted = false;
    
    
    public AbstractTask(Priority priority, String description, boolean isComplete)
    {
        uniqueID++;
        this.priority = priority;
        this.description = description;
        this.isCompleted = isComplete;
    }

    public AbstractTask(Priority priority, String description)
    {
        uniqueID++;
        this.priority = priority;
        this.description = description;
    }
    
    
    /**
     * @return the uniqueID
     */
    public static int getUniqueID() {
        return uniqueID;
    }
    /**
     * @param uniqueID the uniqueID to set
     */
    public static void setUniqueID(int uniqueID) {
        AbstractTask.uniqueID = uniqueID;
    }
    /**
     * @return the priority
     */
    public Priority getPriority() {
        return priority;
    }
    /**
     * @param priority the priority to set
     */
    public void setPriority(Priority priority) {
        this.priority = priority;
    }
    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * @return the isCompleted
     */
    public boolean isCompleted() {
        return isCompleted;
    }
    /**
     * @param isCompleted the isCompleted to set
     */
    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
    
}
