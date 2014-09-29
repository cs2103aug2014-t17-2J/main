/**
 * 
 */
package testCases;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import logic.InvalidCommandException;
import logic.Task;
import logic.TaskParserPlus;
import org.junit.Test;
import definedEnumeration.Priority;

/**
 * @author Kuan Tien Long
 *
 */
public class TaskParserPlusTest {

    @Test
    public void test() throws InvalidCommandException {

      

        Task expectedTask = new Task();
        TaskParserPlus taskParser = new TaskParserPlus();
        
      
        expectedTask.setDescription("floating task");
        assertEquals(
                expectedTask,
                taskParser
                        .buildTask("-add floating task"));

        expectedTask.setStartDate(LocalDate.of(2014, 9, 12));
        expectedTask.setDescription("momo");
        expectedTask.setPriority(Priority.PRIORITY_HIGH);
        assertEquals(expectedTask,
                taskParser.buildTask("-add momo on 12 sept priority high"));

        expectedTask.setStartDate(LocalDate.of(2014, 9, 12));
        expectedTask.setEndDate(LocalDate.of(2014, 9, 18));
        expectedTask.setDescription("buy for me something");
        expectedTask.setPriority(Priority.PRIORITY_LOW);
        assertEquals(
                expectedTask,
                taskParser
                        .buildTask("-add buy for me something date 12 sept to 18 sept priority low"));

        expectedTask.setStartDate(LocalDate.of(2014, 9, 18));
        expectedTask.setStartTime(LocalTime.of(14, 0));
        expectedTask.setEndTime(LocalTime.of(2, 0));
        expectedTask.setEndDate(LocalDate.of(2014, 9, 22));
        expectedTask.setDescription("buy for me something");
        expectedTask.setPriority(Priority.PRIORITY_MEDIUM);
        assertEquals(
                expectedTask,
                taskParser
                        .buildTask("-add buy for me something date 18 sept 2pm to 22 sept 2am med priority"));

        expectedTask = new Task();
        expectedTask.setStartDate(LocalDate.of(2014, 12, 12));
        expectedTask.setDescription("i wan to eat this");
        expectedTask.setPriority(Priority.PRIORITY_LOW);
        assertEquals(
                expectedTask,
                taskParser
                        .buildTask("add i wan to eat this priority low on 12 dec"));

        expectedTask = new Task();
        expectedTask.setStartDate(LocalDate.of(2014, 9, 18));
        expectedTask.setStartTime(LocalTime.of(14, 0));
        expectedTask.setEndTime(LocalTime.of(2, 0));
        expectedTask.setEndDate(LocalDate.of(2014, 9, 22));
        expectedTask.setDescription("buy for me something");
        expectedTask.setPriority(Priority.PRIORITY_MEDIUM);
        assertEquals(
                expectedTask,
                taskParser
                        .buildTask("-add buy for me something med priority date 18 sept 2pm to 22 sept 2am "));
        assertEquals(
                expectedTask,
                taskParser
                        .buildTask("-add buy for me something med priority date 18/09/14 2pm to 22/9/14 2am "));
        assertEquals(
                expectedTask,
                taskParser
                        .buildTask("med priority date 18/09/14 2pm to 22/9/14 2am -add buy for me something"));
        assertEquals(
                expectedTask,
                taskParser
                        .buildTask("18/09/14 2pm to 22/9/14 2am med priority -add buy for me something"));
        assertEquals(
                expectedTask,
                taskParser
                        .buildTask("date 18/09/14 2pm to 22/9/14 2am -add buy for me something priority med"));
        
        


    }

}
