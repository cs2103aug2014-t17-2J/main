package brain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Observer;

import com.google.common.collect.Multimap;

import userInterface.UserIntSwing;
import logic.Command;
import logic.CommandHandler;
import logic.Task;
import dataStorage.BasicDataHandler;
import dataStorage.DataHandler;
import dataStorage.testDataHandler;
import definedEnumeration.TaskFeedBack;


public class Processor {
	
    
    private DataHandler dataHandler;
    private CommandHandler commandHandler;
    
    
	public Processor() 
	{
		
	    dataHandler = new BasicDataHandler();
		commandHandler = new CommandHandler(this);	
	}

	public static void main(String[] args) {

		// list = dataHand.getList("today");
		UserIntSwing swi = new UserIntSwing();
		swi.execute();
		
		//InteractiveForm interForm = new InteractiveForm();
		//interForm.execute();
	}
	
	public TaskFeedBack executeCommand(String userInput)
	{
	    return commandHandler.executeCommand(userInput);
	}
	
	public void addObserver(Observer observer)
    {
        dataHandler.addObserver(observer);
    }
    
	
	public boolean addTask(Task task)
	{
	    return dataHandler.addTask(task);
	}
	
	public boolean removeTask(int index)
	{
	    return dataHandler.removeTask(index);
	}
	
	public boolean removeTask(Task task)
	{
	    return dataHandler.removeTask(task);
	}
	
	public boolean clearTask(LocalDate startDate, LocalDate endDate)
	{
	    return dataHandler.clearTask(startDate, endDate);
	}
	
	public boolean editTask(int index)
	{
	    return dataHandler.editTask(index);
	}
	
	public void addUndoCommand(Command command)
	{
	    dataHandler.addUndoCommand(command);
	}
	
	public Task getTask(int index)
	{
	    return dataHandler.getTask(index);
	}
	
	public ArrayList<Task> getDisplayedTasks(LocalDate startDate, LocalDate endDate)
	{
	    return dataHandler.getDisplayedTasks(startDate, endDate);
	}

	public void setDisplayedTasks(ArrayList<Task> displayedTask)
	{
	     dataHandler.setDisplayedTasks(displayedTask);
	}

	public Multimap<String, Task> getMainList()
	{
	    return dataHandler.getMainList();
	}
	

	
}