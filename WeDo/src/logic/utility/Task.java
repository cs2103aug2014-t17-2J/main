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
public class Task 
{
    public static final LocalTime TIME_NOT_SET = LocalTime.MAX;
    public static final LocalDate DATE_NOT_SET = LocalDate.MAX;
    public static final Priority PRIORITY_NOT_SET = Priority.PRIORITY_UNDEFINED;
    public static final boolean DEFAULT_COMPLETE_STATUS = false;
    
    private static final int INITIAL_ID = 0;
    
    
    private static int uniqueID = INITIAL_ID;
    private String description;
    private Priority priority;
    private LocalDate startDate;
    private LocalTime startTime;
    private LocalDate endDate;
    private LocalTime endTime;
    private boolean isCompleted;
    
    // blank task constructor
    public Task()
    {
        uniqueID++;
    }
    

    /**
     * Constructor for <b>timed</b> task without completed status
     * 
     * @param description
     * @param priority
     * @param startDate
     * @param startTime
     * @param endDate
     * @param endTime
     */
    public Task(String description, Priority priority, LocalDate startDate,
            LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        this.description = description;
        this.priority = priority;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        isCompleted =DEFAULT_COMPLETE_STATUS;
    }
    

    /**
     * Constructor for <b>timed</b> task with completed status 
     * 
     * @param description
     * @param priority
     * @param startDate
     * @param startTime
     * @param endDate
     * @param endTime
     * @param isCompleted
     */
    public Task(String description, Priority priority, LocalDate startDate,
            LocalTime startTime, LocalDate endDate, LocalTime endTime,
            boolean isCompleted) {
        this.description = description;
        this.priority = priority;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.isCompleted = isCompleted;
    }
    
    
   
    /**
     * Constructor for <b>deadline</b> task without completed status
     * @param description
     * @param priority
     * @param endDate
     * @param endTime
     */
    public Task(String description, Priority priority, LocalDate endDate,
            LocalTime endTime) {
        this.description = description;
        this.priority = priority;
        this.endDate = endDate;
        this.endTime = endTime;
        this.startDate = DATE_NOT_SET;
        this.startTime = TIME_NOT_SET;
        this.isCompleted = DEFAULT_COMPLETE_STATUS;
    }
    
    
    /**
     * Constructor for <b>deadline</b> task with completed status
     * @param description
     * @param priority
     * @param endDate
     * @param endTime
     * @param isCompleted
     */
    public Task(String description, Priority priority, LocalDate endDate,
            LocalTime endTime, boolean isCompleted) {
        super();
        this.description = description;
        this.priority = priority;
        this.endDate = endDate;
        this.endTime = endTime;
        this.isCompleted = isCompleted;
        this.startDate = DATE_NOT_SET;
        this.startTime = TIME_NOT_SET;
    }




    /**
     * Constructor for <b>floating</b> task without completed status
     * @param description
     * @param priority
     */
    public Task(String description, Priority priority) {
        super();
        this.description = description;
        this.priority = priority;

        this.startDate = this.endDate = DATE_NOT_SET;
        this.startTime = this.endTime = TIME_NOT_SET;
        this.isCompleted = DEFAULT_COMPLETE_STATUS;
    }


    /**
     * Constructor for <b>deadline</b> task with completed status
     * @param description
     * @param priority
     * @param isCompleted
     */
    public Task(String description, Priority priority, boolean isCompleted) {
        super();
        this.description = description;
        this.priority = priority;
        this.isCompleted = isCompleted;

        this.startDate = this.endDate = DATE_NOT_SET;
        this.startTime = this.endTime = TIME_NOT_SET;
    }














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
    
    @Override
    public String toString()
    {
        return "description:[" + description + "],startDate:[" + startDate +","+ startTime + "],endDate:[" + endDate +","+ endTime + "],Priority:[" + priority +"]" ;
        
    }
    

    /**
     * @return the iD
     */
    public int getID() {
        return uniqueID;
    }
    /**
     * @param iD the iD to set
     */
    public void setID(int iD) {
        uniqueID = iD;
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
    public boolean isCompleted() {
        return isCompleted;
    }
    /**
     * @param valid the valid to set
     */
    public void setCompleted(boolean valid) {
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
