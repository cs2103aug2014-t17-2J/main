/**
 * 
 */
package logic;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.joestelmach.natty.DateGroup;
import com.joestelmach.natty.Parser;

import edu.emory.mathcs.backport.java.util.Collections;

/**
 * @author Kuan Tien Long
 *
 */
public interface TaskAttribute {
    void set(Task task, String arguments);
}

class TaskPriorityAttribute implements TaskAttribute {

    /*
     * (non-Javadoc)
     * 
     * @see logic.TaskAttribute#set()
     */
    @Override
    public void set(Task task, String arguments) {
        // TODO Auto-generated method stub
        //task.setTestPriority(arguments);

    }

}

class TaskDateAttribute implements TaskAttribute {

    /*
     * (non-Javadoc)
     * 
     * @see logic.TaskAttribute#set()
     */
    @Override
    public void set(Task task, String arguments) {

        final int START_INDEX = 0;
        final int LIST_OFFSET = 1;
        Parser nattyParser = new Parser();
        
        arguments = StringHandler.convertFormalDate(arguments);
        
        List<DateGroup> dateGroup = nattyParser.parse(arguments);
               
        if (isDateAvailable(dateGroup)) {
            List<Date> dateList = getDateList(START_INDEX, dateGroup);
            
            setStartDateTime(task, isTimeSpecified(dateGroup),
                    dateList.get(START_INDEX));

            int noOfDates = dateList.size();
            
            if (noOfDates > 1) {
                setEndDateTime(task, isTimeSpecified(dateGroup),
                        dateList.get(noOfDates - LIST_OFFSET));
            }
        }
    }

    private boolean isDateAvailable(List<DateGroup> dateGroup) {
        return dateGroup.size() > 0;
    }

    private List<Date> getDateList(final int START_INDEX,
            List<DateGroup> dateGroup) {
        List<Date> dateList = dateGroup.get(START_INDEX).getDates();
        Collections.sort(dateList);
        return dateList;
    }

    private void setStartDateTime(Task task, final boolean isTimeSpecified,
            final Date startDate) {
        task.setStartDate(dateToLocalDate(startDate));
        if (isTimeSpecified) {
            task.setStartTime(dateToLocalTime(startDate));
        }
    }

    private void setEndDateTime(Task task, final boolean isTimeSpecified, final Date endDate) {
        task.setEndDate(dateToLocalDate(endDate));
        if (isTimeSpecified) {
            task.setEndTime(dateToLocalTime(endDate));
        }
    }

    private boolean isTimeSpecified(List<DateGroup> dateGroup) {
        return !dateGroup.get(0).isTimeInferred();
    }

    public LocalDate dateToLocalDate(Date date) {
        Instant instant = Instant.ofEpochMilli(date.getTime());
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
                .toLocalDate();
    }

    public LocalTime dateToLocalTime(Date time) {
        Instant instant = Instant.ofEpochMilli(time.getTime());
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
                .toLocalTime();
    }

}

class TaskDescriptionAttribute implements TaskAttribute {

    /*
     * (non-Javadoc)
     * 
     * @see logic.TaskAttribute#set()
     */
    @Override
    public void set(Task task, String arguments) {
        // TODO Auto-generated method stub
        task.setDescription(arguments);
    }

}

class TaskInvalidAttribute implements TaskAttribute {

    /*
     * (non-Javadoc)
     * 
     * @see logic.TaskAttribute#set()
     */
    @Override
    public void set(Task task, String arguments) {
        // TODO Auto-generated method stub

    }

}