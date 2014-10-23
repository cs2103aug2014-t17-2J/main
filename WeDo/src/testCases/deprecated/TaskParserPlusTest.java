/**
 * 
 */
package testCases.deprecated;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.regex.Pattern;

import logic.deprecated.TaskParserPlus;
import logic.parser.ParserManager;
import logic.utility.Task;

import org.junit.Test;

import definedEnumeration.Priority;

/**
 * @author Kuan Tien Long
 *
 */
public class TaskParserPlusTest {

    @Test
    public void test() {
        
//        Scanner sc = new Scanner(System.in);
//        TaskParserUlti ulti = new TaskParserUlti();
//        while(true)
//        {
//            String input = sc.nextLine();
//            ulti.buildTask(new StringBuilder(input));
//
//        }

        Task expectedTask = new Task();
        TaskParserPlus taskParser = new TaskParserPlus();


        
        expectedTask.setDescription("floating task");
        assertEquals(
                expectedTask,
                taskParser
                        .buildTask(new StringBuilder("-add floating task")));

        expectedTask.setEndDate(LocalDate.of(2014, 9, 12));
        expectedTask.setDescription("momo");
        expectedTask.setPriority(Priority.PRIORITY_HIGH);
        assertEquals(expectedTask,
                taskParser.buildTask(new StringBuilder("-add momo on 12 sept priority high")));

        expectedTask.setStartDate(LocalDate.of(2014, 9, 12));
        expectedTask.setEndDate(LocalDate.of(2014, 9, 18));
        expectedTask.setDescription("buy for me something");
        expectedTask.setPriority(Priority.PRIORITY_LOW);
        assertEquals(
                expectedTask,
                taskParser
                        .buildTask(new StringBuilder("-add buy for me something date 12 sept to 18 sept priority low")));

        expectedTask.setStartDate(LocalDate.of(2014, 9, 18));
        expectedTask.setStartTime(LocalTime.of(14, 0));
        expectedTask.setEndTime(LocalTime.of(2, 0));
        expectedTask.setEndDate(LocalDate.of(2014, 9, 22));
        expectedTask.setDescription("buy for me something");
        expectedTask.setPriority(Priority.PRIORITY_MEDIUM);
        assertEquals(
                expectedTask,
                taskParser
                        .buildTask(new StringBuilder("-add buy for me something date 18 sept 2pm to 22 sept 2am med priority")));

        expectedTask = new Task();
        expectedTask.setEndDate(LocalDate.of(2014, 12, 12));
        expectedTask.setDescription("i wan to eat this");
        expectedTask.setPriority(Priority.PRIORITY_LOW);
        assertEquals(
                expectedTask,
                taskParser
                        .buildTask(new StringBuilder("add i wan to eat this priority low on 12 dec")));

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
                        .buildTask(new StringBuilder("-add buy for me something med priority date 18 sept 2pm to 22 sept 2am ")));
        assertEquals(
                expectedTask,
                taskParser
                        .buildTask(new StringBuilder("-add buy for me something med priority date 18/09/14 2pm to 22/9/14 2am ")));
        assertEquals(
                expectedTask,
                taskParser
                        .buildTask(new StringBuilder("med priority date 18/09/14 2pm to 22/9/14 2am -add buy for me something")));
        assertEquals(
                expectedTask,
                taskParser
                        .buildTask(new StringBuilder("18/09/14 14:00 to 22/9/14 2:00 med priority -add buy for me something")));
        assertEquals(
                expectedTask,
                taskParser
                        .buildTask(new StringBuilder("date 18/09 2pm to 22/9 2am -add buy for me something priority med")));
        
        expectedTask = new Task();
        expectedTask.setStartDate(LocalDate.now());
        expectedTask.setStartTime(LocalTime.of(20, 0));
        expectedTask.setEndTime(LocalTime.of(22, 0));
        expectedTask.setEndDate(LocalDate.now());
        expectedTask.setDescription("hello page 321 to 232 and pg 23123 to 21321");
        assertEquals(
                expectedTask,
                taskParser
                        .buildTask(new StringBuilder("add hello page 321 to 232 and pg 23123 to 21321 at 8pm to 10 pm")));
        


   }

}
