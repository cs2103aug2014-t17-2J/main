/**
 * 
 */
package testCases;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

import logic.taskParser.ParserManager;
import logic.taskParser.TaskParserPlus;
import logic.utility.AbstractTask;
import logic.utility.FloatingTask;
import logic.utility.Task;

import org.junit.Test;

import definedEnumeration.Priority;

/**
 * @author Kuan Tien Long
 *
 */
public class TaskManagerTest {

    @Test
    public void test() {
        // Scanner sc = new Scanner(System.in);
        // ParserManager ulti = new ParserManager();
        // while(true)
        // {
        // String input = sc.nextLine();
        // ulti.interpret(input);
        //
        // }

        AbstractTask expectedTask;
        ParserManager taskParser = new ParserManager();

        expectedTask = new FloatingTask(AbstractTask.PRIORITY_NOT_SET,
                "floating task");
        taskParser.interpret("-add floating task");
        assertEquals(expectedTask, taskParser.getTask());

        // expectedTask.setEndDate(LocalDate.of(2014, 9, 12));
        // expectedTask.setDescription("momo");
        // expectedTask.setPriority(Priority.PRIORITY_HIGH);
        // assertEquals(expectedTask,
        // taskParser.interpret(("-add momo on 12 sept priority high")));
        //
        // expectedTask.setStartDate(LocalDate.of(2014, 9, 12));
        // expectedTask.setEndDate(LocalDate.of(2014, 9, 18));
        // expectedTask.setDescription("buy for me something");
        // expectedTask.setPriority(Priority.PRIORITY_LOW);
        // assertEquals(
        // expectedTask,
        // taskParser
        // .interpret(("-add buy for me something date 12 sept to 18 sept priority low")));
        //
        // expectedTask.setStartDate(LocalDate.of(2014, 9, 18));
        // expectedTask.setStartTime(LocalTime.of(14, 0));
        // expectedTask.setEndTime(LocalTime.of(2, 0));
        // expectedTask.setEndDate(LocalDate.of(2014, 9, 22));
        // expectedTask.setDescription("buy for me something");
        // expectedTask.setPriority(Priority.PRIORITY_MEDIUM);
        // assertEquals(
        // expectedTask,
        // taskParser
        // .interpret(("-add buy for me something date 18 sept 2pm to 22 sept 2am med priority")));
        //
        // expectedTask = new Task();
        // expectedTask.setEndDate(LocalDate.of(2014, 12, 12));
        // expectedTask.setDescription("i wan to eat this");
        // expectedTask.setPriority(Priority.PRIORITY_LOW);
        // assertEquals(
        // expectedTask,
        // taskParser
        // .interpret(("add i wan to eat this priority low on 12 dec")));
        //
        // expectedTask = new Task();
        // expectedTask.setStartDate(LocalDate.of(2014, 9, 18));
        // expectedTask.setStartTime(LocalTime.of(14, 0));
        // expectedTask.setEndTime(LocalTime.of(2, 0));
        // expectedTask.setEndDate(LocalDate.of(2014, 9, 22));
        // expectedTask.setDescription("buy for me something");
        // expectedTask.setPriority(Priority.PRIORITY_MEDIUM);
        // assertEquals(
        // expectedTask,
        // taskParser
        // .interpret(("-add buy for me something med priority date 18 sept 2pm to 22 sept 2am ")));
        // assertEquals(
        // expectedTask,
        // taskParser
        // .interpret(("-add buy for me something med priority date 18/09/14 2pm to 22/9/14 2am ")));
        // assertEquals(
        // expectedTask,
        // taskParser
        // .interpret(("med priority date 18/09/14 2pm to 22/9/14 2am -add buy for me something")));
        // assertEquals(
        // expectedTask,
        // taskParser
        // .interpret(("18/09/14 14:00 to 22/9/14 2:00 med priority -add buy for me something")));
        // assertEquals(
        // expectedTask,
        // taskParser
        // .interpret(("date 18/09 2pm to 22/9 2am -add buy for me something priority med")));
        //
        // expectedTask = new Task();
        // expectedTask.setStartDate(LocalDate.now());
        // expectedTask.setStartTime(LocalTime.of(20, 0));
        // expectedTask.setEndTime(LocalTime.of(22, 0));
        // expectedTask.setEndDate(LocalDate.now());
        // expectedTask.setDescription("hello page 321 to 232 and pg 23123 to 21321");
        // assertEquals(
        // expectedTask,
        // taskParser
        // .interpret(("add hello page 321 to 232 and pg 23123 to 21321 at 8pm to 10 pm")));
        //

    }
}
