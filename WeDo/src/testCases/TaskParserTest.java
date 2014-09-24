/**
 * 
 */
package testCases;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import logic.CommandHandler;
import logic.MyException;
import logic.Task;
import logic.TaskParser;

import org.junit.Test;

import dataStorage.DataHandler;

/**
 * @author Kuan Tien Long
 *
 */
public class TaskParserTest {

    @Test
    public void test() throws MyException {

        // CommandHandler cmd = new CommandHandler(new DataHandler());
        // cmd.executeCommand("-add milo -date sept 12 to sept 18 -priority high");

        Task expectedTask = new Task();
        TaskParser taskParser = new TaskParser();

        expectedTask.setStartDate(LocalDate.of(2014, 9, 12));
        expectedTask.setDescription("momo");
        assertEquals(expectedTask,
                taskParser.buildTask("-add momo -date 12 sept -priority high"));

        expectedTask.setStartDate(LocalDate.of(2014, 9, 12));
        expectedTask.setEndDate(LocalDate.of(2014, 9, 18));
        expectedTask.setDescription("buy for me something");
        assertEquals(
                expectedTask,
                taskParser
                        .buildTask("-add buy for me something -date 12 sept to 18 sept -priority high"));

        expectedTask.setStartDate(LocalDate.of(2014, 9, 18));
        expectedTask.setStartTime(LocalTime.of(14, 0));
        expectedTask.setEndTime(LocalTime.of(2, 0));
        expectedTask.setEndDate(LocalDate.of(2014, 9, 22));
        expectedTask.setDescription("buy for me something");
        assertEquals(
                expectedTask,
                taskParser
                        .buildTask("-add buy for me something -date 18 sept 2pm to 22 sept 2am -priority high"));

    }

}
