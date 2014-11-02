package dataStorage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Observer;
import logic.utility.Task;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import edu.emory.mathcs.backport.java.util.Collections;


public class BasicDataHandler implements DataHandler{

    

	private final String SOMEDAY = "someday";
	private final String DEADLINE = "deadLine";
	private final String TIMED = "timed";
	private final String FLOATING = "floating";
	private final String ALL = "all";

	private String currentList;

	FileHandler fileHandler;

	ObservableList<Task> observableList;
	Multimap<String, Task> mainList;
	Multimap<LocalDate, Task> mainList2;
	Task currentRange;
	

    
	public BasicDataHandler() {
		fileHandler = new FileHandler();
		populateLists();
		observableList = new ObservableList<Task>(new ArrayList<Task>());
		showToday();
		System.out.println("DateHandler initialized");
		fileHandler.writeLog(LocalTime.now() + " : DataHandler initialized");

	}
	
	public void showToday() {
		ArrayList<Task> today = new ArrayList<Task>(mainList2.get(LocalDate.now()));
		observableList.replaceList(today);
		currentList = DEADLINE;	
	}
	
	public ArrayList<Task> getCompleted() {
		ArrayList<Task> tmp = new ArrayList<Task>();
		
		for(Task t:mainList2.values()) {
			if(t.getCompleted() == true) {
				tmp.add(t);
			}
		}
		
		return tmp;
	}
	
	
	public ArrayList<Task> getUncompleted() {
		ArrayList<Task> tmp = new ArrayList<Task>();
		
		for(Task t:mainList2.values()) {
			if(t.getCompleted() == false) {
				tmp.add(t);
			}
		}
		
		return tmp;
	}
	
	
	public boolean withinRange(LocalDate startDate,LocalDate endDate,Task task) {
		
		while(startDate.isBefore(endDate) || startDate.equals(endDate)) {
			if(task.getStartDate().equals(startDate) || task.getEndDate().equals(endDate) 
					|| task.getStartDate().equals(endDate) || task.getEndDate().equals(startDate)) {
				return true;
			}
			startDate = startDate.plusDays(1);
			
		}
		return false;
	}
	
	public ArrayList<Task> sort(ArrayList<Task> tasks){
		
		
		Collections.sort(tasks, new Comparator<Task>() {
		    @Override
		    public int compare(Task t1,Task t2 ) {
		        return t1.getEndDate().compareTo(t2.getEndDate());
		    }
		});
		
		
		return tasks;
	}
	
	public ObservableList<Task> getObservableList() {

		fileHandler.writeLog(LocalTime.now() + " : ObservableList retrieved!");
		return observableList;
	}
	
	
	public void addObserver(Observer observer) {
		observableList.addObserver(observer);
		fileHandler.writeLog(LocalTime.now() + " : Added observer "
				+ observer.toString());
	}

	/**
	 * This function add all the lists into a Multimap according to list type
	 * 
	 * @return whether the operation is successful.
	 */
	public boolean populateLists() {

		mainList2 = ArrayListMultimap.create();
		currentRange = new Task();
		mainList2 = fileHandler.getAllTasks();
		


		return false;
	}

	/**
	 * This function put the Arraylist of tasks into a specific key of the
	 * Multimap
	 */
	public Multimap<LocalDate, Task> addToMultimap(ArrayList<Task> tasks) {

		Multimap<LocalDate, Task> tmp = ArrayListMultimap.create();

		for (Task t : tasks) {
			tmp.put(t.getEndDate(), t);
		}

		return tmp;

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
	
	public ArrayList<Task> getAllTasks(){
		ArrayList<Task> tmp = new ArrayList<Task>(mainList2.values());
		
		return tmp;
	}

	public boolean addTask(Task task) {

		String taskType = determineTaskType(task);

		
		mainList2.put(task.getEndDate(), task);
		
		if(currentList.equals(TIMED)) {
			if(withinRange(currentRange.getStartDate(),currentRange.getEndDate(),task)) {
				observableList.add(task);
			}
		}
		else if(currentList.equals(ALL) || (taskType.equals(currentList) && 
				observableList.get(0).getEndDate().equals(task.getEndDate()))) {
			
			observableList.add(task);
		}

		save();
		System.out.println(task.getID() + " is added");

		// fileHandler.read("deadLine");
		fileHandler.writeLog(LocalTime.now() + " : Added Task " + task.getID());

		return true;
	}

	public String save() {

		fileHandler.clear();
		fileHandler.writeToFile(new ArrayList<Task>(mainList2.values()));
		fileHandler.writeLog(LocalTime.now() + " : Saved!");
		return null;
	}

	/**
	 * Check if the task should be added to or removed from both main list and
	 * observable list
	 * 
	 * @param task
	 * @return whether the task should be on display
	 */

	private String determineTaskType(Task task) {
		if (task.getEndDate().equals(LocalDate.MAX)
				&& task.getStartDate().equals(LocalDate.MAX)) {
			return FLOATING;
		} else if (!task.getStartDate().equals(LocalDate.MAX)
				&& !task.getEndDate().equals(LocalTime.MAX)) {
			return TIMED;
		} else {
			return DEADLINE;
		}
	}



	public boolean clearTask(LocalDate starDate, LocalDate endDate) {
		// TODO Auto-generated method stub
		return false;
	}

	public ArrayList<Task> getDisplayedTasks(LocalDate startDate,
			LocalDate endDate) {
		return observableList.getList();
	}

	public void setDisplayedTasks(ArrayList<Task> displayedTask) {
		observableList.replaceList(displayedTask);
		fileHandler.writeLog(LocalTime.now() + " : changed displayed list ");

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


	public ArrayList<Task> getList(LocalDate startDate, LocalDate endDate) {
		ArrayList<Task> tmp = new ArrayList<Task>();
		
		while(startDate.isBefore(endDate) || startDate.equals(endDate)) {
			tmp.addAll(mainList2.get(startDate));
			startDate = startDate.plusDays(1);
		}
		
		return tmp;
	}

	public boolean removeTask(int index) {
		if (indexValid(index)) {

			fileHandler.writeLog(LocalTime.now() + " : deleted "
					
					+ observableList.get(index));

			System.out.println("deleted " + observableList.get(index));
			mainList2.remove(getTask(index).getEndDate(), getTask(index));
			
			observableList.remove(index);
			save();
			return true;
		}
		return false;
	}

	public boolean editTask(Task source, Task replacement) {

		fileHandler.writeLog(LocalTime.now() + " : edited " + source.getID());

		removeTask(source);
		addTask(replacement);

		return true;
	}
	

	public Task getTask(int index) {

		return observableList.get(index);
	}

	public boolean removeTask(Task task) {
		
		System.out.println(determineTaskType(task));
		
		observableList.remove(task);
		mainList2.remove(task.getEndDate(), task);
		save();
		return true;
	}

	public Multimap<String, Task> getMainList() {
		// TODO Auto-generated method stub
		return mainList;
	}

	public void view(Task task) {

		System.out.println(task.getStartDate().toString());
		System.out.println(task.getEndDate().toString());
		System.out.println(task.getDescription());
		ArrayList<Task> tmp = new ArrayList<Task>();
		String type = determineTaskType(task);
		currentList = type;


		 if (type.equals(DEADLINE)) {

			tmp.addAll(mainList2.get(task.getEndDate()));
			observableList.replaceList(tmp);
			
		} else if(type.equals(TIMED)){
			currentRange.setStartDate(task.getStartDate());
			currentRange.setEndDate(task.getEndDate());
			tmp.addAll(getList(task.getStartDate(),task.getEndDate()));
			observableList.replaceList(tmp);
			
		}else if (task.getDescription().equals(SOMEDAY)) {
			tmp.addAll(mainList2.get(LocalDate.MAX));
			observableList.replaceList(tmp);

		} else if(task.getDescription().equals("completed")){
			tmp.addAll(getCompleted());
			observableList.replaceList(tmp);
		} else if(task.getDescription().equals("incompleted") ||
					task.getDescription().equals("uncompleted")){
			tmp.addAll(getUncompleted());
			observableList.replaceList(tmp);
		}
		
		else if(task.getDescription().equalsIgnoreCase(ALL)){
			currentList = ALL;
			tmp.addAll(mainList2.values());
			tmp = sort(tmp);
			observableList.replaceList(tmp);
		}



	}

    /* (non-Javadoc)
     * @see dataStorage.DataHandler#completeTask(logic.utility.Task)
     */
    @Override
    public boolean setCompleteTask(Task task, boolean isComplete) 
    {
//        Task taskToComplete = getTask(index);
        task.setCompleted(isComplete);
        save();
        return observableList.edit(task);
    }


}