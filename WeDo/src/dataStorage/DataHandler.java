package dataStorage;

import java.time.LocalDate;
import java.util.ArrayList;

import logic.Command;
import logic.Task;
import logic.UndoHandler;

public class DataHandler {
    
    UndoHandler undoHandler;
    ObservableList<Task> observableList;

    
	public DataHandler() {
		observableList = new ObservableList<Task>(new ArrayList<Task>());
	}
	

	
	public void addUndo(Command command)
	{
	    undoHandler.add(command);
	}

    /**
     * @param task
     * @return
     */
    public boolean addTask(Task task) 
    {
        observableList.add(task);
    	return false;
    }



    /**
     * @param task
     * @return
     */
    public boolean remove(Task task) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * @param starDate
     * @param endDate
     * @return
     */
    public boolean clearTask(LocalDate starDate, LocalDate endDate) 
    {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * @return
     */
    public ArrayList<Task> getDisplayedTask() 
    {
        return observableList.getList();
    }

    /**
     * @param displayedTask
     */
    public void replaceList(ArrayList<Task> displayedTask) 
    {
        observableList.replaceList(displayedTask);        
    }

    /**
     * @param lineToDelete
     * @return
     */
    public boolean canRemove(int lineToDelete) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * 
     */
    public void clearDisplayedList() {
        observableList.clearList();
    }

    /**
     * Get list from startDate till endDate. Return empty list if there are no list for the dates.
     * @param starDate
     * @param endDate
     * @return 
     */
    public ArrayList<Task> getList(LocalDate starDate, LocalDate endDate) 
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @param lineToDelete
     */
    public void remove(int lineToDelete) {
        // TODO Auto-generated method stub
        
    }

    /**
     * @param lineToDelete
     * @return
     */
    public Task getIndexedTask(int lineToDelete) {
        // TODO Auto-generated method stub
        return null;
    }

}
