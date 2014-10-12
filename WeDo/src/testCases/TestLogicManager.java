/**
 * 
 */
package testCases;

import java.util.Scanner;

import logic.LogicManager;

import org.junit.Test;

import dataStorage.BasicDataHandler;
import dataStorage.DataHandler;

/**
 * @author Kuan Tien Long
 *
 */
public class TestLogicManager {

    @Test
    public void test() 
    {
        
        LogicManager logicManager = new LogicManager(new BasicDataHandler());
        Scanner sc = new Scanner(System.in);
        while(true)
         logicManager.processUserInput(sc.nextLine());
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
