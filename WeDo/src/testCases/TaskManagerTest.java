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
import logic.utility.Task;
import logic.utility.DeadLineTask;
import logic.utility.FloatingTask;
import logic.utility.Task;
import logic.utility.TimedTask;

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

        Task expectedTask;
        ParserManager taskParser = new ParserManager();

        expectedTask = new Task("floating task",Task.PRIORITY_NOT_SET);
        taskParser.interpret("-add floating task");
        assertEquals(expectedTask, taskParser.getTask());

        expectedTask = new Task("momo",Priority.PRIORITY_HIGH, LocalDate.of(2014, 9, 12), Task.TIME_NOT_SET);
        taskParser.interpret("-add momo by 12 sept priority high");
        assertEquals(expectedTask, taskParser.getTask());

        expectedTask = new Task("buy for me something", Priority.PRIORITY_LOW, LocalDate.of(2014, 9, 12), Task.TIME_NOT_SET, LocalDate.of(2014, 9, 18), Task.TIME_NOT_SET );
        taskParser.interpret("-add buy for me something date 12 sept to 18 sept priority low");
        assertEquals(expectedTask, taskParser.getTask());
        
        
        expectedTask = new Task("i wan to eat this", Priority.PRIORITY_LOW , LocalDate.of(2014, 12, 12), Task.TIME_NOT_SET);
        taskParser.interpret("add i wan to eat this priority low on 12 dec");
        assertEquals(expectedTask, taskParser.getTask());


        expectedTask = new Task("buy for me something", Priority.PRIORITY_MEDIUM , LocalDate.of(2014, 9, 18), LocalTime.of(14, 0), LocalDate.of(2014, 9, 22), LocalTime.of(2, 0));
        taskParser.interpret("-add buy for me something date 18 sept 2pm to 22 sept 2am med priority");
        assertEquals(expectedTask, taskParser.getTask());
        
        expectedTask = new Task("buy for me something", Priority.PRIORITY_MEDIUM ,LocalDate.of(2014, 9, 18), LocalTime.of(14, 0), LocalDate.of(2014, 9, 22), LocalTime.of(2, 0));  
        taskParser.interpret("-add buy for me something priority med date 18 sept 2pm to 22 sept 2am ");
        assertEquals(expectedTask, taskParser.getTask());

        expectedTask = new Task("buy for me something", Priority.PRIORITY_MEDIUM ,LocalDate.of(2014, 9, 18), LocalTime.of(14, 0), LocalDate.of(2014, 9, 22), LocalTime.of(2, 0));  
        taskParser.interpret("-add buy for me something med priority date 18/09/14 2pm to 22/9/14 2am ");
        assertEquals(expectedTask, taskParser.getTask());

        expectedTask = new Task("buy for me something", Priority.PRIORITY_MEDIUM ,LocalDate.of(2014, 9, 18), LocalTime.of(14, 0), LocalDate.of(2014, 9, 22), LocalTime.of(2, 0));  
        taskParser.interpret("med priority date 18/09/14 2pm to 22/9/14 2am -add buy for me something");
        assertEquals(expectedTask, taskParser.getTask());

        expectedTask = new Task("buy for me something", Priority.PRIORITY_MEDIUM ,LocalDate.of(2014, 9, 18), LocalTime.of(14, 0), LocalDate.of(2014, 9, 22), LocalTime.of(2, 0));  
        taskParser.interpret("18/09/14 14:00 to 22/9/14 2:00 med priority -add buy for me something");
        assertEquals(expectedTask, taskParser.getTask());

        expectedTask = new Task("buy for me something", Priority.PRIORITY_MEDIUM ,LocalDate.of(2014, 9, 18), LocalTime.of(14, 0), LocalDate.of(2014, 9, 22), LocalTime.of(2, 0));  
        taskParser.interpret("date 18/09 2pm to 22/9 2am -add buy for me something priority med");
        assertEquals(expectedTask, taskParser.getTask());

     
        expectedTask = new Task("hello page 321 to 232 and pg 23123 to 21321", Task.PRIORITY_NOT_SET ,LocalDate.now(), LocalTime.of(20, 0), LocalDate.now(), LocalTime.of(22, 0));  
        taskParser.interpret("add hello page 321 to 232 and pg 23123 to 21321 at 8pm to 10 pm");
        assertEquals(expectedTask, taskParser.getTask());

        
        expectedTask = new Task("bake cookies", Task.PRIORITY_NOT_SET ,LocalDate.now(), Task.TIME_NOT_SET);  
        taskParser.interpret("add bake cookies today");
        assertEquals(expectedTask, taskParser.getTask());
        
        expectedTask = new Task("meeting3", Task.PRIORITY_NOT_SET ,LocalDate.now(), Task.TIME_NOT_SET);  
        taskParser.interpret("add meeting3 today");
        assertEquals(expectedTask, taskParser.getTask());
        
        expectedTask = new Task("meeting", Task.PRIORITY_NOT_SET ,LocalDate.now(), LocalTime.of(3, 0));  
        taskParser.interpret("add meeting 3 today");
        assertEquals(expectedTask, taskParser.getTask());

    }
}
