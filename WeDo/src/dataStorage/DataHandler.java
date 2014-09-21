package dataStorage;

import java.util.Calendar;

import logic.Command;
import logic.Task;
import logic.UndoHandler;

public class DataHandler {
    
    UndoHandler undoHandler;

    
	public DataHandler() {
		// TODO Auto-generated constructor stub
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
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * @param starDatet
     * @param endDate
     * @return
     */
    public boolean clearTask(Calendar starDate, Calendar endDate) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * @param lineToDelete
     * @return
     */
    public boolean remove(int lineToDelete) {
        // TODO Auto-generated method stub
        return false;
    }

}
