package brain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Observer;

import logic.Command;
import logic.CommandHandler;
import logic.Task;
import userInterface.UserIntSwing;

import com.google.common.collect.Multimap;

import dataStorage.BasicDataHandler;
import dataStorage.DataHandler;
import dataStorage.ObservableList;
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

		//list = dataHand.getList("today");
		Processor processor = new Processor();
		UserIntSwing swi = new UserIntSwing(processor);
		swi.execute();
		
		//InteractiveForm interForm = new InteractiveForm();
		//interForm.execute();
	}
	
	public TaskFeedBack executeCommand(String userInput)
	{
	    return commandHandler.executeCommand(userInput);
	}
	
	public ObservableList<Task> getObserverList()
	{
		return dataHandler.getObservableList();
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
	
	public boolean editTask(int index,Task task)
	{
	    return dataHandler.editTask(index,task);
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