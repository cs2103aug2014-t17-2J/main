/**
 * 
 */
package dataStorage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Observer;

import logic.Command;
import logic.Task;

import com.google.common.collect.Multimap;

/**
 * @author Kuan Tien Long
 *
 */
public class testDataHandler implements DataHandler {

    /* (non-Javadoc)
     * @see dataStorage.DataHandler#getMainList()
     */
    @Override
    public Multimap<String, Task> getMainList() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see dataStorage.DataHandler#addUndoCommand(logic.Command)
     */
    @Override
    public void addUndoCommand(Command command) {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see dataStorage.DataHandler#addTask(logic.Task)
     */
    @Override
    public boolean addTask(Task task) {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see dataStorage.DataHandler#clearTask(java.time.LocalDate, java.time.LocalDate)
     */
    @Override
    public boolean clearTask(LocalDate starDate, LocalDate endDate) {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see dataStorage.DataHandler#getDisplayedTasks(java.time.LocalDate, java.time.LocalDate)
     */
    @Override
    public ArrayList<Task> getDisplayedTasks(LocalDate startDate,
            LocalDate endDate) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see dataStorage.DataHandler#setDisplayedTasks(java.util.ArrayList)
     */
    @Override
    public void setDisplayedTasks(ArrayList<Task> displayedTask) {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see dataStorage.DataHandler#removeTask(int)
     */
    @Override
    public boolean removeTask(int index) {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see dataStorage.DataHandler#editTask(int)
     */
    @Override
    public boolean editTask(int index) {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see dataStorage.DataHandler#getTask(int)
     */
    @Override
    public Task getTask(int index) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see dataStorage.DataHandler#removeTask(logic.Task)
     */
    @Override
    public boolean removeTask(Task task) {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see dataStorage.DataHandler#addObserver(java.util.Observer)
     */
    @Override
    public void addObserver(Observer observer) {
        // TODO Auto-generated method stub
        
    }

}
