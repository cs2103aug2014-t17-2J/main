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
public interface TaskAttribute 
{
    void set(Task task, String arguments);
}



class TaskPriorityAttribute implements TaskAttribute
{

    /* (non-Javadoc)
     * @see logic.TaskAttribute#set()
     */
    @Override
    public void set(Task task, String arguments) {
        // TODO Auto-generated method stub
        task.setTestPriority(arguments);
        
    }
    
}

class TaskDateAttribute implements TaskAttribute
{

    /* (non-Javadoc)
     * @see logic.TaskAttribute#set()
     */
    @Override
    public void set(Task task, String arguments) 
    {
        final int START_INDEX = 0;
        final int LIST_OFFSET = 1;
        arguments = StringHandler.convertFormalDate(arguments);
        Parser nattyParser = new Parser();
        List<DateGroup> dateGroup = nattyParser.parse(arguments);
        List<Date> dateList = dateGroup.get(START_INDEX).getDates();
        Collections.sort(dateList);
        int noOfDates = dateGroup.size();
        
        if(noOfDates > 1)
        {
            Date endDate = dateList.get(noOfDates - LIST_OFFSET);
            task.setEndDate(dateToLocalDate(endDate));
            if(dateGroup.get(0).isTimeInferred())
            {
                task.setEndTime(dateToLocalTime(endDate));
            }
        }
        
        Date startDate = dateList.get(START_INDEX);
        task.setStartDate(dateToLocalDate(startDate));
        if(!dateGroup.get(0).isTimeInferred())
        {
            task.setStartTime(dateToLocalTime(startDate));
        }
    
        task.setTestDate(arguments);

    }
    
    public LocalDate dateToLocalDate(Date date)
    {
        Instant instant = Instant.ofEpochMilli(date.getTime());
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
    }
    
    public LocalTime dateToLocalTime(Date time)
    {
        Instant instant = Instant.ofEpochMilli(time.getTime());
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalTime();
    }
    
}

class TaskDescriptionAttribute implements TaskAttribute
{

    /* (non-Javadoc)
     * @see logic.TaskAttribute#set()
     */
    @Override
    public void set(Task task, String arguments) {
        // TODO Auto-generated method stub
        task.setDescription(arguments);
    }
    
}

class TaskInvalidAttribute implements TaskAttribute
{

    /* (non-Javadoc)
     * @see logic.TaskAttribute#set()
     */
    @Override
    public void set(Task task, String arguments) {
        // TODO Auto-generated method stub
        
    }
    
}