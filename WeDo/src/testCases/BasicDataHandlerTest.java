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
	ArrayList<Task> mainList,displayList;
	static int taskNum = 0;
	
	
	@Before
	public void setUp() throws Exception {
		datahandler = new BasicDataHandler();
		mainList = new ArrayList<Task>();
		displayList = new ArrayList<Task>();
		
	}
	
	private void updateList() {
		mainList = datahandler.getAllTasks();
		displayList = datahandler.getObservableList().getList();
	}
	
	private Task createDeadline(int daysFromToday) {
		Task task = new Task();
		task.setDescription("Task " + (taskNum++));
		task.setEndDate(LocalDate.now().plusDays(daysFromToday));
		
		return task;
	}
	
	private Task createFloat() {
		Task task = new Task();
		task.setDescription("Task " + (++taskNum));
		
		return task;
	}
	
	private Task createTimed(int daysBefore,int daysAfter) {
		Task task = new Task();
		task.setDescription("Task " + (++taskNum));
		task.setEndDate(LocalDate.now().plusDays(daysAfter));
		task.setStartDate(LocalDate.now().minusDays(daysBefore));
		
		return task;
	}
	@Test
	public void testAddTaskTask() {
		
		
		
		Task task1 = createDeadline(0);
		
		datahandler.addTask(task1);
		updateList();
		datahandler.view(createDeadline(0));
		
		assertTrue(displayList.contains(task1));
		assertTrue(mainList.contains(task1));
		
		datahandler.view(createDeadline(1));
		updateList();
		assertFalse(displayList.contains(task1));
		
		datahandler.removeTask(task1);
		updateList();
		assertFalse(mainList.contains(task1));

		
		Task task2 = createDeadline(1);
		
		datahandler.addTask(task2);
		updateList();
		datahandler.view(createDeadline(1));
		
		assertTrue(displayList.contains(task2));
		assertTrue(mainList.contains(task2));
		
		datahandler.view(createDeadline(0));
		updateList();
		
		assertFalse(displayList.contains(task2));
		assertTrue(mainList.contains(task2));
		
		datahandler.removeTask(task2);
		updateList();
		assertFalse(mainList.contains(task2));

		
		Task task3 = createFloat();
		datahandler.addTask(task3);
		
		updateList();
		datahandler.view(createFloat());
		
		assertTrue(displayList.contains(task3));
		assertTrue(mainList.contains(task3));
		
		datahandler.view(createDeadline(0));
		updateList();
		
		assertFalse(displayList.contains(task3));
		
		datahandler.removeTask(task3);
		updateList();
		assertFalse(mainList.contains(task3));
		
		Task task4 = createTimed(3,5);
		datahandler.addTask(task4);
		updateList();
		
		assertTrue(mainList.contains(task4));
		datahandler.view(createTimed(3,5));
		updateList();
		
		assertTrue(displayList.contains(task4));
		
		datahandler.removeTask(task4);
		updateList();
		
		assertFalse(displayList.contains(task4));
		assertFalse(mainList.contains(task4));
	
	}

	@Test
	public void testRemoveTaskTask() {
	}

	@Test
	public void testView() {
	}

}
