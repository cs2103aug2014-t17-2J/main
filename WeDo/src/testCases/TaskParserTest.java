/**
 * 
 */
package testCases;

import static org.junit.Assert.*;
import logic.CommandHandler;
import logic.TaskParser;

import org.junit.Test;

/**
 * @author Kuan Tien Long
 *
 */
public class TaskParserTest {

    @Test
    public void test() 
    {
           
        CommandHandler cmd = new CommandHandler(null);
        cmd.executeCommand("-search milo");
        
//        TaskParser taskParser = new TaskParser();
//        taskParser.buildTask("-add momo -date 12 sept -priority high");
    }

}
