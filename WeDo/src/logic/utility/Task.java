/**
 * 
 */
package logic.utility;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
    
    
    private static int taskCreateID = INITIAL_ID;
    private int uniqueID = taskCreateID;
    private String description;
    private Priority priority;
    private LocalDate startDate;
    private LocalTime startTime;
    private LocalDate endDate;
    private LocalTime endTime;
    private boolean isCompleted;
   
    
    /**
     * Copy Constructor
     * @param task to be copied
     */
    public Task(Task task)
    {
        this(task.description, task.priority, task.startDate, task.startTime, task.endDate, task.endTime, task.isCompleted);
    }
    
    // blank task constructor
    public Task()
    {
        taskCreateID++;
   
        this.priority = PRIORITY_NOT_SET;
        this.startDate = this.endDate = DATE_NOT_SET;
        this.startTime = this.endTime = TIME_NOT_SET;
        this.isCompleted = DEFAULT_COMPLETE_STATUS;
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
       
        taskCreateID++;
        
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
        
        taskCreateID++;
        
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

        taskCreateID++;
        
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

        taskCreateID++;
        
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

        taskCreateID++;
        
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

        taskCreateID++;
        
        this.description = description;
        this.priority = priority;
        this.isCompleted = isCompleted;

        this.startDate = this.endDate = DATE_NOT_SET;
        this.startTime = this.endTime = TIME_NOT_SET;
    }



    public String getDateTimeString()
    {
        final String EMPTY_STRING = "";
        final String TIME_CONNECTOR = " at ";
        final String DATE_CONNECTOR = " to ";
        
        String dateString = EMPTY_STRING;
        
        DateTimeFormatter dateFormatter = DateTimeFormatter
                .ofPattern("dd/MM/YYYY");
        DateTimeFormatter timeFormatter = DateTimeFormatter
                .ofPattern("hh:mm a");
        
        if(startDate.equals(DATE_NOT_SET))
        {
            if(endDate.equals(DATE_NOT_SET))
            {
                return dateString;
            }
            else
            {
                dateString += endDate.format(dateFormatter);
                if(!endTime.equals(TIME_NOT_SET))
                {
                    dateString +=  TIME_CONNECTOR + endTime.format(timeFormatter);
                }
                return dateString;
            }
        }
        else
        {
            dateString += startDate.format(dateFormatter);
        }
        
        if(!startTime.equals(TIME_NOT_SET))
        {
            dateString +=  TIME_CONNECTOR + startTime.format(timeFormatter);
        }
        
        if(endDate.equals(DATE_NOT_SET))
        {
            return dateString;
        }
        else
        {
            dateString += DATE_CONNECTOR + endDate.format(dateFormatter);
        }
        
        if(!endTime.equals(TIME_NOT_SET))
        {
            dateString +=  TIME_CONNECTOR + endTime.format(timeFormatter);
        }
        
        return dateString;
        
    }









//    @Override
//    public boolean equals(Object other)
//    {
//        if(other == null)
//            return false;
//        if (!(other instanceof Task))
//            return false;
//        Task otherTask = (Task)other;
//        if (otherTask.startDate.equals(this.startDate) && otherTask.endDate.equals(this.endDate)
//                && otherTask.startTime.equals(this.startTime) && otherTask.endTime.equals(this.endTime)
//                && otherTask.description.equals(this.description) && otherTask.isCompleted == this.isCompleted
//                && otherTask.priority == this.priority)
//        {
//            return true;
//        }
//        else
//            return false;
//    }
    
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
        if(priority == null)
        {
            return PRIORITY_NOT_SET;
        }
        else
        {
            return priority;
        }    
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
    public LocalDate getStartDate() {
        if(startDate == null)
        {
            return DATE_NOT_SET;
        }
        else
        {
            return startDate;
        }   
        
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
        if(endDate == null)
        {
            return DATE_NOT_SET;
        }
        else
        {
            return endDate;
        }   
        
    }
    /**
     * @param end the end to set
     */
    public void setEndDate(LocalDate end) {
        this.endDate = end;
    }
    /**
     * @return the isCompleted
     */
    public boolean getCompleted() {
            return isCompleted;

    }
    /**
     * @param isCompleted the isCompleted to set
     */
    public void setCompleted(boolean isComplete) {
        this.isCompleted = isComplete;
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
        if(startTime == null)
        {
            return TIME_NOT_SET;
        }
        else
        {
            return startTime;
        }
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
        if(endTime == null)
        {
            return TIME_NOT_SET;
        }
        else
        {
            return endTime;
        }
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
        Task other = (Task) obj;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (endDate == null) {
            if (other.endDate != null)
                return false;
        } else if (!endDate.equals(other.endDate))
            return false;
        if (endTime == null) {
            if (other.endTime != null)
                return false;
        } else if (!endTime.equals(other.endTime))
            return false;
        if (isCompleted != other.isCompleted)
            return false;
        if (priority != other.priority)
            return false;
        if (startDate == null) {
            if (other.startDate != null)
                return false;
        } else if (!startDate.equals(other.startDate))
            return false;
        if (startTime == null) {
            if (other.startTime != null)
                return false;
        } else if (!startTime.equals(other.startTime))
            return false;
//        if (uniqueID != other.uniqueID)
//            return false;
        return true;
    }
}
