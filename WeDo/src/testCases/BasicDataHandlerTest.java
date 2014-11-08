package testCases;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;

import logic.utility.Task;

import org.junit.Before;
import org.junit.Test;

import dataStorage.BasicDataHandler;

public class BasicDataHandlerTest {
	
	BasicDataHandler datahandler;
	ArrayList<Task> localList;
	
	
	@Before
	public void setUp() throws Exception {
		datahandler = new BasicDataHandler();
		localList = new ArrayList<Task>();
		
	}
	
	private void updateList() {
		localList = datahandler.getAllTasks();
	}
	
	
	@Test
	public void testAddTaskTask() {
		
		Task task1 = new Task();
		
		task1.setDescription("task1");
		task1.setEndDate(LocalDate.now());
		updateList();
		
		
		datahandler.addTask(task1);
		updateList();
		
		assertTrue(localList.contains(task1));
		datahandler.removeTask(task1);
		updateList();
		assertFalse(localList.contains(task1));

		
		Task task2 = new Task();
		task2.setDescription("task2");
		task2.setEndDate(LocalDate.now().plusDays(1));
		updateList();
		
		
		datahandler.addTask(task2);
		updateList();
		
		assertTrue(localList.contains(task2));
		datahandler.removeTask(task2);
		updateList();
		assertFalse(localList.contains(task2));

		
	
	}

	@Test
	public void testRemoveTaskTask() {
	}

	@Test
	public void testView() {
	}

}
