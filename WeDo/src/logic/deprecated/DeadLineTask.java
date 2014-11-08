/**
 * 
 */
package logic.deprecated;

import java.time.LocalDate;
import java.time.LocalTime;

import definedEnumeration.Priority;

//@author A0112887X - unused
/**
 *
 */
public class DeadLineTask extends AbstractTask {

    private LocalDate endDate;
    private LocalTime endTime = TIME_NOT_SET;

    /**
     * @param priority
     * @param description
     * @param isComplete
     */
    public DeadLineTask(Priority priority, String description,
            boolean isComplete, LocalDate endDate, LocalTime endTime) {

        super(priority, description, isComplete);

        this.endDate = endDate;
        this.endTime = endTime;
    }

    public DeadLineTask(Priority priority, String description,
            LocalDate endDate, LocalTime endTime) {

        super(priority, description);

        this.endDate = endDate;
        this.endTime = endTime;

    }

    /**
     * @return the endDate
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * @param endDate
     *            the endDate to set
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the endTime
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     *            the endTime to set
     */
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "DeadLineTask [endDate=" + endDate + ", endTime=" + endTime
                + ", getPriority()=" + getPriority() + ", getDescription()="
                + getDescription() + ", isCompleted()=" + isCompleted()
                +  "]";
    }


    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        DeadLineTask other = (DeadLineTask) obj;
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
        return true;
    }
}
