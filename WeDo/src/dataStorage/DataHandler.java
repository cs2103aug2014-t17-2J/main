/**
 * 
 */
package dataStorage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Observer;

import com.google.common.collect.Multimap;

import logic.Command;
import logic.Task;

/**
 * @author Kuan Tien Long
 *
 */
public interface DataHandler {

    public abstract Multimap <String,Task> getMainList();
   
    /* (non-Javadoc)
     * @see dataStorage.BasicDataHandler#addUndo(logic.Command)
     */
    public abstract void addUndoCommand(Command command);

   
    public abstract boolean addTask(Task task);

    /* (non-Javadoc)
     * @see dataStorage.BasicDataHandler#clearTask(java.time.LocalDate, java.time.LocalDate)
     */
    public abstract boolean clearTask(LocalDate starDate, LocalDate endDate);

    /**
     * @return
     */
    public abstract ArrayList<Task> getDisplayedTasks(LocalDate startDate, LocalDate endDate);

    /**
     * @param displayedTask
     */
    public abstract void setDisplayedTasks(ArrayList<Task> displayedTask);

    /* (non-Javadoc)
     * @see dataStorage.BasicDataHandler#remove(int)
     */
    public abstract boolean removeTask(int index);

    public abstract boolean editTask(int index);

    public abstract Task getTask(int index);
    
    public abstract boolean removeTask(Task task);
        
    public abstract void addObserver(Observer observer);

}