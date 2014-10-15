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
public class TimedTask extends DeadLineTask {

    private LocalDate startDate;
    private LocalTime startTime = TIME_NOT_SET;

    /**
     * @param priority
     * @param description
     * @param endDate
     * @param endTime
     */
    public TimedTask(Priority priority, String description, LocalDate endDate,
            LocalTime endTime, LocalDate startDate, LocalTime startTime) {

        super(priority, description, endDate, endTime);

        this.startDate = startDate;
        this.startTime = startTime;
    }

    public TimedTask(Priority priority, String description, boolean isComplete,
            LocalDate endDate, LocalTime endTime, LocalDate startDate,
            LocalTime startTime) {

        super(priority, description, isComplete, endDate, endTime);

        this.startDate = startDate;
        this.startTime = startTime;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TimedTask [startDate=" + startDate + ", startTime=" + startTime
                + ", getEndDate()=" + getEndDate() + ", getEndTime()="
                + getEndTime() + ", getPriority()="
                + getPriority() + ", getDescription()=" + getDescription()
                + ", isCompleted()=" + isCompleted() + "]";
    }
}
