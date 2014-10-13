package testCases;

import java.time.LocalDate;
import java.util.ArrayList;

import logic.utility.Task;
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
		task1.setPriority(priority.PRIORITY_HIGH);
		datahandler.addTask(task1);
		ArrayList<Task> tmp1 = new ArrayList<Task>(datahandler.getMainList().get("today"));
		System.out.println(tmp1.get(0));
		assertEquals(task1.getID(),tmp1.get(0).getID());
		
		
		Task task2 = new Task();
		task2.setDescription("this is task 2");
		task2.setEndDate(LocalDate.now().plusDays(1));
		task2.setPriority(priority.PRIORITY_LOW);
		datahandler.addTask(task2);
		ArrayList<Task> tmp2 = new ArrayList<Task>(datahandler.getMainList().get("tomorrow"));
		System.out.println(tmp2.get(0));
		assertEquals(task2.getID(),tmp2.get(0).getID());
		
		Task task3 = new Task();
		task3.setDescription("this is task 3");
		task3.setEndDate(LocalDate.now().plusDays(2));
		task3.setPriority(priority.PRIORITY_MEDIUM);
		datahandler.addTask(task3);
		ArrayList<Task> tmp3 = new ArrayList<Task>(datahandler.getMainList().get("upcoming"));
		System.out.println(tmp3.get(0));
		assertEquals(task3.getID(),tmp3.get(0).getID());
		
		Task task4 = new Task();
		task4.setDescription("this is task 4");
		task4.setPriority(priority.PRIORITY_MEDIUM);
		System.out.println(task4.getEndDate().getYear());
		datahandler.addTask(task4);
		ArrayList<Task> tmp4 = new ArrayList<Task>(datahandler.getMainList().get("someday"));
		System.out.println(tmp4.get(0));
		assertEquals(task4.getID(),tmp4.get(0).getID());
		System.out.println(datahandler.getMainList().size());
		
		Task task5 = new Task();
		task5.setDescription("this is task 5");
		task5.setEndDate(LocalDate.now());
		task5.setPriority(priority.PRIORITY_LOW);
		datahandler.addTask(task5);
		ArrayList<Task> tmp5 = new ArrayList<Task>(datahandler.getMainList().get("today"));
		System.out.println(tmp5.get(1));
		assertEquals(task5.getID(),tmp5.get(1).getID());

		datahandler.removeTask(task1);
		tmp5.clear();
		tmp5.addAll(datahandler.getMainList().get("today"));
		System.out.println(tmp5.size());
		
		
	}

	@Test
	public void testRemoveTaskInt() {
		
		
		
	}

	@Test
	public void testRemoveTaskTask() {
		
		Task task1 = new Task();
		task1.setDescription("this is task today 1");
		task1.setEndDate(LocalDate.now());
		task1.setPriority(priority.PRIORITY_HIGH);
		datahandler.addTask(task1);
		
		Task task2 = new Task();
		task2.setDescription("this is task today 2");
		task2.setEndDate(LocalDate.now());
		task2.setPriority(priority.PRIORITY_LOW);
		datahandler.addTask(task2);
		
		Task task3 = new Task();
		task3.setDescription("this is task tmr 1");
		task3.setEndDate(LocalDate.now().plusDays(1));
		task3.setPriority(priority.PRIORITY_HIGH);
		datahandler.addTask(task3);
		
		Task task4 = new Task();
		task4.setDescription("this is task tmr 2");
		task4.setEndDate(LocalDate.now().plusDays(1));
		task4.setPriority(priority.PRIORITY_MEDIUM);
		datahandler.addTask(task4);
		
		Task task5 = new Task();
		task5.setDescription("this is task tmr 2");
		task5.setEndDate(LocalDate.now().plusDays(1));
		task5.setPriority(priority.PRIORITY_LOW);
		datahandler.addTask(task5);
		
		assertEquals(datahandler.getMainList().size(),5);
		assertEquals(datahandler.getMainList().get("today").size(),2);
		assertEquals(datahandler.getMainList().get("tomorrow").size(),3);

//		System.out.println(datahandler.getMainList().size());
//		System.out.println("today " + datahandler.getMainList().get("today").size());
//		System.out.println("tmr " + datahandler.getMainList().get("tomorrow").size());
		
		datahandler.removeTask(task1);
		assertEquals(datahandler.getMainList().size(),4);
		assertEquals(datahandler.getMainList().get("today").size(),1);
//		System.out.println("today " + datahandler.getMainList().get("today").size());
		
		
		datahandler.removeTask(task5);
		assertEquals(datahandler.getMainList().size(),3);
		assertEquals(datahandler.getMainList().get("tomorrow").size(),2);
//		System.out.println("tmr " + datahandler.getMainList().get("tomorrow").size());
		
//		System.out.println(datahandler.getMainList().get("today"));
//		System.out.println(datahandler.getMainList().get("tomorrow"));
		
	}

	@Test
	public void testRemove() {
	}

}
