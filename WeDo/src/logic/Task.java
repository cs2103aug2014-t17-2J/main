/**
 * 
 */
package logic;

import java.time.LocalDate;
import java.time.LocalTime;
import definedEnumeration.Priority;

/**
 * @author Kuan Tien Long
 *
 */
public class Task 
{
    private static int ID = 1;
    private Priority priority;
    private LocalDate startDate = LocalDate.MAX;
    private LocalDate endDate = LocalDate.MAX;
    private LocalTime startTime = LocalTime.MAX;
    private LocalTime endTime = LocalTime.MAX;
    private String description;
    private boolean isCompleted;
   
    @Override
    public boolean equals(Object other)
    {
        if(other == null)
            return false;
        if (!(other instanceof Task))
            return false;
        Task otherTask = (Task)other;
        if (otherTask.startDate.equals(this.startDate) && otherTask.endDate.equals(this.endDate)
                && otherTask.startTime.equals(this.startTime) && otherTask.endTime.equals(this.endTime)
                && otherTask.description.equals(this.description) && otherTask.isCompleted == this.isCompleted
                && otherTask.priority == this.priority)
        {
            return true;
        }
        else
            return false;
    }
    
    public Task()
    {
        ID++;
    }
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
    public LocalDate getStarDate() {
        return startDate;
    }
    /**
     * @param start the start to set
     */
    public void setStartDate(LocalDate start) {
        this.startDate = start;
    }
    /**
     * @return the end
     */
    public LocalDate getEndDate() {
        return endDate;
    }
    /**
     * @param end the end to set
     */
    public void setEndDate(LocalDate end) {
        this.endDate = end;
    }
    /**
     * @return the valid
     */
    public boolean isValid() {
        return isCompleted;
    }
    /**
     * @param valid the valid to set
     */
    public void setValid(boolean valid) {
        this.isCompleted = valid;
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
     * @return the startTime
     */
    public LocalTime getStartTime() {
        return startTime;
    }
    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }
    /**
     * @return the endTime
     */
    public LocalTime getEndTime() {
        return endTime;
    }
    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
    /**
     * @return the testPriority
     */


    
    
    
}
