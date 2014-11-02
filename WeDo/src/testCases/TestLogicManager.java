/**
 * 
 */
package testCases;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import logic.LogicManager;
import logic.exception.InvalidCommandException;
import logic.parser.ParseResult;
import logic.utility.Task;

import org.junit.Test;


import dataStorage.BasicDataHandler;
import dataStorage.ObservableList;
import definedEnumeration.Priority;

/**
 * @author Kuan Tien Long
 *
 */
public class TestLogicManager {


    BasicDataHandler dataHandler = new BasicDataHandler();
    LogicManager logicManager = new LogicManager(dataHandler);
    ObservableList<Task> expectedList = new ObservableList<Task>(new ArrayList<Task>());
    
    @Test
    public void test() throws InvalidCommandException 
    {
        expectedList = cloneList(dataHandler.getObservableList());
        
        processValidAdd10Task();
        processValidAddCommand("add hello today", new Task("hello", Task.PRIORITY_NOT_SET, LocalDate.now(), Task.TIME_NOT_SET));
        processValidAddCommand("add yoyo today priority high", new Task("yoyo", Priority.PRIORITY_HIGH, LocalDate.now(), Task.TIME_NOT_SET));
        processValidRemoveCommandForIndividualTask("remove 4", 4);
        processValidRemoveCommandForMultipleTask("remove 2-5", 2,3,4,5);
        processValidAdd10Task();
        processValidRemoveCommandForMultipleTask("remove 1-3 and 8,9 hmm 4 too plus 6", 1,2,3,8,9,4,6);        
    }



    private void processValidAdd10Task() throws InvalidCommandException {
        processValidAddCommand("add more task today", new Task("more task", Task.PRIORITY_NOT_SET, LocalDate.now(), Task.TIME_NOT_SET));
        processValidAddCommand("add task2 today", new Task("task2", Task.PRIORITY_NOT_SET, LocalDate.now(), Task.TIME_NOT_SET));
        processValidAddCommand("add task3 today", new Task("task3", Task.PRIORITY_NOT_SET, LocalDate.now(), Task.TIME_NOT_SET));
        processValidAddCommand("add task4 today", new Task("task4", Task.PRIORITY_NOT_SET, LocalDate.now(), Task.TIME_NOT_SET));
        processValidAddCommand("add task5 today", new Task("task5", Task.PRIORITY_NOT_SET, LocalDate.now(), Task.TIME_NOT_SET));
        processValidAddCommand("add task6 today", new Task("task6", Task.PRIORITY_NOT_SET, LocalDate.now(), Task.TIME_NOT_SET));
        processValidAddCommand("add task7 today", new Task("task7", Task.PRIORITY_NOT_SET, LocalDate.now(), Task.TIME_NOT_SET));
        processValidAddCommand("add task8 today", new Task("task8", Task.PRIORITY_NOT_SET, LocalDate.now(), Task.TIME_NOT_SET));
        processValidAddCommand("add task9 today", new Task("task9", Task.PRIORITY_NOT_SET, LocalDate.now(), Task.TIME_NOT_SET));
        processValidAddCommand("add task10 today", new Task("task10", Task.PRIORITY_NOT_SET, LocalDate.now(), Task.TIME_NOT_SET));
    }
    

    
    private void processValidRemoveCommandForMultipleTask(String command, Integer ... indexArray)
            throws InvalidCommandException {
        final int ARRAY_OFFSET = 1;
        List<Integer> indexList = Arrays.asList(indexArray);
        Collections.sort(indexList);
        Collections.reverse(indexList);
        
        for(int index : indexList)
        {
          index = index - ARRAY_OFFSET;
          expectedList.remove(index);
        }
        ParseResult parseResult = logicManager.processCommand(command);
        logicManager.executeCommand(parseResult); 
        assertEquals(expectedList, dataHandler.getObservableList());
    }
    
    private void processValidRemoveCommandForIndividualTask(String command, int index)
            throws InvalidCommandException {
        final int ARRAY_OFFSET = 1;
        index = index - ARRAY_OFFSET;
        expectedList.remove(index);
        ParseResult parseResult = logicManager.processCommand(command);
        logicManager.executeCommand(parseResult); 
        assertEquals(expectedList, dataHandler.getObservableList());
    }
    
    
    private void processValidAddCommand(String command, Task expectedTask)
            throws InvalidCommandException {
        
        expectedTask = setNextUniqueID(expectedTask);
        expectedList.add(expectedTask);
        ParseResult parseResult = logicManager.processCommand(command);
        logicManager.executeCommand(parseResult); 
        assertEquals(expectedList, dataHandler.getObservableList());
    }
    
    private Task setNextUniqueID(Task expectedTask) 
    {
        expectedTask.setUniqueID(Task.getCreateID());
        return expectedTask;
    }
    
    private ObservableList<Task> cloneList(ObservableList<Task> displayList) 
    {
        ObservableList<Task> clonedList = new ObservableList<Task>(new ArrayList<Task>());
        
        for (Task task : displayList.getList()) 
        {   
            clonedList.add(new Task(task));
        }
        
        return clonedList;
    }

}
