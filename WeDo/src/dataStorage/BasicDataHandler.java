package dataStorage;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Observer;

import logic.command.UndoHandler;
import logic.utility.Task;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class BasicDataHandler implements DataHandler {

	private final String TODAY = "today";
	private final String TOMORROW = "tomorrow";
	private final String UPCOMING = "upcoming";
	private final String SOMEDAY = "someday";

	private String currentList;

	UndoHandler undoHandler = new UndoHandler();
	FileHandler fileHandler;

	ObservableList<Task> observableList;
	Multimap<String, Task> mainList;
	ArrayList<String> test;

	public BasicDataHandler() {
		fileHandler = new FileHandler();
		populateLists();
		observableList = new ObservableList<Task>(new ArrayList<Task>(mainList.get(TODAY)));
		currentList = TODAY;
	}
	
	public BasicDataHandler(ObservableList<Task> observableList) 
	{
        fileHandler = new FileHandler();
        populateLists();
        this.observableList = observableList;
        observableList.replaceList(new ArrayList<Task>(mainList.get(TODAY)));
        currentList = TODAY;
    }
    

	public ObservableList<Task> getObservableList(){
		return observableList;
	}
	
	public void addObserver(Observer observer) {
		observableList.addObserver(observer);
	}

	/**
	 * This function add all the lists into a Multimap according to list type
	 * 
	 * @return whether the operation is successful.
	 */
	public boolean populateLists() {
		mainList = ArrayListMultimap.create();

//		addToMultimap(TODAY, fileHandler.getList(TODAY));
//		addToMultimap(TOMORROW, fileHandler.getList(TOMORROW));
//		addToMultimap(UPCOMING, fileHandler.getList(UPCOMING));
//		addToMultimap(SOMEDAY, fileHandler.getList(SOMEDAY));

		return false;
	}

	/**
	 * This function put the Arraylist of tasks into a specific key of the
	 * Multimap
	 */
	public void addToMultimap(String key, ArrayList<Task> value) {
		for (int i = 0; i < value.size(); i++) {
			mainList.put(key, value.get(i));
		}

	}

	/**
	 * This function retrieve a specific list from main list
	 * 
	 * @param list
	 *            name to be displayed
	 * @return list of the tasks to be displayed
	 */
	public ArrayList<Task> getListFromMain(String name) {
		ArrayList<Task> tmp = new ArrayList<Task>(mainList.get(name));

		return tmp;

	}

	
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see dataStorage.DataHandler#addTask(logic.Task)
	 */
	@Override
	public boolean addTask(Task task) {
		System.out.println(determineDate(task));
		if (onDisplay(task) == true) {
			observableList.add(task);
		}
//		observableList.add(task);
		mainList.put(determineDate(task), task);
		
		save();
		System.out.println(task.getID()+" is added");

		return true;
	}
	
	
	public String save() {
		
		fileHandler.clear();
		fileHandler.writeToFile("deadLine",new ArrayList<Task>(mainList.values()));
		
		return null;
	}

	/**
	 * Check if the task should be added to or removed from both main list and
	 * observable list
	 * 
	 * @param task
	 * @return whether the task should be on display
	 */
	public boolean onDisplay(Task task) {

		if (determineDate(task) == currentList) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This function determines the date of the task to know which key of the
	 * map it should be added.
	 * 
	 * @param task
	 * @return
	 */
	public String determineDate(Task task) {

		LocalDate today = LocalDate.now();
		LocalDate tomorrow = today.plusDays(1);

		if ( today.equals(task.getEndDate())) {
			return TODAY;
		} else if (tomorrow.equals(task.getEndDate())){
			return TOMORROW;
		}

		else if (task.getEndDate() == LocalDate.MAX && task.getStarDate() == LocalDate.MAX) {
			return SOMEDAY;
		} else {
			return UPCOMING;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dataStorage.DataHandler#clearTask(java.time.LocalDate,
	 * java.time.LocalDate)
	 */
	@Override
	public boolean clearTask(LocalDate starDate, LocalDate endDate) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dataStorage.DataHandler#getDisplayedTasks()
	 */
	@Override
	public ArrayList<Task> getDisplayedTasks(LocalDate startDate,
			LocalDate endDate) {
		return observableList.getList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dataStorage.DataHandler#setDisplayedTasks(java.util.ArrayList)
	 */
	@Override
	public void setDisplayedTasks(ArrayList<Task> displayedTask) {
		observableList.replaceList(displayedTask);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see dataStorage.BasicDataHandler#canRemove(int)
	 */
	public boolean indexValid(int index) {
	    

	    
		if (index >= (observableList.getList().size()) || index < 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
     * 
     */
	private void clearDisplayedList() {
		observableList.clearList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dataStorage.BasicDataHandler#getList(java.time.LocalDate,
	 * java.time.LocalDate)
	 */
	public ArrayList<Task> getList(LocalDate starDate, LocalDate endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dataStorage.DataHandler#remove(int)
	 */
	public boolean removeTask(int index) {
		if (indexValid(index)) {
			System.out.println("deleted " + observableList.get(index));
			mainList.remove(determineDate(getTask(index)), getTask(index));
			observableList.remove(index);

			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dataStorage.DataHandler#editTask()
	 */
	@Override
	public boolean editTask(Task source,Task replacement) {
		
		removeTask(source);
		addTask(replacement);
		
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dataStorage.DataHandler#getTask(int)
	 */
	@Override
	public Task getTask(int index) {
		
		return observableList.get(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dataStorage.DataHandler#remove(logic.Task)
	 */
	@Override
	public boolean removeTask(Task task) {
		observableList.remove(task);
		mainList.remove(determineDate(task), task);
		
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dataStorage.DataHandler#getMainList()
	 */
	@Override
	public Multimap<String, Task> getMainList() {
		// TODO Auto-generated method stub
		return mainList;
	}


	
    /* (non-Javadoc)
     * @see dataStorage.DataHandler#view(java.lang.String)
     */
    @Override
    public void view(Task task) {
        // TODO Auto-generated method stub
        if (task.getEndDate() != LocalDate.MAX)
        {
            observableList.replaceList(new ArrayList<Task>(mainList.get(determineDate(task))));
            currentList = this.determineDate(task);
        }
        else
        {
            observableList.replaceList(new ArrayList<Task>(mainList.get(task.getDescription())));
            currentList = task.getDescription();
        }
        
    }
    
    


}