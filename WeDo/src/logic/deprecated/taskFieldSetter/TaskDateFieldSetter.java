/**
 * 
 */
package logic.deprecated.taskFieldSetter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import com.joestelmach.natty.DateGroup;
import com.joestelmach.natty.Parser;

import edu.emory.mathcs.backport.java.util.Collections;
import logic.utility.Task;

//@author A0112887X - unused
/**
 *
 */
public class TaskDateFieldSetter implements TaskFieldSetter {

    /*
     * (non-Javadoc)4
     * 
     * @see logic.taskParser.taskFieldSetter.TaskFieldSetter#set(logic.Task,
     * java.lang.String)
     */
    @Override
    public String set(Task task, String source) {

        Parser nattyParser = new Parser();

        List<DateGroup> dateGroups = nattyParser.parse(source);
        if (dateAvailable(dateGroups)) {
            setDate(task, dateGroups);
            return getDateText(source, dateGroups);
        }
        return "";
    }

    /**
     * 
     */
    private String getDateText(String source, List<DateGroup> dateGroups) {
        int startPosition = source.length();
        int endPosition = 0;
        for (DateGroup dateGroup : dateGroups) {
            int position = dateGroup.getPosition();
            int length = dateGroup.getText().length();
            startPosition = Math.min(startPosition, position);
            endPosition = Math.max(position + length, endPosition);
        }

        String dateText = source.substring(startPosition, endPosition);
        return dateText;
    }

    private void setDate(Task task, List<DateGroup> dateGroups) {
        final int START_INDEX = 0;
        final int LIST_OFFSET = 1;

        List<Date> dateList = getDateList(START_INDEX, dateGroups);

        int noOfDates = dateList.size();

        if (noOfDates > 1) {
            setEndDateTime(task, isTimeSpecified(dateGroups),
                    dateList.get(noOfDates - LIST_OFFSET));
            setStartDateTime(task, isTimeSpecified(dateGroups),
                    dateList.get(START_INDEX));
        } else {
            setEndDateTime(task, isTimeSpecified(dateGroups),
                    dateList.get(START_INDEX));
        }
    }

    private boolean dateAvailable(List<DateGroup> dateGroups) {
        return dateGroups.size() > 0;
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

    private void setEndDateTime(Task task, final boolean isTimeSpecified,
            final Date endDate) {
        task.setEndDate(dateToLocalDate(endDate));
        if (isTimeSpecified) {
            task.setEndTime(dateToLocalTime(endDate));
        }
    }

    private boolean isTimeSpecified(List<DateGroup> dateGroups) {
        return !dateGroups.get(0).isTimeInferred();
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
