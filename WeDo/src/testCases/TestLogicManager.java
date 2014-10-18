/**
 * 
 */
package testCases;

import static org.junit.Assert.assertEquals;

import java.util.Scanner;

import logic.InvalidCommandException;
import logic.LogicManager;
import logic.utility.Task;

import org.junit.Test;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import dataStorage.BasicDataHandler;
import dataStorage.DataHandler;
import definedEnumeration.Priority;

/**
 * @author Kuan Tien Long
 *
 */
public class TestLogicManager {

    @Test
    public void test() throws InvalidCommandException 
    {
        Multimap<String, Task> expectedMap = ArrayListMultimap.create();
        Task expectedTask = new Task("hello", Task.PRIORITY_NOT_SET);
        expectedMap.put("someday", expectedTask);

        BasicDataHandler dataHandler = new BasicDataHandler();
        LogicManager logicManager = new LogicManager(dataHandler);
        logicManager.processCommand("add hello");
        
        assertEquals(expectedMap, dataHandler.getMainList());
         
//        commandHandler.executeCommand("date 18/09 2pm to 22/9 2am -add buy for me something priority med");
//        commandHandler.executeCommand("clear 18/09 to 19/09");
//        commandHandler.executeCommand("clear 1");
//        commandHandler.executeCommand("date 18/09 2pm to 22/9 2am -remove buy for me something priority med");
//        commandHandler.executeCommand("-delete 2");
//        commandHandler.executeCommand("delete 10232393434");
//        commandHandler.executeCommand("delete all");
//        commandHandler.executeCommand("exit");
//
//        commandHandler.executeCommand("add read this report from page 39 to page 49 on 23 aug 2012");
        
    }

}
