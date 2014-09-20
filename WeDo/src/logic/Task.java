/**
 * 
 */
package logic;

import java.util.Calendar;

import definedEnumeration.Priority;

/**
 * @author Kuan Tien Long
 *
 */
public class Task 
{
    private int ID;
    private Priority priority;
    private Calendar startDate;
    private Calendar endDate;
    private String description;
    private boolean valid;
    /**
     * @return the iD
     */
    public int getID() {
        return ID;
    }
    /**
     * @param iD the iD to set
     */
    public void setID(int iD) {
        ID = iD;
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
     * @return the start
     */
    public Calendar getStarDatet() {
        return startDate;
    }
    /**
     * @param start the start to set
     */
    public void setStartDate(Calendar start) {
        this.startDate = start;
    }
    /**
     * @return the end
     */
    public Calendar getEndDate() {
        return endDate;
    }
    /**
     * @param end the end to set
     */
    public void setEndDate(Calendar end) {
        this.endDate = end;
    }
    /**
     * @return the valid
     */
    public boolean isValid() {
        return valid;
    }
    /**
     * @param valid the valid to set
     */
    public void setValid(boolean valid) {
        this.valid = valid;
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
    
    
    
}
