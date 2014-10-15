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
    
    public static final LocalTime TIME_NOT_SET = LocalTime.MAX;
    public static final LocalDate DATE_NOT_SET = LocalDate.MAX;
   // public static final Priority PRIORITY_NOT_SET = Priority.PRIORITY_UNDEFINED;
    protected static final boolean DEFAULT_COMPLETE_STATUS = false;
    public static final Priority PRIORITY_NOT_SET = Priority.PRIORITY_UNDEFINED;
    
    private static int uniqueID = 1;
    
    private Priority priority;
    private String description;
    private boolean isCompleted = DEFAULT_COMPLETE_STATUS;
    
    
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

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "AbstractTask [priority=" + priority + ", description="
                + description + ", isCompleted=" + isCompleted + "]";
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
        AbstractTask other = (AbstractTask) obj;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (isCompleted != other.isCompleted)
            return false;
        if (priority != other.priority)
            return false;
        return true;
    }
    
}
