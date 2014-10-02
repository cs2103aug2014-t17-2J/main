package dataStorage;

import java.time.LocalDate;
import java.util.ArrayList;

import logic.Command;
import logic.FileHandler;
import logic.Task;
import logic.UndoHandler;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class DataHandler {
	
    private final String TODAY = "today";
    private final String TOMORROW = "tomorrow";
    private final String UPCOMING = "upcoming";
    private final String SOMEDAY = "someday";
    	
    private String currentList;
    
    UndoHandler undoHandler;
    FileHandler fileHandler;

    ObservableList<Task> observableList;
    Multimap <String,Task> mainList;

    
	public DataHandler() {
		fileHandler = new FileHandler();
		populateLists();
	}
	
	/** This function add all the lists 
	 * into a Multimap according to list type 
     * @return whether the operation is successful.
     */
	public boolean populateLists() {
	        mainList = ArrayListMultimap.create();
	        
	        addToMultimap(TODAY,fileHandler.getList(TODAY));
	        addToMultimap(TOMORROW,fileHandler.getList(TOMORROW));
	        addToMultimap(UPCOMING,fileHandler.getList(UPCOMING));
	        addToMultimap(SOMEDAY,fileHandler.getList(SOMEDAY));
	        
	        return false;
	    }
	
	/** This function put the Arraylist of tasks 
	 * into a specific key of the Multimap
     */
	public void addToMultimap(String key,ArrayList<Task> value) {
	        for(int i=0; i<value.size(); i++) {
	            mainList.put(key, value.get(i));
	        }
	        
	    }
	
	/**This function retrieve a specific list from main list
     * @param list name to be displayed
     * @return list of the tasks to be displayed
     */
	public ArrayList<Task> getListFromMain(String name){
		ArrayList<Task> tmp = new ArrayList<Task>(mainList.get(name));
		
		return tmp;
		
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
    	if(onDisplay(task)==true) {
    		observableList.add(task);
    	}
    	
    	mainList.put(determineDate(task), task);
    	
    	return false;
    }
    
    /**Check if the task should be added to or removed from
     * both main list and observable list
     * @param task
     * @return whether the task should be on display
     */
    public boolean onDisplay(Task task) {
    	
    	if(determineDate(task)==currentList) {
    		return true;
    	}    
    	else {
    		return false;
    	}
    }
    
    /** This function determines the date of the task to 
     * know which key of the map it should be added.
     * @param task
     * @return
     */
    public String determineDate(Task task) {
    	
    	LocalDate day = LocalDate.MAX;
    	LocalDate today = LocalDate.now();
    	LocalDate tomorrow =  day.plusDays(1);
    	
    	
    	if(task.getEndDate()==today || task.getStarDate()==today) {
    		return TODAY;
    	}
    	else if(task.getStarDate()==tomorrow || task.getEndDate()==tomorrow) {
    		return TOMORROW;
    	}
    	
    	else if(task.getEndDate()==null || task.getStarDate()==null ){
    		return SOMEDAY;
    	}
    	else {
    		return UPCOMING;
    	}
    	
    }
    
    /**
     * @param task
     * @return
     */
    public boolean remove(Task task) {
        
    	mainList.remove(currentList, task);
    	
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
    	 if(lineToDelete>=observableList.getList().size() || lineToDelete < 0  ) {
             return false;
         }
         else
             return true;
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
       if(canRemove(lineToDelete)==true) {
    	   mainList.remove(currentList, getIndexedTask(lineToDelete));
       } 
    }

    /**
     * @param lineToDelete
     * @return
     */
    public Task getIndexedTask(int lineToDelete) {
        // TODO Auto-generated method stub
        return observableList.get(lineToDelete-1);
    }

}