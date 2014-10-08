package testCases;

import java.time.LocalDate;
import java.util.ArrayList;

import logic.Task;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import dataStorage.BasicDataHandler;
import dataStorage.DataHandler;
import definedEnumeration.Priority;

public class BasicDataHandlerTest {
	
	DataHandler datahandler;

	ArrayList<Task> tmp;
	Priority priority;

	@Before
	public void setUp() throws Exception {
		 datahandler = new BasicDataHandler();
		 
	}


	@Test
	public void testAddTask() {
		
		Task task1 = new Task();
		task1.setDescription("this is task 1");
		task1.setEndDate(LocalDate.now());
		task1.setStartDate(null);
		task1.setEndTime(null);
		task1.setStartTime(null);
		task1.setPriority(priority.PRIORITY_HIGH);
		datahandler.addTask(task1);
		tmp = new ArrayList<Task>(datahandler.getMainList().get("today"));
		System.out.println(tmp.get(0));
		assertEquals(task1.getID(),tmp.get(0).getID());
		
		
		Task task2 = new Task();
		task2.setDescription("this is task 2");
		task2.setEndDate(LocalDate.now().plusDays(1));
		task2.setStartDate(null);
		task2.setEndTime(null);
		task2.setStartTime(null);
		task2.setPriority(priority.PRIORITY_LOW);
		datahandler.addTask(task2);
		ArrayList<Task> tmp2 = new ArrayList<Task>(datahandler.getMainList().get("tomorrow"));
		System.out.println(tmp2.get(0));
		assertEquals(task2.getID(),tmp2.get(0).getID());
		
		Task task3 = new Task();
		task3.setDescription("this is task 2");
		task3.setEndDate(LocalDate.now().plusDays(2));
		task3.setStartDate(null);
		task3.setEndTime(null);
		task3.setStartTime(null);
		task3.setPriority(priority.PRIORITY_MEDIUM);
		datahandler.addTask(task3);
		ArrayList<Task> tmp3 = new ArrayList<Task>(datahandler.getMainList().get("upcoming"));
		System.out.println(tmp3.get(0));
		assertEquals(task2.getID(),tmp3.get(0).getID());
		System.out.println(datahandler.getMainList().size());

		
		
	}

	@Test
	public void testRemoveTaskInt() {
	}

	@Test
	public void testRemoveTaskTask() {
	}

	@Test
	public void testRemove() {
	}

}
