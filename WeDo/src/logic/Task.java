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
    private LocalDate startDate = null;
    private LocalDate endDate = null;
    private LocalTime startTime = null;
    private LocalTime endTime = null;
    private String description;
    private boolean isCompleted;
    
    private String testPriority;
    private String testDate;
    
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
    public String getTestPriority() {
        return testPriority;
    }
    /**
     * @param testPriority the testPriority to set
     */
    public void setTestPriority(String testPriority) {
        this.testPriority = testPriority;
    }
    /**
     * @return the testDate
     */
    public String getTestDate() {
        return testDate;
    }
    /**
     * @param testDate the testDate to set
     */
    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }

    
    
    
}
