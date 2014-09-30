/**
 * 
 */
package testCases;

import static org.junit.Assert.*;
import logic.CommandHandler;

import org.junit.Test;

import dataStorage.DataHandler;

/**
 * @author Kuan Tien Long
 *
 */
public class TestCommandHandler {

    @Test
    public void test() 
    {
        CommandHandler commandHandler = new CommandHandler(new DataHandler());
        commandHandler.executeCommand("date 18/09 2pm to 22/9 2am -add buy for me something priority med");
        commandHandler.executeCommand("clear 18/09 to 19/09");
        commandHandler.executeCommand("clear 1");
        commandHandler.executeCommand("date 18/09 2pm to 22/9 2am -remove buy for me something priority med");
        commandHandler.executeCommand("-delete 2");
        commandHandler.executeCommand("delete 10232393434");
        commandHandler.executeCommand("delete all");
        commandHandler.executeCommand("exit");
        
    }

}
