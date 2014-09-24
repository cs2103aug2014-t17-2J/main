/**
 * 
 */
package testCases;

import static org.junit.Assert.*;

import java.time.LocalDate;

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
    public void test() 
    {
           
      //  CommandHandler cmd = new CommandHandler(new DataHandler());
      //  cmd.executeCommand("-add milo -date sept 12 to sept 18 -priority high");
        
        Task task = new Task();
        Task expectedTask = new Task();
        TaskParser taskParser = new TaskParser();
            try {
                task = taskParser.buildTask("-add momo -date 12 sept -priority high");
            } catch (MyException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
 
            expectedTask.setStartDate(LocalDate.of(2014, 9, 12));
            expectedTask.setDescription("momo");
            assertEquals(expectedTask, task);

    }

}
