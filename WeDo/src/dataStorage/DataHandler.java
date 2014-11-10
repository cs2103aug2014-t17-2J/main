package dataStorage;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Observer;


import logic.exception.InvalidCommandException;
import logic.utility.Task;

//@author A0112862L

public interface DataHandler {
    
    

    
    public boolean setCompleteTask(Task task, boolean isComplete);
       
    public abstract boolean addTask(Task task) throws InvalidCommandException;


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
    
    public abstract boolean editTask(Task source,Task replacement) throws InvalidCommandException;
    
    public ObservableList<Task> getObservableList();

    public abstract Task getTask(int index);
    
    public abstract boolean removeTask(Task task);
        
    public abstract void addObserver(Observer observer);

    public abstract boolean indexValid(int index);

    // temp add...
    
    public abstract void view(Task task) throws InvalidCommandException;




}