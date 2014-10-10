/**
 * 
 */
package dataStorage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Observer;

import com.google.common.collect.Multimap;

import logic.Task;
import logic.commandList.Command;

/**
 * @author Kuan Tien Long
 *
 */
public interface DataHandler {

    public abstract Multimap <String,Task> getMainList();
   

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
    
    public abstract boolean editTask(Task source,Task replacement);
    
    public ObservableList<Task> getObservableList();

    public abstract Task getTask(int index);
    
    public abstract boolean removeTask(Task task);
        
    public abstract void addObserver(Observer observer);
    
    // temp add...
    
    public abstract void view(String theFourList);

    public abstract boolean indexValid(int index);

}